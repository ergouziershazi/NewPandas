package com.newpandas.model.biz;

import android.os.Bundle;

import com.newpandas.model.entity.EmailRegister;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by 联想 on 2017/7/31.
 */

public interface EmailRegisterModel {
    void loadImgCode(NetWorkCallBack<Bundle> callback);
    void register(String mailAdd, String passWd, String verificationCode, String cookie, NetWorkCallBack<EmailRegister> callback);
}
