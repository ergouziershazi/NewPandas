package com.newpandas.uitls.acache;

import com.newpandas.app.App;
import com.newpandas.model.entity.CollectionDate;

import java.util.ArrayList;

/**
 * Created by 联想 on 2017/7/24.
 */

public class ACacheUtils {
    private static ACacheUtils utils;
    private ArrayList<CollectionDate> dates;

    private ACacheUtils() {
        dates = new ArrayList<>();
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
        dates.add(new CollectionDate(imageurl,movietime,moviename,moviedate,movieurl,movieotherurl));
        ACache aCache = ACache.get(App.context);
        aCache.put("array",dates);
    }
    public void deleteStorage(String moviename){
//        Storage();
//        for(int i = 0; i <dates.size() ; i++) {
//            if(dates.get(i).getMoviename().equals(moviename)){
//                dates.remove(i);
//            }
//        }
//        ACache aCache = ACache.get(App.context);
//        aCache.put("array",dates);
        ACache aCache = ACache.get(App.context);
        aCache.clear();
    }

    public ArrayList<CollectionDate> Storage(){
        ACache aCache = ACache.get(App.context);
        dates = (ArrayList<CollectionDate>) aCache.getAsObject("array");
        return dates;
    }
}
