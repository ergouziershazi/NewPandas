package com.newpandas.activity;

import com.newpandas.base.BasePresenter;
import com.newpandas.base.BaseView;
import com.newpandas.model.entity.UpDate;

/**
 * Created by Nicky on 2017/8/2.
 */

public interface MainContract {
    interface View extends BaseView<Presenter>{
        void setResult(UpDate upDate);
    }

    interface Presenter extends BasePresenter{

    }
}
