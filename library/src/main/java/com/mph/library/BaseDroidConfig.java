package com.mph.library;

import com.mph.library.imgload.ILoader;

/**
 * 常量配置文件,可以在程序入口进行配置
 * Created by：hcs on 2016/12/23 09:30
 * e_mail：aaron1539@163.com
 */
public class BaseDroidConfig {

    // log
    public static boolean LOG = true;
    public static String LOG_TAG = "BaseDroid";

    //imageloader
    /**
     * 默认加载中配置
     */
    public static int IL_LOADING_RES = ILoader.Options.RES_NONE;
    /**
     * 默认加载失败配置
     */
    public static int IL_ERROR_RES = ILoader.Options.RES_NONE;

    /**
     * 沉浸式状态栏默认颜色值
     */
    public static int STATUS_BAT_DEFAULT_COLOR = 0XFFFFFF;
}
