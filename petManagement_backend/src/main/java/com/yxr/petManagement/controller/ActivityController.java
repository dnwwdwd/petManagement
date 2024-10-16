package com.yxr.petManagement.controller;

import com.yxr.petManagement.common.BaseResponse;
import com.yxr.petManagement.common.DeleteRequest;
import com.yxr.petManagement.common.ErrorCode;
import com.yxr.petManagement.common.ResultUtils;
import com.yxr.petManagement.domain.entity.Activity;
import com.yxr.petManagement.exception.BusinessException;
import com.yxr.petManagement.service.ActivityService;
import com.yxr.petManagement.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public BaseResponse<List<Activity>> listActivities() {
        List<Activity> activities = activityService.list();
        return ResultUtils.success(activities);
    }

}
