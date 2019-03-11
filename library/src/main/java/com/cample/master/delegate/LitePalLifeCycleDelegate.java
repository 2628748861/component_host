package com.cample.master.delegate;

import android.content.Context;
import android.content.res.Configuration;

import org.litepal.LitePal;

import app.component.simple.IApplifeCycle;
import app.component.simple.db.DbProxy;
import app.component.simple.db.LitePalApi;


public class LitePalLifeCycleDelegate implements IApplifeCycle
{
    private Context base;

    @Override
    public void attachBaseContext(Context base) {
        this.base=base;
    }

    @Override
    public void onCreate() {
        LitePal.initialize(base);
        DbProxy.init(new LitePalApi());
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
