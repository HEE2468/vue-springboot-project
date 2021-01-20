package com.boot.example.controller;

import com.boot.example.entity.Menu;
import com.boot.example.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HEE
 * @date 2020/11/23
 * @description
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public Map<String,Object> findAllMenu(){

        Map<String,Object> map = new HashMap<>();
        List<Menu> menus = menuService.findAllMenu();
        if(menus.size()!=0){
            map.put("data",menus);
            map.put("code",200);
            map.put("message","数据查询成功!");
        }else{
            map.put("data",null);
            map.put("code",500);
            map.put("message","数据查询失败!");
        }
        return map;
    }

}
