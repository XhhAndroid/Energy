package com.ep.energy.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.ep.energy.EApplication;
import com.ep.energy.R;
import com.ep.energy.db.model.PlateNumberInfo;
import com.ep.energy.interfaces.imterfaceImp.LocalDbhandlerListner;
import com.zxh.q.zlibrary.utils.LogZ;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxiaohui on 2017/10/12.
 */

public class LocalDbManager {
    private Context mContext;
    OpenDbHelper dbHelper;
    SQLiteDatabase db;

    public static final String TAB_NAME = "plant_number";
    public static final String DB_NAME = "plate_num.db";

    public LocalDbManager(Context mContext) {
        this.mContext = mContext;

        dbHelper = new OpenDbHelper(mContext, DB_NAME, null, 1);
        db = dbHelper.getWritableDatabase();
    }

    public boolean tabIsExist(String tabName) {
        boolean result = false;
        if (tabName == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            String sql = "select count(*) as c from sqlite_master where type ='table' and name ='" + tabName.trim() + "' ";
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    result = true;
                }
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void imporDatabase(LocalDbhandlerListner localDbhandlerListner) {
        //存放数据库的目录
        String dirPath = "/data/data/com.ep.energy/databases";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        //数据库文件
        File file = new File(dir, DB_NAME);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            //加载需要导入的数据库
            InputStream is = EApplication.getInstance().getResources().openRawResource(R.raw.plate_num);
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffere = new byte[is.available()];
            is.read(buffere);
            fos.write(buffere);
            is.close();
            fos.close();
            if (localDbhandlerListner != null) {
                localDbhandlerListner.importDbSuccess();
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (localDbhandlerListner != null) {
                localDbhandlerListner.importDbFail();
            }
        }
    }

    private void deleteValue() {
        db.execSQL("delete from plant_number");
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public List<PlateNumberInfo> findAllData() {
        Cursor cursor = db.rawQuery("select * from plant_number", null);
        List<PlateNumberInfo> plateNumberInfoList = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                PlateNumberInfo plateNumberInfo = new PlateNumberInfo();
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String city_name = cursor.getString(cursor.getColumnIndex("city_name"));
                String city_short_name = cursor.getString(cursor.getColumnIndex("city_short_name"));
                String city_plate_letter = cursor.getString(cursor.getColumnIndex("city_plate_letter"));
                String city_province = cursor.getString(cursor.getColumnIndex("city_province"));
                String city_remark = cursor.getString(cursor.getColumnIndex("city_remark"));
                String city_plate_color = cursor.getString(cursor.getColumnIndex("city_plate_color"));

                LogZ.e("id=" + id
                        + " | city_name=" + city_name
                        + " | city_short_name=" + city_short_name
                        + " | city_plate_letter=" + city_plate_letter
                        + " | city_province=" + city_province
                        + " | city_remark=" + city_remark
                        + " | city_plate_color=" + city_plate_color);
                plateNumberInfo.setCity_name(city_name);
                plateNumberInfo.setCity_short_name(city_short_name);
                plateNumberInfo.setCity_plate_letter(city_plate_letter);
                plateNumberInfo.setCity_province(city_province);
                plateNumberInfo.setCity_remark(city_remark);
                plateNumberInfo.setCity_plate_color(city_plate_color);

                plateNumberInfoList.add(plateNumberInfo);
            }
            cursor.close();
        }
        return plateNumberInfoList;
    }

