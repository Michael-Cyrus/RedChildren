package com.itheima.redboyclient.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.utils.PackgeInfoPaser;
import com.itheima.redboyclient.utils.StreamTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class SplashActivity extends Activity {

	public static final int SUCCESS = 0;
	public static final int SHOW_UPDATE_DIALOG = 1;
	public static final int ERROR = 2;
	private TextView tv_version_name;
	String localVersion;		//客户端版本信息

	//服务器下载地址
	public String downloadpath;

	//打印日志
	private String TAG = "SplashActivity";
	//读取flag状态是否每次打开联网更新
	SharedPreferences sp;

	/**
	 * 接收子线程的信息, 进行处理
	 */
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case SUCCESS:	//开启主界面
					loadMainUI();
					break;

				case SHOW_UPDATE_DIALOG:	//提示用户更新
					String desc = (String) msg.obj;
				//	showUpdateDialog(desc);
					break;
				case ERROR:		//提示错误码
					Log.i(TAG, "错误信息：" + msg.obj);
					//MyToast.setToastByTime(SplashActivity.this, "错误信息：" + msg.obj, 1000);
					loadMainUI();
					break;
			}
			super.handleMessage(msg);
		}


	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);//隐藏标题
		setContentView(R.layout.activity_splash);
		localVersion = PackgeInfoPaser.getPackageInfo(this);
		Log.i(TAG , "手机端版本: " + localVersion);
		tv_version_name = (TextView) findViewById(R.id.tv_version_name);
		tv_version_name.setText("版本号:" + localVersion);

		sp = getSharedPreferences("config", MODE_PRIVATE);
		//读取flag状态是否每次打开联网更新
		boolean update = sp.getBoolean("update", true);
		if(update){	//自动跟新为开启状态
			/**
			 * 开启一条子线程检查版本是否需要更新
			 */
			new Thread(new CheckVersionTask()).start();
		}else{		//自动跟新为关闭状态, 延时3000毫秒进入主界面
			//延时是耗时操作需要在子线程中完成
			new Thread(){
				public void run() {
					SystemClock.sleep(2000);
					loadMainUI();
				};
			}.start();
		}
	}


