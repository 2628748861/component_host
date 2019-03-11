package com.cample.master.delegate;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.cample.master.util.ManifestParser;

import java.util.ArrayList;
import java.util.List;

import app.component.simple.IApplifeCycle;
import app.component.simple.IModuleConfiguration;
import app.component.simple.http.HttpApiProxy;
import app.component.simple.http.RetrofitApi;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

public class HttpLifeCycleDelegate implements IApplifeCycle
{
    private List<IModuleConfiguration> moduleConfigurations;
    private List<Interceptor> interceptors=new ArrayList<>();
    public HttpLifeCycleDelegate(Application application)
    {
        moduleConfigurations =new ManifestParser<IModuleConfiguration>(application).parse();
        for (IModuleConfiguration moduleConfiguration: moduleConfigurations)
        {
            moduleConfiguration.injectHttpInterceptor(interceptors);
        }
    }
    @Override
    public void attachBaseContext(Context base) {

    }

    @Override
    public void onCreate() {
        OkHttpClient.Builder builder=RetrofitUrlManager.getInstance().with(new OkHttpClient.Builder());
        for (Interceptor interceptor:interceptors)
        {
            builder.addInterceptor(interceptor);
        }
        OkHttpClient okHttpClient=builder.build();
        HttpApiProxy.init(new RetrofitApi(okHttpClient));
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
