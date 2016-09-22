package com.ep.energy.viewInterface;

import com.ep.energy.BaseFragment;
import com.ep.energy.bean.PositivityModel;

/**
 * Created by zhangxiaohui on 2016/9/22.
 */

public interface PositiveEnergyView {
    BaseFragment Activity();
    int curPage();
    int pageSize();
    boolean refresh();
    void dialogShow();
    void dialogMiss();
    void refreshFinish();
    void suceessData(PositivityModel positivityModel);
}
