package com.mph.basedroid;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.mph.library.base.BaseActivity;
import com.mph.library.base.BasePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    BasePagerAdapter adapter;
    List<ImageView> datas = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {
        ImageView imageView;
        imageView = new ImageView(this);
        imageView.setBackgroundResource(R.mipmap.img_wel_0);
        datas.add(imageView);

        imageView = new ImageView(this);
        imageView.setBackgroundResource(R.mipmap.img_wel_1);
        datas.add(imageView);

        imageView = new ImageView(this);
        imageView.setBackgroundResource(R.mipmap.img_wel_2);
        datas.add(imageView);

        imageView = new ImageView(this);
        imageView.setBackgroundResource(R.mipmap.img_wel_3);
        datas.add(imageView);

        adapter = new BasePagerAdapter(datas);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }
}
