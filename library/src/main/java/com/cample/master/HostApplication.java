package com.cample.master;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.cample.master.delegate.AppDelegateLifeCycleProxyManger;
import com.cample.master.delegate.AppLifeCycleDelegate;
import com.cample.master.delegate.ArouterLifeCycleDelegate;
import com.cample.master.delegate.HttpLifeCycleDelegate;
import com.cample.master.delegate.ImageLoaderLifeCycleDelegate;
import com.cample.master.delegate.LitePalLifeCycleDelegate;

import java.util.ArrayList;
import java.util.List;

import app.component.simple.IApplifeCycle;


/**
 * Application基类,主要工作职责包括:1.解析所有子moudle相关配置
 */
public class HostApplication extends Application
{
    private AppDelegateLifeCycleProxyManger manager;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        manager=new AppDelegateLifeCycleProxyManger.Builder()
                .addApplifeCycle(new AppLifeCycleDelegate(this))
                .addApplifeCycle(new HttpLifeCycleDelegate(this))
                .addApplifeCycle(new LitePalLifeCycleDelegate())
                .addApplifeCycle(new ImageLoaderLifeCycleDelegate())
                .addApplifeCycle(new ArouterLifeCycleDelegate(this))
                .addApplifeCycles(applyApplifeCycles())
                .build();
        manager.attachBaseContext(base);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        manager.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        manager.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        manager.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        manager.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        manager.onTrimMemory(level);
    }

    protected List<IApplifeCycle> applyApplifeCycles()
    {
        return new ArrayList<>();
    }
}
