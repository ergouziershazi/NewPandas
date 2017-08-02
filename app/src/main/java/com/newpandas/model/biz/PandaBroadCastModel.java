package com.newpandas.model.biz;

import com.newpandas.model.entity.PandaBroadcastListBean;
import com.newpandas.model.entity.PandabroadcastBean;
import com.newpandas.model.entity.PbVideoBean;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by yan on 2017/7/28.
 */

public interface PandaBroadCastModel extends BaseModel{
    void getPandabroadcastBean(NetWorkCallBack<PandabroadcastBean> netWorkCallBack);
    void getpandabroadcastListBean(String url,NetWorkCallBack<PandaBroadcastListBean> netWorkCallBack);
    void getpandabroadcastLoadmoreBean(String url,int page,NetWorkCallBack<PandaBroadcastListBean> netWorkCallBack);
    void getPbVideoBean(String url,NetWorkCallBack<PbVideoBean> netWorkCallBack);
}
