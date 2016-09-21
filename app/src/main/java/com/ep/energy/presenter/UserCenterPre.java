package com.ep.energy.presenter;

import com.ep.energy.bean.UserCenterBean;
import com.ep.energy.modelInterface.GetConstellationListner;
import com.ep.energy.viewInterface.UserCenterView;
import com.ep.energy.modelInterface.UserCenterViewInterface;
import com.ep.energy.modelInterface.imple.getConstellationImple;

/**
 * Created by zhangxiaohui on 2016/9/21.
 */
public class UserCenterPre implements UserCenterViewInterface {
    private GetConstellationListner getConstellationListner;
    private UserCenterView userCenterView;

    public UserCenterPre(UserCenterView userCenterView) {
        getConstellationListner = new getConstellationImple();
        this.userCenterView = userCenterView;
    }
    public void getconstellationData(){
        getConstellationListner.getConstellationData(userCenterView.Activity(),userCenterView.conName(),userCenterView.type(),this);
    }

    @Override
    public void startLoading() {
        userCenterView.loadingShow();
    }

    @Override
    public void success() {
        userCenterView.loadingdissmiss();
    }

    @Override
    public void fail() {
        userCenterView.loadingdissmiss();
    }

    @Override
    public void successData(UserCenterBean centerBean) {
        userCenterView.updateUi(centerBean);
    }
}
