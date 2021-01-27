package com.fxz.travel.controller;

import com.fxz.travel.domain.Permission;
import com.fxz.travel.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService iPermissionService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList= iPermissionService.findAll();
        mv.setViewName("permission-list");
        mv.addObject("permissionList",permissionList);
        return mv;
    }
    @RequestMapping("/save.do")
    public String savePermission(Permission permission){
        iPermissionService.savePermission(permission);
        return "redirect:findAll.do";
    }
    @RequestMapping("/deletePermissionById.do")
    public String deletePermission(@RequestParam(name = "id",required = true)String id){
        iPermissionService.deletePermission(id);
        return "redirect:findAll.do";
    }
}
