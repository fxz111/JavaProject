package com.fxz.travel.service;

import com.fxz.travel.domain.Permission;
import com.fxz.travel.domain.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> findAll();
    public void saveRole(Role role);
    public Role findByRoleId(String id);
    public List<Permission> findOtherPermissions(String roleId);
    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;
    void deleteRoleById(String roleId);
}
