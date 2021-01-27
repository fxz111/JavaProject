package com.fxz.travel.service;

import com.fxz.travel.domain.Member;

import java.util.List;

public interface IMemberService {
    List<Member> findAll(Integer page,Integer pageSize);

    void save(Member member);

    void deleteById(String[] ids);

    List<Member> findByName(String name);

    Member findById(String id);

    void update(Member member);
}
