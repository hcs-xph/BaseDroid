package com.mph.library.base.activity;

import android.app.Activity;
import android.os.Bundle;

import com.mph.library.BaseDroidConfig;
import com.mph.library.base.UiCallBack;
import com.mph.library.base.mvp.BasePresenter;
import com.mph.library.event.BusFactory;
import com.mph.library.kit.KnifeKit;
import com.mph.library.util.ActivityCollector;
import com.mph.library.util.StatusBarUtil;

import butterknife.Unbinder;

/**
 * Created by：hcs on 2016/12/23 13:43
 * e_mail：aaron1539@163.com
 */
public abstract class BaseActivity<V,T extends BasePresenter<V>> extends Activity implements UiCallBack {

    protected Activity context;
    private Unbinder unbinder;
    protected T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        if(getLayoutId()>0){
            setContentView(getLayoutId());
            unbinder = KnifeKit.bind(this);
        }
        if(changeStatusBarColor()){
            StatusBarUtil.setColor(this, BaseDroidConfig.STATUS_BAT_DEFAULT_COLOR);
        }
        presenter = initPresenter();
        if(presenter != null){
            presenter.attach((V)this);
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
    protected void onDestroy() {
        if(presenter!=null){
            presenter.detach();
        }
        super.onDestroy();
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    /**
     * 是否使用沉浸式状态栏
     * @return
     */
    public boolean changeStatusBarColor(){
        return false;
    }

    @Override
    public void setListener() {

    }

    /**
     * 初始化presenter
     * @return
     */
    protected T initPresenter(){
        return null;
    }
}
