package com.mph.basedroid.view;

import android.content.Context;

import com.mph.library.view.pickerview.OptionsPickerView;

import java.util.ArrayList;

/**
 * Created by：hcs on 2016/9/21 14:37
 * e_mail：aaron1539@163.com
 */
public class SexSelectView {

    private OptionsPickerView<String> sexPickerView;
    private ArrayList<String> sexList;

    private OnSexListener onSexListener;

    public SexSelectView(Context context){
            sexList = new ArrayList<>();
            sexList.add("男");
            sexList.add("女");
            sexPickerView = new OptionsPickerView<>(context);
            sexPickerView.setPicker(sexList);
            sexPickerView.setSelectOptions(0);
        sexPickerView.setTitle("选择性别");
            sexPickerView.setCyclic(false);
            sexPickerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3) {
                    if(onSexListener!=null){
                        onSexListener.onSelectSex(sexList.get(options1));
                    }
                }
            });
    }

    public void showSexPickerView(){
        if(!sexPickerView.isShowing()){
            sexPickerView.show();
        }
    }

    public void setOnSexListener(OnSexListener onSexListener) {
        this.onSexListener = onSexListener;
    }

    public interface OnSexListener{
        void onSelectSex(String sex);
    }

}
