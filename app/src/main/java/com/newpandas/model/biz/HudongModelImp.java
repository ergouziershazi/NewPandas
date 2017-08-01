package com.newpandas.model.biz;

import com.newpandas.config.Urls;
import com.newpandas.model.entity.HudongListBean;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by 联想 on 2017/7/30.
 */

public class HudongModelImp implements HudongModel {
    @Override
    public void getHudonglistBean(NetWorkCallBack<HudongListBean> netWorkCallBack) {
        iHttp.get(Urls.HUDONG,null,netWorkCallBack);
    }
}
