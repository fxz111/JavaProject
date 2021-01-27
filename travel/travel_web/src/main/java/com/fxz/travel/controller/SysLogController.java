package com.fxz.travel.controller;

import com.fxz.travel.domain.SysLog;
import com.fxz.travel.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private ISysLogService sysLogService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogList = sysLogService.findAll();
        mv.setViewName("sysLog-list");
        mv.addObject("sysLogList",sysLogList);
        return mv;
    }
    @RequestMapping("/deleteAll.do")
    public String deleteAll(){
        sysLogService.deleteAll();
        return "redirect:findAll.do";
    }
}
