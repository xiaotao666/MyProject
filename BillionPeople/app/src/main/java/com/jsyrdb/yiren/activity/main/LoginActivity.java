package com.jsyrdb.yiren.activity.main;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.model.LoginResult;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.jsyrdb.yiren.utils.GlobalParam;
import com.jsyrdb.yiren.utils.StatusBarUtils;

import org.java_websocket.client.WebSocketClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText et_account;
    private EditText et_password;
    private Button btn_login;

    private String username = "";
    private String password = "";

    private LoginResult loginResult;
    private List<LoginResult.ResultBean> resultList;
    private LoginResult.ResultBean.UserBean user;
    private String token;

    private String TAG = "LoginActivity";

    private WebSocketClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        //设置状态栏透明
        StatusBarUtils.setTranslucentStatus(this);

        StatusBarUtils.setStatusBarColor(this, getResources().getColor(R.color.green_item));
        if (!StatusBarUtils.setStatusBarDarkTheme(this, false)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtils.setStatusBarColor(this, 0x55000000);
        }

        initView();
        //checkMainPermission();
    }

    public void initView() {
        et_account = findViewById(R.id.et_account);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

//        loginResult = CommonUtils.getLoginUser(LoginActivity.this);
//        if (loginResult != null) {
//            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
    }


    public void login(String json) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_login/user_login_pwd?"+json)
                .get()
                .build();
        Log.e(TAG,CommonData.server_url+"action_login/user_login_pwd?"+json);
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result =response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG,result);
                        loginResult = JSON.parseObject(result,LoginResult.class);
                        resultList = loginResult.getResult();
                        if (resultList != null && resultList.size() > 0) {
                            user = resultList.get(0).getUser();
                            token = resultList.get(0).getToken();

                            //CommonUtils.saveLoginUser(LoginActivity.this,loginResult);
                            CommonUtils.saveUserBean(LoginActivity.this,user);
                            CommonUtils.saveToken(LoginActivity.this,token);
                            //CommonUtils.saveAccountAndPassword(LoginActivity.this,username,password);

//                            Intent intent = new Intent(GlobalParam.ACTION_LOGIN_IN);
//                            sendBroadcast(intent);

                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                            intent.setAction(GlobalParam.ACTION_LOGIN_IN);
//                            sendBroadcast(intent);
                            startActivity(intent);
                            //setResult(RESULT_OK);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
//            finish();
//            Intent finishIntent = new Intent();
//            finishIntent.setAction(GlobalParam.ACTION_FINISH);
//            sendBroadcast(finishIntent);
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                username = et_account.getText().toString();
                password = et_password.getText().toString();

                String json = "filter[0][username]="+username+"&filter[1][password]="+password;
                try {
                    json = URLEncoder.encode(json, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    json = null;
                }

                json = json.replace("%3D","=");
                json = json.replace("%26","&");

                login(json);

                break;
        }
    }


    //    private void requestGet() {
//        try {
//            String username = et_account.getText().toString();
//            String password = et_password.getText().toString();
//            String json = "filter[0][username]="+username+"&filter[1][password]="+password;
//            json = URLEncoder.encode(json, "utf-8");
//            String baseUrl = CommonData.server_url+"action_login/user_login_pwd?"+json;
//            Log.e(TAG, baseUrl);
////            StringBuilder tempParams = new StringBuilder();
////            int pos = 0;
////            for (String key : paramsMap.keySet()) {
////                if (pos > 0) {
////                    tempParams.append("&");
////                }
////                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key),"utf-8")));
////                pos++;
////            }
//            //String requestUrl = baseUrl + tempParams.toString();
//            // 新建一个URL对象
//            URL url = new URL(baseUrl);
//            // 打开一个HttpURLConnection连接
//            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
//            // 设置连接主机超时时间
//            urlConn.setConnectTimeout(5 * 1000);
//            //设置从主机读取数据超时
//            urlConn.setReadTimeout(5 * 1000);
//            // 设置是否使用缓存  默认是true
//            urlConn.setUseCaches(true);
//            // 设置为Post请求
//            urlConn.setRequestMethod("GET");
//            //urlConn设置请求头信息
//            //设置请求中的媒体类型信息。
//            urlConn.setRequestProperty("Content-Type", "application/json");
//            //设置客户端与服务连接类型
//            //urlConn.addRequestProperty("Connection", "Keep-Alive");
//            // 开始连接
//            urlConn.connect();
//            // 判断请求是否成功
//            if (urlConn.getResponseCode() == 200) {
//                // 获取返回的数据
//                String result = streamToString(urlConn.getInputStream());
//                Log.e(TAG, "Get方式请求成功，result--->" + result);
//            } else {
//                Log.e(TAG, "Get方式请求失败");
//            }
//            // 关闭连接
//            urlConn.disconnect();
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//        }
//    }
//
//    /**
//     * 将输入流转换成字符串
//     *
//     * @param is 从网络获取的输入流
//     * @return
//     */
//    public String streamToString(InputStream is) {
//        try {
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            byte[] buffer = new byte[1024];
//            int len = 0;
//            while ((len = is.read(buffer)) != -1) {
//                baos.write(buffer, 0, len);
//            }
//            baos.close();
//            is.close();
//            byte[] byteArray = baos.toByteArray();
//            return new String(byteArray);
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//            return null;
//        }
//    }
}