/*	protected void showUpdateDialog(String desc) {
		AlertDialog.Builder builder = new Builder(this);
		builder.setCancelable(false);	//设置点击对话框外面没有反应
		builder.setTitle("新版本提示");
		builder.setMessage(desc);
		builder.setPositiveButton("立即更新", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				*//*--------------------设置进度条, 提示下载进度-----------------*//*
				final ProgressDialog pd = new ProgressDialog(SplashActivity.this);
				pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);	//设置进度条的样式：水平
				//如果SD卡是挂载的状态, 即可读可写
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
					//设置安装包存放的路径
					File sdDir = Environment.getExternalStorageDirectory();
					File file = new File(sdDir, SystemClock.uptimeMillis() + ".apk");
					//采用开源框架xUtils下载安装包
					HttpUtils http = new HttpUtils();
					http.download(downloadpath, file.getAbsolutePath(), new RequestCallBack<File>() {

						@Override
						public void onStart() {
							Log.i(TAG, "开始下载安装包");
							pd.show();
							super.onStart();
						}

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							pd.dismiss();
							Log.i(TAG, "下载失败");
							MyToast.setToastByTime(SplashActivity.this, "下载失败", 500);
						}

						@Override
						public void onLoading(long total, long current,
											  boolean isUploading) {
							Log.i(TAG, "正在下载安装包");
							pd.setMax((int) total);
							pd.setProgress((int) current);
							MyToast.setToastByTime(SplashActivity.this, "正在下载安装包", 500);
							super.onLoading(total, current, isUploading);
						}

						@Override
						public void onSuccess(ResponseInfo<File> fileinfo) {
							Log.i(TAG, "下载成功");
							pd.dismiss();
							MyToast.setToastByTime(SplashActivity.this, "下载完毕", 1000);
							*//*----------覆盖安装apk文件---------------------*//*
							Intent intent = new Intent();
							intent.setAction("android.intent.action.VIEW");
							intent.addCategory("android.intent.category.DEFAULT");
							intent.setDataAndType(Uri.fromFile(fileinfo.result), "application/vnd.android.package-archive");
							//startActivity(intent);	//下载完毕如果取消安装会回到欢迎界面
							startActivityForResult(intent, 0);	//下载完毕如果取消安装会回到主界面
						}
					});
				}else{
					Log.i(TAG, "SD卡不可用， 请检查你的SD卡状态");
					MyToast.setToastByTime(SplashActivity.this, "SD卡不可用， 请检查你的SD卡状态", 1500);
					loadMainUI();
				}
			}
		});

		builder.setNegativeButton("稍后再说", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				loadMainUI();
			}
		});
		builder.show();		//显示对话框
	}*/

	/**
	 * 下载安装包后取消安装回到主界面
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		loadMainUI();
	}

	/**
	 * 加载主界面
	 */
	private void loadMainUI() {
		Intent intent = new Intent(SplashActivity.this, TopicActivity.class);
		startActivity(intent);
		finish();	//开启新界面后, 关闭当前界面
	}

	/**
	 * 获取服务器端的版本号信息
	 * 获取本地版本信息
	 * 比较是否相同
	 * 	相同则不需要更新; 不同弹出对话框, 提示是否更新
	 */
	private class CheckVersionTask implements Runnable {

		@Override
		public void run() {
			long startTime = System.currentTimeMillis();
			Message msg = Message.obtain();
			try {
				/**
				 * 获取服务器端的版本号信息
				 */
				//获取服务器端版本的存放路径
				String path = getResources().getString(R.string.url);
				URL url = new URL(path);	//获取URL路径
				//获取Http连接
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");	//设施请求方式
				conn.setConnectTimeout(2000);	//设置连接超时时间
//				conn.setReadTimeout(1000);		//设置读取超时时间
//				conn.connect();					//开始连接
				int code = conn.getResponseCode(); 	//获取请求码
				if(code == 200){	//请求成功
					//获取输入流
					InputStream is = conn.getInputStream();
					//解析存放版本信息的json文件获取版本信息
					String result = StreamTools.readStream(is);
					JSONObject object = new JSONObject(result);
					//获取服务器版本信息
					String serverVersion = object.getString("version");
					String description = object.getString("description");
					downloadpath = object.getString("downloadpath");
					Log.i(TAG, "服务器版本： " + serverVersion);
					if(serverVersion.equals(localVersion)){		//版本相同
						Log.i(TAG, "版本号一致,无需升级,进入程序主界面");

						msg.what = SUCCESS;
					}else{	//版本不同
						Log.i(TAG, "版本号不相同, 弹出对话框, 提示用户是否升级");
						msg.what = SHOW_UPDATE_DIALOG;
						msg.obj = description;
					}
				}else{		//请求失败
					msg.what = ERROR;
					msg.obj = "code:405";
				}
			} catch (NotFoundException e) {
				msg.what = ERROR;
				msg.obj = "code:406";
				e.printStackTrace();
			} catch (MalformedURLException e) {
				msg.what = ERROR;
				msg.obj = "code:407";
				e.printStackTrace();
			} catch (IOException e) {
				msg.what = ERROR;
				msg.obj = "code:408";
				e.printStackTrace();
			} catch (JSONException e) {
				msg.what = ERROR;
				msg.obj = "code:409";
				e.printStackTrace();
			}finally {
				long endTime = System.currentTimeMillis();
				long dTime = endTime - startTime;
				if(dTime < 2000){	//如果加载时间小于2秒, 继续延时到2秒中
					SystemClock.sleep(2000 - dTime);
				}
				handler.sendMessage(msg);
			}
		}
	}
}

