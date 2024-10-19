package com.yxr.petManagement.controller;

import com.yxr.petManagement.common.BaseResponse;
import com.yxr.petManagement.common.DeleteRequest;
import com.yxr.petManagement.common.ErrorCode;
import com.yxr.petManagement.common.ResultUtils;
import com.yxr.petManagement.domain.entity.PetDonate;
import com.yxr.petManagement.domain.vo.PetVO;
import com.yxr.petManagement.exception.BusinessException;
import com.yxr.petManagement.service.PetDonateService;
import com.yxr.petManagement.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/donate")
public class PetDonateController {

    @Resource
    private UserService userService;

    @Resource
    private PetDonateService petDonateService;

    @GetMapping("/pet/list")
    public BaseResponse<List<PetVO>> listAdoptedPetDonateVO() {
        List<PetVO> petVOS = petDonateService.listPetDonateVO();
        return ResultUtils.success(petVOS);
    }

    @GetMapping("/donate/pet/list/all")
    public BaseResponse<List<PetVO>> listAllPetDonateVO() {
        List<PetDonate> petDonates = petDonateService.list();
        List<PetVO> petVOs = petDonates.stream().map(petDonate -> {
            PetVO petVO = new PetVO();
            petVO.setUser(userService.getSafetyUser(userService.getById(petDonate.getUserId())));
            BeanUtils.copyProperties(petDonate, petVO);
            return petVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(petVOs);
    }

    @PostMapping("/pet")
    public BaseResponse<Boolean> donatePet(@RequestBody PetDonate petDonate, HttpServletRequest request) {
        boolean b = petDonateService.donatePet(petDonate, request);
        return ResultUtils.success(b);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deletePetDonate(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = petDonateService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updatePetDonate(@RequestBody PetDonate petDonate, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        return ResultUtils.success(petDonateService.updateById(petDonate));
    }

    @GetMapping("/detail/{id}")
    public BaseResponse<PetVO> getPetDonateDetailById(@PathVariable("id") Integer id) {
        PetDonate petDonate = petDonateService.getById(id);
        PetVO petVO = new PetVO();
        petVO.setUser(userService.getSafetyUser(userService.getById(userService.getById(petDonate.getUserId()))));
        BeanUtils.copyProperties(petDonate, petVO);
        return ResultUtils.success(petVO);
    }
}
