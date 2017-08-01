package com.newpandas.ui.myself.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.newpandas.config.Keys;
import com.newpandas.model.biz.PhoneRegisterModel;
import com.newpandas.model.biz.PhoneRegisterModelImp;
import com.newpandas.net.callback.NetWorkCallBack;
import com.newpandas.net.callback.PhoneNetCallBack;
import com.newpandas.uitls.MyLogs;

import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by 联想 on 2017/8/1.
 */

public class PhonePresenter implements PhoneContract.Presenter{
    private PhoneContract.View phoneview;
    private PhoneRegisterModel phoneRegisterModel;
    private String jsessionid;

    public PhonePresenter(PhoneContract.View phoneview) {
        this.phoneview = phoneview;
        this.phoneview.setPresenter(this);
        phoneRegisterModel=new PhoneRegisterModelImp();
    }

    @Override
    public void start() {
        phoneRegisterModel.loadImgCode(new NetWorkCallBack<Bundle>() {
            @Override
            public void onSuccess(Bundle bundle) {
                jsessionid = bundle.getString(Keys.JSESSIONID);
                byte[] byteArray = bundle.getByteArray(Keys.IMGCODE);
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                phoneview.showImgCode(bitmap);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                phoneview.showMessage(errorMsg);
            }
        });
    }

    @Override
    public void loadPhoneCode(String phoneNumber, String imgCode) {
        HashMap<String, String> bodys = new HashMap<>();
        HashMap<String, String> headers = new HashMap<>();
        bodys.put("method","getRequestVerifiCodeM");
        bodys.put("mobile", phoneNumber);
        bodys.put("verfiCodeType", "1");
        bodys.put("verificationCode",imgCode);

        try {
            headers.put("Referer", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"));
            headers.put("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));
            headers.put("Cookie",jsessionid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        phoneRegisterModel.phoneCode(bodys, headers, new PhoneNetCallBack() {
            @Override
            public void onSuccess(String phonecode) {
                MyLogs.d("TT",phonecode);
                phoneview.showPhoneCode();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                phoneview.showMessage(errorMsg);
            }
        });
    }

    @Override
    public void phoneRegister(String phoneNumber, String pwd, String phoneCode) {
        HashMap<String, String> bodys = new HashMap<>();
        HashMap<String, String> headers = new HashMap<>();

        try {
            bodys.put("method","saveMobileRegisterM");
            bodys.put("mobile",phoneNumber);
            bodys.put("verfiCode",phoneCode);
            bodys.put("passWd",pwd);
            bodys.put("verfiCod eType", "1");
            bodys.put("addons",URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"));

            headers.put("Referer", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"));
            headers.put("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        phoneRegisterModel.register(bodys, headers, new PhoneNetCallBack() {
            @Override
            public void onSuccess(String phonecode) {
                phoneview.toSuccess();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                phoneview.showMessage(errorMsg);
            }
        });
    }
}
