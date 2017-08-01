package com.newpandas.model.biz;

import com.newpandas.config.Urls;
import com.newpandas.model.entity.pandalive.MultipleBean;
import com.newpandas.model.entity.pandalive.OtherDetilsBean;
import com.newpandas.model.entity.pandalive.PandaEyeTabBean;
import com.newpandas.model.entity.pandalive.PlayBean;
import com.newpandas.model.entity.pandalive.SendingBean;
import com.newpandas.net.callback.NetWorkCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yan on 2017/7/30.
 */

public class PandaEyeModelImp implements PandaEyeModel {
    @Override
    public void getTabtitleData(NetWorkCallBack<PandaEyeTabBean> callBack) {
       iHttp.get(Urls.PANDALIVETAB,callBack);
    }

    @Override
    public void getOtherDetailBean(String vsid, String n, String serviceId, String o, String of, String p, NetWorkCallBack<OtherDetilsBean> callbacks) {
       Map<String,String> map=new HashMap<>();
        map.put("vsid",vsid);
        map.put("n",n);
        map.put("serviceId",serviceId);
        map.put("o",o);
        map.put("of",of);
        map.put("p",p);
        iHttp.get(Urls.BASEOTHERFragment,map,callbacks);
    }

    @Override
    public void getPandaPlayBean(String pid, NetWorkCallBack<PlayBean> callbacks) {
       Map<String,String> map=new HashMap<>();
        map.put("pid",pid);
        iHttp.get(Urls.PANDAVOD,map,callbacks);

    }

    @Override
    public void getMultipleBean(NetWorkCallBack<MultipleBean> callbacks) {
        iHttp.get(Urls.PANDALIVEMULTI,null,callbacks);
    }

    @Override
    public void getSendingBean(NetWorkCallBack<SendingBean> callbacks) {
        iHttp.get(Urls.PANDALIVE,null,callbacks);
    }

}
