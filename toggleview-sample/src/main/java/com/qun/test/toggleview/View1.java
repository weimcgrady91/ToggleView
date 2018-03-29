package com.qun.test.toggleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class View1 extends View {

    private Paint mPaint;

    public View1(Context context) {
        this(context, null);
    }

    public View1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.RED);
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(200, 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,200,200,mPaint);
    }
}
