package com.itheima.redboyclient.widget.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by MC on 2016/9/12.
 * 禁止滑动的ViewPager , 引用到 布局文件中 fragment_content.xml
 */
public class NoScrollViewPager extends ViewPager {
    private static final String TAG = "NoScrollViewPager";
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 重写 onTouchEvent 方法
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i(TAG, "NoScrollViewPager.onTouchEvent: 禁用最外层的 viewPager 滑动");
        //重写后以子类为准, 不执行父类的onTouchEvent方法
        //此处什么都不做, 从而达到禁用事件的目的
//        return super.onTouchEvent(ev);
        return true;    //直接返回true
    }

    /**
     * 重写事件的中断拦截
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "NoScrollViewPager.onInterceptTouchEvent: 不拦截新闻详情页签的滑动");
        //true表示拦截, false不拦截,传给子控件
        return false;   //最外层的标签页的ViewPager不拦截新闻菜单详情页里面页签的ViewPager
    }
}
