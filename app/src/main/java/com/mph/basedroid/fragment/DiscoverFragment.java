package com.mph.basedroid.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.mph.basedroid.R;
import com.mph.library.base.BaseFragment;
import com.mph.library.base.adapter.BaseFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends BaseFragment {

    @BindView(R.id.tl_htool)
    TabLayout tlHtool;
    @BindView(R.id.tl_htool_viewpager)
    ViewPager tlHtoolViewpager;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles;

    @Override
    public void initData(Bundle savedInstanceState) {
        titles = new ArrayList<>();
        titles.add("热曲");
        titles.add("必点");
        titles.add("经典");

        fragments.add(new CustomTingFragment());
        fragments.add(new DownloadFragment());
        fragments.add(new PersonalFragment());

        tlHtool.addTab(tlHtool.newTab());
        tlHtool.addTab(tlHtool.newTab());
        tlHtool.addTab(tlHtool.newTab());

        tlHtool.setTabMode(TabLayout.MODE_FIXED);

        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getChildFragmentManager(),fragments,titles);
        tlHtoolViewpager.setAdapter(adapter);
        tlHtool.setupWithViewPager(tlHtoolViewpager);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_discover;
    }

}
