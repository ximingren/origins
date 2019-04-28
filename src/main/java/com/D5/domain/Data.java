package com.D5.domain;

import java.io.Serializable;

/**
 * 数据类
 */
public class Data implements Serializable {
    private int id;
    private int node_num;
    private int package_num;
    private String PH;
    private String DO;
    private String receive_time;
    private String salt;
    private String COND;
    private String tem;
    private String NH;

    public String getNH() {
        return NH;
    }

    public void setNH(String NH) {
        this.NH = NH;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNode_num() {
        return node_num;
    }

    public void setNode_num(int node_num) {
        this.node_num = node_num;
    }

    public int getPackage_num() {
        return package_num;
    }

    public void setPackage_num(int package_num) {
        this.package_num = package_num;
    }

    public String getPH() {
        return PH;
    }

    public void setPH(String PH) {
        this.PH = PH;
    }

    public String getDO() {
        return DO;
    }

    public void setDO(String DO) {
        this.DO = DO;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCOND() {
        return COND;
    }

    public void setCOND(String COND) {
        this.COND = COND;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getReceive_time() {
        return receive_time;
    }

    public void setReceive_time(String receive_time) {
        this.receive_time = receive_time;
    }

    public String toString() {
        return "Data{" +
                "id=" + id +
                ", node_num=" + node_num +
                ", package_num=" + package_num +
                ", PH='" + PH + '\'' +
                ", DO='" + DO + '\'' +
                ", receive_time='" + receive_time + '\'' +
                ", salt='" + salt + '\'' +
                ", COND='" + COND + '\'' +
                ", tem='" + tem + '\'' +
                ", NH='" + NH + '\'' +
                '}';
    }
}
