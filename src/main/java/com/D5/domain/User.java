package com.D5.domain;

import java.io.Serializable;

/*
 * 用户资料类
 */
public class User implements Serializable {
    private int id;
    private int roles_id;
    private String password;
    private String mailName;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoles_id() {
        return roles_id;
    }

    public void setRoles_id(int roles_id) {
        this.roles_id = roles_id;
    }

    public String getMailName() {
        return mailName;
    }

    public void setMailName(String mailName) {
        this.mailName = mailName;
    }

    public String toString() {
        return "User{" +
                "id=" + id +
                ", roles_id=" + roles_id +
                ", password='" + password + '\'' +
                ", mailName='" + mailName + '\'' +
                '}';
    }
}
