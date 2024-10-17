package com.yxr.petManagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxr.petManagement.common.ErrorCode;
import com.yxr.petManagement.domain.entity.Pet;
import com.yxr.petManagement.domain.entity.PetAdopt;
import com.yxr.petManagement.domain.entity.PetDonate;
import com.yxr.petManagement.domain.entity.User;
import com.yxr.petManagement.domain.vo.PetVO;
import com.yxr.petManagement.exception.BusinessException;
import com.yxr.petManagement.mapper.PetAdoptMapper;
import com.yxr.petManagement.service.PetAdoptService;
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
public class PetAdoptServiceImpl extends ServiceImpl<PetAdoptMapper, PetAdopt>
    implements PetAdoptService {

    @Resource
    private PetService petService;

    @Resource
    private UserService userService;

    @Resource
    private PetDonateService petDonateService;

    @Override
    public List<PetVO> listMyPetAdopt(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        QueryWrapper<Pet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", loginUser.getId());
        List<Pet> pets = petService.list(queryWrapper);
        return pets.stream().map(pet -> {
            PetVO petVO = new PetVO();
            petVO.setUser(userService.getSafetyUser(userService.getById(pet.getUserId())));
            BeanUtils.copyProperties(pet, petVO);
            return petVO;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean adoptPet(int petId, User loginUser) {
        Integer userId = loginUser.getId();
        QueryWrapper<Pet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", petId);
        long count = petService.count(queryWrapper);
        if (count < 1) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "宠物不存在");
        }
        boolean b1 = petService.removeById(petId);

        QueryWrapper<PetDonate> queryWrapperPetDonate = new QueryWrapper<>();
        queryWrapperPetDonate.eq("petId", petId);
        long petDonateCount = petDonateService.count(queryWrapperPetDonate);
        boolean b2 = true;
        if (b1 && petDonateCount > 0) {
            PetDonate petDonate = new PetDonate();
            petDonate.setPetId(petId);
            petDonate.setIsAdopted(1);
            b2 = petDonateService.updateById(petDonate);
        }
        PetAdopt petAdopt = new PetAdopt();
        petAdopt.setPetId(petId);
        petAdopt.setUserId(userId);
        boolean b3 = this.save(petAdopt);
        return b1 && b2 && b3;
    }

}




