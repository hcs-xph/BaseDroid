package com.mph.library.net.builder;


import com.mph.library.log.xLog;
import com.mph.library.net.MyOkHttp;
import com.mph.library.net.builder.request.OkHttpRequestBuilder;
import com.mph.library.net.callback.MyCallback;
import com.mph.library.net.response.IResponseHandler;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * put builder
 *
 */
public class PutBuilder extends OkHttpRequestBuilder<PutBuilder> {

    public PutBuilder(MyOkHttp myOkHttp){
        super(myOkHttp);
    }

    @Override
    public void enqueue(IResponseHandler responseHandler) {
        try {
            if(mUrl == null || mUrl.length() == 0) {
                throw new IllegalArgumentException("url can not be null !");
            }

            Request.Builder builder = new Request.Builder().url(mUrl);
            appendHeaders(builder, mHeaders);

            if (mTag != null) {
                builder.tag(mTag);
            }

            builder.put(RequestBody.create(MediaType.parse("text/plain;charset=utf-8"), ""));

            Request request = builder.build();

            mMyOkHttp.getOkHttpClient()
                    .newCall(request)
                    .enqueue(new MyCallback(responseHandler));
        } catch (Exception e) {
            xLog.e("Put enqueue error:" + e.getMessage());
            responseHandler.onFailure(0, e.getMessage());
        }
    }
}