package com.example.yh.wyproject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.FileCallback;
import com.lzy.okhttputils.request.BaseRequest;


import org.xutils.http.RequestParams;

import java.io.File;
import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.yh.wyproject.base.CheckPermissionsActivity;

import com.example.yh.wyproject.bean.UserBean;
import com.example.yh.wyproject.net.rxbus.RxBus;
import com.example.yh.wyproject.net.rxbus.RxBusBaseMessage;
import com.example.yh.wyproject.net.rxbus.RxCodeConstants;
import com.example.yh.wyproject.utils.Log;
import com.example.yh.wyproject.utils.NetUtils;
import com.example.yh.wyproject.utils.SPUtils;
import com.example.yh.wyproject.widget.CustomAlertTowDialog;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * 开屏页
 *
 */
public class SplashActivity extends CheckPermissionsActivity {

	private CompositeSubscription mCompositeSubscription;
	private static final int sleepTime = 2000;
	private CustomAlertTowDialog ad;
	private boolean isStart = false;
	private boolean isInit = false;

	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.em_activity_splash);
		super.onCreate(arg0);
	}

	@Override
	protected void onResume() {
		// 先检测网络，网络不通弹出提示对话框，并卡住
		if (!NetUtils.isConnected(SplashActivity.this)) {
			super.onResume();
			ad = new CustomAlertTowDialog(SplashActivity.this, R.style.dialog, "检查网络设置是否正常", new CustomAlertTowDialog.ViewClickListener() {
				@Override
				public void onClick(View view) {
					switch (view.getId()) {
						case R.id.tv_cancel:
							ad.dismiss();
							android.os.Process.killProcess(android.os.Process.myPid());
							System.exit(0);
							break;
						case R.id.tv_sure:
							ad.dismiss();
							NetUtils.openSetting(SplashActivity.this);
							break;
					}
				}
			});
			ad.setCancelable(false);
			ad.show();
			return;
		} else {
			if (ad != null) {
				ad.dismiss();
			}
		}
		initRxBus();
		super.onResume();
	}

	/**
	 * 获取到权限之后进行得操作
	 */
	private void initRxBus() {
		Log.i("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		addSubscription(RxBus.getDefault().toObservable(RxCodeConstants.APP_GET_PERMISSION, RxBusBaseMessage.class)
				.subscribe(new Action1<RxBusBaseMessage>() {
					@Override
					public void call(RxBusBaseMessage integer) {
						doLogin();
					}
				})
		);
	}

	DialogInterface.OnClickListener exitListener = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);
		}
	};

	DialogInterface.OnClickListener netListener = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			NetUtils.openSetting(SplashActivity.this);
		}
	};

	DialogInterface.OnClickListener retryListener = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			onResume();
		}
	};

	@Override
	protected void onStart() {
		super.onStart();
	}

	public void doLogin(){
		DemoApplication.getInstance().closeActivitys();
		Intent intent = new Intent(SplashActivity.this, MainActivity.class);
		intent.putExtra("extra_index",0);
		startActivity(intent);
		finish();
	}

	public void addSubscription(Subscription s) {
		if (this.mCompositeSubscription == null) {
			this.mCompositeSubscription = new CompositeSubscription();
		}
		this.mCompositeSubscription.add(s);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		removeSubscription();
	}

	public void removeSubscription() {
		if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
			this.mCompositeSubscription.unsubscribe();
		}
	}

}
