package com.fxz.travel.service.impl;

import com.fxz.travel.dao.IRoleDao;
import com.fxz.travel.domain.Permission;
import com.fxz.travel.domain.Role;
import com.fxz.travel.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class IRoleServiceimpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;
    @Override
    public List<Role> findAll(){
        return roleDao.findAll();
    }
    @Override
    public void saveRole(Role role){roleDao.saveRole(role);};
    @Override
    public Role findByRoleId(String id){return roleDao.findByRoleId(id);}
    @Override
    public List<Permission> findOtherPermissions(String roleId){return roleDao.findOtherPermissions(roleId);}
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for(String permissionId:permissionIds){
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
    @Override
    public void deleteRoleById(String roleId){
        roleDao.deleteUser_RoleByRoleId(roleId);
        roleDao.deleteRole_PermissionByRoleId(roleId);
        roleDao.deleteByRoleId(roleId);
    }

}
