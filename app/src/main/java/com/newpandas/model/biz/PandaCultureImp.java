package com.newpandas.model.biz;

import com.newpandas.config.Urls;
import com.newpandas.model.entity.CultureVideo;
import com.newpandas.model.entity.PandaCulture;
import com.newpandas.net.callback.NetWorkCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yan on 2017/7/28.
 */

public class PandaCultureImp implements PandaCulturModel {

    @Override
    public void getPandaCultureData(NetWorkCallBack<PandaCulture> callBack) {
        iHttp.get(Urls.PANDACULTURE,null,callBack);
    }

    @Override
    public void getCultureVideoInfo(String id,NetWorkCallBack<CultureVideo> callBack) {
        Map<String,String> map=new HashMap<String, String>();
        map.put("pid",id);
        iHttp.get(Urls.PANDAVOD,map,callBack);
    }
}
