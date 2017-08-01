package com.newpandas.model.biz;

import com.newpandas.model.entity.UpDate;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by Nicky on 2017/8/2.
 */

public interface MainModel extends BaseModel {
    void getUpDates(NetWorkCallBack<UpDate> callBack);
}
