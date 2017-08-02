package com.newpandas.model.biz;

import com.newpandas.config.Urls;
import com.newpandas.model.entity.CcTvForBean;
import com.newpandas.model.entity.LightChinaBean;
import com.newpandas.model.entity.PandaEyeBean;
import com.newpandas.model.entity.PandaHome;
import com.newpandas.model.entity.VideoInfoBean;
import com.newpandas.net.callback.NetWorkCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yan on 2017/7/28.
 */

public class PandaHomeImp implements IPandaHomeModel {
    @Override
    public void loadHomeList(NetWorkCallBack<PandaHome> callback) {
        iHttp.get(Urls.PANDAHOME,null,callback);
    }

    @Override
    public void loadCcTv(String url, NetWorkCallBack<CcTvForBean> callBack) {
        iHttp.get(url,callBack);

    }

    @Override
    public void loadLightChina(String url, NetWorkCallBack<LightChinaBean> callBack) {
        iHttp.get(url,callBack);
    }

    @Override
    public void loadPandaEye(String url, NetWorkCallBack<PandaEyeBean> callBack) {
        iHttp.get(url,callBack);
    }

    @Override
    public void getRotationBean(String id, NetWorkCallBack<VideoInfoBean> callbacks) {
        Map<String,String> map = new HashMap<>();
        map.put("pid",id);
        iHttp.get(Urls.PLAYVIDEO,map,callbacks);
    }

    @Override
    public void getWonderfulBean(String id, NetWorkCallBack<VideoInfoBean> callbacks) {
        Map<String,String> map = new HashMap<>();
        map.put("pid",id);
        iHttp.get(Urls.PLAYVIDEO,map,callbacks);
    }

    @Override
    public void getItemBean(String id, NetWorkCallBack<VideoInfoBean> callbacks) {
        Map<String,String> map = new HashMap<>();
        map.put("pid",id);
        iHttp.get(Urls.PLAYVIDEO,map,callbacks);
    }


}
