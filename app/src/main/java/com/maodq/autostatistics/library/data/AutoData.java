package com.maodq.autostatistics.library.data;

import java.util.List;

public class AutoData {
    public String distance; // 里程
    public String date;     // 日期
    public List<ProgressData> progressData;

    public int size() {
        return progressData != null ? progressData.size() : 0;
    }

    public ProgressData get(int i) {
        return progressData.get(i);
    }
}
