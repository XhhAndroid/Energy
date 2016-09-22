package com.ep.energy.presenter;

import com.ep.energy.bean.PositivityModel;
import com.ep.energy.modelInterface.PositiveEnergyModelListner;
import com.ep.energy.modelInterface.getEnergyListner;
import com.ep.energy.modelInterface.imple.getEnergyImple;
import com.ep.energy.viewInterface.PositiveEnergyView;

/**
 * Created by zhangxiaohui on 2016/9/22.
 */

public class PositiveEnergyPre implements PositiveEnergyModelListner {
    private PositiveEnergyView positiveEnergyView;
    private getEnergyListner getEnergyListner;


    public PositiveEnergyPre(PositiveEnergyView positiveEnergyView) {
        this.positiveEnergyView = positiveEnergyView;
        getEnergyListner = new getEnergyImple();
    }

    /**
     * 请求数据
     */
    public void getPositiveEnergyData(boolean refresh) {
        getEnergyListner.getEnergyData(positiveEnergyView.Activity(), refresh,
                positiveEnergyView.curPage(),positiveEnergyView.pageSize(),this);
    }

    @Override
    public void dialogShow() {
        positiveEnergyView.dialogShow();
    }

    @Override
    public void dialogMiss() {
        positiveEnergyView.dialogMiss();
    }

    @Override
    public void refreshFinish() {
        positiveEnergyView.refreshFinish();
    }

    @Override
    public void suceessData(PositivityModel positivityModel) {
        positiveEnergyView.suceessData(positivityModel);
    }
}
