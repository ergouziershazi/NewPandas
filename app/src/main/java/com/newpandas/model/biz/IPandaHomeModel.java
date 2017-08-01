package com.newpandas.model.biz;

import com.newpandas.model.entity.CcTvForBean;
import com.newpandas.model.entity.LightChinaBean;
import com.newpandas.model.entity.PandaEyeBean;
import com.newpandas.model.entity.PandaHome;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by yan on 2017/7/28.
 */

public interface IPandaHomeModel extends BaseModel{
    void loadHomeList(NetWorkCallBack<PandaHome> callback);
    void loadCcTv(String url,NetWorkCallBack<CcTvForBean> callBack);
    void loadLightChina(String url, NetWorkCallBack<LightChinaBean> callBack);
    void loadPandaEye(String url, NetWorkCallBack<PandaEyeBean> callBack);
}
