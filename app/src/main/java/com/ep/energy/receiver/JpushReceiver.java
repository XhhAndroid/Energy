package com.ep.energy.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.ep.energy.EApplication;
import com.ep.energy.db.DbManger;
import com.ep.energy.db.model.NewsInfo;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by zhangxiaohui on 2017/9/12.
 */

public class JpushReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
//            Logger.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
//                Logger.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
//                Logger.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
                processCustomMessage(context, bundle);

            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
//                Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知");
                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
//                Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);

            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
//                Logger.d(TAG, "[MyReceiver] 用户点击打开了通知");

                //打开自定义的Activity
//                Intent i = new Intent(context, TestActivity.class);
//                i.putExtras(bundle);
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
//                context.startActivity(i);

            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
//                Logger.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
//                Logger.w(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
            } else {
//                Logger.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
            }
        } catch (Exception e) {

        }
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, Bundle bundle) {
        if (EApplication.getInstance().isAlive) {
            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            if (!TextUtils.isEmpty(message) && !TextUtils.isEmpty(extras)) {
                if ("msg_type=addNews".equals(message)) {
                    try {
                        NewsInfo newsInfo = new NewsInfo();
                        JSONObject jsonObject = new JSONObject(extras);
                        if (jsonObject.has("newsId")) {
                            newsInfo.setNewsId((String) jsonObject.get("newsId"));
                        }
                        if (jsonObject.has("newsTitle")) {
                            newsInfo.setNewsTitle((String) jsonObject.get("newsTitle"));
                        }
                        if (jsonObject.has("newsContent")) {
                            newsInfo.setNewsContent((String) jsonObject.get("newsContent"));
                        }
                        if (jsonObject.has("newsUrl")) {
                            newsInfo.setNewsUrl((String) jsonObject.get("newsUrl"));
                        }
                        if (jsonObject.has("thumbnail")) {
                            newsInfo.setThumbnail((String) jsonObject.get("thumbnail"));
                        }
                        DbManger.saveNewsInfo(newsInfo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if ("msg_type=delNews".equals(message)) {
                    try {
                        JSONObject jsonObject = new JSONObject(extras);
                        if (jsonObject.has("newsId")) {
                            String newsid = (String) jsonObject.get("newsId");
                            DbManger.deleteNewsInfoByNewsId(newsid);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else if("msg_type=delAll".equals(message)){
                    DbManger.deleteAllData();
                }
            }
        }
    }
}
