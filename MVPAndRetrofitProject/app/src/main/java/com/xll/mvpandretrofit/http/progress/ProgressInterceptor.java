package com.xll.mvpandretrofit.http.progress;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 功能：
 * 作者：xll
 * 日期：2018/1/31
 * 邮箱：liangliang.xu1110@gmail.com
 */

public class ProgressInterceptor implements Interceptor{
    private ProgressListener progressListener;

    public ProgressInterceptor(final ProgressHandler mProgressHandler){
        final ProgressBean progressBean = new ProgressBean();
        progressListener = new ProgressListener() {
            //该方法在子线程中运行
            @Override
            public void onProgress(long progress, long total, boolean done) {
                Log.d("progress:", String.format("%d%% done\n",(100 * progress) / total));
                if (mProgressHandler == null){
                    return;
                }

                progressBean.setBytesRead(progress);
                progressBean.setContentLength(total);
                progressBean.setDone(done);
                mProgressHandler.sendMessage(progressBean);

            }
        };
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder().body(
                new ProgressResponseBody(originalResponse.body(), progressListener))
                .build();
    }
}
