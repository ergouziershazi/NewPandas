package com.newpandas.model.biz;

import com.newpandas.model.entity.CultureVideo;
import com.newpandas.model.entity.PandaCulture;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by yan on 2017/7/28.
 */

public interface PandaCulturModel extends BaseModel{
    void getPandaCultureData(NetWorkCallBack<PandaCulture> callBack);
    void getCultureVideoInfo(String id,NetWorkCallBack<CultureVideo> callBack);
}
