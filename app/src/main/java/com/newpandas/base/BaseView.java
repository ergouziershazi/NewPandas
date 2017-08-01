package com.newpandas.base;

/**
 * Created by yan on 2017/7/27.
 */

public interface BaseView<T> {
    void showProgress();
    void dimissProgress();
    void showMessage(String msg);
    void setPresenter(T t);
}
