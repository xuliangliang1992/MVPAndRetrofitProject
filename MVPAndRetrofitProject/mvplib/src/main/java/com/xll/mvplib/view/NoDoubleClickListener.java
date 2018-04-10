package com.xll.mvplib.view;

import android.view.View;

import java.util.Calendar;

/**
 * Created by wujinpeng on 2017/2/17.
 * 防止重复点击
 * Copyright © SAIC FINANCE All Rights Reserved
 */

public abstract class NoDoubleClickListener implements View.OnClickListener{

    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        }
    }

    public abstract void onNoDoubleClick(View v);
}
