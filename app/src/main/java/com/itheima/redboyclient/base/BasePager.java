package com.itheima.redboyclient.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.itheima.redboyclient.R;

/**
 * 5个标签页的基类
 * 共性: 子类都有标题栏, 所以可以直接在父类中加载布局页面
 */
public class BasePager {
    public FrameLayout flContainer;
    public TextView tv_title;
    public ImageButton ibt_menu;
    /**
     * 主页面显示当前页面的根布局
     */
    public View rootView;
    /** 通过构造方法传进来的 上下文, 实际就是 FragmentActivity*/
    public Activity pagerActivity;
    public ImageButton btnDisplay;

    public BasePager(Activity activity) {
        this.pagerActivity = activity;
        rootView = initViews();
    }

    /**
     * 初始化布局
     *
     * @return
     */
    public View initViews() {
        View view = View.inflate(pagerActivity, R.layout.base_pager, null);
//        tv_title = (TextView) view.findViewById(R.id.tv_title);
//        ibt_menu = (ImageButton) view.findViewById(R.id.ibt_menu);
//        flContainer = (FrameLayout) view.findViewById(R.id.fl_container);
//        btnDisplay = (ImageButton) view.findViewById(R.id.btn_display);
        return view;
    }

    /**
     * 初始化数据
     */
    public void initDatas() {

    }
}
