package com.yxr.petManagement.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 宠物表
 * @TableName pet
 */
@TableName(value ="pet")
@Data
public class Pet implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 添加此宠物人的id
     */
    private Integer userId;

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