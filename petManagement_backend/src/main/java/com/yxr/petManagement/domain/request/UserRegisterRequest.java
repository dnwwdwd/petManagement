package com.yxr.petManagement.domain.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {

    String username;

    String password;

    String checkPassword;

    String nickname;

    private static final long serialVersionUID = 1L;

}
