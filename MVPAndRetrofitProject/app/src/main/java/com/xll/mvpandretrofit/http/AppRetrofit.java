package com.xll.mvpandretrofit.http;

import com.xll.mvpandretrofit.constant.Constant;
import com.xll.mvpandretrofit.http.progress.ProgressHandler;
import com.xll.mvpandretrofit.http.progress.ProgressInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 功能：
 * 作者：xll
 * 日期：2018/1/31
 * 邮箱：liangliang.xu1110@gmail.com
 */

public class AppRetrofit {
    private Retrofit retrofit;
    private ProgressHandler mProgressHandler;

    public AppRetrofit(){
        retrofit = new Retrofit.Builder()
                .client(initBuilder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
    }

    /**
     * 带进度监听的请求
     * @param progressHandler
     */
    public AppRetrofit(ProgressHandler progressHandler){
        this.mProgressHandler = progressHandler;
        retrofit = new Retrofit.Builder()
                .client(initProgressBuilder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
    }

    private OkHttpClient.Builder initBuilder(){

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        String headerToken = "";
                        Request newRequest = chain.request().newBuilder()
//                                .addHeader("Connection", "close")
//                                .addHeader("Authorization", headerToken)
//                                .addHeader("AppVersion", LoanApp.APP_VERSION_NAME)//当前版本名称
//                                .addHeader("AppName","AJDL-PAD")//app名称
//                                //                                .addHeader("fingerPrint ", LoanApp.FINGERPRINT)
//                                .addHeader("OsVersion", LoanApp.OS_VERSION)//设备系统版本号
//                                .addHeader("DeviceBrand", LoanApp.BRAND)//设备品牌
//                                .addHeader("Model", LoanApp.MODEL)//设备机型
//                                .addHeader("Terminal","Android")//终端
                                .build();
                        return chain.proceed(newRequest);
                    }
                });
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(level(Constant.IS_LOG));
        builder.addInterceptor(interceptor);
        builder.connectTimeout(180, TimeUnit.SECONDS);
        builder.readTimeout(180, TimeUnit.SECONDS);
        builder.writeTimeout(180, TimeUnit.SECONDS);
        return builder;
    }

    /**
     * 初始化带进度监听的builder 这种情况下 如果下载的大文件
     * httpLoggingInterceptor 会出现内存溢出 所以需要把 日志打印给关掉
     * @return
     */
    private OkHttpClient.Builder initProgressBuilder(){

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        String headerToken = "";
                        Request newRequest = chain.request().newBuilder()
//                                .addHeader("Connection", "close")
//                                .addHeader("Authorization", headerToken)
//                                .addHeader("AppVersion", LoanApp.APP_VERSION_NAME)
//                                .addHeader("AppName","AJDL-PAD")
//                                //                                .addHeader("fingerPrint ", LoanApp.FINGERPRINT)
//                                .addHeader("OsVersion", LoanApp.OS_VERSION)
//                                .addHeader("DeviceBrand", LoanApp.BRAND)
//                                .addHeader("Model", LoanApp.MODEL)
//                                .addHeader("Terminal","Android")
                                .build();
                        return chain.proceed(newRequest);
                    }
                });
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(level(false));
        Interceptor progressInterceptor = new ProgressInterceptor(mProgressHandler);
        builder.networkInterceptors().add(progressInterceptor);
        builder.addInterceptor(progressInterceptor);
        builder.connectTimeout(180, TimeUnit.SECONDS);
        builder.readTimeout(300, TimeUnit.SECONDS);
        return builder;
    }

    private HttpLoggingInterceptor.Level level(boolean isDebug){
        return isDebug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE;
    }

    public FaceIDService getFaceIDService(){
        return retrofit.create(FaceIDService.class);
    }
}
