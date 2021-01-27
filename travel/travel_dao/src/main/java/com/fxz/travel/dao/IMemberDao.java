package com.fxz.travel.dao;

import com.fxz.travel.domain.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IMemberDao {
    @Select("select * from Member where id = #{id}")
    public Member findById(String id);
    @Select("select * from Member")
    List<Member> findAll();
    @Insert("insert into member(name,nickName,phoneNum,email) values(#{name},#{nickName},#{phoneNum},#{email})")
    void save(Member member);
    @Delete("delete from member where id=#{id}")
    void deleteById(String id);
    @Select("select * from member where name like concat(concat('%',#{name}),'%')")
    List<Member> findByName(String name);
    @Update("update member set name=#{name},nickName=#{nickName},phoneNum=#{phoneNum},email=#{email} where id = #{id}")
    void update(Member member);
}
