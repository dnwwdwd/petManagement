package com.yxr.petManagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxr.petManagement.domain.entity.Pet;
import com.yxr.petManagement.domain.entity.PetAdopt;
import com.yxr.petManagement.domain.entity.PetDonate;
import com.yxr.petManagement.domain.entity.User;
import com.yxr.petManagement.domain.request.PetDonateAddRequest;
import com.yxr.petManagement.domain.vo.PetVO;
import com.yxr.petManagement.mapper.PetDonateMapper;
import com.yxr.petManagement.service.PetDonateService;
import com.yxr.petManagement.service.PetService;
import com.yxr.petManagement.service.UserService;
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
    private PetService petService;

    @Resource
    private UserService userService;

    public List<PetVO> listPetDonateVO(){
        QueryWrapper<PetDonate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("isAdopted", 0);
        List<Integer> petIds = this.list(queryWrapper).stream().map(PetDonate::getPetId).collect(Collectors.toList());
        List<Pet> pets = petService.listByIds(petIds);
        return pets.stream().map(pet -> {
            PetVO petVO = new PetVO();
            Integer userId = pet.getUserId();
            User user = userService.getById(userId);
            petVO.setNickname(user.getNickname());
            BeanUtils.copyProperties(pet, petVO);
            return petVO;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean donatePet(PetDonateAddRequest petDonateAddRequest, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        Integer userId = loginUser.getId();
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDonateAddRequest, pet);
        pet.setUserId(userId);
        boolean b1 = petService.save(pet);

        PetDonate petDonate = new PetDonate();
        petDonate.setPetId(pet.getId());
        petDonate.setUserId(userId);
        boolean b2 = this.save(petDonate);
        return b1 && b2;
    }
}




