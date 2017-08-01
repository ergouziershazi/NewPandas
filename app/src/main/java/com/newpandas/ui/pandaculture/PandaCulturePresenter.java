package com.newpandas.ui.pandaculture;

import com.newpandas.model.biz.PandaCulturModel;
import com.newpandas.model.biz.PandaCultureImp;
import com.newpandas.model.entity.CultureVideo;
import com.newpandas.model.entity.PandaCulture;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by Nicky on 2017/7/28.
 */

public class PandaCulturePresenter implements PandaCultureContract.Presenter {

    private PandaCultureContract.View view;
    private PandaCulturModel pandaCulturModel;

    public PandaCulturePresenter(PandaCultureContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        pandaCulturModel=new PandaCultureImp();
    }

    @Override
    public void start() {
        pandaCulturModel.getPandaCultureData(new NetWorkCallBack<PandaCulture>() {
            @Override
            public void onSuccess(PandaCulture pandaCulture) {
                view.setResult(pandaCulture);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                view.showMessage(errorMsg);
            }
        });
    }

    @Override
    public void getCultureVideo(String id) {
        pandaCulturModel.getCultureVideoInfo(id, new NetWorkCallBack<CultureVideo>() {
            @Override
            public void onSuccess(CultureVideo cultureVideo) {
                view.setVideoInfo(cultureVideo);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

            }
        });


    }
}
