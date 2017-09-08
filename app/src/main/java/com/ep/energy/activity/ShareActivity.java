package com.ep.energy.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ep.energy.BaseActivity;
import com.ep.energy.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangxiaohui on 2017/9/4.
 */

public class ShareActivity extends Activity {
    ShareAction shareAction;

    private String shareContext = "";
    private String shareTitle = "";
    private String shareTargetUrl = "";

    public static final String SHARECONTEXT = "shareContext";
    public static final String SHARETITLE = "shareTitle";
    public static final String SHARETARGETURL = "shareTargetUrl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_layout);
        ButterKnife.bind(this);

        shareTitle = getIntent().getStringExtra(SHARETITLE);
        shareContext = getIntent().getStringExtra(SHARECONTEXT);
        shareTargetUrl = getIntent().getStringExtra(SHARETARGETURL);

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher);
        UMImage image = new UMImage(ShareActivity.this, bitmap);
        shareAction = new ShareAction(ShareActivity.this);
        UMWeb web2 = new UMWeb(shareTargetUrl);
        web2.setTitle(shareTitle);
        web2.setDescription(shareContext);
        web2.setThumb(image);
        shareAction.withText(shareContext)
                .withMedia(web2)
                .setCallback(umShareListener);
    }

    @OnClick({R.id.shareQQ, R.id.shareQZone,R.id.shareWX, R.id.shareCircle, R.id.shareSina})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shareQQ:
                shareAction.setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .share();
                break;
            case R.id.shareQZone:
                shareAction.setPlatform(SHARE_MEDIA.QZONE)//传入平台
                        .share();
                break;
            case R.id.shareWX:
                shareAction.setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                        .share();
                break;
            case R.id.shareCircle:
                shareAction.setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                        .share();
                break;
            case R.id.shareSina:
                shareAction.setPlatform(SHARE_MEDIA.SINA)//传入平台
                        .share();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 分享回调监听器
     */
    UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            Log.e("", "");
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Log.e("", "");
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Log.e("", "");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Log.e("", "");
        }
    };
    /**
     * 授权回调监听器
     */
    UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };
}
