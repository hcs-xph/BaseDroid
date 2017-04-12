package com.mph.library.net.callback;


import com.mph.library.log.xLog;
import com.mph.library.net.MyOkHttp;
import com.mph.library.net.response.IResponseHandler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyCallback implements Callback {

    private IResponseHandler mResponseHandler;

    public MyCallback(IResponseHandler responseHandler) {
        mResponseHandler = responseHandler;
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        xLog.e("onFailure", e);
        MyOkHttp.mHandler.post(new Runnable() {
            @Override
            public void run() {
                mResponseHandler.onFailure(0, e.toString());
            }
        });
    }

    @Override
    public void onResponse(Call call, final Response response) {
        if(response.isSuccessful()) {
            mResponseHandler.onSuccess(response);
        } else {
            xLog.e("onResponse fail status=" + response.code());
            MyOkHttp.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mResponseHandler.onFailure(response.code(), "fail status=" + response.code());
                }
            });
        }
    }
}
