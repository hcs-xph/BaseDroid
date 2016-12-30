package com.mph.library.imgload;

import android.content.Context;
import android.widget.ImageView;

import com.mph.library.BaseDroidConfig;

import java.io.File;

/**
 * Created by：hcs on 2016/12/23 09:26
 * e_mail：aaron1539@163.com
 */
public interface ILoader {
    /**
     * 图片加载框架初始化，通常在APP-onCreate方法中调用
     * @param context
     */
    void init(Context context);

    /**
     * 加载网络资源
     * @param target
     * @param url
     * @param options
     */
    void loadNet(ImageView target,String url,Options options);

    void loadNet(Context context,String url,Options options,LoadCallBack callBack);

    /**
     * 加载resource
     * @param target
     * @param resId
     * @param options
     */
    void loadResource(ImageView target,int resId,Options options);

    /**
     * 加载asset目录文件
     * @param target
     * @param assetName
     * @param options
     */
    void loadAssets(ImageView target,String assetName,Options options);

    /**
     * 加载文件
     * @param target
     * @param file
     * @param options
     */
    void loadFile(ImageView target, File file,Options options);

    /**
     * clear 内存缓存
     * @param context
     */
    void clearMemoryCache(Context context);

    /**
     * clear 磁盘缓存
     * @param context
     */
    void clearDiskCache(Context context);
    void resume(Context context);
    void pause(Context context);

    /**
     * 图片加载中、加载失败默认图片配置
     */
    class Options{
        /**
         * 加载中的资源id
         */
        public int loadingResId = RES_NONE;
        /**
         * 加载失败的资源id
         */
        public int loadErrorResId = RES_NONE;
        public static final int RES_NONE = -1;

        /**
         * 返回默认配置
         * @return
         */
        public static Options defaultOptions(){
            return new Options(BaseDroidConfig.IL_LOADING_RES,BaseDroidConfig.IL_ERROR_RES);
        }

        public Options(int loadingResId, int loadErrorResId) {
            this.loadingResId = loadingResId;
            this.loadErrorResId = loadErrorResId;
        }
    }
}
