package com.yxr.petManagement.domain.vo;

import com.yxr.petManagement.domain.entity.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class ActivityVO implements Serializable {

    private Integer id;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 活动地点
     */
    private String address;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    private User user;

    private Integer userId;

    private static final long serialVersionUID = 1L;

}
