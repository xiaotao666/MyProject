package com.jsyrdb.yiren.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.jsyrdb.yiren.app.MyApplication;
import com.jsyrdb.yiren.utils.WsManager;

public class NetStatusReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {

            // 获取网络连接管理器
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) MyApplication.getAppContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            // 获取当前网络状态信息
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();

            if (info != null && info.isAvailable()) {
               Log.e("receiver","监听到可用网络切换,调用重连方法");
                //WsManager.getInstance().reconnect();//wifi 4g切换重连websocket
            }

        }
    }
}
