package com.itheima.redboyclient.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * 包信息的工具类
 */
public class PackgeInfoPaser {

    /**
     * 获取应用程序apk包的版本信息
     *
     * @param context 上下文
     * @return apk的版本号
     */
    public static String getPackageInfo(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String version = packageInfo.versionName;    //是一个版本的描述, 展示给用户看的
            //int versionCode = packageInfo.versionCode;	//是方便程序开发者运行和维护Application而设置的一个有效的值
            return version;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "解析失败";
        }
    }
}
