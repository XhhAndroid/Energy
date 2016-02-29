package com.zxh.q.zlibrary.widget;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zxh.q.zlibrary.BaseActivity;
import com.zxh.q.zlibrary.R;
import com.zxh.q.zlibrary.bean.PicInfo;

/**
 *
 * @ClassName: ChoosePictureDialogActivity
 * @Description: TODO(调用相机或系统图库选择图片)
 * @author HePJ
 * @date 2015-9-24 下午5:09:12
 * @version 1.0
 */
public class ChoosePictureDialogActivity extends BaseActivity {

    /**
     * 标题
     */
    private HandyTextView htvTitle;

    /**
     * 内容
     */
    private HandyTextView htvMessage;

    /**
     * 底部按钮1
     */
    private Button btnFirst;

    /**
     * 底部按钮3
     */
    private Button btnSecond;

    /**
     * 存储照片的临时文件
     */
    private File tempFile = null;

    /**
     * 外部参数传递过来的图片信息对象，包括缩放比例，图片大小等
     */
    private PicInfo mPicInfo = null;

    /**
     * 外部参数：图片信息外部参数在intent中保存时使用的key
     */
    public static final String EXTRA_PIC_INFO = "pic_info";

    /**
     * 外部参数：回传调用activity的intent中保存的图片的URL
     */
    public static final String EXTRA_PIC_SAVED_URL = "pic_saved_url";

    /**
     * startActivityForResult的requestCode：相册
     */
    private static final int PHOTO_REQUEST_GALLERY = 0x001;

    /**
     * startActivityForResult的requestCode：拍照
     */
    private static final int PHOTO_REQUEST_TAKEPHOTO = 0x002;

    /**
     * startActivityForResult的requestCode：裁剪
     */
    private static final int PHOTO_REQUEST_CUT = 0x003;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    protected void initUI() {
        setContentView(R.layout.custm_dialog_generic);

        htvTitle = (HandyTextView) findViewById(R.id.htvTitle);
        htvTitle.setText(R.string.label_dialog_activity_name);

        htvMessage = (HandyTextView) findViewById(R.id.htvMessage);
        htvMessage.setText(R.string.tips_choose_open_way);

        initOper();

        String tempFileDir = Environment.getExternalStorageDirectory().getPath() + "/account/";
        File directory = new File(tempFileDir);
        if (!directory.exists()) {
            directory.mkdir();
        }

        tempFile = new File(directory, getPhotoFileName());

        Intent it = getIntent();
        Bundle bd = it.getExtras();
        if (null != bd) {
            mPicInfo = (PicInfo) bd.getSerializable(EXTRA_PIC_INFO);
        }

    }

    /**
     *
     * @Title: initOper
     * @Description: TODO(初始化按钮)
     * @return void 返回类型
     * @throws
     */
    private void initOper() {
        LinearLayout lnlBottom = (LinearLayout) findViewById(R.id.lnlBottom);
        lnlBottom.setVisibility(View.VISIBLE);

        btnFirst = (Button) findViewById(R.id.btnFirst);
        btnFirst.setVisibility(View.VISIBLE);
        btnFirst.setText(R.string.btn_gallery);
        btnFirst.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = makeCropImageIntent(Intent.ACTION_GET_CONTENT, Uri.fromFile(tempFile), "pick-pic");
                if (null == intent) {
                    return;
                }

                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
            }
        });

        btnSecond = (Button) findViewById(R.id.btnSecond);
        btnSecond.setVisibility(View.VISIBLE);
        btnSecond.setText(R.string.btn_camera);
        btnSecond.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
            }
        });
    }

    /**
     *
     * @Title: makeCropImageIntent
     * @Description: TODO(生成一个图片裁剪的intent用于发起图片裁剪)
     * @param action
     *            intent需要携带的action
     * @param uri
     *            裁剪完图片保存的URL
     * @param type
     *            类型：crop 裁剪 pick-pic
     *            获取图片，主要是适配有的机型在调用crop的时候，不能使用setDataAndType(uri, "image/*")的问题
     * @return Intent 返回类型
     * @throws
     */
    protected Intent makeCropImageIntent(String action, Uri uri, String type) {
        if (null == mPicInfo) {
            return null;
        }

        Intent intent = new Intent(action, null);

        if ("crop".equals(type)) {
            intent.setDataAndType(uri, "image/jpeg");
        } else {
            intent.setType("image/*");
        }

        intent.putExtra("crop", mPicInfo.isCrop);
        intent.putExtra("aspectX", mPicInfo.aspectX);
        intent.putExtra("aspectY", mPicInfo.aspectY);
        intent.putExtra("outputX", mPicInfo.outputX);
        intent.putExtra("outputY", mPicInfo.outputY);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("noFaceDetection", true);

        return intent;
    }

    /**
     *
     * @Title: getPhotoFileName
     * @Description: TODO(获取临时文件的名字)
     * @return String 临时文件的名称
     * @throws
     */
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss", Locale.getDefault());
        return dateFormat.format(date) + ".png";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case PHOTO_REQUEST_TAKEPHOTO:
                if (resultCode == RESULT_OK) {
                    startPhotoZoom(Uri.fromFile(tempFile));
                }

                break;

            case PHOTO_REQUEST_GALLERY:
                if (Uri.fromFile(tempFile) != null && resultCode == RESULT_OK) {
                    Intent it = new Intent();
                    it.putExtra(EXTRA_PIC_SAVED_URL, Uri.fromFile(tempFile).toString());
                    setResult(RESULT_OK, it);

                    finish();
                }
                break;

            case PHOTO_REQUEST_CUT:
                if (Uri.fromFile(tempFile) != null && resultCode == RESULT_OK) {
                    Intent it = new Intent();
                    it.putExtra(EXTRA_PIC_SAVED_URL, Uri.fromFile(tempFile).toString());
                    setResult(RESULT_OK, it);

                    finish();
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     *
     * @Title: startPhotoZoom
     * @Description: TODO(启动intent裁剪图片)
     * @param uri
     *            需要裁剪的图片的URL
     * @throws
     */
    private void startPhotoZoom(Uri uri) {
        Intent intent = makeCropImageIntent("com.android.camera.action.CROP", uri, "crop");
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
}