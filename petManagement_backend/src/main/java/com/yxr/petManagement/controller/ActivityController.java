package com.yxr.petManagement.controller;

import com.yxr.petManagement.common.BaseResponse;
import com.yxr.petManagement.common.DeleteRequest;
import com.yxr.petManagement.common.ErrorCode;
import com.yxr.petManagement.common.ResultUtils;
import com.yxr.petManagement.domain.entity.Activity;
import com.yxr.petManagement.domain.entity.User;
import com.yxr.petManagement.domain.vo.ActivityVO;
import com.yxr.petManagement.exception.BusinessException;
import com.yxr.petManagement.service.ActivityService;
import com.yxr.petManagement.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Resource
    private UserService userService;

    @Resource
    private ActivityService activityService;

    @PostMapping("/add")
    public BaseResponse<Integer> addActivity(@RequestBody Activity activity, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        activity.setUserId(loginUser.getId());
        activityService.save(activity);
        return ResultUtils.success(activity.getId());
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updateActivity(@RequestBody Activity activity, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = activityService.updateById(activity);
        return ResultUtils.success(b);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteActivity(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        int id = deleteRequest.getId();
        boolean b = activityService.removeById(id);
        return ResultUtils.success(b);
    }

    @GetMapping("/list")
    public BaseResponse<List<ActivityVO>> listActivities() {
        List<Activity> activities = activityService.list();
        List<ActivityVO> activityVOS = activities.stream().map(activity -> {
            ActivityVO activityVO = new ActivityVO();
            activityVO.setUser(userService.getById(activity.getUserId()));
            BeanUtils.copyProperties(activity, activityVO);
            return activityVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(activityVOS);
    }

    @GetMapping("/detail/{id}")
    public BaseResponse<ActivityVO> getActivityDetailById(@PathVariable("id") Integer id) {
        Activity activity = activityService.getById(id);
        ActivityVO activityVO = new ActivityVO();
        activityVO.setUser(userService.getById(activity.getUserId()));
        BeanUtils.copyProperties(activity, activityVO);
        return ResultUtils.success(activityVO);
    }

}
