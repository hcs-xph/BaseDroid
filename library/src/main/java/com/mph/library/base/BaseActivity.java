package com.mph.library.base;

import android.app.Activity;
import android.os.Bundle;

import com.mph.library.event.BusFactory;
import com.mph.library.kit.KnifeKit;
import com.mph.library.util.ActivityCollector;

import butterknife.Unbinder;

/**
 * Created by：hcs on 2016/12/23 13:43
 * e_mail：aaron1539@163.com
 */
public abstract class BaseActivity extends Activity implements UiCallBack {

    protected Activity context;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        if(getLayoutId()>0){
            setContentView(getLayoutId());
            unbinder = KnifeKit.bind(this);
        }
        initData(savedInstanceState);
        setListener();
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(useEventBus()){
            BusFactory.getBus().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        BusFactory.getBus().unregister(this);
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    public void setListener() {

    }
}
