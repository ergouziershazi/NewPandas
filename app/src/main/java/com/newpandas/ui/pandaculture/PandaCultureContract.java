package com.newpandas.ui.pandaculture;

import com.newpandas.base.BasePresenter;
import com.newpandas.base.BaseView;
import com.newpandas.model.entity.CultureVideo;
import com.newpandas.model.entity.PandaCulture;

/**
 * Created by Nicky on 2017/7/28.
 */

public interface PandaCultureContract {
    interface View extends BaseView<Presenter>{
        void setResult(PandaCulture pandaCulture);
        void setVideoInfo(CultureVideo videoInfo);
    }

    interface Presenter extends BasePresenter{
        void getCultureVideo(String id);
    }
}
