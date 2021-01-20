package com.boot.example.service.impl;

import com.boot.example.dao.MenuDao;
import com.boot.example.entity.Menu;
import com.boot.example.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HEE
 * @date 2020/11/23
 * @description
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> findAllMenu() {
        return menuDao.findAllMenu();
    }
}
