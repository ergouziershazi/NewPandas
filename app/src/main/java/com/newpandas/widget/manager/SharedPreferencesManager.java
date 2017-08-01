package com.newpandas.widget.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.newpandas.app.App;

/**
 * Created by yan on 2017/7/27.
 */

public class SharedPreferencesManager {
   public static SharedPreferences sharedPreferences=  App.context.getSharedPreferences("user", Context.MODE_PRIVATE);
   private static SharedPreferences.Editor editor=sharedPreferences.edit();
   public static void saveUserInfor(String userName,String userIcon){
       editor.putString("userName",userName);
       editor.putString("userIcon",userIcon);
       editor.commit();
   }
}
