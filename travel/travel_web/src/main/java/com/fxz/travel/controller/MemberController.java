package com.fxz.travel.controller;

import com.fxz.travel.domain.Member;
import com.fxz.travel.service.IMemberService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private IMemberService memberService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3")Integer pageSize){
        ModelAndView mv = new ModelAndView();
        List<Member> memberList = memberService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo(memberList);
        mv.setViewName("member-list");
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }
    @RequestMapping("/save.do")
    public String save(Member member){
        memberService.save(member);
        return "redirect:findAll.do";
    }
    @RequestMapping("/deleteById.do")
    public String deleteById(@RequestParam String[] ids){
        memberService.deleteById(ids);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findByName.do")
    public ModelAndView findByName(@RequestParam String name){
        ModelAndView mv = new ModelAndView();
        List<Member> members = memberService.findByName(name);
        mv.setViewName("member-search");
        mv.addObject("members",members);
        return mv;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam String id){
        ModelAndView mv = new ModelAndView();
        Member member = memberService.findById(id);
        mv.setViewName("member-update");
        mv.addObject("member",member);
        return mv;
    }
    @RequestMapping("/update.do")
    public String update(Member member,@RequestParam String id){
        member.setId(id);
        memberService.update(member);
        return "redirect:findAll.do";
    }
}
