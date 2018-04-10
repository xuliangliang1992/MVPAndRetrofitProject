package com.xll.mvpandretrofit.http.progress;


import android.os.Looper;
import android.os.Message;

/**
 * 功能：
 * 作者：xll
 * 日期：2018/1/31
 * 邮箱：liangliang.xu1110@gmail.com
 */
public abstract class DownloadProgressHandler extends ProgressHandler{

    private static final int DOWNLOAD_PROGRESS = 1;
    protected ResponseHandler mHandler = new ResponseHandler(this, Looper.getMainLooper());

    @Override
    protected void sendMessage(ProgressBean progressBean) {
        mHandler.obtainMessage(DOWNLOAD_PROGRESS,progressBean).sendToTarget();

    }

    @Override
    protected void handleMessage(Message message){
        switch (message.what){
            case DOWNLOAD_PROGRESS:
                ProgressBean progressBean = (ProgressBean)message.obj;
                onProgress(progressBean.getBytesRead(),progressBean.getContentLength(),progressBean.isDone());

        }
    }


}
