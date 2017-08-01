package com.newpandas.ui.myself.fragment;

import android.graphics.Bitmap;

import com.newpandas.base.BasePresenter;
import com.newpandas.base.BaseView;

/**
 * Created by 联想 on 2017/8/1.
 */

public interface PhoneContract {
    interface View extends BaseView<EmailContract.Presenter> {
        void showImgCode(Bitmap bitmap);
    }

    interface Presenter extends BasePresenter {

    }
}
