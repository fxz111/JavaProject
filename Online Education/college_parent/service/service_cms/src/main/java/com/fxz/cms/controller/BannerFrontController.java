package com.fxz.cms.controller;

import com.fxz.cms.entity.CrmBanner;
import com.fxz.cms.service.CrmBannerService;
import com.fxz.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cms/bannerfront")
@CrossOrigin
public class BannerFrontController {
    @Autowired
    private CrmBannerService bannerService;
    @GetMapping("getAllBanner")
    public R getAllBanner(){
        List<CrmBanner> list = bannerService.getAllBanner();
        return R.ok().data("list",list);
    }
}
