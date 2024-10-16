package com.yxr.petManagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxr.petManagement.common.ErrorCode;
import com.yxr.petManagement.constant.UserConstant;
import com.yxr.petManagement.domain.entity.User;
import com.yxr.petManagement.domain.request.UserRegisterRequest;
import com.yxr.petManagement.exception.BusinessException;
import com.yxr.petManagement.mapper.UserMapper;
import com.yxr.petManagement.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.yxr.petManagement.constant.UserConstant.USER_LOGIN_STATE;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public long userRegister(UserRegisterRequest userRegisterRequest) {
        String username = userRegisterRequest.getUsername();
        String password = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String nickname = userRegisterRequest.getNickname();
        synchronized (username.intern()) {
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(checkPassword)
                    || StringUtils.isEmpty(nickname)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
            }
            if (!password.equals(checkPassword)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入的密码不一致");
            }
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", username);
            long count = this.count(queryWrapper);
            if (count > 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户已存在");
            }
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setNickname(nickname);
            this.save(user);
            return user.getId();
        }
    }

    @Override
    public User userLogin(String username, String password, HttpServletRequest request) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "信息不能为空");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", password);
        User user = this.getOne(queryWrapper);
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        return this.getSafetyUser(user);
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    @Override
    public boolean isAdmin(HttpServletRequest request) {
        User loginUser = this.getLoginUser(request);
        return UserConstant.ADMIN_ROLE.equals(loginUser.getRole());
    }

    @Override
    public boolean userLogout(HttpServletRequest request) {
        if (request.getSession().getAttribute(USER_LOGIN_STATE) == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "未登录");
        }
        // 移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }


    private User getSafetyUser(User user) {
        Integer id = user.getId();
        String username = user.getUsername();
        String nickname = user.getNickname();
        String email = user.getEmail();
        String phone = user.getPhone();
        String address = user.getAddress();
        Date createTime = user.getCreateTime();
        String avatarUrl = user.getAvatarUrl();
        String role = user.getRole();
        String sex = user.getSex();
        String birth = user.getBirth();
        User safetyUser = new User();
        safetyUser.setId(id);
        safetyUser.setUsername(username);
        safetyUser.setNickname(nickname);
        safetyUser.setEmail(email);
        safetyUser.setPhone(phone);
        safetyUser.setAddress(address);
        safetyUser.setCreateTime(createTime);
        safetyUser.setAvatarUrl(avatarUrl);
        safetyUser.setRole(role);
        safetyUser.setSex(sex);
        safetyUser.setBirth(birth);
        return safetyUser;
    }

}
