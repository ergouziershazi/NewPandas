package com.newpandas.ui.myself;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.newpandas.R;
import com.newpandas.base.BaseActivity;
import com.newpandas.net.OkHttpUtils;
import com.newpandas.uitls.MyLogs;
import com.newpandas.widget.manager.SharedPreferencesManager;
import com.newpandas.widget.manager.ToastManager;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.File;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by 联想 on 2017/8/2.
 */

public class PersonalActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.personal_back)
    ImageButton personalBack;
    @BindView(R.id.login_register)
    TextView loginRegister;
    @BindView(R.id.tv_headjiantou)
    TextView tvHeadjiantou;
    @BindView(R.id.personal_headicon)
    ImageView personalHeadicon;
    @BindView(R.id.person_have_login_layout)
    RelativeLayout personHaveLoginLayout;
    @BindView(R.id.tv_nichengjiantou)
    TextView tvNichengjiantou;
    @BindView(R.id.personal_nickname)
    TextView personalNickname;
    @BindView(R.id.personal_nickname_layout)
    RelativeLayout personalNicknameLayout;
    @BindView(R.id.personal_uplogin)
    TextView personalUplogin;
    @BindView(R.id.login_out_layout)
    RelativeLayout loginOutLayout;
    private SharedPreferences spf;
    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";
    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 480;
    private static int output_Y = 480;
    private PopupWindow popupWindow;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_presonal;
    }

    @Override
    protected void init() {
        spf = SharedPreferencesManager.sharedPreferences;
        String userName = spf.getString("userName", "");
        String userIcon = spf.getString("userIcon", "");
        MyLogs.d("TGA", userName + "-----------" + userIcon);
        personalNickname.setText(userName);
        if (!userIcon.equals("")) {
            OkHttpUtils.getInstence().loadImage(userIcon, personalHeadicon);
        } else {
            personalHeadicon.setBackgroundResource(R.drawable.personal_login_head);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @OnClick({R.id.personal_back, R.id.person_have_login_layout, R.id.personal_nickname_layout, R.id.personal_uplogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_back:
//                startActivity(new Intent(this,MineActivity.class));
                finish();
                break;
            case R.id.person_have_login_layout:
                popwindow();
                break;
            case R.id.personal_nickname_layout:
                ToastManager.showToast("修改昵称");
                break;
            case R.id.personal_uplogin:
                SharedPreferencesManager.deleteUserInfor();
                UMShareAPI.get(this).deleteOauth(this, SHARE_MEDIA.SINA,umAuthListener);
                UMShareAPI.get(this).deleteOauth(this, SHARE_MEDIA.QQ,umAuthListener);
//                startActivity(new Intent(this,MineActivity.class));
                finish();
                break;
        }
    }

    UMAuthListener umAuthListener=new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            Toast.makeText(PersonalActivity.this, "成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };

    public void popwindow() {
        View view = LayoutInflater.from(this).inflate(
                R.layout.item_popwindow, null);
        popupWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

        Button btn_camera = (Button) view.findViewById(R.id.btn_camera);
        Button btn_album = (Button) view.findViewById(R.id.btn_album);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);

        btn_camera.setOnClickListener(this);
        btn_album.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {

        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }

        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(intent.getData());
                break;

            case CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                            .show();
                }

                break;

            case CODE_RESULT_REQUEST:
                if (intent != null) {

                    setImageToHeadView(intent);
                }

                break;
        }

        super.onActivityResult(requestCode, resultCode, intent);
    }
    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            personalHeadicon.setImageBitmap(photo);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_camera:
                // 从本地相册选取图片作为头像

                Intent intentFromGallery = new Intent();
                // 设置文件类型
                intentFromGallery.setType("image/*");
                intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
                break;
            case R.id.btn_album:
                // 启动手机相机拍摄照片作为头像

                Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                // 判断存储卡是否可用，存储照片文件
                if (hasSdcard()) {
                    intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                            .fromFile(new File(Environment
                                    .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
                }

                startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
                break;
            case R.id.btn_cancel:
                popupWindow.dismiss();
                break;
        }
    }

    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }
}
