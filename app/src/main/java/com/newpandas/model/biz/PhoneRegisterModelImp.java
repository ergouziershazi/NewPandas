package com.newpandas.model.biz;

import android.os.Bundle;

import com.newpandas.config.Urls;
import com.newpandas.net.OkHttpUtils;
import com.newpandas.net.callback.NetWorkCallBack;
import com.newpandas.net.callback.PhoneNetCallBack;

import java.util.Map;

/**
 * Created by 联想 on 2017/8/1.
 */

public class PhoneRegisterModelImp implements PhoneRegisterModel{
    @Override
    public void loadImgCode(NetWorkCallBack<Bundle> callback) {
        OkHttpUtils.getInstence().lodeImageCode(Urls.IMGCODE,callback);
    }

    @Override
    public void phoneCode(Map<String, String> params, Map<String, String> headers, PhoneNetCallBack callback) {
        iHttp.phonepost(Urls.PHONEYANZHENG,params,headers,callback);
    }

    @Override
    public void register(Map<String, String> params, Map<String, String> headers, PhoneNetCallBack callback) {
        iHttp.phonepost(Urls.PHONEREGISTER,params,headers,callback);
    }

}
