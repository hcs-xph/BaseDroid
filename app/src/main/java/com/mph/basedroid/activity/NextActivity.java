package com.mph.basedroid.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mph.basedroid.R;
import com.mph.basedroid.adapter.TestRecAdapter;
import com.mph.basedroid.view.EmptyView;
import com.mph.basedroid.view.ErrorView;
import com.mph.basedroid.view.HeadView;
import com.mph.basedroid.view.LoadingView;
import com.mph.library.base.BaseActivity;
import com.mph.library.base.RecyclerItemCallBack;
import com.mph.library.log.xLog;
import com.mph.library.view.xrecyclerview.XRecyclerContentLayout;
import com.mph.library.view.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NextActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_loading)
    TextView tvLoading;
    @BindView(R.id.contentLayout)
    XRecyclerContentLayout contentLayout;

    TestRecAdapter adapter;

    static final int MAX_PAGE = 5;
    private int pageSize = 12;

    @Override
    public void initData(Bundle savedInstanceState) {
        if(adapter ==null){
            adapter = new TestRecAdapter(this);
        }
        XRecyclerView recyclerView = contentLayout.getRecyclerView();
        recyclerView.verticalLayoutManager(this).setAdapter(adapter);
        recyclerView.horizontalDivider(R.color.x_red,R.dimen.divider_height);
        recyclerView.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                loadData(1);
            }

            @Override
            public void onLoadMore(int page) {
                loadData(page);
            }
        });

        recyclerView.useDefLoadMoreView();
        recyclerView.addHeaderView(new HeadView(this));

        contentLayout.loadingView(new LoadingView(this));
        contentLayout.errorView(new ErrorView(this));
        contentLayout.emptyView(new EmptyView(this));

        adapter.setRecItemClick(new RecyclerItemCallBack<TestRecAdapter.Item, TestRecAdapter.ViewHolder>() {
            @Override
            public void onItemClick(int position, TestRecAdapter.Item model, int tag, TestRecAdapter.ViewHolder holder) {
                xLog.d("RecyclerActivity","position-->"+position +" ,item-->"+model.getText());
            }
        });

        contentLayout.showLoading();
        loadData(1);
    }

    private void loadData(final int page) {
        contentLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<TestRecAdapter.Item> list = buildData(page);
                if(page>1){
                    adapter.addData(list);
                }else{
                    adapter.setData(list);
                }
                contentLayout.getRecyclerView().setPage(page,MAX_PAGE);
            }
        },500);
    }

    @Override
    public void setListener() {
        tvError.setOnClickListener(this);
        tvEmpty.setOnClickListener(this);
        tvContent.setOnClickListener(this);
        tvLoading.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_next;
    }


    private List<TestRecAdapter.Item> buildData(int page) {
        int init = (page - 1) * pageSize;
        List<TestRecAdapter.Item> list = new ArrayList<>();

        for (int pos = init; pos < page * pageSize; pos++) {
            list.add(new TestRecAdapter.Item("测试" + pos));
        }

        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_error:
                contentLayout.showError();
                break;

            case R.id.tv_loading:
                contentLayout.showLoading();
                break;

            case R.id.tv_content:
                contentLayout.showContent();
                break;

            case R.id.tv_empty:
                contentLayout.showEmpty();
                break;
        }
    }
}
