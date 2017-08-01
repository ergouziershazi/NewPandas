package com.newpandas.widget.manager;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.newpandas.app.App;
import com.newpandas.base.BaseFragment;

/**
 * Created by yan on 2017/7/28.
 */

public class FragmentBiulder {

    public static BaseFragment lastfragment;

    public static BaseFragment changeFragment(Class<? extends BaseFragment> fragmentClass, int containId, boolean isHidden, Bundle bundle, boolean isBack){
//管理frgament
        FragmentManager manager= App.context.getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
//获取Fragment的类名，用类名当做Tag
        String fragmentname=fragmentClass.getSimpleName();
//根据tag来查找当前的fragment如果不为空则表示已经创建过一次了
        BaseFragment currentFragment=(BaseFragment)manager.findFragmentByTag(fragmentname);
        if(currentFragment==null){
//如果为空就创建并且添加到manager中
            try {
                //通过Java动态代理创建的对象
                currentFragment=fragmentClass.newInstance();
                transaction.add(containId,currentFragment,fragmentname);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if(isHidden){
            //隐藏上一个Fragment
            if (lastfragment != null)
                transaction.hide(lastfragment);
             transaction.show(currentFragment);
        }else{
            transaction.replace(containId,currentFragment,fragmentname);
        }
//传递参数
        if(bundle!=null){
            currentFragment.setBundle(bundle);
        }
        if(isBack){
            transaction.addToBackStack(fragmentname);
        }
        transaction.commit();
        lastfragment = currentFragment;
        return lastfragment;

    }

}
