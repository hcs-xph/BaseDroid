package com.mph.basedroid.presenter;

import com.mph.basedroid.entity.City;
import com.mph.basedroid.model.GetCityInfo;
import com.mph.basedroid.model.ICity;
import com.mph.basedroid.view.CityView;
import com.mph.library.base.mvp.BasePresenter;
import com.mph.library.base.mvp.BaseView;

import java.util.List;

/**
 * Created by：hcs on 2017/1/11 16:53
 * e_mail：aaron1539@163.com
 */
public class CityPresenter extends BasePresenter<CityView> implements ICity.OnGetCityListener {

    private ICity iCity;

    public CityPresenter() {
        iCity = new GetCityInfo();
    }

    public void getCityData(){
        if(mView!=null){
            mView.showLoadingView();
        }
        iCity.getCity(this);
    }

    @Override
    public void onGetCitySuccess(List<City> data) {
        if(mView!=null){
            mView.hideLoadingView(BaseView.NET_SUCCESS);
            mView.getCitySuccess(data);
        }
    }

    @Override
    public void onGetCityFailed(String msg) {
        if(mView!=null){
            mView.showFailedView("");
        }
    }

    @Override
    public void cancel() {
        if(mView!=null){
            mView.hideLoadingView(BaseView.NET_CANCEL);
            iCity.cancel();
        }
    }
}
