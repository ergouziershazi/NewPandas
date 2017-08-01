package com.newpandas.model.biz;

import com.newpandas.model.entity.LiveChina;
import com.newpandas.model.entity.LiveInfo;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by yan on 2017/7/28.
 */

public interface LiveChinaModel extends BaseModel{
    void getLiveChinaData(NetWorkCallBack<LiveChina> callBack);
    void getLiveInfoData(String url,NetWorkCallBack<LiveInfo> callBack);
}
