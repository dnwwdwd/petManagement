package com.yxr.petManagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxr.petManagement.domain.entity.User;
import com.yxr.petManagement.domain.request.UserRegisterRequest;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends IService<User> {

    long userRegister(UserRegisterRequest userRegisterRequest);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    User getSafetyUser(User user);
}
