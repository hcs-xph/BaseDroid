package com.mph.basedroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mph.basedroid.adapter.TestRecAdapter;
import com.mph.library.base.BaseActivity;
import com.mph.library.base.RecyclerItemCallBack;
import com.mph.library.log.xLog;
import com.mph.library.view.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;

    TestRecAdapter adapter;

    static final int MAX_PAGE = 5;
    private int pageSize = 12;


    @Override
    public void initData(Bundle savedInstanceState) {
        if(adapter == null){
            adapter = new TestRecAdapter(this);
        }

        recyclerView.verticalLayoutManager(this).setAdapter(adapter);

        adapter.setRecItemClick(new RecyclerItemCallBack<TestRecAdapter.Item, TestRecAdapter.ViewHolder>() {
            @Override
            public void onItemClick(int position, TestRecAdapter.Item model, int tag, TestRecAdapter.ViewHolder holder) {
                xLog.d("RecyclerActivity","position-->"+position +" ,item-->"+model.getText());
            }
        });

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

        loadData(1);
    }

    private void loadData(final int i) {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<TestRecAdapter.Item> list = buildData(i);
                if(i>1){
                    adapter.addData(list);
                }else{
                    adapter.setData(list);
                }
                recyclerView.setPage(i,MAX_PAGE);
            }


        },500);
    }

    private List<TestRecAdapter.Item> buildData(int i) {
        int init = (i-1)*pageSize;
        List<TestRecAdapter.Item> list = new ArrayList<>();
        for (int j = init; j < i*pageSize; j++) {
            list.add(new TestRecAdapter.Item("测试-->"+j));
        }
        return list;
    }

    @Override
    public void setListener() {
        tvNext.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycler;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(RecyclerActivity.this, NextActivity.class));
    }
}
