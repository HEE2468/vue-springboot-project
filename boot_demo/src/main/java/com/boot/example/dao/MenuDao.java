package com.boot.example.dao;

import com.boot.example.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author HEE
 * @date 2020/11/23
 * @description
 */
@Mapper
@Component(value = "MenuDao")
public interface MenuDao {


    List<Menu> findAllMenu();



}
