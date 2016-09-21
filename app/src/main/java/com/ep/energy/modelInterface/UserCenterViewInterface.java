package com.ep.energy.modelInterface;

import com.ep.energy.bean.UserCenterBean;

/**
 * Created by zhangxiaohui on 2016/9/21.
 */
public interface UserCenterViewInterface {
    void startLoading();
    void success();
    void fail();
    void successData(UserCenterBean centerBean);
}
