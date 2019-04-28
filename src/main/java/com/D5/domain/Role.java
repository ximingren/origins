package com.D5.domain;

import java.io.Serializable;

public class Role implements Serializable {
    private int roles_id;
    private String place_name;
    private String menu_Id;
    private String remark;

    public int getRoles_id() {
        return roles_id;
    }

    public void setRoles_id(int roles_id) {
        this.roles_id = roles_id;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getMenu_Id() {
        return menu_Id;
    }

    public void setMenu_Id(String menu_Id) {
        this.menu_Id = menu_Id;
    }

    public String toString() {
        return "Role{" +
                "roles_id=" + roles_id +
                ", place_name='" + place_name + '\'' +
                ", menu_Id='" + menu_Id + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
