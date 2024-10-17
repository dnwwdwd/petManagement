package com.yxr.petManagement.controller;

import com.yxr.petManagement.common.BaseResponse;
import com.yxr.petManagement.common.DeleteRequest;
import com.yxr.petManagement.common.ErrorCode;
import com.yxr.petManagement.common.ResultUtils;
import com.yxr.petManagement.domain.entity.PetForum;
import com.yxr.petManagement.domain.entity.User;
import com.yxr.petManagement.domain.vo.PetForumVO;
import com.yxr.petManagement.exception.BusinessException;
import com.yxr.petManagement.service.PetForumService;
import com.yxr.petManagement.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pet/forum")
public class PetForumController {

    @Resource
    private UserService userService;

    @Resource
    private PetForumService petForumService;

    @PostMapping("/add")
    public BaseResponse<Integer> addPetForum(@RequestBody PetForum petForum, HttpServletRequest request) {
        boolean isAdmin = userService.isAdmin(request);
        if (!isAdmin) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        petForum.setUserId(loginUser.getId());
        petForumService.save(petForum);
        return ResultUtils.success(petForum.getId());
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deletePetForum(@RequestBody DeleteRequest deleteRequest,
                                                HttpServletRequest request) {
        boolean isAdmin = userService.isAdmin(request);
        if (!isAdmin) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        int id = deleteRequest.getId();
        return ResultUtils.success(petForumService.removeById(id));
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updatePetForum(@RequestBody PetForum petForum,
                                                HttpServletRequest request) {
        boolean isAdmin = userService.isAdmin(request);
        if (!isAdmin) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        Integer userId = userService.getLoginUser(request).getId();
        petForum.setUserId(userId);
        boolean b = petForumService.updateById(petForum);
        return ResultUtils.success(b);
    }

    @GetMapping("/list")
    public BaseResponse<List<PetForumVO>> listPetForum() {
        List<PetForum> petForums = petForumService.list();
        List<PetForumVO> petForumVOS = petForums.stream().map(petForum -> {
            PetForumVO petForumVO = new PetForumVO();
            Integer userId = petForum.getUserId();
            User user = userService.getById(userId);
            petForumVO.setUser(user);
            BeanUtils.copyProperties(petForum, petForumVO);
            return petForumVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(petForumVOS);
    }
}
