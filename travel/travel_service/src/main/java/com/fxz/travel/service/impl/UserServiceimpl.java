package com.fxz.travel.service.impl;

import com.fxz.travel.dao.IUserDao;
import com.fxz.travel.domain.Role;
import com.fxz.travel.domain.UserInfo;
import com.fxz.travel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceimpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;

        try {
            userInfo = userDao.findByUsername(username);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        User user = new User(userInfo.getUsername(),userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));

        return user;
    }
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll(){
        return userDao.findAll();
    }

    @Override
    public void saveUser(UserInfo userInfo) throws Exception {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userDao.saveUser(userInfo);
    }
    @Override
    public UserInfo findById(String id) throws Exception{
        return userDao.findById(id);
    }
    @Override
    public void deleteUser(String id){
        userDao.deleteUser_Role(id);
        userDao.deleteUser(id);
    }
    @Override
    public List<Role> findOtherRole(String id){
        return userDao.findOtherRole(id);
    }
    @Override
    public void addRoleToUser(String userId,String[] roleIds){
        for (String roleId:roleIds){
            userDao.addRoleToUser(userId,roleId);
        }
    }

}
