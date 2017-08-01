package com.newpandas.model.biz;

import com.newpandas.config.Urls;
import com.newpandas.model.entity.LiveChina;
import com.newpandas.model.entity.LiveInfo;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by yan on 2017/7/28.
 */

public class LiveChinaModelImp implements LiveChinaModel {
    @Override
    public void getLiveChinaData(NetWorkCallBack<LiveChina> callBack) {
        iHttp.get(Urls.LIVECHINA,null,callBack);
    }

    @Override
    public void getLiveInfoData(String url,NetWorkCallBack<LiveInfo> callBack) {
        iHttp.get(url,null,callBack);
    }
}
