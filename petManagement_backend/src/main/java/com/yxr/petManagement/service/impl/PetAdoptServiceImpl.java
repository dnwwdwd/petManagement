package com.yxr.petManagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxr.petManagement.domain.entity.PetAdopt;
import com.yxr.petManagement.domain.entity.PetDonate;
import com.yxr.petManagement.domain.entity.User;
import com.yxr.petManagement.domain.vo.PetVO;
import com.yxr.petManagement.mapper.PetAdoptMapper;
import com.yxr.petManagement.service.PetAdoptService;
import com.yxr.petManagement.service.PetDonateService;
import com.yxr.petManagement.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetAdoptServiceImpl extends ServiceImpl<PetAdoptMapper, PetAdopt>
    implements PetAdoptService {

    @Resource
    private UserService userService;

    @Resource
    private PetDonateService petDonateService;

    @Override
    public List<PetVO> listMyPetAdopt(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        Integer userId = loginUser.getId();
        QueryWrapper<PetAdopt> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        List<PetAdopt> petAdopts = this.list(queryWrapper);
        List<Integer> petIds = petAdopts.stream().map(PetAdopt::getPetId).collect(Collectors.toList());
        List<PetDonate> pets = petDonateService.listByIds(petIds);
        List<PetVO> petVOs = pets.stream().map(pet -> {
            PetVO petVO = new PetVO();
            petVO.setUser(userService.getById(pet.getUserId()));
            BeanUtils.copyProperties(pet, petVO);
            return petVO;
        }).collect(Collectors.toList());
        return petVOs;
    }

    @Override
    public boolean adoptPet(int petId, User loginUser) {
        PetDonate petDonate = new PetDonate();
        petDonate.setId(petId);
        petDonate.setIsAdopted(1);
        boolean b1 = petDonateService.updateById(petDonate);
        Integer userId = loginUser.getId();
        PetAdopt petAdopt = new PetAdopt();
        petAdopt.setUserId(userId);
        petAdopt.setPetId(petId);
        boolean b2 = this.save(petAdopt);
        return b1 && b2;
    }

}




