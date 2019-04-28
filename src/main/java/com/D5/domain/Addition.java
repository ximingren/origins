package com.D5.domain;

import java.io.Serializable;

/**
 * 设备的状态类
 */
public class Addition implements Serializable {
    private String name;
    private String addition = "正常";
    private String remarks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String toString() {
        return "Addition{" +
                "name='" + name + '\'' +
                ", addition='" + addition + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
