package com.mph.basedroid.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.mph.basedroid.R;
import com.mph.basedroid.adapter.MusicAdapter;
import com.mph.library.base.BaseFragment;
import com.mph.library.view.EmptyContentLayout;
import com.mph.library.view.refreshlayout.RefreshListenerAdapter;
import com.mph.library.view.refreshlayout.TwinklingRefreshLayout;
import com.mph.library.view.refreshlayout.header.progresslayout.ProgressLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends BaseFragment {

    private MusicAdapter adapter;

    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.refreshView)
    TwinklingRefreshLayout refreshView;
    @BindView(R.id.emptyLayout)
    EmptyContentLayout emptyLayout;
    protected View errorView;
    private Button errRefresh;


    @Override
    public void initData(Bundle savedInstanceState) {
        ProgressLayout headerView = new ProgressLayout(getActivity());
        refreshView.setHeaderView(headerView);
        View exHeader = View.inflate(getContext(), R.layout.header_music, null);
        listview.addHeaderView(exHeader);
        refreshView.setOverScrollRefreshShow(false);

        initEmptyView(emptyLayout);

        adapter = new MusicAdapter();
        listview.setAdapter(adapter);
        emptyLayout.showLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.refreshCard();
                emptyLayout.showContent();
            }
        },3000);

    }

    @Override
    public void setListener() {

        refreshView.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        emptyLayout.showContent();
                        adapter.refreshCard();
                        refreshLayout.finishRefreshing();
                        Log.d("MeiTuanHeaderView","刷新了");
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        emptyLayout.showContent();
                        adapter.loadMoreCard();
                        refreshLayout.finishLoadmore();
                    }
                }, 2000);
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("MusicActivity","集合大小原始-->"+adapter.getCards().size());
                long itemId = parent.getAdapter().getItemId(position);
                adapter.getCards().remove((int)itemId);
                adapter.notifyDataSetChanged();
                Log.d("MusicActivity","集合删除元素后大小-->"+adapter.getCards().size());
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_download;
    }

    public void initEmptyView(EmptyContentLayout emptyLayout){
        emptyLayout.emptyView(LayoutInflater.from(getContext()).inflate(R.layout.view_empty,null));
        emptyLayout.loadingView(LayoutInflater.from(getContext()).inflate(R.layout.view_loading,null));
        errorView = LayoutInflater.from(getContext()).inflate(R.layout.view_error, null);
        errRefresh = (Button) errorView.findViewById(R.id.refresh);
        emptyLayout.errorView(errorView);

        errRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.refreshCard();
            }
        });
    }

}
