package com.boot.example.service;

import com.boot.example.entity.User;
import com.boot.example.utils.PageObject;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author shenwenwen
 * @date 2020/9/2
 * @description
 */
public interface UserService {

    PageObject<User> findAllUsers(String name, Integer page, Integer rows) throws ParseException;

    User login(User user);

    Integer userStateChanged(User user);

    Integer addUserByUserInfo(User user);

    Integer updateUserByUserInfo(User user);
}
