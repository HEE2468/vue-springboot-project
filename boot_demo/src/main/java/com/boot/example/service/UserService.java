package com.boot.example.service;

import com.boot.example.entity.User;

import java.util.List;

/**
 * @author shenwenwen
 * @date 2020/9/2
 * @description
 */
public interface UserService {

    List<User> findAllUsers();

    User login(User user);
}
