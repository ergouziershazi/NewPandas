package com.newpandas.model.biz;

import com.newpandas.model.entity.HudongListBean;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by 联想 on 2017/7/30.
 */

public interface HudongModel extends BaseModel{
    void getHudonglistBean(NetWorkCallBack<HudongListBean> netWorkCallBack);
}
