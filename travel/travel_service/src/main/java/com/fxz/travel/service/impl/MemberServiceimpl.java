package com.fxz.travel.service.impl;

import com.fxz.travel.dao.IMemberDao;
import com.fxz.travel.domain.Member;
import com.fxz.travel.service.IMemberService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceimpl implements IMemberService {
    @Autowired
    IMemberDao memberDao;
    @Override
    public List<Member> findAll(Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        return memberDao.findAll();
    }

    @Override
    public void save(Member member) {
        memberDao.save(member);
    }

    @Override
    public void deleteById(String[] ids) {
        for (String id:ids) {
            memberDao.deleteById(id);
        }

    }

    @Override
    public List<Member> findByName(String name) {
        return memberDao.findByName(name);
    }

    @Override
    public Member findById(String id) {
        return memberDao.findById(id);
    }

    @Override
    public void update(Member member) {
        memberDao.update(member);
    }
}
