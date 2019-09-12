package com.jsyrdb.yiren.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.main.MainNewsActivity;
import com.jsyrdb.yiren.activity.main.MainSearchActivity;
import com.jsyrdb.yiren.model.LoginResult;
import com.jsyrdb.yiren.utils.ActivityManager;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.jsyrdb.yiren.utils.GlobalParam;
import com.jsyrdb.yiren.utils.StatusBarUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BaseActivity extends Activity {

    public Context mContext;
    public Activity mActivity;

    private LinearLayout parentLinearLayout; //把父类的activity和子类的activity的view都add到这里来

    //添加进来的标题栏控件
    private TextView tv_title,tv_unread_count;
    private ImageView iv_message,iv_search;
    private LinearLayout ll_back;
    private RelativeLayout rl_message;

    private SparseArray<OnPermissionResultListener> listenerMap = new SparseArray<>();

    private String user_id;

    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(GlobalParam.ACTION_NOTIFICATION)) {
                getApplyCount();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_base);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//禁止横屏
        mContext = this;
        mActivity = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        LoginResult.ResultBean.UserBean userBean = CommonUtils.getUserBean(this);
        if (userBean != null) {
            user_id = userBean.get_id();
        }

        initContentView(R.layout.main_titlebar);

        ActivityManager.addActivity(this);

        //沉浸式代码配置
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtils.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarUtils.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
//        if (!StatusBarUtils.setStatusBarDarkTheme(this, true)) {
//            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
//            //这样半透明+白=灰, 状态栏的文字能看得清
//            StatusBarUtils.setStatusBarColor(this, 0x55000000);
//        }

        IntentFilter filter = new IntentFilter();
        filter.addAction(GlobalParam.ACTION_NOTIFICATION);
        registerReceiver(receiver, filter);
    }

    private void initContentView(int layoutResID) {
        // TODO Auto-generated method stub
        ViewGroup group = (ViewGroup) findViewById(android.R.id.content);  //得到窗口的根布局
        group.removeAllViews(); //首先先移除在根布局上的组件
        //创建自定义父布局
        parentLinearLayout  = new LinearLayout(this);
        parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        group.addView(parentLinearLayout); //将自定义的父布局，加载到窗口的根布局上
        View view = LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);//这句话的意思就是将自定义的子布局加到parentLinearLayout上，true的意思表示添加上去
        tv_title = view.findViewById(R.id.tv_title);
        tv_unread_count = view.findViewById(R.id.tv_unread_count);
        iv_message = view.findViewById(R.id.iv_message);
        iv_search = view.findViewById(R.id.iv_search);
        ll_back = view.findViewById(R.id.ll_back);
        rl_message = view.findViewById(R.id.rl_message);

        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setResult(RESULT_OK);
                finish();
            }
        });

        iv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setNotificationRead();
                Intent intent = new Intent(BaseActivity.this, MainNewsActivity.class);
                startActivity(intent);
            }
        });

        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(BaseActivity.this, MainSearchActivity.class);
                startActivity(intent1);
            }
        });

        getApplyCount();
    }

    public void setTitle(String text) {
        if (tv_title != null) {
            tv_title.setText(text);
        }
    }

    public void setControlVisible(int v1,int v2) {
        rl_message.setVisibility(v1);
        iv_search.setVisibility(v2);
    }

    @Override
    public void setContentView(int layoutResID) {
        //super.setContentView(layoutResID);//一定不能调用这句话，不然之前做的添加的布局都被覆盖了
        LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);
    }

    @Override
    public void setContentView(View view) {
        //super.setContentView(view);
        parentLinearLayout.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        //super.setContentView(view, params);
        parentLinearLayout.addView(view, params);
    }


    //设置状态栏深浅色主题(改变字体颜色)
    public void setStatusBarDarkTheme(boolean isDark) {
        if (!StatusBarUtils.setStatusBarDarkTheme(this, isDark)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtils.setStatusBarColor(this, 0x55000000);
        }
    }

    //设置状态栏颜色
    public void setStatusBarColor(int color) {
        StatusBarUtils.setStatusBarColor(this, color);
    }

    /**
     *  @功能描述 防止快速点击,一秒钟只响应一次
     *  @入参
     *  @返回值
     *  @时间  2019/4/22 13:58
     *  @版本
     *  @作者  
     *  @修改原因
     */
    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

    /**
     *  @功能描述 吐司信息
     *  @入参
     *  @返回值
     *  @时间  2019/4/22 14:00
     *  @版本
     *  @作者  
     *  @修改原因
     */
    protected void T(String info) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
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
            ActivityCompat.requestPermissions(this, permissions, size);
        }
    }



    /**
     * 跳转系统的App应用详情页
     */
    protected void toAppDetailSetting() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        startActivity(localIntent);
    }

    @Override
    protected void onDestroy() {
        ActivityManager.removeActivity(this);
        listenerMap.clear();
        listenerMap = null;

        if (receiver != null) {
            unregisterReceiver(receiver);
        }
        super.onDestroy();
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
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
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

    //获取标题通知栏收到的申请的数量
    public void getApplyCount() {
        String json = "filter[0][status]=unreplied&filter[1][to_user_id]="+user_id;
        json = CommonUtils.getParameter(json);


        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_apply/get_apply_count?"+json)
                .get()
                //.addHeader("authorization",CommonUtils.getToken(this))
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result =response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("count",result);
                        JSONObject jsonObject = JSON.parseObject(result);
                        if (jsonObject.getString("error") == null && jsonObject.getInteger("result") > 0) {
                            tv_unread_count.setVisibility(View.VISIBLE);
                            tv_unread_count.setText(jsonObject.getInteger("result") + "");
                        } else {
                            tv_unread_count.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }

    //获取标题栏通知提醒的数量
    public void getNotificationCount() {
        String json = "filter[0][status]=unread";
        json = CommonUtils.getParameter(json);


        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_notice/get_notic_count?"+json)
                .get()
                //.addHeader("authorization",CommonUtils.getToken(this))
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result =response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("count",result);
                        JSONObject jsonObject = JSON.parseObject(result);
                        if (jsonObject.getString("error") == null && jsonObject.getInteger("result") > 0) {
                            tv_unread_count.setVisibility(View.VISIBLE);
                            tv_unread_count.setText(jsonObject.getInteger("result") + "");
                        } else {
                            tv_unread_count.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }

    //设置通知消息为已读
    public void setNotificationRead() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("status","unread");
        jsonArray.add(jsonObject1);

        jsonObject.put("filter",jsonArray);
        jsonObject.put("status","read");

        MediaType json = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_notice/set_all_notic_status")
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        T("请求失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("setRead",result);
                    }
                });
            }
        });

    }

}
