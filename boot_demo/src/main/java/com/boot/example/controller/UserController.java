package com.boot.example.controller;

import com.boot.example.entity.User;
import com.boot.example.service.UserService;
import com.boot.example.utils.PageObject;
import com.boot.example.utils.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shenwenwen
 * @date 2020/9/2
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/findAllUsers", method = RequestMethod.POST)
    public Map<String,Object> findAllUsers(@RequestBody Map<String,Object> object) throws ParseException {
        Map<String,Object> map = new HashMap<>();
        PageObject<User> userList = userService.findAllUsers(String.valueOf(object.get("name")),Integer.valueOf(object.get("page")+""),Integer.valueOf(object.get("rows")+""));
        if(userList!=null){
           map.put("code",200);
           map.put("msg","数据查询成功!");
           map.put("data",userList);
       }else {
            map.put("code",200);
            map.put("msg","数据查询为空!");
            map.put("data",null);
       }
        return map;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {
        User resultUser = userService.login(user);
        if(resultUser == null){
            return "failure";
        }
       return "success";
    }

    @RequestMapping(value = "/userStateChanged", method = RequestMethod.POST)
    public ResultObject userStateChanged(@RequestBody User user){
        ResultObject resultObject = new ResultObject();
        if(user.getId() == null || "".equals(user.getId())){
            resultObject.setCode(503);
            resultObject.setMsg("参数不能为空!");
        }

        Integer resultInt = userService.userStateChanged(user);

        if(resultInt > 0){
            resultObject.setCode(200);
            resultObject.setMsg("查询成功!");
        }else{
            resultObject.setCode(503);
            resultObject.setMsg("数据更新失败!");
        }
        return resultObject;
    }

    @RequestMapping(value = "/addUserByUserInfo", method = RequestMethod.POST)
    public ResultObject addUserByUserInfo(@RequestBody User user){
        ResultObject resultObject = new ResultObject();

        Integer resultInt = userService.addUserByUserInfo(user);
        if(resultInt > 0){
            resultObject.setCode(200);
            resultObject.setMsg("添加成功!");
        }else{
            resultObject.setCode(503);
            resultObject.setMsg("添加失败!");
        }
        return resultObject;
    }


    @RequestMapping(value = "/updateUserByUserInfo", method = RequestMethod.POST)
    public ResultObject updateUserByUserInfo(@RequestBody User user){
        ResultObject resultObject = new ResultObject();
        Integer resultInt = userService.updateUserByUserInfo(user);
        if(resultInt > 0){
            resultObject.setCode(200);
            resultObject.setMsg("修改成功!");
        }else{
            resultObject.setCode(503);
            resultObject.setMsg("修改失败!");
        }
        return resultObject;
    }
}
