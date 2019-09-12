package com.jsyrdb.yiren.base;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.WindowManager;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.jsyrdb.yiren.utils.StatusBarUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BaseFragment extends Fragment {

    private SparseArray<OnPermissionResultListener> listenerMap = new SparseArray<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (listenerMap != null) {
            listenerMap.clear();
            listenerMap = null;
        }
        super.onDestroy();
    }

    //设置状态栏深浅色主题(改变字体颜色)
    public void setStatusBarDarkTheme(boolean isDark) {
        if (!StatusBarUtils.setStatusBarDarkTheme(getActivity(), isDark)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtils.setStatusBarColor(getActivity(), 0x55000000);
        }
    }

    //设置状态栏颜色
    public void setStatusBarColor(int color) {
        StatusBarUtils.setStatusBarColor(getActivity(), color);
    }

    protected void T(String info) {
        Toast.makeText(getActivity(), info, Toast.LENGTH_SHORT).show();
    }

    /**
     * 权限请求结果监听者
     */
    public interface OnPermissionResultListener {
        /**
         * 权限被允许
         */
        void onAllow();

        /**
         * 权限被拒绝
         */
        void onReject();
    }

    /**
     * 镜像权限申请
     * @param onPermissionResultListener 申请权限结果回调
     */
    public void checkPermissions(final String[] permissions, OnPermissionResultListener onPermissionResultListener) {
        if (Build.VERSION.SDK_INT < 23 || permissions.length == 0) {// android6.0已下不需要申请，直接为"同意"
            if (onPermissionResultListener != null)
                onPermissionResultListener.onAllow();
        } else {
            int size = listenerMap.size();
            if (onPermissionResultListener != null) {
                listenerMap.put(size, onPermissionResultListener);
            }
            ActivityCompat.requestPermissions(getActivity(), permissions, size);
        }
    }



    /**
     * 跳转系统的App应用详情页
     */
    protected void toAppDetailSetting() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent.setData(Uri.fromParts("package", getActivity().getPackageName(), null));
        startActivity(localIntent);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        OnPermissionResultListener onPermissionResultListener = listenerMap.get(requestCode);
        if (onPermissionResultListener != null) {
            listenerMap.remove(requestCode);
            // 循环判断权限，只要有一个拒绝了，则回调onReject()。 全部允许时才回调onAllow()
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {// 拒绝权限
                    // 对于 ActivityCompat.shouldShowRequestPermissionRationale
                    // 1：用户拒绝了该权限，没有勾选"不再提醒"，此方法将返回true。
                    // 2：用户拒绝了该权限，有勾选"不再提醒"，此方法将返回 false。
                    // 3：如果用户同意了权限，此方法返回false
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permissions[i])) {
                        // 拒绝选了"不再提醒"，一般提示跳转到权限设置页面
                        toAppDetailSetting();
                    } else {
                        onPermissionResultListener.onReject();
                    }
                    return;
                }
            }
            onPermissionResultListener.onAllow();
        }
    }
}
