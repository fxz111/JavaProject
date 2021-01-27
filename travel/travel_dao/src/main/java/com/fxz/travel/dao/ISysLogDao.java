package com.fxz.travel.dao;

import com.fxz.travel.domain.SysLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISysLogDao {
    @Insert("insert into sysLog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog);
    @Select("select * from sysLog")
    public List<SysLog> findAll();
    @Delete("delete from sysLog")
    public void deleteAll();
}
