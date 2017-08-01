package com.newpandas.ui.pandalive.detilsfragment;

import com.newpandas.model.biz.PandaEyeModel;
import com.newpandas.model.biz.PandaEyeModelImp;
import com.newpandas.model.entity.pandalive.OtherDetilsBean;
import com.newpandas.model.entity.pandalive.PlayBean;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by yan on 2017/7/31.
 */

public class OtherPresenter implements OtherContract.presenter {
    OtherContract.view otherview;
    PandaEyeModel pandaEyeModel;
    public OtherPresenter( OtherContract.view otherview){
        this.otherview=otherview;
        otherview.setPresenter(this);
        this.pandaEyeModel=new PandaEyeModelImp();

    }

    @Override
    public void getOtherTab(String vsid, String n, String serviceId, String o, String of, String p) {
        pandaEyeModel.getOtherDetailBean(vsid, n, serviceId, o, of, p, new NetWorkCallBack<OtherDetilsBean>() {
            @Override
            public void onSuccess(OtherDetilsBean otherDetilsBean) {
                otherview.loadMore(otherDetilsBean);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                otherview.showMessage(errorMsg);
            }
        });
    }

    @Override
    public void getPlayVideoData(String pid) {
         pandaEyeModel.getPandaPlayBean(pid, new NetWorkCallBack<PlayBean>() {
             @Override
             public void onSuccess(PlayBean playBean) {
                 otherview.palyvideo(playBean);
             }

             @Override
             public void onError(int errorCode, String errorMsg) {
               otherview.showMessage(errorMsg);
             }
         });
    }

    @Override
    public void start() {

    }
}
