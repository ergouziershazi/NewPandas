package com.newpandas.activity.hudong;

import com.newpandas.base.BasePresenter;
import com.newpandas.base.BaseView;
import com.newpandas.model.entity.HudongListBean;

/**
 * Created by 联想 on 2017/7/30.
 */

public interface HudongContract {
    interface view extends BaseView<presenter>{
        void showListBean(HudongListBean hudongListBean);
    }
    interface presenter extends BasePresenter{

    }
}
