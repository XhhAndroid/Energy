package com.ep.energy.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ep.energy.BaseFragment;
import com.ep.energy.Constact;
import com.ep.energy.R;
import com.ep.energy.SharePrefrenceUtil;
import com.ep.energy.activity.OOMLogActivity;
import com.ep.energy.bean.UserCenterBean;
import com.ep.energy.dialog.SelectConstellationActivity;
import com.ep.energy.presenter.UserCenterPre;
import com.ep.energy.viewInterface.UserCenterView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/10/24.
 */
public class FrgUserCenter extends BaseFragment implements UserCenterView {
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
    @Bind(R.id.healthBar)
    ProgressBar healthBar;
    @Bind(R.id.loveBar)
    ProgressBar loveBar;
    @Bind(R.id.businessBar)
    ProgressBar businessBar;
    @Bind(R.id.moneyBar)
    ProgressBar moneyBar;

    private UserCenterPre userCenterPre;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.e_usercenter, container, false);
        ButterKnife.bind(this, layout);

        initview();

        return layout;
    }

    private void initview() {
        userCenterPre = new UserCenterPre(this);
        userCenterPre.getconstellationData();
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

    @Override
    public BaseFragment Activity() {
        return this;
    }

    @Override
    public String conName() {
        return SharePrefrenceUtil.getConstellation();
    }

    @Override
    public String type() {
        return "today";
    }

    @Override
    public void loadingShow() {
        showLoading();
    }

    @Override
    public void loadingdissmiss() {
        dissmissLoading();
    }

    @Override
    public void updateUi(UserCenterBean centerBean) {
        constellation.setText(centerBean.getName());
        healthNum.setText("健康指数:" + centerBean.getHealth());
        loveNum.setText("爱情指数:" + centerBean.getLove());
        businessNum.setText("事业指数:" + centerBean.getWork());
        financeNum.setText("财运指数:" + centerBean.getMoney());
        setProgressData(healthBar, centerBean.getHealth());
        setProgressData(loveBar, centerBean.getLove());
        setProgressData(businessBar, centerBean.getWork());
        setProgressData(moneyBar, centerBean.getMoney());

        luckNum.setText("幸运数字:" + centerBean.getNumber());
        tip.setText(centerBean.getSummary());
    }

    @OnClick({R.id.constellationImg, R.id.constellation, R.id.settingTv, R.id.handleSettingTv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.constellationImg:
                break;
            case R.id.constellation:
                startActivityForResult(new Intent(getActivity(), SelectConstellationActivity.class), Constact.SELECT_CONSTELLATION_CODE);
                break;
            case R.id.settingTv:
                startActivity(new Intent(getActivity(), OOMLogActivity.class));
                break;
            case R.id.handleSettingTv:// TODO: 2017/9/4 行为习惯设置

                break;
        }
    }

    private void setProgressData(ProgressBar progressData, String number) {
        int value = 0;
        try {
            value = Integer.parseInt(number.replace("%", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        progressData.setProgress(value);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case Constact.SELECT_CONSTELLATION_CODE:
                    userCenterPre.getconstellationData();
                    break;
            }
        }
    }
}
