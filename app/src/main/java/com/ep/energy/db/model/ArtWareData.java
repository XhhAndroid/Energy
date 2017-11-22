package com.ep.energy.db.model;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by zhangxiaohui on 2017/11/17.
 */

public class ArtWareData extends DataSupport {
    @Column(unique = true, defaultValue = "unknown")
    private String artId;
    private String artTitle;
    private String artImgUrl;

    public String getArtId() {
        return artId == null ? "" : artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    public String getArtTitle() {
        return artTitle == null ? "" : artTitle;
    }

    public void setArtTitle(String artTitle) {
        this.artTitle = artTitle;
    }

    public String getArtImgUrl() {
        return artImgUrl == null ? "" : artImgUrl;
    }

    public void setArtImgUrl(String artImgUrl) {
        this.artImgUrl = artImgUrl;
    }
}
