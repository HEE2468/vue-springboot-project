package com.boot.example.dao;

import com.boot.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 分页查询所有用户
     * @param offset
     * @param rows
     * @return
     */
    List<User> findAllUsers(@Param("username") String name, @Param("offset") Integer offset, @Param("rows") Integer rows);

    /**
     * 计算所有用户
     * @return
     */
    Integer countAllUsers();


    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 更新用户是否启用的状态
     * @param user
     * @return
     */
    Integer userStateChanged(User user);

    /**
     * 新增用户
     * @return
     */
    Integer addUserByUserInfo(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    Integer updateUserByUserInfo(User user);

}
