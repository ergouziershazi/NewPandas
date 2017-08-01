package com.newpandas.model.biz;

import com.newpandas.config.Urls;
import com.newpandas.model.entity.PandaCulture;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by yan on 2017/7/28.
 */

public class PandaCultureImp implements PandaCulturModel {

    @Override
    public void getPandaCultureData(NetWorkCallBack<PandaCulture> callBack) {
        iHttp.get(Urls.PANDACULTURE,null,callBack);
    }
}
