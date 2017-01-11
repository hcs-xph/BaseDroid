package com.mph.basedroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mph.basedroid.activity.ActionSheetActivity;
import com.mph.basedroid.activity.CityActivity;
import com.mph.basedroid.activity.DownloadFileActivity;
import com.mph.basedroid.activity.Home2Activity;
import com.mph.basedroid.activity.HomeActivity;
import com.mph.basedroid.activity.ImgActivity;
import com.mph.basedroid.activity.SelectActivity;
import com.mph.basedroid.activity.WelcomeActivity;
import com.mph.library.base.activity.BaseActivity;

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
    @BindView(R.id.home2)
    Button home2;
    @BindView(R.id.pickerview)
    Button pickerview;
    @BindView(R.id.actionsheet)
    Button actionsheet;
    @BindView(R.id.downloadfile)
    Button downloadfile;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setListener() {
        postBtn.setOnClickListener(this);
        loadImg.setOnClickListener(this);
        viewpager.setOnClickListener(this);
        home.setOnClickListener(this);
        home2.setOnClickListener(this);
        pickerview.setOnClickListener(this);
        actionsheet.setOnClickListener(this);
        downloadfile.setOnClickListener(this);
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
            case R.id.home:
                Intent intent4 = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent4);
                break;
            case R.id.home2:
                Intent intent5 = new Intent(MainActivity.this, Home2Activity.class);
                startActivity(intent5);
                break;
            case R.id.pickerview:
                Intent intent7 = new Intent(MainActivity.this, SelectActivity.class);
                startActivity(intent7);
                break;
            case R.id.actionsheet:
                Intent intent8 = new Intent(MainActivity.this, ActionSheetActivity.class);
                startActivity(intent8);
                break;
            case R.id.downloadfile:
                Intent intent9 = new Intent(MainActivity.this, DownloadFileActivity.class);
                startActivity(intent9);
                break;
        }
    }
}
