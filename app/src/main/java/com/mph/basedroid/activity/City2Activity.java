package com.mph.basedroid.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.mph.basedroid.R;
import com.mph.basedroid.adapter.CityAdapter;
import com.mph.basedroid.entity.City;
import com.mph.basedroid.presenter.CityPresenter;
import com.mph.basedroid.view.CityView;
import com.mph.library.base.activity.BaseActivity;
import com.mph.library.base.mvp.BaseView;
import com.mph.library.view.EmptyContentLayout;

import java.util.List;

import butterknife.BindView;

public class City2Activity extends BaseActivity<CityView, CityPresenter> implements CityView {

    @BindView(R.id.listview2)
    ListView listview2;
    @BindView(R.id.emptyLayout2)
    EmptyContentLayout emptyLayout2;
    private CityAdapter adapter;


    @Override
    protected CityPresenter initPresenter() {
        return new CityPresenter();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initEmptyView(emptyLayout2);
        adapter = new CityAdapter(this);
        listview2.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        presenter.getCityData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_city2;
    }

    @Override
    public void getCitySuccess(List<City> datas) {
        adapter.setData(datas);
    }

    @Override
    public void showLoadingView() {
        emptyLayout2.showLoading();
    }

    @Override
    public void hideLoadingView(int tag) {
        if (tag == BaseView.NET_CANCEL) {
            emptyLayout2.showEmpty();
        }else if(tag == BaseView.NET_SUCCESS){
            emptyLayout2.showContent();
        }
    }

    @Override
    public void showFailedView(String msg) {
        emptyLayout2.showError();
    }

    public void initEmptyView(EmptyContentLayout emptyLayout) {
        emptyLayout.emptyView(LayoutInflater.from(this).inflate(R.layout.view_empty, null));
        emptyLayout.loadingView(LayoutInflater.from(this).inflate(R.layout.view_loading, null));
        emptyLayout.errorView(LayoutInflater.from(this).inflate(R.layout.view_error, null));
    }

}
