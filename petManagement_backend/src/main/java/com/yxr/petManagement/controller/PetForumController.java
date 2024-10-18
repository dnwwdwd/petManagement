package com.yxr.petManagement.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxr.petManagement.common.BaseResponse;
import com.yxr.petManagement.common.DeleteRequest;
import com.yxr.petManagement.common.ErrorCode;
import com.yxr.petManagement.common.ResultUtils;
import com.yxr.petManagement.domain.entity.Comment;
import com.yxr.petManagement.domain.entity.PetForum;
import com.yxr.petManagement.domain.entity.User;
import com.yxr.petManagement.domain.vo.CommentVO;
import com.yxr.petManagement.domain.vo.PetForumVO;
import com.yxr.petManagement.exception.BusinessException;
import com.yxr.petManagement.service.CommentService;
import com.yxr.petManagement.service.PetForumService;
import com.yxr.petManagement.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pet/forum")
public class PetForumController {

    @Resource
    private UserService userService;

    @Resource
    private PetForumService petForumService;

    @Resource
    private CommentService commentService;

    @PostMapping("/add")
    public BaseResponse<Integer> addPetForum(@RequestBody PetForum petForum, HttpServletRequest request) {
        boolean isAdmin = userService.isAdmin(request);
        if (!isAdmin) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        petForum.setUserId(loginUser.getId());
        petForumService.save(petForum);
        return ResultUtils.success(petForum.getId());
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deletePetForum(@RequestBody DeleteRequest deleteRequest,
                                                HttpServletRequest request) {
        boolean isAdmin = userService.isAdmin(request);
        if (!isAdmin) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        int id = deleteRequest.getId();
        return ResultUtils.success(petForumService.removeById(id));
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updatePetForum(@RequestBody PetForum petForum,
                                                HttpServletRequest request) {
        boolean isAdmin = userService.isAdmin(request);
        if (!isAdmin) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        Integer userId = userService.getLoginUser(request).getId();
        petForum.setUserId(userId);
        boolean b = petForumService.updateById(petForum);
        return ResultUtils.success(b);
    }

    @GetMapping("/list")
    public BaseResponse<List<PetForumVO>> listPetForum() {
        List<PetForum> petForums = petForumService.list();
        List<PetForumVO> petForumVOS = petForums.stream().map(petForum -> {
            PetForumVO petForumVO = new PetForumVO();
            Integer userId = petForum.getUserId();
            User user = userService.getById(userId);
            petForumVO.setUser(user);
            BeanUtils.copyProperties(petForum, petForumVO);
            return petForumVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(petForumVOS);
    }

    @GetMapping("/detail/{id}")
    public BaseResponse<PetForumVO> getPetForumDetailById(@PathVariable("id") Integer id) {
        PetForumVO petForumVO = new PetForumVO();
        PetForum petForum = petForumService.getById(id);
        Integer petForumId = petForum.getId();
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("petForumId", petForumId);
        List<Comment> commentList = commentService.list(queryWrapper);
        List<CommentVO> commentVOs = commentList.stream().map(comment -> {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(comment, commentVO);
            commentVO.setUser(userService.getById(comment.getUserId()));
            return commentVO;
        }).collect(Collectors.toList());
        petForumVO.setCommentVOs(commentVOs);
        petForumVO.setUser(userService.getById(petForum.getUserId()));
        BeanUtils.copyProperties(petForum, petForumVO);
        return ResultUtils.success(petForumVO);
    }

    @PostMapping("/comment")
    public BaseResponse<Integer> addPetForumComment(@RequestBody Comment comment, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        Integer userId = loginUser.getId();
        comment.setUserId(userId);
        commentService.save(comment);
        return ResultUtils.success(comment.getId());
    }

    @PostMapping("/delete/comment")
    public BaseResponse<Boolean> deletePetForumComment(@RequestBody Comment comment, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        Comment commentData = commentService.getById(comment.getId());
        if (commentData == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "评论不存在");
        }
        if (comment.getUserId() != loginUser.getId() || !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = commentService.removeById(commentData.getId());
        return ResultUtils.success(b);
    }

    @PostMapping("/update/comment")
    public BaseResponse<Boolean> updatePetForumComment(@RequestBody Comment comment, HttpServletRequest request) {
        Comment commentData = commentService.getById(comment.getId());
        if (commentData == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "评论不存在");
        }
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = commentService.updateById(comment);
        return ResultUtils.success(b);
    }

}
