package com.yxr.petManagement.domain.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PetDonateAddRequest implements Serializable {

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
     * 身体状态
     */
    private String state;

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

    /**
     * 宠物类型
     */
    private Integer type;

}
