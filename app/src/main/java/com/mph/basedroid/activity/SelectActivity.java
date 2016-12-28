package com.mph.basedroid.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mph.basedroid.R;
import com.mph.basedroid.view.AgeSelectView;
import com.mph.basedroid.view.SexSelectView;
import com.mph.library.base.BaseActivity;
import com.mph.library.log.xLog;
import com.mph.library.view.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectActivity extends BaseActivity implements View.OnClickListener, AgeSelectView.OnAgeListener, SexSelectView.OnSexListener, TimePickerView.OnTimeSelectListener {

    @BindView(R.id.ageselect)
    Button ageselect;
    @BindView(R.id.sexselect)
    Button sexselect;
    @BindView(R.id.timeselect)
    Button timeselect;

    private AgeSelectView ageSelectView;
    private SexSelectView sexSelectView;
    private TimePickerView timePickerView;

    @Override
    public void initData(Bundle savedInstanceState) {
        ageSelectView = new AgeSelectView(this);
        sexSelectView = new SexSelectView(this);

        timePickerView = new TimePickerView(this,TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setRange(1800,2100);
        timePickerView.setTime(new Date());
        timePickerView.setCyclic(false);
        timePickerView.setCancelable(false);

    }

    @Override
    public void setListener() {
        ageselect.setOnClickListener(this);
        sexselect.setOnClickListener(this);
        timeselect.setOnClickListener(this);

        ageSelectView.setOnAgeListener(this);
        sexSelectView.setOnSexListener(this);
        timePickerView.setOnTimeSelectListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_select;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ageselect:
                ageSelectView.showAgePickerView();
                break;
            case R.id.sexselect:
                sexSelectView.showSexPickerView();
                break;
            case R.id.timeselect:
                timePickerView.show();
                break;
        }
    }

    @Override
    public void onSelectAge(String age) {
        xLog.d("SelectActivity","age-->"+age);
    }

    @Override
    public void onSelectSex(String sex) {
        xLog.d("SelectActivity","sex-->"+sex);
    }

    @Override
    public void onTimeSelect(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        xLog.d("SelectActivity","time-->"+format);
    }
}
