package com.yxr.petManagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxr.petManagement.domain.entity.PetAdopt;
import com.yxr.petManagement.domain.entity.User;
import com.yxr.petManagement.domain.vo.PetVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PetAdoptService extends IService<PetAdopt> {

    List<PetVO> listMyPetAdopt(HttpServletRequest request);

    boolean adoptPet(int petId, User loginUser);
}
