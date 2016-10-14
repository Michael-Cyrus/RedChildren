package com.itheima.redboyclient.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Window;
import android.view.WindowManager;

import com.itheima.redboyclient.App;

import org.senydevpkg.utils.MyToast;
import org.senydevpkg.utils.NetworkUtils;

import butterknife.ButterKnife;

/**
 * ━━━━ Code is far away from ━━━━━━
 * 　　  () 　　　  ()
 * 　　  ( ) 　　　( )
 * 　　  ( ) 　　　( )
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━ bug with the XYY protecting━━━
 * <p>
 * Created by Seny on 2015/12/1.
 */
public abstract class BaseActivity extends FragmentActivity {

    protected SharedPreferences SP;
    protected SharedPreferences.Editor EDIT;
    protected FragmentManager FM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        //界面中如果有EditText，默认隐藏输入法
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //初始化通用的SP&EDIT
        SP = App.SP;
        EDIT = App.EDIT;

        //Fragment相关
        FM = getSupportFragmentManager();

        setContentView(initContentView());
        ButterKnife.inject(this);//初始化ButterKnife
        initViews();
        initListener();
        initDatas();

    }


    @Override
    protected void onResume() {
        super.onResume();
        checkNetworked();
    }

    protected boolean checkNetworked() {
        if (!NetworkUtils.checkNetwork(this)) {
            MyToast.show(getApplicationContext(), "手机无可用网络！");
            return false;
        }

        return true;
    }

    /**
     * 初始化contentView
     *
     * @return 返回contentView的layout id
     */
    protected abstract int initContentView();

    /** 初始化布局, 必须由子类去实现 */
    public abstract void initViews() ;

    /**
     * 初始化数据, 子类可以不实现
     */
    public void initDatas(){

    }


    /**
     * 初始化监听器
     */
    protected void initListener() {

    }


}
