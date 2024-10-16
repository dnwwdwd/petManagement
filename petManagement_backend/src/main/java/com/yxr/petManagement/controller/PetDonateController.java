package com.yxr.petManagement.controller;

import com.yxr.petManagement.common.BaseResponse;
import com.yxr.petManagement.common.ResultUtils;
import com.yxr.petManagement.domain.request.PetDonateAddRequest;
import com.yxr.petManagement.domain.vo.PetVO;
import com.yxr.petManagement.service.PetDonateService;
import com.yxr.petManagement.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/donate")
public class PetDonateController {

    @Resource
    private PetDonateService petDonateService;

    @Resource
    private UserService userService;

    @GetMapping("/pet/list")
    public BaseResponse<List<PetVO>> listPetDonateVO() {
        List<PetVO> petVOS = petDonateService.listPetDonateVO();
        return ResultUtils.success(petVOS);
    }

    @PostMapping("/pet")
    public BaseResponse<Boolean> donatePet(@RequestBody PetDonateAddRequest PetDonateAddRequest, HttpServletRequest request) {
        boolean b = petDonateService.donatePet(PetDonateAddRequest, request);
        return ResultUtils.success(b);
    }
}
