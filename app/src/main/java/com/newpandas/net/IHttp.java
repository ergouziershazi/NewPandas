package com.newpandas.net;

import android.widget.ImageView;

import com.newpandas.net.callback.NetCallBack;
import com.newpandas.net.callback.NetWorkCallBack;
import com.newpandas.net.callback.PhoneNetCallBack;

import java.util.Map;

/**
 * Created by yan on 2017/7/27.
 */

public interface IHttp {
    <T> void get(String url, NetWorkCallBack<T> callback);
    <T> void get(String url, Map<String,String> params, NetWorkCallBack<T> callback);
    <T> void get(String url, Map<String,String> params, Map<String,String> headers,NetWorkCallBack<T> callback);
    <T> void loginget(String url, Map<String,String> params, Map<String,String> headers,NetCallBack<T> callback);
    <T> void post(String url, Map<String,String> params, NetWorkCallBack<T> callback);
    <T> void phonepost(String url, Map<String,String> params,Map<String, String> headers, PhoneNetCallBack callback);
    void loadImage(String url, ImageView imageView);

}
