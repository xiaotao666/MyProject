package com.jsyrdb.yiren.app;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.broadcast.NetStatusReceiver;
import com.jsyrdb.yiren.utils.ForegroundCallbacks;
import com.jsyrdb.yiren.utils.WsManager;
import com.jsyrdb.yiren.widget.MyRefreshLayout;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.smtt.sdk.QbSdk;

public class MyApplication extends Application {

    /**系统上下文*/
    private static Context mAppContext;


    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new MaterialHeader(context).setColorSchemeResources(R.color.green_item);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });

        //设置全局的Header构建器
        MyRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new MaterialHeader(context).setColorSchemeResources(R.color.green_item);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppContext = getApplicationContext();

        QbSdk.PreInitCallback callback = new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("application", " onViewInitFinished is " + b);
            }
        };

        //x5内核初始化接口
        QbSdk.initX5Environment(mAppContext,callback);

        //动态注册监听网络变化的广播
//        NetStatusReceiver receiver = new NetStatusReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        this.registerReceiver(receiver, intentFilter);

        //监听应用是否回到前台
        initAppStatusListener();

    }

    /**获取系统上下文*/
    public static Context getAppContext() {
        return mAppContext;
    }

    private void initAppStatusListener() {
        ForegroundCallbacks.init(this).addListener(new ForegroundCallbacks.Listener() {
            @Override
            public void onBecameForeground() {
                Log.e("foreground","应用回到前台调用重连方法");
                //WsManager.getInstance().reconnect();
            }

            @Override
            public void onBecameBackground() {

            }
        });

    }
}
