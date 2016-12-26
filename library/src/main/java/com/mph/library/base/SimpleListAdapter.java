package com.mph.library.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 单一类型数据基类adapter
 * Created by：hcs on 2016/12/22 16:39
 * e_mail：aaron1539@163.com
 */
public abstract class SimpleListAdapter<T,H> extends BaseListAdapter<T> {

    private Context context;

    public SimpleListAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        if(convertView == null){
            ret = LayoutInflater.from(context).inflate(getLayoutId(),parent,false);
        }else{
            ret = convertView;
        }

        H holder = (H) ret.getTag();

        if(holder == null){
            holder = newViewHolder(ret);
            ret.setTag(holder);
        }

        T item = mData.get(position);

        convert(holder,item,position);

        return ret;
    }

    /**
     * 获取加载的item布局
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化holder 包括holder id设置
     * @param convertView
     * @return
     */
    protected abstract H newViewHolder(View convertView);

    /**
     * item布局数据及操作设置
     * @param holder
     * @param item
     * @param position
     */
    protected abstract void convert(H holder,T item,int position);
}
