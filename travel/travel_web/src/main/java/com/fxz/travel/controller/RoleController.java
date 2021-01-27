package com.fxz.travel.controller;

import com.fxz.travel.domain.Permission;
import com.fxz.travel.domain.Role;
import com.fxz.travel.service.IRoleService;
import org.apache.taglibs.standard.extra.spath.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
   private IRoleService iRoleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv =new ModelAndView();
        List<Role> roleList = iRoleService.findAll();
        mv.setViewName("role-list");
        mv.addObject("roleList",roleList);
        return mv;
    };
    @RequestMapping("/save.do")
    public String saveRole(Role role){
        iRoleService.saveRole(role);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findById.do")
    public ModelAndView findByRoleId(String id){
        ModelAndView mv = new ModelAndView();
        Role role = iRoleService.findByRoleId(id);
        mv.setViewName("role-show");
        mv.addObject("role",role);
        return mv;
    }
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) String roleId, @RequestParam(name = "ids", required = true) String[] permissionIds) throws Exception {
        iRoleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findRoleAndPermission.do")
    public ModelAndView findRoleAndPermission(@RequestParam(name = "id", required = true) String roleId){
        ModelAndView mv = new ModelAndView();
        Role role = iRoleService.findByRoleId(roleId);
        List<Permission> permissionList = iRoleService.findOtherPermissions(roleId);
        mv.setViewName("role-permission-add");
        mv.addObject("permissionList",permissionList);
        mv.addObject("role",role);
        return mv;
    }
    @RequestMapping("/deleteById.do")
    public String deleteRoleById(@RequestParam(name = "id",required = true)String roleId){
        iRoleService.deleteRoleById(roleId);
        return "redirect:findAll.do";
    }
}
