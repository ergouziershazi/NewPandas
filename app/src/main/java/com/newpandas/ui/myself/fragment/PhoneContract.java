package com.newpandas.ui.myself.fragment;

import android.graphics.Bitmap;

import com.newpandas.base.BasePresenter;
import com.newpandas.base.BaseView;

/**
 * Created by 联想 on 2017/8/1.
 */

public interface PhoneContract {
    interface View extends BaseView<Presenter> {
        void showImgCode(Bitmap bitmap);
        void showPhoneCode();
        void toSuccess();
    }

    interface Presenter extends BasePresenter {
        void loadPhoneCode(String phoneNumber,String imgCode);
        void phoneRegister(String phoneNumber,String pwd,String phoneCode);
    }
}
