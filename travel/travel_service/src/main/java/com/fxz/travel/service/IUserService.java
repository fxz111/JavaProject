package com.fxz.travel.service;

import com.fxz.travel.domain.Role;
import com.fxz.travel.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    public List<UserInfo> findAll();

    public void saveUser(UserInfo userInfo) throws Exception;

    public UserInfo findById(String id) throws Exception;

    public void deleteUser(String id);

    public List<Role> findOtherRole(String id);

    public void addRoleToUser(String userId,String[] roleIds);
}
