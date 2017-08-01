package com.newpandas.model.biz;

import com.newpandas.config.Urls;
import com.newpandas.model.entity.UpDate;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by Nicky on 2017/8/2.
 */

public class MainImp implements MainModel {

    @Override
    public void getUpDates(NetWorkCallBack<UpDate> callBack) {
        iHttp.get(Urls.VERSION,null,callBack);
    }
}
