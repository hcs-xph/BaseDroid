package com.mph.basedroid.view;

import com.mph.basedroid.entity.City;
import com.mph.library.base.mvp.BaseView;

import java.util.List;

/**
 * Created by：hcs on 2017/1/11 16:40
 * e_mail：aaron1539@163.com
 */
public interface CityView extends BaseView {

    void getCitySuccess(List<City> datas);

}
