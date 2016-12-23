package com.mph.library.base;

/**
 * Created by：hcs on 2016/12/23 14:34
 * e_mail：aaron1539@163.com
 */
public abstract class RecyclerItemCallBack<T,F> {

    /**
     * 单击时间
     * @param position 位置
     * @param model 实体
     * @param tag 标签
     * @param holder 控件
     */
    public void onItemClick(int position,T model,int tag,F holder){

    }

    /**
     * 长按事件
     * @param position 位置
     * @param model 实体
     * @param tag 标签
     * @param holder 控件
     */
    public void onItemLongClick(int position,T model,int tag,F holder){

    }
}
