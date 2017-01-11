package com.mph.basedroid.activity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mph.basedroid.MyApp;
import com.mph.basedroid.R;
import com.mph.library.base.activity.BaseActivity;
import com.mph.library.log.xLog;
import com.mph.library.net.MyOkHttp;
import com.mph.library.net.response.DownloadResponseHandler;

import java.io.File;

import butterknife.BindView;

public class DownloadFileActivity extends BaseActivity implements View.OnClickListener {

    public static final String fileUrl = "http://ivy.pconline.com.cn/click?adid=434690&id=pc.xz.android.zd.tl1.&__uuid=10220796";
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.tv_progress)
    TextView tvProgress;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.startDownload)
    Button startDownload;
    @BindView(R.id.cancelDownload)
    Button cancelDownload;
    private MyOkHttp okHttp;


    @Override
    public void initData(Bundle savedInstanceState) {
        okHttp = MyApp.getInstance().getOkHttp();
        progressbar.setIndeterminate(false);
        progressbar.setMax(100);
    }

    @Override
    public void setListener() {
        startDownload.setOnClickListener(this);
        cancelDownload.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_download_file;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startDownload:
                downloadFile();
                break;
            case R.id.cancelDownload:
                okHttp.cancel(this);
                break;
        }
    }

    private void downloadFile() {
        okHttp.download().url(fileUrl).tag(this).filePath(Environment.getExternalStorageDirectory()+"/testbase/kyw.apk")
                .enqueue(new DownloadResponseHandler() {

                    int rate = 0;

                    @Override
                    public void onFinish(File downloadFile) {
                        name.setText("下载完成");
                    }

                    @Override
                    public void onProgress(long currentBytes, long totalBytes) {
                        rate = (int)(((float)currentBytes/totalBytes)*100);
                        tvProgress.setText(rate+"%");
                        progressbar.setProgress(rate);
                        xLog.d("download","下载量-->"+(float)(currentBytes/1024/1024.0));
                    }

                    @Override
                    public void onFailure(String error_msg) {

                    }

                    @Override
                    public void onCancel() {
                        progressbar.setProgress(0);
                        tvProgress.setText("");
                        name.setText("正在下载");
                        xLog.d("download","下载请求取消");
                    }
                });
    }
}
