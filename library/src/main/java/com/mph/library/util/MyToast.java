package com.mph.library.util;

import android.content.Context;

/**
 * Created by：hcs on 2017/3/2 13:24
 * e_mail：aaron1539@163.com
 */
public class MyToast {
    private android.widget.Toast mToast;

    public void showToast(Context context, String msg){
        if(mToast == null){
            mToast = android.widget.Toast.makeText(context,msg, android.widget.Toast.LENGTH_SHORT);
        }else{
            mToast.setText(msg);
        }
        mToast.show();
    }
}
