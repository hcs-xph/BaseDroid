package com.mph.basedroid.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.mph.basedroid.R;
import com.mph.basedroid.widget.CircleImageView;
import com.mph.library.base.activity.BaseActivity;
import com.mph.library.imgload.ILFactory;
import com.mph.library.imgload.ILoader;

import butterknife.BindView;

public class CircleImgActivity extends BaseActivity {

    public static final String img="http://img.hb.aicdn.com/6a2d1ea6c5ec5e2a67e1873c4c8e85abc6e48c739730f-DG4EbV_fw658";

    @BindView(R.id.cimg)
    CircleImageView cimg;

    @Override
    public void initData(Bundle savedInstanceState) {
        ILoader.Options options = new ILoader.Options(R.mipmap.aa,R.mipmap.load_failed);
        ILFactory.getLoader().loadNetCircle(cimg,img,options);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_circle_img;
    }
}
