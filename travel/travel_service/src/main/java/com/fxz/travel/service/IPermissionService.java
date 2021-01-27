package com.fxz.travel.service;

import com.fxz.travel.domain.Permission;

import java.util.List;

public interface IPermissionService {
    public List<Permission> findAll();
    public void savePermission(Permission permission);
    public void deletePermission(String id);
}
