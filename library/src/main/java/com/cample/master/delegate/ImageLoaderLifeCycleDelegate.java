package com.cample.master.delegate;

import android.content.Context;
import android.content.res.Configuration;

import app.component.simple.IApplifeCycle;
import app.component.simple.image.GlideImageLoaderAPI;
import app.component.simple.image.ImageLoaderProxy;


public class ImageLoaderLifeCycleDelegate implements IApplifeCycle
{

    @Override
    public void attachBaseContext(Context base) {

    }

    @Override
    public void onCreate() {
        ImageLoaderProxy.init(new GlideImageLoaderAPI());
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
