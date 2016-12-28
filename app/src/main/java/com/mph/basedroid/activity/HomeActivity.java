package com.mph.basedroid.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.mph.basedroid.R;
import com.mph.basedroid.fragment.CustomTingFragment;
import com.mph.basedroid.fragment.DiscoverFragment;
import com.mph.basedroid.fragment.DownloadFragment;
import com.mph.basedroid.fragment.PersonalFragment;
import com.mph.library.base.BaseActivity;
import com.mph.library.base.BaseFragmentActivity;

import butterknife.BindView;

public class HomeActivity extends BaseFragmentActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.main_tab_bar)
    RadioGroup mainTabBar;

    private Fragment[] fragments;

    @Override
    public void initData(Bundle savedInstanceState) {
        fragments = new Fragment[4];
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();
        if(savedInstanceState != null){
            int len = fragments.length;
            for (int i = 0; i < len; i++) {
                fragments[i] = manager.findFragmentByTag("f"+i);
                tx.hide(fragments[i]);
            }
        }else{
            fragments[0] = new DiscoverFragment();
            fragments[1] = new CustomTingFragment();
            fragments[2] = new DownloadFragment();
            fragments[3] = new PersonalFragment();
            int length = fragments.length;
            for (int i = 0; i < length; i++) {
                tx.add(R.id.home_container,fragments[i],"f"+i);
                tx.hide(fragments[i]);
            }
        }
        tx.show(fragments[0]);
        tx.commit();
    }

    @Override
    public void setListener() {
        mainTabBar.setOnCheckedChangeListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int index = 0;
        switch (i){
            case R.id.main_tab_item_discover:
                index = 0;
                break;
            case R.id.main_tab_item_custom:
                index = 1;
                break;
            case R.id.main_tab_item_download:
                index = 2;
                break;
            case R.id.main_tab_item_personal:
                index = 3;
                break;
        }

        int length = fragments.length;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();
        for (int j = 0; j < length; j++) {
            if(j == index){
                tx.show(fragments[j]);
            }else{
                tx.hide(fragments[j]);
            }
        }
        tx.commit();
    }
}
