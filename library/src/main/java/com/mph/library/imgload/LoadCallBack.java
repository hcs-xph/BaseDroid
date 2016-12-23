package com.mph.library.imgload;

import android.graphics.Bitmap;

/**
 * 图片加载回调
 * Created by：hcs on 2016/12/23 09:35
 * e_mail：aaron1539@163.com
 */
public abstract class LoadCallBack {
    void onLoadFailed(Throwable e){}
    public abstract void onLoadReady(Bitmap bitmap);
    void onLoadCanceled(){}
}
