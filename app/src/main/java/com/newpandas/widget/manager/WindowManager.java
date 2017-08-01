package com.newpandas.widget.manager;

import com.newpandas.app.App;

/**
 * Created by yan on 2017/7/28.
 */

public class WindowManager {
  public WindowManager(){

  }
  public static WindowManager windowManager;
  public static WindowManager getInstance(){
      if(windowManager==null){
          synchronized (WindowManager.class){
              if(windowManager==null){
                  windowManager=new WindowManager();
              }
          }
      }
     return windowManager;
  }
public void getLength(){
    android.view.WindowManager wm= App.context.getWindowManager();
    int width = wm.getDefaultDisplay().getWidth();
    int height = wm.getDefaultDisplay().getHeight();

}
}
