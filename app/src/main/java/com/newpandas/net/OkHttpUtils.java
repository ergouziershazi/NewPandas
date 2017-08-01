package com.newpandas.net;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.newpandas.app.App;
import com.newpandas.config.Keys;
import com.newpandas.net.callback.NetWorkCallBack;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yan on 2017/7/27.
 */

public class OkHttpUtils implements IHttp{
    private OkHttpClient okHttpClient;
    //构造函数私有化
   public OkHttpUtils(){
       okHttpClient=new OkHttpClient.Builder().build();
   }
    public static OkHttpUtils okHttpUtils;
  public static OkHttpUtils getInstence(){
      if(okHttpUtils==null){
          synchronized (OkHttpUtils.class){
              if(okHttpUtils==null)
                  okHttpUtils=new OkHttpUtils();
          }
      }
    return okHttpUtils;

  }



    @Override
    public <T> void get(String url, final NetWorkCallBack<T> callback) {
        Request request=new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callback.onError(404,e.getMessage().toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final  String jsonData=response.body().string();
                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callback.onSuccess(getGeneric(jsonData,callback));

                    }
                });
            }
        });
    }

    @Override
    public <T> void get(String url, Map<String, String> params, final NetWorkCallBack<T> callback) {
     StringBuffer sb=new StringBuffer(url);
    if(params!=null&&params.size()>0){
     sb.append("?");
        Set<String> keys=params.keySet();
        for (String key:keys){
          String value=params.get(key);
            sb.append(key).append("=").append(value).append("&");
        }
        url=sb.deleteCharAt(sb.length()-1).toString();
    }
        Request request=new Request.Builder().url(url).build();
      okHttpClient.newCall(request).enqueue(new Callback() {
          @Override
          public void onFailure(Call call, final IOException e) {
              App.context.runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      //执行在主线程
                      callback.onError(404,e.getMessage().toString());
                  }
              });
          }

          @Override
          public void onResponse(Call call, Response response) throws IOException {
             final  String jsonData=response.body().string();
              App.context.runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      //执行在主线程
                      callback.onSuccess(getGeneric(jsonData,callback));

                  }
              });
          }
      });

    }

    @Override
    public <T> void get(String url, Map<String, String> params, Map<String, String> headers, final NetWorkCallBack<T> callback) {
        StringBuffer sb=new StringBuffer(url);
        if(params!=null&&params.size()>0){
            sb.append("?");
            Set<String> keys=params.keySet();
            for (String key:keys){
                String value=params.get(key);
                sb.append(key).append("=").append(value).append("&");
            }
            url=sb.deleteCharAt(sb.length()-1).toString();
        }
        Request.Builder builder = new Request.Builder();
      if(headers!=null&&headers.size()>0){
        Set<String> keys=headers.keySet();
          for(String key:keys){
              String value=headers.get(key);
              builder.addHeader(key,value);
          }
      }
        Request request=builder.url(url).build();
      okHttpClient.newCall(request).enqueue(new Callback() {
          @Override
          public void onFailure(Call call, final IOException e) {
              App.context.runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      //执行在主线程
                      callback.onError(404,e.getMessage().toString());
                  }
              });
          }

          @Override
          public void onResponse(Call call, Response response) throws IOException {
           final String jsonData = response.body().string();
           App.context.runOnUiThread(new Runnable() {
               @Override
               public void run() {
                   //执行在主线程
                   callback.onSuccess(getGeneric(jsonData,callback));
               }
           });
          }
      });


    }

    @Override
    public <T> void post(String url, Map<String, String> params, final NetWorkCallBack<T> callback) {
        FormBody.Builder builder=new FormBody.Builder();
       if(params!=null&&params.size()>0){
          Set<String> keys=params.keySet();
          for(String key:keys){
         String value=params.get(key);
         builder.add(key,value);
          }
      }
        Request request=new Request.Builder().url(url).post(builder.build()).build();
       okHttpClient.newCall(request).enqueue(new Callback() {
           @Override
           public void onFailure(Call call, final IOException e) {
               App.context.runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       //执行在主线程
                       callback.onError(404,e.getMessage().toString());
                   }
               });
           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {

           final String jsonData = response.body().string();
           App.context.runOnUiThread(new Runnable() {
               @Override
               public void run() {
                   //执行在主线程
                   callback.onSuccess(getGeneric(jsonData,callback));
               }
           });
           }
       });

    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        Glide.with(App.context).load(url).into(imageView);
    }


    public void lodeImageCode(String url, final NetWorkCallBack<Bundle> callBack) {
         Request request=new Request.Builder().url(url).build();
       okHttpClient.newCall(request).enqueue(new Callback() {
           @Override
           public void onFailure(Call call, final IOException e) {
               App.context.runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       callBack.onError(404,e.getMessage().toString());
                   }
               });
           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {
               byte[] bytes = response.body().bytes();
               Headers headers = response.headers();
               String jsessionId =  headers.get("Set-Cookie");
               final Bundle bundle = new Bundle();
               bundle.putString(Keys.JSESSIONID,jsessionId);
               bundle.putByteArray(Keys.IMGCODE,bytes);
                App.context.runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      //执行在主线程
                      callBack.onSuccess(bundle);
                  }
              });
           }
       });
    }
    private <T> T getGeneric(String jsonData,NetWorkCallBack<T> callBack){
        Gson gson = new Gson();
        //通过反射获取泛型的实例
        Type[] types = callBack.getClass().getGenericInterfaces();
        Type[] actualTypeArguments = ((ParameterizedType) types[0]).getActualTypeArguments();
        Type type = actualTypeArguments[0];
        T t = gson.fromJson(jsonData,type);
        return t;
    }
}
