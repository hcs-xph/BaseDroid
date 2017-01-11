package com.mph.basedroid.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.mph.basedroid.R;
import com.mph.library.base.activity.BaseActivity;
import com.mph.library.imgload.ILFactory;
import com.mph.library.imgload.ILoader;

import butterknife.BindView;

public class ImgActivity extends BaseActivity {

    @BindView(R.id.image)
    ImageView image;

    @Override
    public void initData(Bundle savedInstanceState) {
        ILoader.Options options = new ILoader.Options(R.mipmap.aa, R.mipmap.bb);
        ILFactory.getLoader().loadNet(image, "http://img.hb.aicdn.com/761f1bce319b745e663fed957606b4b5d167b9bff70a-nfBc9N_fw580", options);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_img;
    }
}
