package com.newpandas.model.biz;

import com.newpandas.model.entity.LoginBean;
import com.newpandas.model.entity.NickNameBean;
import com.newpandas.net.callback.NetCallBack;
import com.newpandas.net.callback.NetWorkCallBack;

import java.util.Map;

/**
 * Created by 联想 on 2017/8/1.
 */

public interface LoginModel extends BaseModel{
    void getLoginBean(Map<String, String> params, Map<String, String> headers, NetCallBack<LoginBean> netCallBack);
    void getNickNameBean(Map<String, String> params, Map<String, String> headers, NetWorkCallBack<NickNameBean> netCallBack);
}
