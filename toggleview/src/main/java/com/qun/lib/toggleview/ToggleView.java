package com.qun.lib.toggleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class ToggleView extends View {

    private Bitmap mBackground;
    private Bitmap mSlider;
    private Paint mPaint;
    private boolean mChecked;
    private GestureDetector mGestureDetector;
    private Scroller mScroller;
    private boolean mIsMove;

    public ToggleView(Context context) {
        this(context, null);
    }

    public ToggleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToggleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ToggleView);
        Drawable backgroundDrawable = ta.getDrawable(R.styleable.ToggleView_cv_background);
        if (backgroundDrawable == null) {
            backgroundDrawable = context.getResources().getDrawable(R.drawable.switch_background);
        }
        mBackground = ((BitmapDrawable) backgroundDrawable).getBitmap();

        Drawable sliderDrawable = ta.getDrawable(R.styleable.ToggleView_cv_slider);
        if (sliderDrawable == null) {
            sliderDrawable = context.getResources().getDrawable(R.drawable.slide_button);
        }
        mSlider = ((BitmapDrawable) sliderDrawable).getBitmap();
        ta.recycle();
        setBackground(backgroundDrawable);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                if (mChecked) {
                    int dx = getMeasuredWidth() - mSlider.getWidth();
                    mScroller.startScroll(getScrollX(), 0, dx, 0, 500);
                } else {
                    int dx = getMeasuredWidth() - mSlider.getWidth();
                    mScroller.startScroll(0, 0, -dx, 0, 500);
                }
                mChecked = !mChecked;
                invalidate();
                return super.onSingleTapUp(e);
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }
        });
        mScroller = new Scroller(context, new LinearInterpolator());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mSlider, 0, 0, mPaint);
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
            mIsMove = true;
        } else {
            mIsMove = false;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mBackground.getWidth(), mBackground.getHeight());
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mBackground.getWidth(), heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, mBackground.getHeight());
        }
    }

    private int mLastX;
    private int mLastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int currentX = (int) event.getX();
                int currentY = (int) event.getY();
                Log.e("weiqun12345", "currentX=" + currentX + ",currentY=" + currentY);
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }


    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(boolean checked) {
        mChecked = checked;
        if (checked) {
            int dx = getMeasuredWidth() - mSlider.getWidth();
            Log.e("weiqun12345", "getMeasuredWidth=" + getMeasuredWidth() + "setChecked dx=" + dx);
            scrollTo(-dx, 0);
        } else {
            int dx = getMeasuredWidth() - mSlider.getWidth();
            scrollTo(0, 0);
        }
    }
}
