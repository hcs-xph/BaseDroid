package com.mph.basedroid.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.mph.basedroid.R;
import com.mph.library.base.activity.BaseActivity;
import com.mph.library.view.EmptyContentLayout;

/**
 * Created by：hcs on 2017/1/3 10:03
 * e_mail：aaron1539@163.com
 */
public abstract class CityBaseActivity extends BaseActivity {

    protected View errorView;
    private Button errRefresh;

    public void initEmptyView(EmptyContentLayout emptyLayout){
        emptyLayout.emptyView(LayoutInflater.from(this).inflate(R.layout.view_empty,null));
        emptyLayout.loadingView(LayoutInflater.from(this).inflate(R.layout.view_loading,null));
        errorView = LayoutInflater.from(this).inflate(R.layout.view_error, null);
        errRefresh = (Button) errorView.findViewById(R.id.refresh);
        emptyLayout.errorView(errorView);

        errRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorRefresh();
            }
        });
    }

    public abstract void errorRefresh();
}
