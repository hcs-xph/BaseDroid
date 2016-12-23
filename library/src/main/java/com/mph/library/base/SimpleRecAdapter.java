package com.mph.library.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 单一类型数据基类adapter
 * Created by：hcs on 2016/12/23 14:28
 * e_mail：aaron1539@163.com
 */
public abstract class SimpleRecAdapter<T, F extends RecyclerView.ViewHolder> extends BaseRecyclerAdapter<T,F>{

    public SimpleRecAdapter(Context context) {
        super(context);
    }

    @Override
    public F onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(getLayoutId(),parent,false);
        return newViewHolder(view);
    }

    /**
     * 创建viewholder
     * @param itemView
     * @return
     */
    public abstract F newViewHolder(View itemView);

    /**
     * 获取资源id
     * @return
     */
    public abstract int getLayoutId();
}
