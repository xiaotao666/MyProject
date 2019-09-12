package com.jsyrdb.yiren.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.android.jwt.JWT;
import com.jsyrdb.yiren.activity.main.LoginActivity;
import com.jsyrdb.yiren.app.MyApplication;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = CommonUtils.getToken(MyApplication.getAppContext());
        if (!TextUtils.isEmpty(token)) {
            if (isEffective(token)) {
                Request headRequest = chain.request()
                        .newBuilder()
                        .addHeader("authorization",token)
                        .build();
                Response headResponse = chain.proceed(headRequest);
                return headResponse;
            } else {
                String newToken = getNewToken();
                Request newRequest = chain.request()
                        .newBuilder()
                        .addHeader("authorization",newToken)
                        .build();
                Response newResponse = chain.proceed(newRequest);
                return newResponse;
            }

        } else {
            Request request = chain.request();
            Response response = chain.proceed(request);
            return response;
        }
    }

    //判断token是否有效
    public boolean isEffective(String token) {
        JWT jwt = new JWT(token);
        Date exp = jwt.getExpiresAt();
        Log.e("hhhhh",DateUtils.getDate2String(exp.getTime(),"yyyy-MM-dd HH:mm:ss"));
        Date currentDate = new Date(System.currentTimeMillis());

        if (currentDate.getTime() < exp.getTime()) {
            return  true;
        } else {
            return false;
        }

    }

    //刷新token
    public String getNewToken(){
        String json = CommonUtils.getToken(MyApplication.getAppContext());
        try {
            json = URLEncoder.encode(json, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            json = null;
        }
        String newToken = "";
        OkHttpClient okHttpClient = new OkHttpClient();//创建OkHttpClient对象
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_token/refresh_user_token?token="+json)//请求接口。如果需要传参拼接到接口后面。
                //.addHeader("authorization",CommonUtils.getToken(MyApplication.getAppContext()))
                .build();//创建Request 对象
        Log.e("sttt",CommonUtils.getToken(MyApplication.getAppContext()));
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();//得到Response 对象
            String result = response.body().string();
            Log.e("sttt1",result);
            JSONObject jsonObject = JSON.parseObject(result);
            if (jsonObject.getString("error") == null) {
                JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                newToken = jsonObject1.getString("token");
                CommonUtils.saveToken(MyApplication.getAppContext(),newToken);
            } else {
                //Toast.makeText(MyApplication.getAppContext(),"认证失效,请重新登录", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyApplication.getAppContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getAppContext().startActivity(intent);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return newToken;
    }
}
