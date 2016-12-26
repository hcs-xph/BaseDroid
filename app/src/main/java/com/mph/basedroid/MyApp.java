package com.mph.basedroid;

import android.app.Application;

import com.mph.library.net.MyOkHttp;

/**
 * Created by：hcs on 2016/12/26 10:06
 * e_mail：aaron1539@163.com
 */
public class MyApp extends Application {

    private static MyApp instance;
    private MyOkHttp okHttp;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        okHttp = new MyOkHttp();
    }

    public static synchronized MyApp getInstance() {
        return instance;
    }

    public MyOkHttp getOkHttp() {
        return okHttp;
    }
}
