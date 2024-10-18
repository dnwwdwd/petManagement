package com.yxr.petManagement.domain.vo;

import com.yxr.petManagement.domain.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PetForumVO implements Serializable {

    private Integer id;

    private Integer userId;

    /**
     * 论坛名称
     */
    private String name;

    /**
     * 图片
     */
    private String imgUrl;

    /**
     * 描述
     */
    private String description;

    private User user;

    private Integer commentNum;

    private List<CommentVO> commentVOs;

    private static final long serialVersionUID = 1L;
}
