package com.newpandas.net.callback;

/**
 * Created by 联想 on 2017/8/1.
 */

public interface PhoneNetCallBack{
    void onSuccess(String phonecode);
    void onError(int errorCode, String errorMsg);
}
