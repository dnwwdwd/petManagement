package com.yxr.petManagement.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 宠物捐赠
 * @TableName pet_donate
 */
@TableName(value ="pet_donate")
@Data
public class PetDonate implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}