package com.newpandas.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.newpandas.R;
import com.newpandas.activity.hudong.HuDongActivity;
import com.newpandas.base.BaseActivity;
import com.newpandas.base.BaseFragment;
import com.newpandas.model.entity.UpDate;
import com.newpandas.ui.home.HomeFragment;
import com.newpandas.ui.livechina.LiveChinaContract;
import com.newpandas.ui.livechina.LiveChinaFragment;
import com.newpandas.ui.livechina.LiveChinaPresenter;
import com.newpandas.ui.myself.MineActivity;
import com.newpandas.ui.pandabroadcast.PandaBroadCastFragment;
import com.newpandas.ui.pandaculture.PandaCultureContract;
import com.newpandas.ui.pandaculture.PandaCultureFragment;
import com.newpandas.ui.pandaculture.PandaCulturePresenter;
import com.newpandas.ui.pandalive.PandaEyeContract;
import com.newpandas.ui.pandalive.PandaEyePresenter;
import com.newpandas.ui.pandalive.PandaLiveFragment;
import com.newpandas.widget.manager.ToastManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.newpandas.widget.manager.FragmentBiulder.changeFragment;

public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.iconImg)
    ImageView iconImg;
    @BindView(R.id.personImg)
    ImageView personImg;
    @BindView(R.id.titleTv)
    TextView titleTv;
    @BindView(R.id.hudongImg)
    ImageView hudongImg;
    @BindView(R.id.homePage)
    RadioButton homePage;
    @BindView(R.id.homePandaLive)
    RadioButton homePandaLive;
    @BindView(R.id.homeRollVideo)
    RadioButton homeRollVideo;
    @BindView(R.id.homePandaBroadcast)
    RadioButton homePandaBroadcast;
    @BindView(R.id.homeLiveChina)
    RadioButton homeLiveChina;
    private long lastTime;//上一次点击back键的时间毫秒数
    public static final int HOMETYPE = 1;
    FragmentManager fragmentManager;
    private MainContract.Presenter presenter;
    private static int versionCode;
    private String versionsUrl;
    private AlertDialog alertDialog;
    private  int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        new MainPresenter(this);
        HomeFragment homeFragment = (HomeFragment) changeFragment(HomeFragment.class, R.id.container, true, null, false);

    }

    @OnClick({R.id.iconImg, R.id.personImg, R.id.titleTv, R.id.hudongImg, R.id.homePage, R.id.homePandaLive, R.id.homeRollVideo, R.id.homePandaBroadcast, R.id.homeLiveChina})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iconImg:
                break;
            case R.id.personImg:
                startActivity(new Intent(this,MineActivity.class));
                break;
            case R.id.titleTv:
                break;
            case R.id.hudongImg:
                startActivity(new Intent(this, HuDongActivity.class));
                break;
            case R.id.homePage:
                showTitle(null, HOMETYPE);
                changeFragment(HomeFragment.class, R.id.container, true, null, false);
                break;
            case R.id.homePandaLive:
                showTitle("熊猫直播", 0);
                BaseFragment pandalivefrag=changeFragment(PandaLiveFragment.class, R.id.container, true, null, false);
                new PandaEyePresenter((PandaEyeContract.view) pandalivefrag);
                break;
            case R.id.homeRollVideo:
                showTitle("熊猫文化", 0);
                BaseFragment pandaCultureFragment = changeFragment(PandaCultureFragment.class, R.id.container, true, null, false);
                new PandaCulturePresenter((PandaCultureContract.View) pandaCultureFragment);
                break;
            case R.id.homePandaBroadcast:
                showTitle("熊猫观察", 0);
                changeFragment(PandaBroadCastFragment.class, R.id.container, true, null, false);
                break;
            case R.id.homeLiveChina:
                showTitle("直播中国", 0);
                BaseFragment LiveChinaFragment = changeFragment(LiveChinaFragment.class, R.id.container, true, null, false);
                new LiveChinaPresenter((LiveChinaContract.View) LiveChinaFragment);
                break;
        }
        presenter.start();
    }

    private void showTitle(String title, int type) {
        if (type == HOMETYPE) {
            iconImg.setVisibility(View.VISIBLE);
            titleTv.setVisibility(View.GONE);
            hudongImg.setVisibility(View.VISIBLE);
        } else {
            titleTv.setText(title);
            iconImg.setVisibility(View.GONE);
            titleTv.setVisibility(View.VISIBLE);
            hudongImg.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {

        if (System.currentTimeMillis() - lastTime < 2000) {
            finish();
        } else {
            ToastManager.showToast("再按一次退出应用");
            lastTime = System.currentTimeMillis();
        }

    }

    @Override
    public void setResult(UpDate upDate) {
        getAppVersionName(this);
        UpDate.DataBean data = upDate.getData();
        String getVersionsNum = data.getVersionsNum();
        int VersionsNum = Integer.parseInt(getVersionsNum);
        String versionsUrl1 = data.getVersionsUrl();
        versionsUrl=versionsUrl1;
        if(versionCode<VersionsNum){
            showDialogUpdate();
        }else {
            Toast.makeText(MainActivity.this, "已经是最新版本", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void dimissProgress() {

    }

    @Override
    public void showMessage(String msg) {
        ToastManager.showToast(msg);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter=presenter;
    }

    //获取当前版本
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            //versioncode = pi.versionCode;
            versionCode = pi.versionCode;
            Log.e("aaa", versionCode + "");
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("aaa", versionName);
        }
        return versionName;

    }

    /**
     * 提示版本更新的对话框
     */
    private void showDialogUpdate() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置提示框的标题
        builder.setTitle("版本升级").
                // 设置要显示的信息
                        setMessage("发现新版本  修复了已知的BUG").
                // 设置确定按钮
                        setPositiveButton("现在去更新", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "选择确定哦", 0).show();
                        dialog.dismiss();
                        loadNewVersionProgress();//下载最新的版本程序

                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("以后再说", null);

        // 生产对话框
        alertDialog = builder.create();
        // 显示对话框
        alertDialog.show();


    }

    /**
     * 下载新版本程序，需要子线程
     */
    private void loadNewVersionProgress() {
        final String uri = versionsUrl;
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(uri, pd);
                    sleep(3000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    //下载apk失败
                    Log.e("abc", "下载失败");
//                    Toast.makeText(getActivity(), "下载新版本失败", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 从服务器获取apk文件的代码
     * 传入网址uri，进度条对象即可获得一个File文件
     * （要在子线程中执行哦）
     */
    public File getFileFromServer(String uri, final ProgressDialog pd) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength()/1024/1024);
            InputStream is = conn.getInputStream();
            long time = System.currentTimeMillis();//当前时间的毫秒数
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), time + "updata.apk");


            if (!file.exists())
                file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                pd.setProgress(total/1024/1024);
            }
            Log.d("HomeActivity", "total:" + total);
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }

    /**
     * 安装apk
     */
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }
}
