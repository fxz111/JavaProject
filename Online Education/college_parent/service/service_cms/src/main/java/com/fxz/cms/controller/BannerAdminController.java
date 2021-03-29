package com.fxz.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.cms.entity.CrmBanner;
import com.fxz.cms.service.CrmBannerService;
import com.fxz.commonutils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author FengXZ
 * @since 2021-03-02
 */
@RestController
@RequestMapping("/cms/banneradmin")
@CrossOrigin
public class BannerAdminController {
    @Autowired
    private CrmBannerService bannerService;
    //分页查询
    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable Long page,
                        @PathVariable Long limit)  {
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        bannerService.page(pageBanner,null);
        Long total = pageBanner.getSize()+1;
        //System.out.println(total);
        return R.ok().data("items",pageBanner.getRecords()).data("total",total);
    }
    //2 添加banner
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.save(crmBanner);
        return R.ok();
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return R.ok().data("item", banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public R updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return R.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        bannerService.removeById(id);
        return R.ok();
    }

}

