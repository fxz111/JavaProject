package com.fxz.travel.dao;

import com.fxz.travel.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface
IProductDao {
    @Select("select * from product")
    public List<Product> findAll();
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void save(Product product);
    @Select("select * from product where id = #{id}")
    public Product findById(String id);
    @Select("select * from product where productName like concat(concat('%',#{productName}),'%')")
    public List<Product> findByName(String productName);
    @Delete("delete from product where id = #{id}")
    public void deleteById(String id);
    @Update("update product set productNum=#{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where id = #{id}")
    public void update(Product product);
    @Update("update product set productStatus=1 where id=#{id}")
    void openById(String id);
    @Update("update product set productStatus=0 where id=#{id}")
    void closeById(String id);
}
