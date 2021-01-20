package com.boot.example.entity;

/**
 * @author HEE
 * @date 2020/11/25
 * @description
 */
public class SubMenu {

    private String id;
    private String menuId;
    private String subMenuName;
    private String subMenuIcon;
    private String path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getSubMenuName() {
        return subMenuName;
    }

    public void setSubMenuName(String subMenuName) {
        this.subMenuName = subMenuName;
    }

    public String getSubMenuIcon() {
        return subMenuIcon;
    }

    public void setSubMenuIcon(String subMenuIcon) {
        this.subMenuIcon = subMenuIcon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
