package com.yxr.petManagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxr.petManagement.common.ErrorCode;
import com.yxr.petManagement.domain.entity.PetDonate;
import com.yxr.petManagement.domain.entity.User;
import com.yxr.petManagement.domain.request.PetDonateAddRequest;
import com.yxr.petManagement.domain.vo.PetVO;
import com.yxr.petManagement.exception.BusinessException;
import com.yxr.petManagement.mapper.PetDonateMapper;
import com.yxr.petManagement.service.PetDonateService;
import com.yxr.petManagement.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetDonateServiceImpl extends ServiceImpl<PetDonateMapper, PetDonate>
    implements PetDonateService {

    @Resource
    private UserService userService;

    public List<PetVO> listPetDonateVO(){
        QueryWrapper<PetDonate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("isAdopted", 0);
        List<PetDonate> petDonates = this.list(queryWrapper);
        return petDonates.stream().map(petDonate -> {
            PetVO petVO = new PetVO();
            petVO.setUser(userService.getSafetyUser(userService.getById(petDonate.getUserId())));
            BeanUtils.copyProperties(petDonate, petVO);
            return petVO;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean donatePet(@RequestBody PetDonateAddRequest petDonateAddRequest, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        Integer userId = loginUser.getId();
        String petName = petDonateAddRequest.getPetName();
        Integer age = petDonateAddRequest.getAge();
        String sex = petDonateAddRequest.getSex();
        String state = petDonateAddRequest.getState();
        Integer isSterilized = petDonateAddRequest.getIsSterilized();
        Integer isVaccination = petDonateAddRequest.getIsVaccination();
        String description = petDonateAddRequest.getDescription();
        Integer type = petDonateAddRequest.getType();
        if (StringUtils.isEmpty(petName) ||
                age == null ||
                StringUtils.isEmpty(sex) ||
                StringUtils.isEmpty(state) ||
                isSterilized == null ||
                isVaccination == null ||
                StringUtils.isEmpty(description) ||
                type == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "捐赠信息未填全");
        }
        PetDonate petDonate = new PetDonate();
        BeanUtils.copyProperties(petDonateAddRequest, petDonate);
        petDonate.setUserId(userId);
        boolean b = this.save(petDonate);
        return b;
    }
}




