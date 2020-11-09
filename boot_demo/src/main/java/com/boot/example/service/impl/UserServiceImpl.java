package com.boot.example.service.impl;

import com.boot.example.dao.UserDao;
import com.boot.example.entity.User;
import com.boot.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shenwenwen
 * @date 2020/9/2
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAllUsers() {
       return userDao.findAllUsers();
    }


    @Override
    public User login(User user) {
        return userDao.login(user);
    }
}
