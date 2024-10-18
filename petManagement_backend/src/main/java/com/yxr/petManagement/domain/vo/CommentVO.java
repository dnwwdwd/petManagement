package com.yxr.petManagement.domain.vo;

import com.yxr.petManagement.domain.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class CommentVO {

    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 宠物论坛id
     */
    private Integer petForumId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTIme;

    private User user;
}
