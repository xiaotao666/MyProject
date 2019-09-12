package com.jsyrdb.yiren.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.haibin.calendarview.CalendarLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class MyRefreshLayout extends SmartRefreshLayout {
    private boolean isExpand = false;
    private int mLastXIntercepted;
    private int mLastYIntercepted;

    public MyRefreshLayout(Context context) {
        super(context);
    }

    public MyRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                mLastXIntercepted = x;
                mLastYIntercepted = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int y2 = (int) ev.getY();
                int deltaY = y2 - mLastYIntercepted;
                if (deltaY > 0 && Math.abs(deltaY) > mTouchSlop) {
                    /*下拉*/
                    if (isExpand) {
                        intercepted = true;
                    } else {
                        intercepted = false;
                    }

                }

                break;
            case MotionEvent.ACTION_UP:
//                final int deltaY = x - mLastYIntercepted;
//                if (deltaY > 0 && Math.abs(deltaY) > mTouchSlop) {
//                    /*下拉*/
//                    if (isExpand) {
//                        intercepted = true;
//                    } else {
//                        intercepted = false;
//                    }
//
//                }
                intercepted = false;
                break;
        }
        mLastXIntercepted = x;
        mLastYIntercepted = y;
        return intercepted;
    }

    public void setExpand(boolean isExpand) {
        this.isExpand = isExpand;
    }
}
