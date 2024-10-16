package com.yxr.petManagement.domain.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginRequest implements Serializable {

    String username;

    String password;

    private static final long serialVersionUID = 1L;

}
