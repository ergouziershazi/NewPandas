package com.newpandas.model.biz;

import com.newpandas.model.entity.VideoSet;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by Nicky on 2017/8/2.
 */

public interface CultureVideoModel extends BaseModel {
    void getVideoSet(String vsid,NetWorkCallBack<VideoSet> callBack);
}
