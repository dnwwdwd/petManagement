package com.yxr.petManagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxr.petManagement.domain.entity.PetDonate;
import com.yxr.petManagement.domain.request.PetDonateAddRequest;
import com.yxr.petManagement.domain.vo.PetVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PetDonateService extends IService<PetDonate> {

    List<PetVO> listPetDonateVO();

    boolean donatePet(PetDonateAddRequest petDonateAddRequest, HttpServletRequest request);

}
