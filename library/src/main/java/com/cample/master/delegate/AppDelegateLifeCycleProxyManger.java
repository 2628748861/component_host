package com.cample.master.delegate;

import android.content.Context;
import android.content.res.Configuration;

import java.util.ArrayList;
import java.util.List;

import app.component.simple.IApplifeCycle;


public class AppDelegateLifeCycleProxyManger implements IApplifeCycle
{
    private List<IApplifeCycle> applifeCycles;
    private AppDelegateLifeCycleProxyManger(Builder builder)
    {
        this.applifeCycles=builder.applifeCycles;
    }
    @Override
    public void attachBaseContext(Context base) {
        for (IApplifeCycle applifeCycle:applifeCycles)
        {
            applifeCycle.attachBaseContext(base);
        }
    }

    @Override
    public void onCreate() {
        for (IApplifeCycle applifeCycle:applifeCycles)
        {
            applifeCycle.onCreate();
        }
    }

    @Override
    public void onTerminate() {
        for (IApplifeCycle applifeCycle:applifeCycles)
        {
            applifeCycle.onTerminate();
        }
    }

    @Override
    public void onLowMemory() {
        for (IApplifeCycle applifeCycle:applifeCycles)
        {
            applifeCycle.onLowMemory();
        }
    }

    @Override
    public void onTrimMemory(int level) {
        for (IApplifeCycle applifeCycle:applifeCycles)
        {
            applifeCycle.onTrimMemory(level);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        for (IApplifeCycle applifeCycle:applifeCycles)
        {
            applifeCycle.onConfigurationChanged(newConfig);
        }
    }

    public static class Builder
    {
        private List<IApplifeCycle> applifeCycles=new ArrayList<>();

        public Builder addApplifeCycle(IApplifeCycle applifeCycle)
        {
            this.applifeCycles.add(applifeCycle);
            return this;
        }
        public Builder addApplifeCycles(List<IApplifeCycle> applifeCycles)
        {
            this.applifeCycles.addAll(applifeCycles);
            return this;
        }
        public AppDelegateLifeCycleProxyManger build()
        {
            return new AppDelegateLifeCycleProxyManger(this);
        }
    }
}
