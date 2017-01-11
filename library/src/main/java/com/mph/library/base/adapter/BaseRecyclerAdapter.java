package com.mph.library.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mph.library.base.RecyclerItemCallBack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * RecyclerAdapter适配器基类
 * Created by：hcs on 2016/12/23 14:33
 * e_mail：aaron1539@163.com
 */
public abstract class BaseRecyclerAdapter<T,F extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<F> {

    protected Context context;
    protected List<T> data = new ArrayList<>();
    private RecyclerItemCallBack<T,F> recItemClick;

    public BaseRecyclerAdapter(Context context) {
        this.context = context;
    }

    /**
     * 设置数据源
     * @param data 集合类型
     */
    public void setData(List<T> data){

        if(data!=null && data.size()>0){
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }

    }

    /**
     * 设置数据源
     * @param data 数组类型
     */
    public void setData(T[] data){
        if(data!=null && data.length>0){
            setData(Arrays.asList(data));
        }
    }

    /**
     * 添加数据
     * @param data 集合类型
     */
    public void addData(List<T> data){

        if(data!=null && data.size()>0){
            if (this.data == null){
                this.data = new ArrayList<>();
            }
            int preSize = this.data.size();
            this.data.addAll(data);
            notifyItemRangeInserted(preSize,this.data.size());
        }
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     * @param data 数组类型
     */
    public void addData(T[] data){
        if(data!=null && data.length>0){
            addData(Arrays.asList(data));
        }
    }

    /**
     * 删除元素
     * @param item 实例
     */
    public void removeItem(T item){
        if(data!=null && data.size()>0){
            if(data.contains(item)){
                int position = data.indexOf(item);
                data.remove(item);
                notifyItemRemoved(position);
                notifyItemChanged(position);
            }
        }
    }

    /**
     * 删除元素
     * @param position 位置
     */
    public void removeItem(int position){
        if(data!=null && data.size()>position){
            data.remove(position);
            notifyItemRemoved(position);
            notifyItemChanged(position);
        }
    }

    /**
     * 删除一组元素
     * @param items 集合
     */
    public void removeItems(List<T> items){
        if(data!=null && items!=null && items.size()>0 && data.size()>items.size()){
            for (T item:items) {
                if(data.contains(item)){
                    data.remove(item);
                }
            }
            notifyDataSetChanged();
        }
    }

    /**
     * 删除一组元素
     * @param items 数组
     */
    public void removeItems(T[] items){
        if(items !=null && items.length>0){
            removeItems(Arrays.asList(items));
        }
    }

    /**
     * 更新元素
     * @param item 实例
     * @param position 位置
     */
    public void updateItem(T item,int position){
        if(position>=0 && data.size()>position){
            data.remove(position);
            data.add(position,item);
            notifyItemChanged(position);
        }
    }

    /**
     * 添加元素
     * @param item
     */
    public void addItem(T item){
        if(item!=null){
            if(this.data == null){
                this.data = new ArrayList<>();
            }
            data.add(item);
            notifyItemInserted(this.data.size());
        }
    }

    /**
     * 添加元素
     * @param position
     * @param item
     */
    public void addItem(int position,T item){
        if(item!=null){
            if(this.data == null){
                this.data = new ArrayList<>();
            }
            data.add(position,item);
            notifyItemInserted(position);
        }
    }

    /**
     * 清空集合
     */
    public void clearData(){
        if(data!=null){
            data.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 获取数据
     * @return
     */
    public List<T> getData(){
        return data;
    }

    public void setRecItemClick(RecyclerItemCallBack<T, F> recItemClick) {
        this.recItemClick = recItemClick;
    }

    public RecyclerItemCallBack<T, F> getRecItemClick() {
        return recItemClick;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public abstract F onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(F holder, int position);

    @Override
    public int getItemCount() {
        return data == null || data.isEmpty()?0:data.size();
    }
}
