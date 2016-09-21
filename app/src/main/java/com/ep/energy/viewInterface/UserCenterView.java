package com.ep.energy.viewInterface;

import com.ep.energy.BaseFragment;
import com.ep.energy.bean.UserCenterBean;

/**
 * Created by zhangxiaohui on 2016/9/21.
 */
public interface UserCenterView {
    BaseFragment Activity();
    String conName();
    String type();
    void loadingShow();
    void loadingdissmiss();
    void updateUi(UserCenterBean centerBean);
}
