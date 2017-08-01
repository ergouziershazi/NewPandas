package com.newpandas.uitls;

import android.util.Log;

/**
 * Created by yan on 2017/7/27.
 */

public class MyLogs {
   private static boolean tolog=true;
   public static void d(String tag,String msg){
     if(tolog){
         Log.d(tag,msg);
     }
   }

}
