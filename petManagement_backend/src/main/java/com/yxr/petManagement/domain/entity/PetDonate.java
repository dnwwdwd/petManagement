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
     * 宠物id
     */
    private Integer petId;

    /**
     * 捐赠人id
     */
    private Integer userId;

    /**
     * 是否被收养（0 - 未被收养 1 - 被收养）
     */
    private Integer isAdopted;

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