package com.newpandas.ui.livechina;

import com.newpandas.base.BasePresenter;
import com.newpandas.base.BaseView;
import com.newpandas.model.entity.LiveChina;
import com.newpandas.model.entity.LiveInfo;

/**
 * Created by Nicky on 2017/7/29.
 */

public interface LiveChinaContract {
    interface View extends BaseView<Presenter>{
        void setResult(LiveChina liveChina);
        void setInfoResult(LiveInfo liveInfo);
    }

    interface Presenter extends BasePresenter{
        void onLoadData(String url);
    }
}
