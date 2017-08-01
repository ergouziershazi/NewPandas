package com.newpandas.model.biz;

import com.newpandas.model.entity.pandalive.MultipleBean;
import com.newpandas.model.entity.pandalive.OtherDetilsBean;
import com.newpandas.model.entity.pandalive.PandaEyeTabBean;
import com.newpandas.model.entity.pandalive.PlayBean;
import com.newpandas.model.entity.pandalive.SendingBean;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by yan on 2017/7/30.
 */

public interface PandaEyeModel extends BaseModel{
    void getTabtitleData(NetWorkCallBack<PandaEyeTabBean> callBack);
    //   直播 其他 tab  类
    void getOtherDetailBean(String vsid, String n, String serviceId, String o, String of, String p,NetWorkCallBack<OtherDetilsBean> callbacks);
    void getPandaPlayBean(String pid,NetWorkCallBack<PlayBean> callbacks);
    //    多视角直播
    void getMultipleBean(NetWorkCallBack<MultipleBean> callbacks);
    //   直播首页 类
    void getSendingBean(NetWorkCallBack<SendingBean> callbacks);
}
