package com.mph.library.net.response;

import okhttp3.Response;

/**
 * 返回结果处理回调接口
 */
public interface IResponseHandler {

    void onSuccess(Response response);

    void onFailure(int statusCode, String error_msg);

    void onProgress(long currentBytes, long totalBytes);
}
