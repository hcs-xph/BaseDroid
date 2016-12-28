package com.mph.basedroid.view;

import android.content.Context;

import com.mph.library.view.pickerview.OptionsPickerView;

import java.util.ArrayList;

/**
 * Created by：hcs on 2016/9/26 14:03
 * e_mail：aaron1539@163.com
 */
public class AgeSelectView {
    private final ArrayList<Integer> ageList;
    private OptionsPickerView<Integer> agePickerView;
    private OnAgeListener onAgeListener;

    public AgeSelectView(Context context){

        ageList = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            ageList.add(i);
        }
        agePickerView = new OptionsPickerView<>(context);
        agePickerView.setPicker(ageList);
        agePickerView.setSelectOptions(18);
        agePickerView.setTitle("选择年龄");
        agePickerView.setCyclic(false);
        agePickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                if(onAgeListener!=null){
                    onAgeListener.onSelectAge(ageList.get(options1)+"");
                }
            }
        });
    }

    public void showAgePickerView(){
        if(!agePickerView.isShowing()){
            agePickerView.show();
        }
    }

    public void setOnAgeListener(OnAgeListener onAgeListener) {
        this.onAgeListener = onAgeListener;
    }

    public interface OnAgeListener{
        void onSelectAge(String age);
    }
}
