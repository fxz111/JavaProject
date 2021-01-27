package com.fxz.travel.dao;

import com.fxz.travel.domain.Traveller;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ITravellerDao {
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
    public List<Traveller> findByOrdersId(String ordersId) throws Exception;
    @Select("select * from traveller")
    List<Traveller> findAll();
    @Insert("insert into traveller(name,sex,phoneNum,credentialsType,credentialsNum,travellerType) values(#{name},#{sex},#{phoneNum},#{credentialsType},#{credentialsNum},#{travellerType})")
    void save(Traveller traveller);
    @Delete("delete from traveller where id=#{id}")
    void deleteById(String id);
    @Select("select * from traveller where name like concat(concat('%',#{name}),'%')")
    List<Traveller> findByName(String name);
    @Select("select * from traveller where id = #{id}")
    Traveller findById(String id);
    @Update("update traveller set name=#{name},sex=#{sex},phoneNum=#{phoneNum},credentialsType=#{credentialsType},credentialsNum=#{credentialsNum},travellerType=#{travellerType} where id = #{id}")
    void update(Traveller traveller);
}
