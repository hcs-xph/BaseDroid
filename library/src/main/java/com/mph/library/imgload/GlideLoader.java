package com.mph.library.imgload;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;

/**
 * Created by：hcs on 2016/12/23 09:45
 * e_mail：aaron1539@163.com
 */
public class GlideLoader implements ILoader {

    @Override
    public void init(Context context) {

    }

    @Override
    public void loadNet(ImageView target, String url, Options options) {
        load(getRequestManager(target.getContext()).load(url),target,options);
    }

    @Override
    public void loadNet(Context context, String url, Options options, final LoadCallBack callBack) {
        DrawableTypeRequest request = getRequestManager(context).load(url);
        if(options == null){
            options = Options.defaultOptions();
        }
        if(options.loadingResId != Options.RES_NONE){
            request.placeholder(options.loadingResId);
        }
        if(options.loadErrorResId!=Options.RES_NONE){
            request.error(options.loadErrorResId);
        }
        request.diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade().into(new SimpleTarget<GlideBitmapDrawable>() {

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                if(callBack!=null){
                    callBack.onLoadFailed(e);
                }
            }

            @Override
            public void onResourceReady(GlideBitmapDrawable resource, GlideAnimation<? super GlideBitmapDrawable> glideAnimation) {
                if(resource !=null && resource.getBitmap()!=null){
                    if(callBack!=null){
                        callBack.onLoadReady(resource.getBitmap());
                    }
                }
            }
        });
    }

    @Override
    public void loadResource(ImageView target, int resId, Options options) {
        load(getRequestManager(target.getContext()).load(resId),target,options);
    }

    @Override
    public void loadAssets(ImageView target, String assetName, Options options) {
        load(getRequestManager(target.getContext()).load("file:///android_asset/"+assetName),target,options);
    }

    @Override
    public void loadFile(ImageView target, File file, Options options) {
        load(getRequestManager(target.getContext()).load(file),target,options);
    }

    @Override
    public void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    @Override
    public void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    @Override
    public void resume(Context context) {
        getRequestManager(context).resumeRequests();
    }

    @Override
    public void pause(Context context) {
        getRequestManager(context).pauseRequests();
    }

    private RequestManager getRequestManager(Context context){
        if(context instanceof Activity){
            return Glide.with((Activity) context);
        }
        return Glide.with(context);
    }

    private void load(DrawableTypeRequest request,ImageView target,Options options){
        if(options == null){
            options = Options.defaultOptions();
        }

        if(options.loadErrorResId!=Options.RES_NONE){
            request.error(options.loadErrorResId);
        }

        if(options.loadingResId != Options.RES_NONE){
            request.placeholder(options.loadingResId);
        }

        request.diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade().into(target);
    }


}
