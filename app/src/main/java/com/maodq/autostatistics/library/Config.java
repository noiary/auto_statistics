package com.maodq.autostatistics.library;

import android.support.annotation.ColorInt;

public class Config {
    public static final int MIN_SPEED = 40;
    public static final int MAX_SPEED = 90;

    @ColorInt public static final int COLOR_TITLE = 0xff333333;                  // 今日行驶xx里程
    @ColorInt public static final int COLOR_DATE = 0xffababab;                   // 日期
    @ColorInt public static final int COLOR_LESS_MIN = 0xff3c9de4;               // 小于40km
    @ColorInt public static final int COLOR_BETWEEN_MIN_AND_MAX = 0xff55c57d;    // 40-90km
    @ColorInt public static final int COLOR_GREATER_MAX = 0xffed4348;            // 大于90km
    @ColorInt public static final int COLOR_QUIET = 0xffe0e0e0;                  // 静止

    public static final String TITLE = "%s行驶 %s 公里";
    public static final float PROGRESS_HEIGHT = 13; // 进度条高度 (dp)
    public static final float TITLE_HEIGHT = 40; // 标题高度
    public static final int DATE_WIDTH = 50;    // 日期宽度
}
