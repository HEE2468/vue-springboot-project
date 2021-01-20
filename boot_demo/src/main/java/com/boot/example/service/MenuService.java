package com.boot.example.service;

import com.boot.example.entity.Menu;

import java.util.List;

/**
 * @author HEE
 * @date 2020/11/23
 * @description
 */
public interface MenuService {
    List<Menu> findAllMenu();
}
