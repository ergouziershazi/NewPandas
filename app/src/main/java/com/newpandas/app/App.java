package com.newpandas.app;

import android.app.Application;

import com.newpandas.base.BaseActivity;
import com.newpandas.uitls.MyLogs;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by yan on 2017/7/27.
 */

public class App extends Application {
    public static BaseActivity context = null;
    {
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106321296", "R4YYzxlybmY8zJSt");
        PlatformConfig.setSinaWeibo("4000939436", "40a6d00c42f7f2b67224ba33f4da97b1", "http://sina.com");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        Config.DEBUG = true;

        PushAgent mPushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                MyLogs.d("TTAG",deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
    }
}
