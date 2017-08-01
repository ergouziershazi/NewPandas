package com.newpandas.ui.pandaculture;

import com.newpandas.base.BasePresenter;
import com.newpandas.base.BaseView;
import com.newpandas.model.entity.VideoSet;

/**
 * Created by Nicky on 2017/8/2.
 */

public interface CultureVideoContract {

    interface View extends BaseView<Presenter>{
        void setResult(VideoSet videoSet);
    }

    interface Presenter extends BasePresenter{

    }
}
