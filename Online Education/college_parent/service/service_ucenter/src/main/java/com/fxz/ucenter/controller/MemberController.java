package com.fxz.ucenter.controller;


import com.fxz.commonutils.JwtUtils;
import com.fxz.commonutils.R;
import com.fxz.commonutils.orderVo.MemberOrder;
import com.fxz.ucenter.entity.Member;
import com.fxz.ucenter.entity.vo.MemberWeb;
import com.fxz.ucenter.entity.vo.RegisterVo;
import com.fxz.ucenter.service.MemberService;
import io.swagger.models.auth.In;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author FengXZ
 * @since 2021-03-03
 */
@RestController
@RequestMapping("/ucenter/member")
@CrossOrigin
public class MemberController {
    @Autowired
    private MemberService memberService;

    //登录
    @PostMapping("login")
    public R loginUser(@RequestBody Member member){
        String token = memberService.login(member);
        return R.ok().data("token",token);
    }

    //注册
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }
    //根据token得到用户信息
    @GetMapping("memberInfo")
    public R getMembetInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        Member member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }
    @GetMapping("getInfoById/{id}")
    public MemberWeb getInfoById(@PathVariable String id){
        Member member = memberService.getById(id);
        MemberWeb memberWeb = new MemberWeb();
        BeanUtils.copyProperties(member,memberWeb);
        return memberWeb;
    }
    @GetMapping("getMemerOrder/{memberId}")
    public MemberOrder getMemberOrder(@PathVariable String memberId){
        Member member = memberService.getById(memberId);
        MemberOrder memberOrder = new MemberOrder();
        BeanUtils.copyProperties(member,memberOrder);
        return memberOrder;
    }
    //查询某一天的注册人数
    @GetMapping("getRegisterCount/{time}")
    public R getRegisterCount(@PathVariable String time){
        Integer count = memberService.getRegisterCount(time);
        return R.ok().data("countRegister",count);
    }

}

