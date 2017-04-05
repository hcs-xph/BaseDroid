package com.mph.library.base.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mph.library.BaseDroidConfig;
import com.mph.library.R;
import com.mph.library.base.UiCallBack;
import com.mph.library.base.mvp.BasePresenter;
import com.mph.library.event.BusFactory;
import com.mph.library.kit.KnifeKit;
import com.mph.library.util.ActivityCollector;
import com.mph.library.util.Kits;
import com.mph.library.util.MyToast;
import com.mph.library.util.StatusBarUtil;
import com.mph.library.view.EmptyContentLayout;

import butterknife.Unbinder;

/**
 * Created by：hcs on 2016/12/23 13:43
 * e_mail：aaron1539@163.com
 */
public abstract class BaseActivity<V,T extends BasePresenter<V>> extends Activity implements UiCallBack {

    protected Activity context;
    private Unbinder unbinder;
    protected T presenter;

    protected View errorView;
    private Button refreshBtn;
    protected MyToast toast = new MyToast();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(useEventBus()){
            BusFactory.getBus().register(this);
        }
        this.context = this;
        if(getLayoutId()>0){
            setContentView(getLayoutId());
            unbinder = KnifeKit.bind(this);
        }
        if(changeStatusBarColor()){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
                StatusBarUtil.setColorNoTranslucent(this, BaseDroidConfig.STATUS_BAT_DEFAULT_COLOR);
            }else{
                RelativeLayout rl = setTitleViewHeight();
                if(rl!=null){
                    rl.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Kits.Dimens.dip2px(this,45)));
                }
            }
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
    protected void onDestroy() {
        if(presenter!=null){
            presenter.detach();
        }
        toast = null;
        ActivityCollector.removeActivity(this);
        BusFactory.getBus().unregister(this);
        super.onDestroy();
    }

    /**
     * 是否使用eventbus
     * @return
     */
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

    /**
     * 设置监听事件
     */
    @Override
    public void setListener() {

    }

    /**
     * 动态设置titleview高度
     * @return
     */
    protected RelativeLayout setTitleViewHeight(){
        return null;
    }

    /**
     * 初始化presenter
     * @return
     */
    protected T initPresenter(){
        return null;
    }

    /**
     * 加载失败刷新加载
     */
    protected void errorRefresh(){

    }

    /**
     * 初始化错误页面
     * @param emptyLayout
     */
    protected  void initEmptyView(EmptyContentLayout emptyLayout){
        emptyLayout.emptyView(LayoutInflater.from(this).inflate(R.layout.view_empty,null));
        emptyLayout.loadingView(LayoutInflater.from(this).inflate(R.layout.view_loading,null));
        errorView = LayoutInflater.from(this).inflate(R.layout.view_error, null);
        refreshBtn = (Button) errorView.findViewById(R.id.refresh);
        emptyLayout.errorView(errorView);

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorRefresh();
            }
        });
    }
}
