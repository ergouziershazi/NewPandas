package com.newpandas.model.biz;

import com.newpandas.config.Urls;
import com.newpandas.model.entity.LoginBean;
import com.newpandas.model.entity.NickNameBean;
import com.newpandas.net.callback.NetCallBack;
import com.newpandas.net.callback.NetWorkCallBack;

import java.util.Map;

/**
 * Created by 联想 on 2017/8/1.
 */

public class LoginModelImp implements LoginModel{
    @Override
    public void getLoginBean(Map<String, String> params, Map<String, String> headers, NetCallBack<LoginBean> netCallBack) {
        iHttp.loginget(Urls.FORM,params,headers,netCallBack);
    }

    @Override
    public void getNickNameBean(Map<String, String> params, Map<String, String> headers, NetWorkCallBack<NickNameBean> netCallBack) {
        iHttp.get(Urls.GETNiCkNAME,params,headers,netCallBack);
    }
}
