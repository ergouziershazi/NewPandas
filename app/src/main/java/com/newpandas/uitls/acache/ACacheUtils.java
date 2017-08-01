package com.newpandas.uitls.acache;

import com.newpandas.app.App;

/**
 * Created by 联想 on 2017/7/24.
 */

public class ACacheUtils {
    private static ACacheUtils utils;

    private ACacheUtils() {

    }
    public static ACacheUtils getUtils() {
        if(utils==null){
            synchronized (ACacheUtils.class){
                if(utils==null) {
        utils = new ACacheUtils();
                }
            }
        }
        return utils;
    }

    public void setStorage(String imageurl, String movietime, String moviename, String moviedate, String movieurl, String movieotherurl) {

    }
    public void deleteStorage(String moviename){

        ACache aCache = ACache.get(App.context);
        aCache.clear();
    }


}
