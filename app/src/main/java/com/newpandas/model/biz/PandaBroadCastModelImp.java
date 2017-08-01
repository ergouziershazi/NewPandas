package com.newpandas.model.biz;

import com.newpandas.config.Urls;
import com.newpandas.model.entity.PandaBroadcastListBean;
import com.newpandas.model.entity.PandabroadcastBean;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by yan on 2017/7/28.
 */

public class PandaBroadCastModelImp implements PandaBroadCastModel {
    @Override
    public void getPandabroadcastBean(NetWorkCallBack<PandabroadcastBean> netWorkCallBack) {
        iHttp.get(Urls.PANDABROADCAST,null,netWorkCallBack);
    }

    @Override
    public void getpandabroadcastListBean(String url, NetWorkCallBack<PandaBroadcastListBean> netWorkCallBack) {
        iHttp.get(url,null,netWorkCallBack);
    }

    @Override
    public void getpandabroadcastLoadmoreBean(String url, int page, NetWorkCallBack<PandaBroadcastListBean> netWorkCallBack) {
        iHttp.get(url+"&pageSize=6&page="+page,null,netWorkCallBack);
    }

}
