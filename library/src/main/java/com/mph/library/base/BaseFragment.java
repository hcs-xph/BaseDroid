package com.mph.library.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mph.library.R;
import com.mph.library.base.mvp.BasePresenter;
import com.mph.library.event.BusFactory;
import com.mph.library.kit.KnifeKit;
import com.mph.library.util.MyToast;
import com.mph.library.view.EmptyContentLayout;

import butterknife.Unbinder;

/**
 * Created by：hcs on 2016/12/23 13:59
 * e_mail：aaron1539@163.com
 */
public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment implements UiCallBack {
    protected View rootView;
    protected LayoutInflater layoutInflater;
    protected Activity context;
    private Unbinder unbinder;
    protected T presenter;

    protected View errorView;
    private Button refreshBtn;
    protected MyToast toast = new MyToast();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.context = (Activity) context;
        }
        if (useEventBus()) {
            BusFactory.getBus().register(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutInflater = inflater;
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), null);
            unbinder = KnifeKit.bind(this, rootView);
        } else {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter = initPresenter();
        if(presenter!=null){
            presenter.attach((V)this);
        }
        initData(savedInstanceState);
        setListener();

    }


    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusFactory.getBus().unregister(this);
        toast = null;
        if(presenter!=null){
            presenter.detach();
        }
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    public void setListener() {

    }

    /**
     * 初始化presenter
     *
     * @return
     */
    protected T initPresenter() {
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
        emptyLayout.emptyView(LayoutInflater.from(getContext()).inflate(R.layout.view_empty,null));
        emptyLayout.loadingView(LayoutInflater.from(getContext()).inflate(R.layout.view_loading,null));
        errorView = LayoutInflater.from(getContext()).inflate(R.layout.view_error, null);
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
