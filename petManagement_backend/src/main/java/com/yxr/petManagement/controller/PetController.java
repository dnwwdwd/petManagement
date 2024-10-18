package com.yxr.petManagement.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.yxr.petManagement.common.BaseResponse;
import com.yxr.petManagement.common.DeleteRequest;
import com.yxr.petManagement.common.ErrorCode;
import com.yxr.petManagement.common.ResultUtils;
import com.yxr.petManagement.domain.entity.Pet;
import com.yxr.petManagement.domain.entity.User;
import com.yxr.petManagement.domain.vo.PetVO;
import com.yxr.petManagement.exception.BusinessException;
import com.yxr.petManagement.service.PetService;
import com.yxr.petManagement.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Resource
    private PetService petService;

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public BaseResponse<List<Pet>> listPets() {
        return ResultUtils.success(petService.list());
    }

    @PostMapping("/add")
    public BaseResponse<Boolean> addPet(@RequestBody Pet pet, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        pet.setUserId(loginUser.getId());
        boolean b = petService.save(pet);
        return ResultUtils.success(b);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deletePet(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = petService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }

    @GetMapping("/detail/{id}")
    public BaseResponse<PetVO> getPetDetailById(@PathVariable("id") Integer id) {
        Pet pet = petService.getById(id);
        Integer userId = pet.getUserId();
        User user = userService.getById(userId);
        User safetyUser = userService.getSafetyUser(user);
        PetVO petVO = new PetVO();
        petVO.setUser(safetyUser);
        BeanUtils.copyProperties(pet, petVO);
        return ResultUtils.success(petVO);
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updatePet(@RequestBody Pet pet, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        return ResultUtils.success(petService.updateById(pet));
    }

}
