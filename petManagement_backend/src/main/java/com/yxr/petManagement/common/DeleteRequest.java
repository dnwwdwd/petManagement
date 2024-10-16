package com.yxr.petManagement.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 删除请求

 */
@Data
public class DeleteRequest implements Serializable {

    private int id;

    private static final long serialVersionUID = 1L;
}