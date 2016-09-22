package com.ep.energy.modelInterface;

import com.ep.energy.BaseFragment;

/**
 * Created by zhangxiaohui on 2016/9/22.
 */

public interface getEnergyListner {
    void getEnergyData(BaseFragment baseFragment, boolean fresh, int curPage, int PageSize, PositiveEnergyModelListner positiveEnergyView);
}
