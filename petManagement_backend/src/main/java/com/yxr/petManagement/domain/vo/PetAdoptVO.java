package com.yxr.petManagement.domain.vo;

import com.yxr.petManagement.domain.entity.User;
import lombok.Data;

@Data
public class PetAdoptVO {
    private Integer id;

    /**
     * 宠物名称
     */
    private String petName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private String state;

    /**
     * 是否绝育
     */
    private Integer isSterilized;

    /**
     * 是否接种疫苗
     */
    private Integer isVaccination;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 捐赠人id
     */
    private Integer userId;

    /**
     * 是否被收养
     */
    private Integer isAdopted;

    private String avatarUrl;

    private String variety;

    private User donateUser;

    private User adoptUser;
}
