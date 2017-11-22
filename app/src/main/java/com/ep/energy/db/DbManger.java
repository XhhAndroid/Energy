package com.ep.energy.db;

import com.ep.energy.db.model.ArtWareData;
import com.ep.energy.db.model.NewsInfo;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxiaohui on 2017/9/12.
 */

public class DbManger {

    /**
     * 保存单条数据
     *
     * @param newsInfo
     */
    public static void saveNewsInfo(NewsInfo newsInfo) {
        boolean saveState = newsInfo.save();
    }

    public static void saveArtWare(ArtWareData artWareData){
        artWareData.save();
    }

    /**
     * 保存多条数据
     *
     * @param newsInfoList
     */
    public static void saveNewsInfo(List<NewsInfo> newsInfoList) {
        for (NewsInfo newsInfo : newsInfoList) {
            newsInfo.save();
        }
    }

    /**
     * 根据id删除数据
     *
     * @param value
     */
    public static void deleteNewsInfoByNewsId(String value) {
        DataSupport.deleteAll(NewsInfo.class, "newsId = ? ", value);
    }

    /**
     * 更新数据
     */
    public static void updateNewsInfo() {
        NewsInfo bean = DataSupport.find(NewsInfo.class, 1);
        bean.setNewsTitle("654");
        bean.save();
    }

    /**
     * 清空数据库
     */
    public static void deleteAllData() {
        DataSupport.deleteAll(NewsInfo.class);
    }

    public static NewsInfo findNewsInfoById() {
        return DataSupport.find(NewsInfo.class, 1);
    }

    public static ArrayList<NewsInfo> findNewsInfoAll() {
        return (ArrayList<NewsInfo>) DataSupport.findAll(NewsInfo.class);
    }
    public static ArrayList<ArtWareData> findArtInfoAll() {
        return (ArrayList<ArtWareData>) DataSupport.findAll(ArtWareData.class);
    }
}
