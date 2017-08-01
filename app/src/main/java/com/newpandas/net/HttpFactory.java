package com.newpandas.net;

/**
 * Created by yan on 2017/7/27.
 */

public class HttpFactory {
public static IHttp create(){
   return OkHttpUtils.getInstence();
}
}
