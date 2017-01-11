package com.mph.library.base.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * ViewPager基础实现(如：引导页)
 * Created by：hcs on 2016/12/23 15:13
 * e_mail：aaron1539@163.com
 */
public class BasePagerAdapter extends PagerAdapter {

    private List<ImageView> datas;

    public BasePagerAdapter(List<ImageView> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager)container).removeView(datas.get(position));
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(datas.get(position), 0);
        return datas.get(position);
    }
}
