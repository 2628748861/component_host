package com.cample.master.delegate;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;


import com.cample.master.util.ManifestParser;

import java.util.ArrayList;
import java.util.List;

import app.component.simple.IApplifeCycle;
import app.component.simple.IModuleConfiguration;

public class AppLifeCycleDelegate implements IApplifeCycle
{
    private List<IModuleConfiguration> moduleConfigurations;
    private List<IApplifeCycle> mAppLifecycles = new ArrayList<>();
    private List<Application.ActivityLifecycleCallbacks> mActivityLifecycles = new ArrayList<>();

    public AppLifeCycleDelegate(Application application)
    {
        moduleConfigurations =new ManifestParser<IModuleConfiguration>(application).parse();
        for (IModuleConfiguration moduleConfiguration: moduleConfigurations)
        {
            moduleConfiguration.injectApplifeCycle(mAppLifecycles);
            moduleConfiguration.injectActivityLifecycle(mActivityLifecycles);
        }
        //设置ActivityLifecycleCallback
        for (Application.ActivityLifecycleCallbacks activityLifecycleCallback:mActivityLifecycles)
        {
            application.registerActivityLifecycleCallbacks(activityLifecycleCallback);
        }
    }

    @Override
    public void attachBaseContext(Context base) {
        for (IApplifeCycle lifecycle : mAppLifecycles) {
            lifecycle.attachBaseContext(base);
        }
    }

    @Override
    public void onCreate() {
        for (IApplifeCycle lifecycle : mAppLifecycles) {
            lifecycle.onCreate();
        }

    }

    @Override
    public void onTerminate() {
        for (IApplifeCycle lifecycle : mAppLifecycles) {
            lifecycle.onTerminate();
        }
    }

    @Override
    public void onLowMemory() {
        for (IApplifeCycle lifecycle : mAppLifecycles) {
            lifecycle.onLowMemory();
        }
    }

    @Override
    public void onTrimMemory(int level) {
        for (IApplifeCycle lifecycle : mAppLifecycles) {
            lifecycle.onTrimMemory(level);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        for (IApplifeCycle lifecycle : mAppLifecycles) {
            lifecycle.onConfigurationChanged(newConfig);
        }
    }
}
