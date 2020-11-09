package com.boot.example.dao;

import com.boot.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author shenwenwen
 * @date 2020/9/2
 * @description
 */
@Mapper
@Component(value = "UserDao")
public interface UserDao {

    List<User> findAllUsers();

    User login(User user);

}
