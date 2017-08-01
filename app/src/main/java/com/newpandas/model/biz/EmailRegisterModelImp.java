package com.newpandas.model.biz;

import android.os.Bundle;

import com.newpandas.config.Urls;
import com.newpandas.model.entity.EmailRegister;
import com.newpandas.net.OkHttpUtils;
import com.newpandas.net.callback.NetWorkCallBack;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.newpandas.model.biz.BaseModel.iHttp;

/**
 * Created by 联想 on 2017/7/31.
 */

public class EmailRegisterModelImp implements EmailRegisterModel{
    @Override
    public void loadImgCode(NetWorkCallBack<Bundle> callback) {
        OkHttpUtils.getInstence().lodeImageCode(Urls.IMGCODE,callback);
    }

    @Override
    public void register(String mailAdd, String passWd, String verificationCode, String cookie, NetWorkCallBack<EmailRegister> callback) {
        String addons = null;
        String userAgent = null;
        try {
            addons = URLEncoder.encode("iPanda.Android", "UTF-8");
            userAgent = URLEncoder.encode("CNTV_APP_CLIENT_CNTV_MOBILE", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String,String> params = new HashMap<>();
        params.put("mailAdd",mailAdd);
        params.put("passWd",passWd);
        params.put("verificationCode",verificationCode);
        params.put("addons", addons);

        Map<String,String> headers = new HashMap<>();
        headers.put("Referer",addons);
        headers.put("User-Agent",userAgent);
        headers.put("Cookie",cookie);

        iHttp.get(Urls.EMAILREGISTER,params,headers,callback);
    }
}
