package com.mph.library.base.mvp;

/**
 * Created by：hcs on 2017/1/11 14:43
 * e_mail：aaron1539@163.com
 */
public interface BaseView {

    public static final int NET_SUCCESS = 0;
    public static final int NET_ERROR = 1;
    public static final int NET_CANCEL = 2;

    /**
     * 显示加载view
     */
    void showLoadingView();

    /**
     * 关闭加载view
     */
    void hideLoadingView(int Tag);

    /**
     * 显示加载失败view
     * @param msg
     */
    void showFailedView(String msg);
}
