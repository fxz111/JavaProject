package com.fxz.ucenter.service;

import com.fxz.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author FengXZ
 * @since 2021-03-03
 */
public interface MemberService extends IService<Member> {

    String login(Member member);

    void register(RegisterVo registerVo);

    Member getByOpenId(String openid);

    Integer getRegisterCount(String time);
}
