package com.mph.basedroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.mph.library.view.EmptyContentLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_showLoading;
    Button bt_showError;
    Button bt_showEmpty;
    Button bt_showContent;

    EmptyContentLayout emptyContentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_showLoading= (Button) findViewById(R.id.bt_showLoading);
        bt_showError= (Button) findViewById(R.id.bt_showError);
        bt_showEmpty= (Button) findViewById(R.id.bt_showEmpty);
        bt_showContent= (Button) findViewById(R.id.bt_showContent);
        emptyContentLayout= (EmptyContentLayout) findViewById(R.id.contentLayout);
        emptyContentLayout.emptyView(LayoutInflater.from(this).inflate(R.layout.view_empty,null));
        emptyContentLayout.errorView(LayoutInflater.from(this).inflate(R.layout.view_error,null));
        emptyContentLayout.loadingView(LayoutInflater.from(this).inflate(R.layout.view_loading,null));
        bt_showLoading.setOnClickListener(this);
        bt_showEmpty.setOnClickListener(this);
        bt_showError.setOnClickListener(this);
        bt_showContent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_showContent:
                emptyContentLayout.showContent();
                break;

            case R.id.bt_showEmpty:
                emptyContentLayout.showEmpty();
                break;

            case R.id.bt_showError:
                emptyContentLayout.showError();
                break;

            case R.id.bt_showLoading:
                emptyContentLayout.showLoading();
                break;
        }
    }
}
