package com.xll.mvpandretrofit.base;


import com.orhanobut.logger.Logger;
import com.xll.mvplib.base.APP;
import com.xll.mvplib.utils.FileUtils;

/**
 * 功能：
 * 作者：xll
 * 日期：2018/1/31
 * 邮箱：liangliang.xu1110@gmail.com
 */
public class MyApp extends APP {

    private static MyApp instance;

    public static MyApp getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //删掉照片信息
        boolean isDelete = FileUtils.deleteFile(FileUtils.IMAGE_SD_PATH);
        Logger.i(" isDelete = " + isDelete);
         /*CrashHandler crashHandler = new CrashHandler();
        crashHandler.init(getApplicationContext());*/

    }


}
