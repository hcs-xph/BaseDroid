package com.mph.library.net.builder;


import com.mph.library.log.xLog;
import com.mph.library.net.MyOkHttp;
import com.mph.library.net.builder.request.OkHttpRequestBuilderHasParam;
import com.mph.library.net.callback.MyCallback;
import com.mph.library.net.response.IResponseHandler;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * post builder
 *
 */
public class PostBuilder extends OkHttpRequestBuilderHasParam<PostBuilder> {

    private String mJsonParams;

    public PostBuilder(MyOkHttp myOkHttp) {
        super(myOkHttp);
    }

    public PostBuilder jsonParams(String json){
        this.mJsonParams = json;
        return this;
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

            if(mJsonParams!=null && !"".equals(mJsonParams) && mParams!=null && !mParams.isEmpty()){
                throw new RuntimeException("json params and form params cannot be set at the same time");
            }

            if(mJsonParams!=null && !"".equals(mJsonParams)){
                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),mJsonParams);
                builder.post(body);
            }else if(mParams!=null && !mParams.isEmpty()){
                FormBody.Builder encodingBuilder = new FormBody.Builder();
                appendParams(encodingBuilder, mParams);
                builder.post(encodingBuilder.build());
            }else{
                RequestBody body = RequestBody.create(MediaType.parse("text/plain; charset=utf-8"),"");//default is string request body
                builder.post(body);
            }

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
