package com.ep.energy.db.model;

/**
 * Created by zhangxiaohui on 2017/10/12.
 */

public class PlateNumberInfo {
    private String city_name;
    private String city_short_name;
    private String city_plate_letter;
    private String city_province;
    private String city_remark;
    private String city_plate_color;
    private String city_story;

    public String getCity_story() {
        return city_story == null ? "" : city_story;
    }

    public void setCity_story(String city_story) {
        this.city_story = city_story;
    }

    public String getCity_name() {
        return city_name == null ? "" : city_name;
    }

    public String getCity_short_name() {
        return city_short_name == null ? "" : city_short_name;
    }

    public String getCity_plate_letter() {
        return city_plate_letter == null ? "" : city_plate_letter;
    }

    public String getCity_province() {
        return city_province == null ? "" : city_province;
    }

    public String getCity_remark() {
        return city_remark == null ? "" : city_remark;
    }

    public String getCity_plate_color() {
        return city_plate_color == null ? "" : city_plate_color;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public void setCity_short_name(String city_short_name) {
        this.city_short_name = city_short_name;
    }

    public void setCity_plate_letter(String city_plate_letter) {
        this.city_plate_letter = city_plate_letter;
    }

    public void setCity_province(String city_province) {
        this.city_province = city_province;
    }

    public void setCity_remark(String city_remark) {
        this.city_remark = city_remark;
    }

    public void setCity_plate_color(String city_plate_color) {
        this.city_plate_color = city_plate_color;
    }
}
