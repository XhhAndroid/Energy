package com.ep.energy.db.model;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by zhangxiaohui on 2017/9/12.
 */

public class NewsInfo extends DataSupport {
    @Column(unique = true, defaultValue = "unknown")
    private String newsId;
    private String newsTitle;
    private String newsContent;
    private String newsUrl;
    private String thumbnail;//缩略图
    private long newsPeriod;

    public String getThumbnail() {
        return thumbnail == null ? "" : thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle == null ? "" : newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent == null ? "" : newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsUrl() {
        return newsUrl == null ? "" : newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public long getNewsPeriod() {
        return newsPeriod;
    }

    public void setNewsPeriod(long newsPeriod) {
        this.newsPeriod = newsPeriod;
    }
}
