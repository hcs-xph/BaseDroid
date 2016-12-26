package com.mph.basedroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mph.basedroid.entity.HomeActivity;
import com.mph.library.base.BaseActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    public static final String APP_URL = "http://192.168.1.232:9190/api/";
    @BindView(R.id.postBtn)
    Button postBtn;
    @BindView(R.id.loadImg)
    Button loadImg;
    @BindView(R.id.viewpager)
    Button viewpager;
    @BindView(R.id.home)
    Button home;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setListener() {
        postBtn.setOnClickListener(this);
        loadImg.setOnClickListener(this);
        viewpager.setOnClickListener(this);
        home.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.postBtn:
                Intent intent1 = new Intent(MainActivity.this, CityActivity.class);
                startActivity(intent1);
                break;
            case R.id.loadImg:
                Intent intent2 = new Intent(MainActivity.this, ImgActivity.class);
                startActivity(intent2);
                break;
            case R.id.viewpager:
                Intent intent3 = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(intent3);
                break;
            case R.id.activity_home:
                Intent intent4 = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
