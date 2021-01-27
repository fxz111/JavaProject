package com.fxz.travel.dao;

import com.fxz.travel.domain.Permission;
import com.fxz.travel.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",javaType = List.class, many = @Many(select = "com.fxz.travel.dao.IPermissionDao.findByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId);

    @Select("select * from role")
    public List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void saveRole(Role role);

    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",javaType = List.class, many = @Many(select = "com.fxz.travel.dao.IPermissionDao.findByRoleId"))
    })
    public Role findByRoleId(String id);

    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    public List<Permission> findOtherPermissions(String roleId);
    @Delete("delete from role where id = #{roleId}")
    public void deleteByRoleId(String roleId);
    @Delete("delete from users_role where roleId=#{roleId}")
    public void deleteUser_RoleByRoleId(String roleId);
    @Delete("delete from role_permission where roleId=#{roleId}")
    public void deleteRole_PermissionByRoleId(String roleId);




}
