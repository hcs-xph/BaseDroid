package com.mph.basedroid.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.RadioGroup;

import com.mph.basedroid.R;
import com.mph.basedroid.fragment.CustomTingFragment;
import com.mph.basedroid.fragment.DiscoverFragment;
import com.mph.basedroid.fragment.DownloadFragment;
import com.mph.basedroid.fragment.PersonalFragment;
import com.mph.library.base.activity.BaseFragmentActivity;
import com.mph.library.base.adapter.FragmentTabAdapter;
import com.mph.library.log.xLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Home2Activity extends BaseFragmentActivity {


    @BindView(R.id.main2_tab_bar)
    RadioGroup main2TabBar;

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {
        fragments.add(new DiscoverFragment());
        fragments.add(new CustomTingFragment());
        fragments.add(new DownloadFragment());
        fragments.add(new PersonalFragment());

        FragmentTabAdapter adapter = new FragmentTabAdapter(this,fragments,R.id.home2_container,main2TabBar);
        adapter.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener(){
            @Override
            public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
                xLog.d("Home2","index-->"+index);
            }
        });
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home2;
    }

}
