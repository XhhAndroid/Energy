package com.zxh.q.zlibrary.bean;

import java.io.Serializable;

public class PicInfo implements Serializable {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示)
     */
    private static final long serialVersionUID = 3272861379671779288L;

    public boolean isCrop = false;

    public int aspectX;

    public int aspectY;

    public int outputX;

    public int outputY;

    public boolean isCrop() {
        return isCrop;
    }

    public void setCrop(boolean isCrop) {
        this.isCrop = isCrop;
    }

    public int getAspectX() {
        return aspectX;
    }

    public void setAspectX(int aspectX) {
        this.aspectX = aspectX;
    }

    public int getAspectY() {
        return aspectY;
    }

    public void setAspectY(int aspectY) {
        this.aspectY = aspectY;
    }

    public int getOutputX() {
        return outputX;
    }

    public void setOutputX(int outputX) {
        this.outputX = outputX;
    }

    public int getOutputY() {
        return outputY;
    }

    public void setOutputY(int outputY) {
        this.outputY = outputY;
    }
}