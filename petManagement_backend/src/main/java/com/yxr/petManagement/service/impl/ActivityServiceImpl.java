package com.yxr.petManagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxr.petManagement.domain.entity.Activity;
import com.yxr.petManagement.mapper.ActivityMapper;
import com.yxr.petManagement.service.ActivityService;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
    implements ActivityService {

}




