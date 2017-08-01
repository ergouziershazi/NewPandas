package com.newpandas.net.callback;

/**
 * Created by yan on 2017/7/27.
 */

public interface NetWorkCallBack<T> {
    void onSuccess(T t);
    void onError(int errorCode,String errorMsg);
}
