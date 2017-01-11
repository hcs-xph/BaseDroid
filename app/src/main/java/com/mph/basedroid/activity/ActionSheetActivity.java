package com.mph.basedroid.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mph.basedroid.R;
import com.mph.library.base.activity.BaseFragmentActivity;
import com.mph.library.log.xLog;
import com.mph.library.view.ActionSheet;

import butterknife.BindView;

public class ActionSheetActivity extends BaseFragmentActivity implements ActionSheet.ActionSheetListener, View.OnClickListener {


    @BindView(R.id.ios6)
    Button ios6;
    @BindView(R.id.ios7)
    Button ios7;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setListener() {
        ios6.setOnClickListener(this);
        ios7.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_action_sheet;
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
        xLog.d("ActionSheetActivity","是否取消了-->"+isCancel);
    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        xLog.d("ActionSheetActivity","点击了-->"+index);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ios6:
                setTheme(R.style.ActionSheetStyleiOS6);
                break;
            case R.id.ios7:
                setTheme(R.style.ActionSheetStyleiOS7);
                break;
        }
        ActionSheet.createBuilder(ActionSheetActivity.this, getSupportFragmentManager())
                .setCancelButtonTitle("Cancel")
                .setOtherButtonTitles("Item1", "Item2", "Item3", "Item4")
                .setCancelableOnTouchOutside(true)
                .setListener(ActionSheetActivity.this).show();
    }
}
