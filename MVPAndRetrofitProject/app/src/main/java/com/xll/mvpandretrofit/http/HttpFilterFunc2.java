package com.xll.mvpandretrofit.http;

import android.os.Handler;
import android.os.Looper;

import com.xll.mvpandretrofit.base.MyApp;
import com.xll.mvpandretrofit.dialog.DialogManager;
import com.xll.mvplib.utils.ToastUtil;

import java.util.Map;

import io.reactivex.functions.Predicate;

/**
 * 功能：过滤器
 * 作者：xll
 * 日期：2018/1/31
 * 邮箱：liangliang.xu1110@gmail.com
 */
public class HttpFilterFunc2 implements Predicate<Map<String, Object>> {

    @Override
    public boolean test(Map<String, Object> map) throws Exception {
        if (map == null) {
            return false;
        }
        boolean success = (boolean) map.get("success");
        if (!success) {
            final String retMsg = (String) map.get("retMsg");
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    DialogManager.getInstance().dismissProgressDialog();
                    ToastUtil.showToast(MyApp.getInstance().getApplicationContext(), retMsg);
                }
            });
            return false;
        }
        return true;
    }
}
