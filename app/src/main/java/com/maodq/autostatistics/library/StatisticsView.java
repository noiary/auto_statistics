package com.maodq.autostatistics.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.maodq.autostatistics.library.data.AutoData;
import com.maodq.autostatistics.library.data.ProgressData;

import static com.maodq.autostatistics.library.Config.COLOR_DATE;


public class StatisticsView extends View {
    private AutoData mData;
    private Paint mProgressPaint;
    private float mProgressDensity; // 进度条密度
    private Paint mDatePaint;
    private float density;

    public StatisticsView(Context context, AutoData autoData) {
        super(context);
        mData = autoData;
        init(context);
    }

    private void init(Context context) {
        density = context.getResources().getDisplayMetrics().density;
        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);

        mDatePaint = new Paint();
        mDatePaint.setColor(COLOR_DATE);
        mDatePaint.setTextSize(density * 12);
        mDatePaint.setAntiAlias(true);
    }

    public void setData(AutoData data) {
        mData = data;
        invalidate();
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mProgressDensity = getMeasuredWidth() / 100f;
    }

//        @Override
//        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//            super.onLayout(changed, left, top, right, bottom);
//            setTop(top);
//            setBottom(bottom);
//        }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mData == null) {
            return;
        }

        int width = (int) (Config.DATE_WIDTH * density + 12 * density);
        canvas.drawText(mData.date, 0, density * 12, mDatePaint);
        canvas.translate(width, 0);

        for (int i = 0; i < mData.size(); i++) {
            ProgressData data = mData.get(i);
//                canvas.save();

            float left = data.progress * getMeasuredWidth();
            mProgressPaint.setColor(data.getColor());
            canvas.drawRect(left, 0, left + mProgressDensity, getMeasuredHeight(), mProgressPaint);
        }
    }
}
