package com.newpandas.model.biz;

import android.os.Bundle;

import com.newpandas.model.entity.Register;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by yan on 2017/7/28.
 */

public class MaineModelImp implements IMineModel  {
    @Override
    public void loadImgCode(NetWorkCallBack<Bundle> callback) {

    }

    @Override
    public void register(String mailAdd, String passWd, String verificationCode, String cookie, NetWorkCallBack<Register> callback) {

    }
}
