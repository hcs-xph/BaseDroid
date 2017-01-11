package com.mph.library.base.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ListView adapter 基本类（数据可以是多类型item）
 * Created by：hcs on 2016/12/22 15:58
 * e_mail：aaron1539@163.com
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {

    protected ArrayList<T> mData;

    public BaseListAdapter() {
        mData = new ArrayList<>();
    }

    /**
     * 清空数据
     */
    public void clearList(){
        if(mData !=null){
            mData.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 得到数据集合
     * @return
     */
    public ArrayList<T> getData(){
        return mData;
    }

    /**
     * 根据position删除改位置记录
     * @param position
     */
    public void removeItem(int position){
        if(mData!=null && mData.size()>position){
            mData.remove(position);
            notifyDataSetChanged();
        }
    }

    /**
     * 根据实体类删除某条记录
     * @param item
     */
    public void removeItem(T item){
        if(mData.contains(item)){
            mData.remove(item);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加数据集合
     * @param data
     */
    public void addData(List<T> data){
        if(data!=null && data.size()>0){
            if(mData == null){
                mData = new ArrayList<>();
            }
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加数组类型数据
     * @param data
     */
    public void addData(T[] data){
        if(data!=null && data.length>0){
            addData(Arrays.asList(data));
        }
    }

    /**
     * 添加一条记录到末尾
     * @param item
     */
    public void addItemLast(T item){
        if(item!=null){
            if(mData==null){
                mData = new ArrayList<>();
            }
            mData.add(item);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加一条记录到position位置
     * @param item
     * @param position
     */
    public void addItem(T item,int position){
        if(item!=null){
            if(mData==null){
                mData = new ArrayList<>();
            }
            mData.add(position,item);
            notifyDataSetChanged();
        }
    }

    /**
     * 设置数据
     * @param data
     */
    public void setData(List<T> data){
        if(data!=null){
            this.mData.clear();
            this.mData.addAll(data);
        }else{
            this.mData.clear();
        }
        notifyDataSetChanged();
    }

    /**
     * 设置数组类型数据
     * @param data
     */
    public void setData(T[] data){
        if(data!=null && data.length>0){
            setData(Arrays.asList(data));
        }
    }

    /**
     * 移除一组数据
     * @param items 集合
     */
    public void removeItems(List<T> items){
        if(mData!=null && items!=null && items.size()>0 && mData.size()>items.size()){
            for (T item:items) {
                if(mData.contains(item)){
                    mData.remove(item);
                }
            }
            notifyDataSetChanged();
        }
    }

    /**
     * 移除一组数据
     * @param items 数组
     */
    public void removeItems(T[] items){
        if(items!=null && items.length>0){
            removeItems(Arrays.asList(items));
        }
    }

    /**
     * 更新某位置上数据
     * @param item
     * @param position
     */
    public void updateItem(T item,int position){
        if(position>=0 && mData.size()>position){
            mData.remove(item);
            mData.add(position,item);
            notifyDataSetChanged();
        }
    }

    /**
     * 获取数据集合大小
     * @return
     */
    public int getSize(){
        return mData == null?0:mData.size();
    }

    @Override
    public int getCount() {
        return mData==null||mData.isEmpty()?0:mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData!=null?mData.get(i):null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public abstract View getView(int position, View convertView,ViewGroup parent);
}
