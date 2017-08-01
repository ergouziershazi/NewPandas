package com.newpandas.model.biz;

import android.os.Bundle;

import com.newpandas.model.entity.Register;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by yan on 2017/7/28.
 */

public interface IMineModel extends BaseModel{
    void loadImgCode(NetWorkCallBack<Bundle> callback);
    void register(String mailAdd,String passWd,String verificationCode,String cookie,NetWorkCallBack<Register> callback);
}
