package com.newpandas.ui.myself;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by 联想 on 2017/8/2.
 */

public class PersonalActivity extends BaseActivity {
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
                ToastManager.showToast("选择头像");
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
}
