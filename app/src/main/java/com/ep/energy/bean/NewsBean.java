package com.ep.energy.bean;

import java.io.Serializable;

/**
 * Created by jkt-yf-002 on 2015/10/29.
 */
public class NewsBean implements Serializable{
    public String gravity;
    public String text;
    public String time;
    public String source;
    public String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
