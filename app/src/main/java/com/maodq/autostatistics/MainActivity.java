package com.maodq.autostatistics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.maodq.autostatistics.library.AutoStatisticsView;
import com.maodq.autostatistics.library.data.AutoData;
import com.maodq.autostatistics.library.data.ProgressData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private AutoStatisticsView asv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asv = findViewById(R.id.asv);
        init();
    }

    private void init() {
        asv.setData(getTestData());
    }

    private List<AutoData> getTestData() {
        List<AutoData> data = new ArrayList<>();

        float lastSpeed = 0.0f;
        for (int i = 0; i < 7; i++) {
            AutoData autoData = new AutoData();
            autoData.distance = String.valueOf((int) (Math.random() * 10000));
            autoData.date = getDayByIndex(i);
            autoData.progressData = new ArrayList<>();
            for (int j = 0; j < 100; j++) {
                ProgressData d = new ProgressData();
                d.progress = j / 100f;
                d.speed = Math.random() > 0.4d ? lastSpeed : Math.random() > 0.5 ? 0.0f : (float) (Math.random() * 120f);
                lastSpeed = d.speed;
                autoData.progressData.add(d);
            }
            data.add(autoData);
        }
        return data;
    }

    private String getDayByIndex(int index) {
        long currentTimeMillis = System.currentTimeMillis() - 3600 * 1000 * 24 * index;
        Date date = new Date(currentTimeMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日", Locale.CHINA);
        return dateFormat.format(date);
    }

}
