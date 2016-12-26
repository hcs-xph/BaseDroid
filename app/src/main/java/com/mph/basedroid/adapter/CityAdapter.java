package com.mph.basedroid.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.mph.basedroid.R;
import com.mph.basedroid.entity.City;
import com.mph.library.base.SimpleListAdapter;

/**
 * Created by：hcs on 2016/12/26 10:36
 * e_mail：aaron1539@163.com
 */
public class CityAdapter extends SimpleListAdapter<City,CityAdapter.CityHolder>{


    public CityAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.province_item;
    }

    @Override
    protected CityHolder newViewHolder(View convertView) {
        CityHolder holder = new CityHolder();
        holder.city = (TextView) convertView.findViewById(R.id.tv_province);
        return holder;
    }

    @Override
    protected void convert(CityHolder holder, City item, int position) {
        holder.city.setText(item.getParentName());
    }

    public static class CityHolder{
        TextView city;
    }
}
