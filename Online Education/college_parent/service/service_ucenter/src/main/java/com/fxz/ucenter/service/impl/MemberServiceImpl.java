package com.fxz.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fxz.commonutils.JwtUtils;
import com.fxz.commonutils.MD5;
import com.fxz.servicebase.exceptionhandler.GuliException;
import com.fxz.ucenter.entity.Member;
import com.fxz.ucenter.entity.vo.RegisterVo;
import com.fxz.ucenter.mapper.MemberMapper;
import com.fxz.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author FengXZ
 * @since 2021-03-03
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public String login(Member member) {
        String mobile = member.getMobile();
        String password = member.getPassword();
        //非空判断
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new GuliException(20001,"登录失败");
        }
        //判断手机号是否正确
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Member mobileMember = baseMapper.selectOne(wrapper);
        if(mobileMember == null){
            throw new GuliException(20001,"登录失败，手机号不存在");
        }
        //判断密码是否正确

        if (!MD5.encrypt(password).equals(mobileMember.getPassword())){
            throw new GuliException(20001,"登录失败，密码错误");
        }
        //判断用户是否禁用
        if(mobileMember.getIsDisabled()){
            throw new GuliException(20001,"登录失败，用户禁用");
        }
        String token = JwtUtils.getJwtToken(mobileMember.getId(),mobileMember.getNickname());

        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {

        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        if (StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            throw new GuliException(20001,"注册失败");
        }
//        String redisCode = redisTemplate.opsForValue().get(mobile);
//        if(!redisCode.equals(code)){
//            throw new GuliException(20001,"注册失败");
//        }
        //判断手机号是否重复
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if(count>0){
            throw new GuliException(20001,"注册失败");
        }
        //添加数据
        Member member =  new Member();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("https://college-fxz.oss-cn-beijing.aliyuncs.com/2021/02/16/0ed5c4d282ac4df5bf83395f6cb796d0file.png");
        baseMapper.insert(member);
    }

    @Override
    public Member getByOpenId(String openid) {
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("openid",openid);
        Member member = baseMapper.selectOne(memberQueryWrapper);
        return member;
    }

    @Override
    public Integer getRegisterCount(String time) {
        return baseMapper.getRegisterCount(time);
    }
}
