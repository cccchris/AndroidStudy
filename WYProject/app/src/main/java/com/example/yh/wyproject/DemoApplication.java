package com.example.yh.wyproject;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.lzy.okhttputils.OkHttpUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.x;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.example.yh.wyproject.net.FlowAPI;
import com.example.yh.wyproject.utils.Constants;
//import cn.tthud.taitian.net.FlowAPI;
//import cn.tthud.taitian.utils.Constants;

/**
 * Created by wb on 2017/10/7.
 */

public class DemoApplication extends Application {

    private List<Activity> mList = new LinkedList<>();

    public static Context applicationContext;
    private static DemoApplication instance;
    private MainActivity mainActivity;

    @Override
    public void onCreate() {
        MultiDex.install(this);
        super.onCreate();

        applicationContext = this;
        instance = this;

        // 腾讯bugly
        CrashReport.initCrashReport(getApplicationContext(), "9690120717", true);

        Config.DEBUG = true;
        initUM();

        // 初始化
        OkHttpUtils.init(this);

        initTools();
        initExt();
    }

    private void initUM() {
        UMShareAPI.get(this);
        PlatformConfig.setWeixin(Constants.WX_APP_ID, "17e5b3674552080fc3430d5825560bc9");
    }

    public static DemoApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void addActivity(Activity activity){
        mList.add(activity);
    }

    public void closeActivitys() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
            mList.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initTools(){
        String out_file_path = FlowAPI.YYW_FILE_PATH;
        File dir = new File(out_file_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public void initExt(){
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
