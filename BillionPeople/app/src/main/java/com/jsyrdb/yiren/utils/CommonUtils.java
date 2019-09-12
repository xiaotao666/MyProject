package com.jsyrdb.yiren.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;

import com.jsyrdb.yiren.model.LoginResult;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class CommonUtils {

    public static final String TOKEN_SHARED = "token_shared";
    public static final String TOKEN_RESULT = "token";
    public static final String LOGIN_SHARED = "login_shared";
    public static final String LOGIN_RESULT = "login";
    public static final String ACCOUNT_SHARED = "account_shared";
    public static final String ACCOUNT_RESULT = "account";
    public static final String PASSWORD_RESULT = "password";
    public static final String USER_SHARED = "user_shared";
    public static final String USER_RESULT = "user";


    public static OkHttpClient getMyOKHttpClient(Context context) {
        //final String token = getToken(context);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .addInterceptor(new TokenInterceptor())
                .build();

        return okHttpClient;
    }

    public static void saveToken(Context context,String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TOKEN_SHARED,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_RESULT,token);
        editor.commit();
    }

    public static String getToken(Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences(TOKEN_SHARED, Context.MODE_PRIVATE);
        String str = sharedPreferences.getString(TOKEN_RESULT, "");

        return str;
    }

    public static void clearToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TOKEN_SHARED,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();
    }

    public static void saveLoginUser(Context context, LoginResult user){
        SharedPreferences preferences = context.getSharedPreferences(LOGIN_SHARED,  Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        ByteArrayOutputStream baos = new ByteArrayOutputStream(3000);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(user);
            oos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 将Product对象放到OutputStream中
        // 将Product对象转换成byte数组，并将其进行base64编码
        String server = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
        // 将编码后的字符串写到base64.xml文件中
        editor.putString(LOGIN_RESULT, server);

        editor.commit();
    }

    public static LoginResult getLoginUser(Context context){

        SharedPreferences preferences = context.getSharedPreferences(LOGIN_SHARED,  Context.MODE_PRIVATE);
        String str = preferences.getString(LOGIN_RESULT, "");
        LoginResult login = null;
        if(str.equals("")){
            return null;
        }
        // 对Base64格式的字符串进行解码
        byte[] base64Bytes = Base64.decode(str.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(bais);
            // 从ObjectInputStream中读取Product对象
            // AddNewWord addWord= (AddNewWord ) ois.readObject();
            login = (LoginResult) ois.readObject();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return login;
    }

    public static void saveUserBean(Context context, LoginResult.ResultBean.UserBean user){
        SharedPreferences preferences = context.getSharedPreferences(USER_SHARED, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        ByteArrayOutputStream baos = new ByteArrayOutputStream(3000);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(user);
            oos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 将Product对象放到OutputStream中
        // 将Product对象转换成byte数组，并将其进行base64编码
        String server = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
        // 将编码后的字符串写到base64.xml文件中
        editor.putString(USER_RESULT, server);

        editor.commit();
    }

    public static LoginResult.ResultBean.UserBean getUserBean(Context context){

        SharedPreferences preferences = context.getSharedPreferences(USER_SHARED,  Context.MODE_PRIVATE);
        String str = preferences.getString(USER_RESULT, "");
        LoginResult.ResultBean.UserBean userBean = null;
        if(str.equals("")){
            return null;
        }
        // 对Base64格式的字符串进行解码
        byte[] base64Bytes = Base64.decode(str.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(bais);
            // 从ObjectInputStream中读取Product对象
            // AddNewWord addWord= (AddNewWord ) ois.readObject();
            userBean = (LoginResult.ResultBean.UserBean) ois.readObject();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userBean;
    }

    public static void clearUserBean(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(USER_SHARED, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().commit();
    }

    public static void saveAccountAndPassword(Context context,String account,String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ACCOUNT_SHARED,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ACCOUNT_RESULT,account);
        editor.putString(PASSWORD_RESULT,password);
        editor.commit();
    }



    //http get方法拼接参数
    public static void splicingParameters() {

    }

    public static String getParameter(String param) {//将传递的参数转码
        try {
            param = URLEncoder.encode(param, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            param = null;
        }
        param = param.replace("%3D","=");
        param = param.replace("%26","&");

        return param;
    }

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
