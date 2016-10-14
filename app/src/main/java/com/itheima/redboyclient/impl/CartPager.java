package com.itheima.redboyclient.impl;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itheima.redboyclient.base.BasePager;

/**
 * Created by MC on 2016/9/12.
 */
public class CartPager extends BasePager {
    private static final String TAG = "HomePager";
    public CartPager(Activity activity) {
        super(activity);
    }

    /**
     * 给首页添加布局
     */
    @Override
    public void initDatas() {
        super.initDatas();
        Log.i(TAG, "HomePager.initDatas: 首页初始化了...");
        /*------给空的帧布局动态添加布局对象--------*/

        TextView view = new TextView(pagerActivity);
        view.setText("首页");
        view.setTextColor(Color.RED);
        view.setTextSize(40f);
        view.setGravity(Gravity.CENTER);    //居中显示
        flContainer.addView(view);          //给帧布局添加对象
        //修改标题
        tv_title.setText("智慧北京");

        //隐藏菜单按钮
        ibt_menu.setVisibility(View.GONE);
    }
}
