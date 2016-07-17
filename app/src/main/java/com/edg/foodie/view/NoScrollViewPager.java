package com.edg.foodie.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 作者：Aller  2016/7/17 16:03
 * <p/>
 * 邮箱：1105894953@qq.com
 * <p/>
 * 描述：
 */
public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public NoScrollViewPager(Context context) {
        super(context);

    }
    //表示事件是否拦截，返回false表示不拦截，事件可以往下传递
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }
    //表示事件的点击事件，返回false表示什么都不用做
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }
}
