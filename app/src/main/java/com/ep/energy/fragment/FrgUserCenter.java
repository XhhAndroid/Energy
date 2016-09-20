package com.ep.energy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ep.energy.BaseFragment;
import com.ep.energy.R;
import com.ep.energy.fragment.presenter.UserCenterPresenter;
import com.ep.energy.fragment.uiInterface.UserCenterInterface;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/10/24.
 */
public class FrgUserCenter extends BaseFragment {

    @Bind(R.id.constellation)
    TextView constellation;
    @Bind(R.id.healthNum)
    TextView healthNum;
    @Bind(R.id.loveNum)
    TextView loveNum;
    @Bind(R.id.businessNum)
    TextView businessNum;
    @Bind(R.id.financeNum)
    TextView financeNum;
    @Bind(R.id.luckNum)
    TextView luckNum;
    @Bind(R.id.tip)
    TextView tip;
    @Bind(R.id.luckColorLin)
    LinearLayout luckColorLin;
    @Bind(R.id.constellationImg)
    ImageView constellationImg;

    private UserCenterInterface userCenterInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.e_usercenter, container, false);
        ButterKnife.bind(this, layout);

        initview();

        return layout;
    }

    private void initview() {
        userCenterInterface = new UserCenterPresenter(this);

        userCenterInterface.businessNum(businessNum);
        userCenterInterface.constellation(constellation);
        userCenterInterface.constellationImg(constellationImg);
        userCenterInterface.financeNum(financeNum);
        userCenterInterface.healthNum(healthNum);
        userCenterInterface.loveNum(loveNum);
        userCenterInterface.luckColorLin(luckColorLin);
        userCenterInterface.luckNum(luckNum);
        userCenterInterface.tip(tip);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
