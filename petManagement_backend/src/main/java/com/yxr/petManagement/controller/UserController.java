package com.yxr.petManagement.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxr.petManagement.common.BaseResponse;
import com.yxr.petManagement.common.DeleteRequest;
import com.yxr.petManagement.common.ErrorCode;
import com.yxr.petManagement.common.ResultUtils;
import com.yxr.petManagement.domain.entity.User;
import com.yxr.petManagement.domain.request.UserLoginRequest;
import com.yxr.petManagement.domain.request.UserRegisterRequest;
import com.yxr.petManagement.exception.BusinessException;
import com.yxr.petManagement.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.yxr.petManagement.constant.UserConstant.USER_LOGIN_STATE;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public BaseResponse<User> login(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        String username = userLoginRequest.getUsername();
        String password = userLoginRequest.getPassword();
        User user = userService.userLogin(username, password, request);
        return ResultUtils.success(user);
    }

    @PostMapping("/register")
    public BaseResponse<Long> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        long userId = userService.userRegister(userRegisterRequest);
        return ResultUtils.success(userId);
    }

    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        User user = userService.getById(loginUser.getId());
        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updateUser(@RequestBody User user, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        if (loginUser.getId() != user.getId() || !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        return ResultUtils.success(userService.updateById(user));
    }

    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return ResultUtils.success(1);
    }

    @GetMapping("/list")
    public BaseResponse<List<User>> listCommonUser(HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role", "ROLE_USER");
        List<User> users = userService.list(queryWrapper);
        return ResultUtils.success(users);
    }

    @PostMapping("/add")
    public BaseResponse<Integer> addUser(@RequestBody User user, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        user.setRole("ROLE_USER");
        userService.save(user);
        return ResultUtils.success(user.getId());
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        int id = deleteRequest.getId();
        return ResultUtils.success(userService.removeById(id));
    }

}

