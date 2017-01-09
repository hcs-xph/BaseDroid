package com.mph.library.net.builder;


import com.mph.library.log.xLog;
import com.mph.library.net.MyOkHttp;
import com.mph.library.net.callback.MyCallback;
import com.mph.library.net.response.IResponseHandler;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * post builder
 *
 */
public class PostBuilder extends OkHttpRequestBuilderHasParam<PostBuilder> {

    public PostBuilder(MyOkHttp myOkHttp) {
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

            FormBody.Builder encodingBuilder = new FormBody.Builder();
            appendParams(encodingBuilder, mParams);

            builder.post(encodingBuilder.build());

            Request request = builder.build();

            mMyOkHttp.getOkHttpClient()
                    .newCall(request)
                    .enqueue(new MyCallback(responseHandler));
        } catch (Exception e) {
            xLog.e("Post enqueue error:" + e.getMessage());
            responseHandler.onFailure(0, e.getMessage());
        }
    }

    //append params to form builder
    private void appendParams(FormBody.Builder builder, Map<String, String> params) {

        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
    }
}
