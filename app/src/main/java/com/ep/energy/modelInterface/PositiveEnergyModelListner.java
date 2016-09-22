package com.ep.energy.modelInterface;

import com.ep.energy.bean.PositivityModel;

/**
 * Created by zhangxiaohui on 2016/9/22.
 */

public interface PositiveEnergyModelListner {
    void dialogShow();
    void dialogMiss();
    void refreshFinish();
    void suceessData(PositivityModel positivityModel);
}
