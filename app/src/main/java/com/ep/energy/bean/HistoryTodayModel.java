package com.ep.energy.bean;

import java.util.List;

/**
 * @author Created by zhangxiaohui on 2018/1/26.
 */

public class HistoryTodayModel extends BaseModel {
    private int showapi_res_code;// 0,
    private String showapi_res_error;// "",
    private Body showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error == null ? "" : showapi_res_error;
    }

    public Body getShowapi_res_body() {
        return showapi_res_body;
    }

    public class Body{
        private int ret_code;
        private List<HistoryEntity> list;

        public int getRet_code() {
            return ret_code;
        }

        public List<HistoryEntity> getList() {
            return list;
        }
    }
    public class HistoryEntity{
        private String title;//"世界卫生组织宣布已经成功控制SARS",
        private String month;//7,
        private String img;//"http://img.showapi.com/201107/5/099368663.jpg",
        private String year;//"2003",
        private String day;//5

        public String getTitle() {
            return title == null ? "" : title;
        }

        public String getMonth() {
            return month == null ? "" : month+"/";
        }

        public String getImg() {
            return img == null ? "" : img;
        }

        public String getYear() {
            return year == null ? "" : year+"/";
        }

        public String getDay() {
            return day == null ? "" : day;
        }
    }
}
