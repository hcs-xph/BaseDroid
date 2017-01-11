package com.mph.library.base;

import android.os.Bundle;

/**
 * Created by：hcs on 2016/12/23 13:42
 * e_mail：aaron1539@163.com
 */
public interface UiCallBack {

    /**
     * 初始化数据
     * @param savedInstanceState
     */
    void initData(Bundle savedInstanceState);

    /**
     * 设置监听事件
     */
    void setListener();

    /**
     * 设置布局资源id
     * @return
     */
    int getLayoutId();

    /**
     * 是否使用EventBus
     * @return
     */
    boolean useEventBus();
}
