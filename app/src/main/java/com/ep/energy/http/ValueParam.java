package com.ep.energy.http;

import java.io.Serializable;

/**
 * Created by zhangxiaohui on 2016/8/22.
 */
public class ValueParam implements Serializable{
    private String name;
    private String value;

    public ValueParam(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }
    public String getValue() {
        return value;
    }
}
