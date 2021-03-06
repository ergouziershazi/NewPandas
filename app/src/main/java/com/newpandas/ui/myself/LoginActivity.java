package com.newpandas.ui.myself;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.newpandas.R;
import com.newpandas.base.BaseActivity;
import com.newpandas.ui.myself.login.LoginContract;
import com.newpandas.ui.myself.login.LoginPresenter;
import com.newpandas.widget.manager.JCPlayVideo;
import com.newpandas.widget.manager.SharedPreferencesManager;
import com.newpandas.widget.manager.ToastManager;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 联想 on 2017/7/28.
 * 温宇航123
 */

public class LoginActivity extends BaseActivity implements LoginContract.view,JCPlayVideo.OnCollect{
    @BindView(R.id.login_back)
    ImageButton loginBack;
    @BindView(R.id.login_register)
    TextView loginRegister;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.login_weixin)
    LinearLayout loginWeixin;
    @BindView(R.id.login_qq)
    LinearLayout loginQq;
    @BindView(R.id.login_sina)
    LinearLayout loginSina;
    @BindView(R.id.login_email)
    EditText loginEmail;
    @BindView(R.id.login_check_email)
    TextView loginCheckEmail;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    @BindView(R.id.login_check_pwd)
    TextView loginCheckPwd;
    @BindView(R.id.forget_pwd)
    TextView forgetPwd;
    @BindView(R.id.login)
    Button login;
    private LoginContract.presenter presenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        JCPlayVideo.setOnCollect(this);
        new LoginPresenter(this);
        SharedPreferences spf = SharedPreferencesManager.sharedPreferences;
        if(spf.getBoolean("state",false)){
            startActivity(new Intent(this,PersonalActivity.class));
            finish();
        }
    }


    @OnClick({R.id.login_back, R.id.register, R.id.login_weixin, R.id.login_qq, R.id.login_sina, R.id.forget_pwd, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.login_weixin:
                JCPlayVideo.startFullscreen(this,JCPlayVideo.class,"http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4","温大少");
                break;
            case R.id.login_qq:
//                UMImage image = new UMImage(this, R.mipmap.ic_launcher);//资源文件
//                UMImage thumb =  new UMImage(this, R.drawable.collect_no);
//                image.setThumb(thumb);
//                new ShareAction(this)
//                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
//                        .withText("hello")//分享内容
//                        .withMedia(image)
//                        .setCallback(new UMShareListener() {
//                            @Override
//                            public void onStart(SHARE_MEDIA share_media) {
//
//                            }
//
//                            @Override
//                            public void onResult(SHARE_MEDIA share_media) {
//
//                            }
//
//                            @Override
//                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
//
//                            }
//
//                            @Override
//                            public void onCancel(SHARE_MEDIA share_media) {
//
//                            }
//                        })//回调监听器
//                        .share();



                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.login_sina:
//                new ShareAction(this)
//                        .setPlatform(SHARE_MEDIA.SINA)//传入平台
//                        .withText("hello")//分享内容
//                        .setCallback(new UMShareListener() {
//                            @Override
//                            public void onStart(SHARE_MEDIA share_media) {
//
//                            }
//
//                            @Override
//                            public void onResult(SHARE_MEDIA share_media) {
//
//                            }
//
//                            @Override
//                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
//
//                            }
//
//                            @Override
//                            public void onCancel(SHARE_MEDIA share_media) {
//
//                            }
//                        })//回调监听器
//                        .share();


                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.SINA, umAuthListener);
                break;
            case R.id.forget_pwd:
                startActivity(new Intent(this, ForgetPwdActivity.class));
                break;
            case R.id.login:
                presenter.login(loginEmail.getText().toString().trim(),loginPwd.getText().toString().trim());
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    UMAuthListener umAuthListener=new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            ToastManager.showToast("登录成功");
            String iconurl = map.get("iconurl");
            String name = map.get("name");
            Log.e("TAG", iconurl);
            Log.e("TAG", name);
            SharedPreferencesManager.saveUserInfor(name,iconurl,true);
            startActivity(new Intent(LoginActivity.this,PersonalActivity.class));
            finish();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };



    @Override
    public void showLoginBean(String jsonID, String userID) {
        presenter.getNickname(jsonID,userID);
    }

    @Override
    public void toSuccess() {
        startActivity(new Intent(this,PersonalActivity.class));
        finish();
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void dimissProgress() {

    }

    @Override
    public void showMessage(String msg) {
        ToastManager.showToast(msg);
    }

    @Override
    public void setPresenter(LoginContract.presenter presenter) {
        this.presenter=presenter;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SplashScreen"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SplashScreen"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }



    @Override
    public void successful() {
        ToastManager.showToast("收藏成功");
//                UMImage image = new UMImage(this, R.mipmap.ic_launcher);//资源文件
//                UMImage thumb =  new UMImage(this, R.drawable.collect_no);
//                image.setThumb(thumb);
//                new ShareAction(this)
//                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
//                        .withText("hello")//分享内容
//                        .withMedia(image)
//                        .setCallback(new UMShareListener() {
//                            @Override
//                            public void onStart(SHARE_MEDIA share_media) {
//
//                            }
//
//                            @Override
//                            public void onResult(SHARE_MEDIA share_media) {
//                                JCPlayVideo.startFullscreen(LoginActivity.this,JCPlayVideo.class,"http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4","温大少");
//                            }
//
//                            @Override
//                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
//
//                            }
//
//                            @Override
//                            public void onCancel(SHARE_MEDIA share_media) {
//
//                            }
//                        })//回调监听器
//                        .share();

    }

    @Override
    public void filed() {
        ToastManager.showToast("取消收藏");
    }

    @Override
    public void share() {
        UMImage image = new UMImage(this, R.mipmap.ic_launcher);//资源文件
        UMImage thumb =  new UMImage(this, R.drawable.collect_no);
        UMVideo video = new UMVideo("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4");
        video.setTitle("This is music title");//视频的标题
        video.setThumb(thumb);//视频的缩略图
        video.setDescription("my description");//视频的描述
        new ShareAction(this).setPlatform(SHARE_MEDIA.QQ).withText("hello").withMedia(video).setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {
                                JCPlayVideo.startFullscreen(LoginActivity.this,JCPlayVideo.class,"http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4","温大少");
                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {

                            }
                        }
        ).share();
    }
}
