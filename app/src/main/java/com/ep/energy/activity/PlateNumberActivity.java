package com.ep.energy.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.ep.energy.BaseActivity;
import com.ep.energy.R;
import com.ep.energy.adapter.PlateNumberAdapter;
import com.ep.energy.db.LocalDbManager;
import com.ep.energy.db.model.PlateNumberInfo;
import com.ep.energy.interfaces.imterfaceImp.BaseAdapterListner;
import com.ep.energy.interfaces.imterfaceImp.LocalDbhandlerListner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangxiaohui on 2017/10/12.
 */

public class PlateNumberActivity extends BaseActivity {
    @Bind(R.id.shortName)
    GridView shortNameListView;
    @Bind(R.id.cityName)
    TextView cityName;
    @Bind(R.id.cityStory)
    TextView cityStory;
    @Bind(R.id.previewCityName)
    TextView previewCityName;

    LocalDbManager localDbManager;
    private boolean pressed = false;

    List<PlateNumberInfo> charNameList = new ArrayList<>();
    PlateNumberAdapter charNameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plate_number_layout);
        ButterKnife.bind(this);
        localDbManager = new LocalDbManager(this);

        initAdapter();
        initDataBase();
    }

    private void initAdapter() {
        charNameAdapter = new PlateNumberAdapter(new BaseAdapterListner<PlateNumberInfo>() {
            @Override
            public void onClick0(View v, PlateNumberInfo plateNumberInfo, int position) {
                super.onClick0(v, plateNumberInfo, position);
                PlateNumberInfo plateNumberInfo1 = localDbManager.findByCharName(plateNumberInfo.getCity_short_name(), plateNumberInfo.getCity_plate_letter());
                cityName.setText(plateNumberInfo1.getCity_name() + "(" + plateNumberInfo1.getCity_remark() + ")");
                cityStory.setText(plateNumberInfo1.getCity_story());
            }

            @Override
            public void OnClick1(View v, PlateNumberInfo plateNumberInfo, int position) {
                super.OnClick1(v, plateNumberInfo, position);
                if (pressed) {
                    previewCityName.setText(plateNumberInfo.getCity_short_name() + plateNumberInfo.getCity_plate_letter());
                } else {
                    previewCityName.setText("");
                }
            }
        }, this);

        shortNameListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pressed = (event.getAction() == MotionEvent.ACTION_DOWN
                        || event.getAction() == MotionEvent.ACTION_MOVE
                        || event.getAction() == MotionEvent.ACTION_SCROLL);
                return false;
            }
        });
    }

    private void initDataBase() {
        if (!localDbManager.tabIsExist(LocalDbManager.TAB_NAME)) {
            localDbManager.imporDatabase(new LocalDbhandlerListner() {
                @Override
                public void importDbSuccess() {
                    super.importDbSuccess();
                    adapterSetting();
                }

                @Override
                public void importDbFail() {
                    super.importDbFail();
                    showToast("导入数据失败！");
                    finish();
                }
            });
        } else {
            adapterSetting();
        }
    }

    private void adapterSetting() {
        charNameList.addAll(localDbManager.findAllData());
//        shortNameListView.setNumColumns(charNameList.size());
        charNameAdapter.setList(charNameList);
        shortNameListView.setAdapter(charNameAdapter);
        charNameAdapter.setContentType(PlateNumberAdapter.CHAR_NAME);
    }
}
