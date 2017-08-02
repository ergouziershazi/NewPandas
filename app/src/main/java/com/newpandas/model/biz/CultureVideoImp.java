package com.newpandas.model.biz;

import com.newpandas.config.Urls;
import com.newpandas.model.entity.VideoSet;
import com.newpandas.net.callback.NetWorkCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nicky on 2017/8/2.
 */

public class CultureVideoImp implements CultureVideoModel {
    @Override
    public void getVideoSet(String vsid,NetWorkCallBack<VideoSet> callBack) {
        Map<String,String> map=new HashMap<String, String>();
        map.put("vsid",vsid);
        map.put("n","6");
        map.put("p","1");
        map.put("serviceId","panda");
        iHttp.get(Urls.BASEOTHERFragment,map,callBack);
    }
}
