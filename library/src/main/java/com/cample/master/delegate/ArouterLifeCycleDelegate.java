package com.cample.master.delegate;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cample.master.BuildConfig;

import app.component.simple.IApplifeCycle;


public class ArouterLifeCycleDelegate implements IApplifeCycle
{
    private Application application;

    public ArouterLifeCycleDelegate(Application application) {
        this.application = application;
    }

    @Override
    public void attachBaseContext(Context base) {

    }

    @Override
    public void onCreate() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application);
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onTrimMemory(int level) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }
}
