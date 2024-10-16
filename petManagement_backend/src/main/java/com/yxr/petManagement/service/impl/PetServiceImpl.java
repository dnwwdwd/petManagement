package com.yxr.petManagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxr.petManagement.domain.entity.Pet;
import com.yxr.petManagement.mapper.PetMapper;
import com.yxr.petManagement.service.PetService;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl extends ServiceImpl<PetMapper, Pet>
    implements PetService {

}




