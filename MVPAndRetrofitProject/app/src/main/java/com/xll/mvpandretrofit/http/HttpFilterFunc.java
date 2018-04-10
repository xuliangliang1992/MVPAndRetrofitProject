package com.xll.mvpandretrofit.http;

import android.os.Handler;
import android.os.Looper;

import com.xll.mvpandretrofit.base.MyApp;
import com.xll.mvpandretrofit.dialog.DialogManager;
import com.xll.mvpandretrofit.http.responseBean.BaseResponse;
import com.xll.mvplib.utils.ToastUtil;

import io.reactivex.functions.Predicate;

/**
 * 功能：过滤器
 * 作者：xll
 * 日期：2018/1/31
 * 邮箱：liangliang.xu1110@gmail.com
 */
public class HttpFilterFunc<T> implements Predicate<BaseResponse<T>> {

    @Override
    public boolean test(BaseResponse<T> map) throws Exception {
        if (map == null) {
            return false;
        }
        if (!map.isSuccess()) {


            final String retMsg = map.getRetMsg();
            new Handler(Looper.getMainLooper()).post(
                    new Runnable() {
                        @Override
                        public void run() {
                            DialogManager.getInstance().dismissProgressDialog();
                            ToastUtil.showToast(MyApp.getInstance().getApplicationContext(), retMsg);
                        }
                    }
            );
            return false;
        }
        return true;
    }
}