    public List<PlateNumberInfo> findByCharName(String shortName, String letter) {
        Cursor cursor = db.rawQuery("select * from plant_number where city_short_name = ? and city_plate_letter = ?", new String[]{shortName, letter});
        List<PlateNumberInfo> plateNumberInfoList = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                PlateNumberInfo plateNumberInfo = new PlateNumberInfo();
                String city_name = cursor.getString(cursor.getColumnIndex("city_name"));
                String city_short_name = cursor.getString(cursor.getColumnIndex("city_short_name"));
                String city_plate_letter = cursor.getString(cursor.getColumnIndex("city_plate_letter"));
                String city_province = cursor.getString(cursor.getColumnIndex("city_province"));
                String city_remark = cursor.getString(cursor.getColumnIndex("city_remark"));
                String city_plate_color = cursor.getString(cursor.getColumnIndex("city_plate_color"));

                plateNumberInfo.setCity_name(city_name);
                plateNumberInfo.setCity_short_name(city_short_name);
                plateNumberInfo.setCity_plate_letter(city_plate_letter);
                plateNumberInfo.setCity_province(city_province);
                plateNumberInfo.setCity_remark(city_remark);
                plateNumberInfo.setCity_plate_color(city_plate_color);

                plateNumberInfoList.add(plateNumberInfo);
            }
            cursor.close();
        }
        return plateNumberInfoList;
    }

    public void readTextByFileInputStream(LocalDbhandlerListner localDbhandlerListner) {
        try {
            InputStream is = EApplication.getInstance().getResources().openRawResource(R.raw.plate);
//            FileInputStream is = new FileInputStream(file);
            byte[] b = new byte[is.available()];
            is.read(b);
            String result = new String(b);
//            LogZ.e(result);
            subString(result);
            if (localDbhandlerListner != null) {
                localDbhandlerListner.importDbSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (localDbhandlerListner != null) {
                localDbhandlerListner.importDbFail();
            }
        }
    }

    private void subString(String oldText) {
        String provicenceInfo[] = oldText.split("#s");
        for (int i = 0; i < provicenceInfo.length; i++) {
            String outPutInfo = "";
            String subString = provicenceInfo[i];
            String provienceNameInfo[] = subString.split("#e");
            String provicenceName = provienceNameInfo[0];
            String provienceAllName = provicenceName.substring(0, provicenceName.indexOf("（"));
            String provienceChar = provicenceName.substring(provicenceName.indexOf("（") + 1, provicenceName.indexOf("）"));
            String shortNameArray = provienceNameInfo[1];
            String shortName[] = shortNameArray.split("，");

            outPutInfo = provienceAllName.trim() + "/" + provienceChar.trim();
            for (String shorN : shortName) {
                String remark[] = shorN.split(" ");
                String charIndex = remark[0].trim();
                outPutInfo = outPutInfo + "/" + cutChar(charIndex) + "-" + remark[1].trim();

                LogZ.e(outPutInfo);
                ContentValues contentValues = new ContentValues();
                contentValues.put("city_name", provienceAllName.trim());
                contentValues.put("city_short_name", provienceChar.trim());
                contentValues.put("city_plate_letter", cutChar(charIndex));
                contentValues.put("city_province", provienceAllName.trim());
                contentValues.put("city_remark", remark[1].trim());
                contentValues.put("city_plate_color", "#2882e0");
                db.insert("plant_number", null, contentValues);
            }
        }
    }

    private String cutChar(String str) {
        return str.replaceAll("[(\\u4e00-\\u9fa5)]", "");
    }

    private void readTextByInputStream() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String readline = "";
            StringBuffer sb = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                System.out.println("readline:" + readline);
                sb.append(readline);
            }
            br.close();
            System.out.println("读取成功：" + sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exportDb() {
        String dbName = DB_NAME;
        // File dataDirectory = Environment.getDataDirectory();
        File databaseFile = EApplication.getInstance().getDatabasePath(dbName);
        try {
            File f = new File(Environment.getExternalStorageDirectory() + "/" + dbName);//这里的参数是数据库的名字
            FileOutputStream fs = new FileOutputStream(f);
            FileInputStream input = new FileInputStream(databaseFile);
            int len = -1;
            byte[] buffer = new byte[1024];
            while ((len = input.read(buffer, 0, 1024)) > 0) {
                fs.write(buffer, 0, len);
            }
            fs.close();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
