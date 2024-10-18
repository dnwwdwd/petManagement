package com.yxr.petManagement.domain.vo;

import com.yxr.petManagement.domain.entity.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class PetVO implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 宠物名称
     */
    private String petName;

    /**
     * 宠物头像
     */
    private String avatarUrl;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 身体状态
     */
    private String state;

    /**
     * 品种
     */
    private String variety;

    /**
     * 是否绝育（0 - 未绝育 1 - 已绝育）
     */
    private Integer isSterilized;

    /**
     * 是否接种疫苗（0 - 未接种 1 - 已接种）
     */
    private Integer isVaccination;

    /**
     * 描述
     */
    private String description;

    private User user;
}
