package com.yxr.petManagement.controller;


import com.yxr.petManagement.common.BaseResponse;
import com.yxr.petManagement.common.ResultUtils;
import com.yxr.petManagement.domain.entity.User;
import com.yxr.petManagement.domain.request.UserLoginRequest;
import com.yxr.petManagement.domain.request.UserRegisterRequest;
import com.yxr.petManagement.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


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

}

