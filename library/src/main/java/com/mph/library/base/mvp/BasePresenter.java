package com.mph.library.base.mvp;

/**
 * Created by：hcs on 2017/1/11 14:33
 * e_mail：aaron1539@163.com
 */
public abstract class BasePresenter<V> {
    protected V mView;

    public void attach(V mView) {
        this.mView = mView;
    }

    public void detach(){
        mView = null;
    }

}
