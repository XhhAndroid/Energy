package com.ep.energy.bean;

import java.util.List;

/**
 * Created by zhangxiaohui on 2016/8/22.
 */
public class PositivityModel extends BaseModel{

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        private int totalPage;
        private int ps;
        private int pno;
        /**
         * firstImg : http://zxpic.gtimg.com/infonew/0/wechat_pics_-7609075.jpg/640
         * id : wechat_20160823022039
         * source : 大众影评网
         * title : 《青云志》“田不易”戏里女儿精灵戏外女儿可爱
         * url : http://v.juhe.cn/weixin/redirect?wid=wechat_20160823022039
         * mark :
         */

        private List<ListBean> list;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getPs() {
            return ps;
        }

        public void setPs(int ps) {
            this.ps = ps;
        }

        public int getPno() {
            return pno;
        }

        public void setPno(int pno) {
            this.pno = pno;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String firstImg;
            private String id;
            private String source;
            private String title;
            private String url;
            private String mark;

            public String getFirstImg() {
                return firstImg;
            }

            public void setFirstImg(String firstImg) {
                this.firstImg = firstImg;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getMark() {
                return mark;
            }

            public void setMark(String mark) {
                this.mark = mark;
            }
        }
    }
}
