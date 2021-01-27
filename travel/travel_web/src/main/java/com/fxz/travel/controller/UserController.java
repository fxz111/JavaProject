package com.fxz.travel.controller;

import com.fxz.travel.domain.Role;
import com.fxz.travel.domain.UserInfo;
import com.fxz.travel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfoList = userService.findAll();
        mv.setViewName("user-list");
        mv.addObject("userInfoList",userInfoList);
        return mv;
    }
    @RequestMapping("/save.do")
    public String saveUser(UserInfo userInfo) throws Exception{
        userService.saveUser(userInfo);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id" ,required = true) String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(id);
        mv.setViewName("user-show");
        mv.addObject("user",user);
        return mv;
    }
    @RequestMapping("/deleteUser.do")
    public String deleteUser(@RequestParam(name = "id",required = true)String id){
        userService.deleteUser(id);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findUserAndRole.do")
    public ModelAndView findUserAndRole(@RequestParam(name = "id" ,required = true)String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(id);
        List<Role> roleList = userService.findOtherRole(id);
        mv.setViewName("user-role-add");
        mv.addObject("user",user);
        mv.addObject("roleList",roleList);
        return mv;
    }
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)String userId,@RequestParam(name = "ids",required = true)String[] ids){
        userService.addRoleToUser(userId,ids);
        return "redirect:findAll.do";
    }
}
