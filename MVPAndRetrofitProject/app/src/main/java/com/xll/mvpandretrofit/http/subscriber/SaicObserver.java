package com.xll.mvpandretrofit.http.subscriber;


import com.xll.mvplib.subscriber.HttpObserver;
import com.xll.mvplib.subscriber.SubscriberOnCompleteListener;
import com.xll.mvplib.subscriber.SubscriberOnErrorListener;

/**
 *
 * Created by wujinpeng on 2016/12/20.
 */

public abstract class SaicObserver<T> extends BaseObserver<T>{

    private SubscriberOnCompleteListener mOnCompleteListener;

    public SaicObserver(HttpObserver httpObserver) {
        super(httpObserver, null);
    }

    public SaicObserver(HttpObserver httpObserver, SubscriberOnErrorListener onErrorListener){
        super(httpObserver, onErrorListener);
    }

    public SaicObserver(HttpObserver httpObserver, SubscriberOnCompleteListener onCompleteListener){
        super(httpObserver, null);
        this.mOnCompleteListener = onCompleteListener;
    }

    @Override
    public void onComplete() {
        if (mOnCompleteListener != null){
            mOnCompleteListener.onComplete();
        }
    }

}
