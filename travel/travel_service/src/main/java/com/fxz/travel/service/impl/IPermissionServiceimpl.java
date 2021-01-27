package com.fxz.travel.service.impl;

import com.fxz.travel.dao.IPermissionDao;
import com.fxz.travel.domain.Permission;
import com.fxz.travel.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IPermissionServiceimpl implements IPermissionService {
    @Autowired
    private IPermissionDao iPermissiondao;
    @Override
    public List<Permission> findAll(){
        return iPermissiondao.findAll();
    }
    @Override
    public void savePermission(Permission permission){
        iPermissiondao.savePermission(permission);
    }
    @Override
    public void deletePermission(String id){
        iPermissiondao.deleteRole_PermissionById(id);
        iPermissiondao.deletePermissionById(id);
    }
}
