package com.boot.example.entity;

import org.omg.CORBA.DATA_CONVERSION;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author shenwenwen
 * @date 2020/9/2
 * @description
 */
public class User {

    private String id;

    private String username;

    private String password;

    private Integer isExist;

    private String sex;

    private String telephone;

    private Date registerDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsExist() {
        return isExist;
    }

    public void setIsExist(Integer isExist) {
        this.isExist = isExist;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
