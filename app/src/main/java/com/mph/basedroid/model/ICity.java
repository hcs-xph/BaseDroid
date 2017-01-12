package com.mph.basedroid.model;

import com.mph.basedroid.entity.City;

import java.util.List;

/**
 * Created by：hcs on 2017/1/11 16:45
 * e_mail：aaron1539@163.com
 */
public interface ICity {
    interface OnGetCityListener{
        void onGetCitySuccess(List<City> data);
        void onGetCityFailed(String msg);
    }

    void getCity(OnGetCityListener listener);
    void cancel();
}
