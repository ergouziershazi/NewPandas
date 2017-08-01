package com.newpandas.net.callback;

/**
 * Created by 联想 on 2017/8/1.
 */

public interface NetCallBack <T>{
    void onSuccess(String jsonid,T t);
    void onError(int errorCode,String errorMsg);
}
