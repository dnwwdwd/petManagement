package com.yxr.petManagement.controller;

import com.yxr.petManagement.common.BaseResponse;
import com.yxr.petManagement.common.DeleteRequest;
import com.yxr.petManagement.common.ErrorCode;
import com.yxr.petManagement.common.ResultUtils;
import com.yxr.petManagement.domain.entity.PetAdopt;
import com.yxr.petManagement.domain.entity.PetDonate;
import com.yxr.petManagement.domain.entity.User;
import com.yxr.petManagement.domain.vo.PetAdoptVO;
import com.yxr.petManagement.domain.vo.PetVO;
import com.yxr.petManagement.exception.BusinessException;
import com.yxr.petManagement.service.PetAdoptService;
import com.yxr.petManagement.service.PetDonateService;
import com.yxr.petManagement.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/adopt")
public class PetAdoptController {

    @Resource
    private PetAdoptService petAdoptService;

    @Resource
    private PetDonateService petDonateService;

    @Resource
    private UserService userService;

    @GetMapping("/pet/list/my")
    public BaseResponse<List<PetVO>> listMyPetAdoptVO(HttpServletRequest request) {
        List<PetVO> petDonateVOS = petAdoptService.listMyPetAdopt(request);
        return ResultUtils.success(petDonateVOS);
    }

    @PostMapping("/pet")
    public BaseResponse<Boolean> adoptPet(HttpServletRequest request, @RequestBody DeleteRequest deleteRequest) {
        int petId = deleteRequest.getId();
        boolean b = petAdoptService.adoptPet(petId, userService.getLoginUser(request));
        return ResultUtils.success(b);
    }

    @GetMapping("/list")
    public BaseResponse<List<PetAdopt>> listPetAdopt(HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        return ResultUtils.success(petAdoptService.list());
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deletePetAdopt(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = petAdoptService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updatePetAdopt(@RequestBody PetAdopt petAdopt, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        return ResultUtils.success(petAdoptService.updateById(petAdopt));
    }

    @PostMapping("/add")
    public BaseResponse<Integer> addPetAdopt(@RequestBody PetAdopt petAdopt, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        petAdoptService.save(petAdopt);
        return ResultUtils.success(petAdopt.getId());
    }

    @GetMapping("/list/info")
    public BaseResponse<List<PetAdoptVO>> lisPetAdopts() {
        List<PetAdopt> petAdopts = petAdoptService.list();
        List<Integer> adoptedUserIds = petAdopts.stream().map(PetAdopt::getUserId).collect(Collectors.toList());
        List<User> adoptedUsers = userService.listByIds(adoptedUserIds);
        List<Integer> petIds = petAdopts.stream().map(PetAdopt::getPetId).collect(Collectors.toList());
        List<PetDonate> petDonates = petDonateService.listByIds(petIds);
        List<Integer> donatedUserIds = petDonates.stream().map(PetDonate::getUserId).collect(Collectors.toList());
        List<User> donatedUsers = userService.listByIds(donatedUserIds);
        List<PetAdoptVO> list = new ArrayList<>();
        for (int i=0;i<petDonates.size();i++) {
            PetAdoptVO petAdoptVO = new PetAdoptVO();
            petAdoptVO.setDonateUser(donatedUsers.get(i));
            petAdoptVO.setAdoptUser(adoptedUsers.get(i));
            BeanUtils.copyProperties(petDonates.get(i), petAdoptVO);
            list.add(petAdoptVO);
        }
        return ResultUtils.success(list);
    }

    @GetMapping("/detail/info/{id}")
    public BaseResponse<PetAdoptVO> listPetAdoptVOById(@PathVariable("id") Integer id) {
        PetAdoptVO petAdoptVO = new PetAdoptVO();
        PetAdopt petAdopt = petAdoptService.getById(id);
        Integer adoptUserId = petAdopt.getUserId();
        User adoptedUser = userService.getById(adoptUserId);
        PetDonate petDonate = petDonateService.getById(petAdopt.getPetId());
        Integer donateUserId = petDonate.getUserId();
        User donatedUser = userService.getById(donateUserId);
        BeanUtils.copyProperties(petDonate, petAdoptVO);
        petAdoptVO.setDonateUser(donatedUser);
        petAdoptVO.setAdoptUser(adoptedUser);
        return ResultUtils.success(petAdoptVO);
    }

}