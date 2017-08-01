package com.newpandas.ui.livechina;

import com.newpandas.model.biz.LiveChinaModel;
import com.newpandas.model.biz.LiveChinaModelImp;
import com.newpandas.model.entity.LiveChina;
import com.newpandas.model.entity.LiveInfo;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by Nicky on 2017/7/29.
 */

public class LiveChinaPresenter implements LiveChinaContract.Presenter {

    private LiveChinaContract.View view;
    private LiveChinaModel liveChinaModel;

    public LiveChinaPresenter(LiveChinaContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        liveChinaModel=new LiveChinaModelImp();
    }

    @Override
    public void start() {
        liveChinaModel.getLiveChinaData(new NetWorkCallBack<LiveChina>() {
            @Override
            public void onSuccess(LiveChina liveChina) {
                view.setResult(liveChina);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                view.showMessage(errorMsg);
            }
        });
    }

    @Override
    public void onLoadData(String url) {
        liveChinaModel.getLiveInfoData(url, new NetWorkCallBack<LiveInfo>() {
            @Override
            public void onSuccess(LiveInfo liveInfo) {
                view.setInfoResult(liveInfo);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                view.showMessage(errorMsg);
            }
        });
    }
}
