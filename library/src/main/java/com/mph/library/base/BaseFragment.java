package com.mph.library.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mph.library.event.BusFactory;
import com.mph.library.kit.KnifeKit;

import butterknife.Unbinder;

/**
 * Created by：hcs on 2016/12/23 13:59
 * e_mail：aaron1539@163.com
 */
public abstract class BaseFragment extends Fragment implements UiCallBack{
    protected View rootView;
    protected LayoutInflater layoutInflater;
    protected Activity context;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutInflater = inflater;
        if(rootView == null){
            rootView = inflater.inflate(getLayoutId(),null);
            unbinder = KnifeKit.bind(this,rootView);
        }else{
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if(viewGroup!=null){
                viewGroup.removeView(rootView);
            }
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(useEventBus()){
            BusFactory.getBus().register(this);
        }
        initData(savedInstanceState);
        setListener();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.context = (Activity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BusFactory.getBus().unregister(this);
    }

    @Override
    public void setListener() {

    }
}
