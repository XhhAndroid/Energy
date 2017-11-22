package com.ep.energy.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.ep.energy.BaseActivity;
import com.ep.energy.R;
import com.ep.energy.adapter.ArtWareAdapter;
import com.ep.energy.db.DbManger;
import com.ep.energy.db.model.ArtWareData;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangxiaohui on 2017/11/17.
 */

public class ArtWareActivity extends BaseActivity {
    @Bind(R.id.listView)
    ListView listView;

    ArtWareAdapter artWareAdapter;
    List<ArtWareData> imageUrl = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artware_layout);
        ButterKnife.bind(this);

        artWareAdapter = new ArtWareAdapter(this);
        artWareAdapter.setList(imageUrl);
        listView.setAdapter(artWareAdapter);

        getData();
    }
    private void getData(){
        imageUrl.clear();
        imageUrl.addAll(DbManger.findArtInfoAll());
        artWareAdapter.notifyDataSetChanged();
    }
}
