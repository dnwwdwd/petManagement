package com.yxr.petManagement.controller;

import com.yxr.petManagement.common.BaseResponse;
import com.yxr.petManagement.common.DeleteRequest;
import com.yxr.petManagement.common.ResultUtils;
import com.yxr.petManagement.domain.vo.PetVO;
import com.yxr.petManagement.service.PetAdoptService;
import com.yxr.petManagement.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/adopt")
public class PetAdoptController {

    @Resource
    private PetAdoptService petAdoptService;

    @Resource
    private UserService userService;

    @GetMapping("/pet/list")
    public BaseResponse<List<PetVO>> listMyPetAdoptVO(HttpServletRequest request) {
        List<PetVO> petDonateVOS = petAdoptService.listMyPetAdopt(request);
        return ResultUtils.success(petDonateVOS);
    }

    @PostMapping("/pet")
    public BaseResponse<Boolean> adoptPet(HttpServletRequest request, @RequestBody DeleteRequest deleteRequest) {
        int petId = deleteRequest.getId();
        boolean b = petAdoptService.adoptPet(petId, userService.getLoginUser(request));
        return ResultUtils.success(b);
    }

}
