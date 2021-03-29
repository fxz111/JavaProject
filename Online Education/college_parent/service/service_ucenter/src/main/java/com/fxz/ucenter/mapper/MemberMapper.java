package com.fxz.ucenter.mapper;

import com.fxz.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author FengXZ
 * @since 2021-03-03
 */
public interface MemberMapper extends BaseMapper<Member> {

    Integer getRegisterCount(String time);
}
