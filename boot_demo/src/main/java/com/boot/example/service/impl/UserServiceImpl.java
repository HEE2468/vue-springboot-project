package com.boot.example.service.impl;

import com.boot.example.dao.UserDao;
import com.boot.example.entity.User;
import com.boot.example.service.UserService;
import com.boot.example.utils.MD5Util;
import com.boot.example.utils.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    public PageObject<User> findAllUsers(String name, Integer page, Integer rows) throws ParseException {

        PageObject<User> pageObject = new PageObject<>();
        List<User> userList = userDao.findAllUsers(name,(page-1)*rows,rows);
        pageObject.setRows(userList);
        pageObject.setPageTotal(userDao.countAllUsers());
        pageObject.setCurrentPage(page);

        return pageObject;
    }

    @Override
    public User login(User user) {
        // 将密码进行MD5加密
        String md5Before = MD5Util.getMD5_32(user.getPassword());
        user.setPassword(md5Before);
        return userDao.login(user);
    }

    @Override
    public Integer userStateChanged(User user) {
        return userDao.userStateChanged(user);
    }

    @Override
    public Integer addUserByUserInfo(User user) {
        user.setId(UUID.randomUUID().toString());
        return userDao.addUserByUserInfo(user);
    }

    @Override
    public Integer updateUserByUserInfo(User user) {
        return userDao.updateUserByUserInfo(user);
    }
}
