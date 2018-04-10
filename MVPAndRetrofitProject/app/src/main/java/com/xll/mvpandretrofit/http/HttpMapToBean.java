package com.xll.mvpandretrofit.http;


import com.xll.mvpandretrofit.http.responseBean.BaseResponse;

import io.reactivex.functions.Function;

/**
 * 功能：去掉通讯里面外层 获取数据bean
 * 作者：xll
 * 日期：2018/1/31
 * 邮箱：liangliang.xu1110@gmail.com
 */
public class HttpMapToBean<T> implements Function<BaseResponse<T>, T> {

    @Override
    public T apply(BaseResponse<T> tBaseResponse) throws Exception {
        return tBaseResponse.getObj();
    }
}
