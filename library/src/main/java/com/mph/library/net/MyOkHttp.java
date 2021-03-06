package com.mph.library.net;

import android.os.Handler;
import android.os.Looper;

import com.mph.library.net.builder.DeleteBuilder;
import com.mph.library.net.builder.DownloadBuilder;
import com.mph.library.net.builder.GetBuilder;
import com.mph.library.net.builder.PatchBuilder;
import com.mph.library.net.builder.PostBuilder;
import com.mph.library.net.builder.PutBuilder;
import com.mph.library.net.builder.UploadBuilder;

import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

/**
 * MyOkhttp
 *
 */
public class MyOkHttp {

    public static Handler mHandler = new Handler(Looper.getMainLooper());

    private static OkHttpClient mOkHttpClient;

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public MyOkHttp()
    {
        this(null);
    }

    public MyOkHttp(OkHttpClient okHttpClient)
    {
        if(mOkHttpClient == null) {
            synchronized (MyOkHttp.class) {
                if (mOkHttpClient == null) {
                    if (okHttpClient == null) {
                        mOkHttpClient = new OkHttpClient();
                    } else {
                        mOkHttpClient = okHttpClient;
                    }
                }
            }
        }
    }

    public GetBuilder get() {
        return new GetBuilder(this);
    }

    public PostBuilder post() {
        return new PostBuilder(this);
    }

    public PutBuilder put(){
        return new PutBuilder(this);
    }

    public PatchBuilder patch(){
        return new PatchBuilder(this);
    }

    public DeleteBuilder delete(){
        return new DeleteBuilder(this);
    }

    public UploadBuilder upload() {
        return new UploadBuilder(this);
    }

    public DownloadBuilder download() {
        return new DownloadBuilder(this);
    }

    /**
     * 根据tag取消对应的请求
     * @param tag tag
     */
    public void cancel(Object tag) {
        Dispatcher dispatcher = mOkHttpClient.dispatcher();
        for (Call call : dispatcher.queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : dispatcher.runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }
}
