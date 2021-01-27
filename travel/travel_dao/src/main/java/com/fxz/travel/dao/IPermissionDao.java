package com.fxz.travel.dao;

import com.fxz.travel.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {
    @Select("select * from permission where id in(select permissionId from role_permission where roleId = #{roleId})")
    public List<Permission> findByRoleId(String roleId) throws Exception;
    @Select("select * from permission")
    public List<Permission> findAll();
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    public void savePermission(Permission permission);
    @Delete("delete from permission where id = #{id}")
    public void deletePermissionById(String id);
    @Delete("delete from role_permission where permissionId = #{id}")
    public void deleteRole_PermissionById(String id);
}
