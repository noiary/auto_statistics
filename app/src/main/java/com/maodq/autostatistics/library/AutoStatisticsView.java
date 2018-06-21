package com.maodq.autostatistics.library;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maodq.autostatistics.R;
import com.maodq.autostatistics.library.data.AutoData;

import java.util.List;

import static com.maodq.autostatistics.library.Config.COLOR_TITLE;
import static com.maodq.autostatistics.library.Config.TITLE;

public class AutoStatisticsView extends LinearLayoutCompat {

    private int mProgressHeight; // 进度条高度
    private int mTitleHeight;   // 标题高度
    private TextView mHeaderView; // 今日行驶xx公里


    private List<AutoData> mData;
    private int mDateWidth;

    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override public void onClick(int index, StatisticsView v) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (child instanceof StatisticsView && child.isSelected()) {
                    if (child == v) return;
                    child.setSelected(false);
                }
            }

            v.setSelected(true);
            AutoData autoData = mData.get(index);
            if (autoData != null) {
                setHeaderTitle(autoData.date, autoData.distance);
            }
        }
    };

    public AutoStatisticsView(Context context) {
        this(context, null);
    }

    public AutoStatisticsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoStatisticsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        float density = context.getResources().getDisplayMetrics().density;
        setPadding((int) (density * 8), 0, ((int) density * 36), 0);
        mProgressHeight = (int) (density * Config.PROGRESS_HEIGHT + 0.5);
        mTitleHeight = (int) (density * Config.TITLE_HEIGHT + 0.5);
        mDateWidth = (int) (density * Config.DATE_WIDTH + 0.5);
    }

    public void setData(List<AutoData> data) {
        mData = data;
        if (mData == null) {
            return;
        }

        removeAllViews();
        // title (今日行驶xxx公里)
        setHeaderTitle("今日", mData.get(0).distance);
        addView(getHeaderView(getContext()),
                generateLayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mTitleHeight));

        // 日期
        addView(getDescView(getContext(), "日期"), generateLayoutParams(mDateWidth, mProgressHeight));

        // 图表
        for (int i = 0; i < mData.size(); i++) {
            AutoData autoData = mData.get(i);
            final StatisticsView child = new StatisticsView(getContext(), autoData);
            final int index = i;
            child.setOnClickListener(new OnClickListener() {
                @Override public void onClick(View v) {
                    mOnItemClickListener.onClick(index, child);
                }
            });
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    mProgressHeight);
            layoutParams.topMargin = mProgressHeight;
            addView(child, layoutParams);
        }
        // 时间
        View timeRow = LayoutInflater.from(getContext()).inflate(R.layout.auto_time_row, this, false);
        addView(timeRow);

        // 底部四个描述长方形
        View bottom = LayoutInflater.from(getContext()).inflate(R.layout.auto_bottom_row, this, false);
        addView(bottom);

    }

    private View getDescView(Context context, String text) {
        TextView tv = new TextView(context);
        tv.setText(text);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        tv.setTextColor(COLOR_TITLE);
        tv.setGravity(Gravity.RIGHT);
        return tv;
    }

    private LinearLayout.LayoutParams generateLayoutParams(int width, int height) {
        return new LinearLayout.LayoutParams(width, height);
    }


    private void setHeaderTitle(String date, String distance) {
        TextView headerView = getHeaderView(getContext());
        if (TextUtils.isEmpty(date)) {
            date = "今日";
        }
        // FIXME: 2018/6/20 用span设置distance颜色
        headerView.setText(String.format(TITLE, date, distance));
    }

    private TextView getHeaderView(Context context) {
        if (mHeaderView == null) {
            mHeaderView = new TextView(context);
            mHeaderView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            mHeaderView.setTextColor(COLOR_TITLE);
            mHeaderView.setGravity(Gravity.CENTER);
        }

        return mHeaderView;
    }

    private interface OnItemClickListener {
        void onClick(int index, StatisticsView v);
    }
}
