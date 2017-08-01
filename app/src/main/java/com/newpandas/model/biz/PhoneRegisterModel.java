package com.newpandas.model.biz;

import android.os.Bundle;

import com.newpandas.net.callback.NetWorkCallBack;
import com.newpandas.net.callback.PhoneNetCallBack;

import java.util.Map;

/**
 * Created by 联想 on 2017/7/31.
 */

public interface PhoneRegisterModel extends BaseModel{
    void loadImgCode(NetWorkCallBack<Bundle> callback);
    void phoneCode(Map<String, String> params, Map<String, String> headers, PhoneNetCallBack callback);
    void register(Map<String, String> params, Map<String, String> headers, PhoneNetCallBack callback);
}
