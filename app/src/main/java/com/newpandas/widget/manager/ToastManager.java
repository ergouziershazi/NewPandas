package com.newpandas.widget.manager;

import android.widget.Toast;

import com.newpandas.app.App;

/**
 * Created by yan on 2017/7/27.
*/

public class ToastManager {
    public static void showToast(String msg){
        Toast.makeText(App.context,msg,Toast.LENGTH_LONG).show();
    }
}
