package com.D5.domain;

import java.io.Serializable;

/**
 * 阈值
 */
public class Threshold  implements Serializable {
    private String name;
    private double uplimit;
    private double lowlimit;
    private double max_limit;
    private double min_limit;

    public double getLowlimit() {
        return lowlimit;
    }

    public void setLowlimit(double lowlimit) {
        this.lowlimit = lowlimit;
    }

    public double getUplimit() {
        return uplimit;
    }

    public void setUplimit(double uplimit) {
        this.uplimit = uplimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMax_limit() {
        return max_limit;
    }

    public void setMax_limit(double max_limit) {
        this.max_limit = max_limit;
    }

    public double getMin_limit() {
        return min_limit;
    }

    public void setMin_limit(double min_limit) {
        this.min_limit = min_limit;
    }

    public String toString() {
        return "Threshold{" +
                "name='" + name + '\'' +
                ", uplimit=" + uplimit +
                ", lowlimit=" + lowlimit +
                ", max_limit=" + max_limit +
                ", min_limit=" + min_limit +
                '}';
    }
}
