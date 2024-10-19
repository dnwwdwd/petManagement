package com.yxr.petManagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxr.petManagement.common.ErrorCode;
import com.yxr.petManagement.domain.entity.PetDonate;
import com.yxr.petManagement.domain.entity.User;
import com.yxr.petManagement.domain.vo.PetVO;
import com.yxr.petManagement.exception.BusinessException;
import com.yxr.petManagement.mapper.PetDonateMapper;
import com.yxr.petManagement.service.PetDonateService;
import com.yxr.petManagement.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
    public boolean donatePet(PetDonate petDonate, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        Integer userId = loginUser.getId();
        String petName = petDonate.getPetName();
        Integer age = petDonate.getAge();
        String sex = petDonate.getSex();
        String description = petDonate.getDescription();
        String state = petDonate.getState();
        Integer isSterilized = petDonate.getIsSterilized();
        Integer isVaccination = petDonate.getIsVaccination();
        Integer type = petDonate.getType();
        String avatarUrl = petDonate.getAvatarUrl();
        String variety = petDonate.getVariety();
        if (StringUtils.isEmpty(petName) ||
                age == null ||
                StringUtils.isEmpty(sex) ||
                StringUtils.isEmpty(state) ||
                isSterilized == null ||
                isVaccination == null ||
                StringUtils.isEmpty(description) || StringUtils.isEmpty(avatarUrl) || StringUtils.isEmpty(variety) ||
                type == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "捐赠信息未填全");
        }
        petDonate.setUserId(userId);
        boolean b = this.save(petDonate);
        return b;
    }
}




