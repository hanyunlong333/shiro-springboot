package com.shiro.service;

import com.shiro.dao.UserDao;
import com.shiro.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User findByName(String name){
        return this.userDao.findByUsername(name);
    }

    public List<User> findAll(){
        return userDao.findAll();
    }
}
