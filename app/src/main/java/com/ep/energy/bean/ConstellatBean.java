package com.ep.energy.bean;

import java.io.Serializable;

/**
 * Created by zhangxiaohui on 2017/5/2.
 */

public class ConstellatBean implements Serializable{
    private String name;
    private String date;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date == null ? "" : date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
