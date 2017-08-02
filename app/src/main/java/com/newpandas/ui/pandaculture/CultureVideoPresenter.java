package com.newpandas.ui.pandaculture;

import com.newpandas.model.biz.CultureVideoImp;
import com.newpandas.model.biz.CultureVideoModel;
import com.newpandas.model.entity.VideoSet;
import com.newpandas.net.callback.NetWorkCallBack;

/**
 * Created by Nicky on 2017/8/2.
 */

public class CultureVideoPresenter implements CultureVideoContract.Presenter {

    private CultureVideoContract.View view;
    private CultureVideoModel cultureVideoModel;

    public CultureVideoPresenter(CultureVideoContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        cultureVideoModel=new CultureVideoImp();
    }

    @Override
    public void playVideo(String vsid) {
        cultureVideoModel.getVideoSet(vsid, new NetWorkCallBack<VideoSet>() {
            @Override
            public void onSuccess(VideoSet videoSet) {
                view.setResult(videoSet);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

            }
        });
    }

    @Override
    public void start() {

    }
}
