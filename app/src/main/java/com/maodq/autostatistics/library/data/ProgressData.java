package com.maodq.autostatistics.library.data;

import android.support.annotation.ColorInt;

import static com.maodq.autostatistics.library.Config.*;

public class ProgressData {
    public float progress;  // 进度(0.0f - 1.0f)
    public float speed;     // 时速

    public  @ColorInt int getColor() {
        if (speed == 0.0f) {
            return COLOR_QUIET;
        } else if (speed < MIN_SPEED) {
            return COLOR_LESS_MIN;
        } else if (speed <= MAX_SPEED) {
            return COLOR_BETWEEN_MIN_AND_MAX;
        } else if (speed > MAX_SPEED) {
            return COLOR_GREATER_MAX;
        }

        return 0xffff0000; // FIXME: 2018/6/20 错误颜色
    }
}
