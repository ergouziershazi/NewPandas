package com.newpandas.model.biz;

import com.newpandas.config.Urls;
import com.newpandas.model.entity.CcTvForBean;
import com.newpandas.model.entity.LightChinaBean;
import com.newpandas.model.entity.PandaEyeBean;
import com.newpandas.model.entity.PandaHome;
import com.newpandas.net.callback.NetWorkCallBack;

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


}
