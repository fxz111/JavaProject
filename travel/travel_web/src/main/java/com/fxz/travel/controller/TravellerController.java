package com.fxz.travel.controller;

import com.fxz.travel.domain.Traveller;
import com.fxz.travel.service.ITravellerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/traveller")
public class TravellerController {
    @Autowired
    private ITravellerService travellerService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3")Integer pageSize) {
        ModelAndView mv = new ModelAndView();
        List<Traveller> travellerList = travellerService.findAll(page, pageSize);
        PageInfo pageInfo = new PageInfo(travellerList);
        mv.setViewName("traveller-list");
        mv.addObject("pageInfo", pageInfo);
        return mv;
    }
    @RequestMapping("/save.do")
    public String save(Traveller traveller){
        travellerService.save(traveller);
        return "redirect:findAll.do";
    }
    @RequestMapping("/deleteById")
    public String deleteById(@RequestParam String[] ids){
        travellerService.deleteById(ids);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findByName.do")
    public ModelAndView findByName(@RequestParam String name){
        ModelAndView mv= new ModelAndView();
        List<Traveller> travellerList= travellerService.findByName(name);
        mv.setViewName("traveller-search");
        mv.addObject("travellerList",travellerList);
        return mv;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam String id){
        ModelAndView mv= new ModelAndView();
        Traveller traveller = travellerService.findById(id);
        mv.setViewName("traveller-update");
        mv.addObject("traveller",traveller);
        return mv;
    }
    @RequestMapping("/update.do")
    public String update(Traveller traveller,@RequestParam String id){
        traveller.setId(id);
        travellerService.update(traveller);
        return "redirect:findAll.do";
    }
}
