package com.ep.energy.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
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
    ListView cityNameListView;
    @Bind(R.id.previewCityName)
    TextView previewCityName;

    LocalDbManager localDbManager;
    private boolean pressed = false;

    List<PlateNumberInfo> charNameList = new ArrayList<>();
    PlateNumberAdapter charNameAdapter;

    List<PlateNumberInfo> cityNameList = new ArrayList<>();
    PlateNumberAdapter cityNameAdapter;

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
                cityNameList.clear();
                cityNameList.addAll(localDbManager.findByCharName(plateNumberInfo.getCity_short_name(), plateNumberInfo.getCity_plate_letter()));
                cityNameAdapter.notifyDataSetChanged();
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
        cityNameAdapter = new PlateNumberAdapter(this);

        shortNameListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pressed = (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE);
                return false;
            }
        });
    }

    private void initDataBase() {
        if (!localDbManager.tabIsExist(LocalDbManager.TAB_NAME)) {
            localDbManager.readTextByFileInputStream(new LocalDbhandlerListner() {
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

        cityNameAdapter.setList(cityNameList);
        cityNameListView.setAdapter(cityNameAdapter);
        cityNameAdapter.setContentType(PlateNumberAdapter.CITY_NAME);
    }
}
