package com.xll.mvplib.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.lang.ref.WeakReference;

/**
 *
 * Created by wujinpeng on 2016/11/3.
 */
public class AsyncDrawable extends BitmapDrawable {

    private WeakReference<BitmapWorkerTask> mBitmapWorkerTaskWeakReference;

    public AsyncDrawable(Resources res, Bitmap bitmap, BitmapWorkerTask bitmapWorkerTask){
        super(res, bitmap);
        mBitmapWorkerTaskWeakReference = new WeakReference<>(bitmapWorkerTask);
    }

    public BitmapWorkerTask getBitmapWorkerTaskWeakReference() {
        return mBitmapWorkerTaskWeakReference.get();
    }
}
