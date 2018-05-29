package com.bawei.Zhangjinfeng.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.bawei.Zhangjinfeng.model.db.AddrDao;
import com.bawei.zxinglibrary.activity.ZXingLibrary;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;


public class MYApplication extends Application{

    private static int myTid;
    private static Handler handler;
    private static Context context;


    {

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        myTid = Process.myTid();
        handler = new Handler();
        context = getApplicationContext();

        //初始化自己的异常捕获机制
        //CrashHandler.getInstance().init(this);

        //如果要使用腾讯的bugly处理异常...必须把自己的异常处理给注释掉

        //初始化友盟
        UMShareAPI.get(this);
        Config.DEBUG = true;//开启debug
        //初始化地址的数据库
        new AddrDao(this).initAddrDao();
        //初始化zxing库
        ZXingLibrary.initDisplayOpinion(this);

    }

    //获取主线程的id
    public static int getMainThreadId() {

        return myTid;
    }

    //获取handler
    public static Handler getAppHanler() {
        return handler;
    }

    public static Context getAppContext() {
        return context;
    }
}
