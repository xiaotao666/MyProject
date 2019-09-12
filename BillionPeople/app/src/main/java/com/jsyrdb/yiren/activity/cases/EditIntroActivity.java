package com.jsyrdb.yiren.activity.cases;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.mine.EditDataActivity;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.model.LoginResult;
import com.jsyrdb.yiren.utils.ClearEditText;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.jsyrdb.yiren.utils.StatusBarUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EditIntroActivity extends Activity implements View.OnClickListener {

    //标题栏
    private TextView tv_title,tv_commit;
    private LinearLayout ll_back;

    private ClearEditText et_intro;

    private String intro = "";

    private LoginResult.ResultBean.UserBean userBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_intro);

        StatusBarUtils.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarUtils.setTranslucentStatus(this);

        StatusBarUtils.setStatusBarColor(this, getResources().getColor(R.color.green_item));
        if (!StatusBarUtils.setStatusBarDarkTheme(this, false)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtils.setStatusBarColor(this, 0x55000000);
        }

        userBean = CommonUtils.getUserBean(this);

        initView();
        intro = getIntent().getStringExtra("intro");
        et_intro.setText(intro);
    }

    public void initView() {
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("编辑简介");
        tv_commit = findViewById(R.id.tv_commit);
        tv_commit.setText("保存");
        ll_back = findViewById(R.id.ll_back);
        et_intro = findViewById(R.id.et_intro);
        ll_back.setOnClickListener(this);
        tv_commit.setOnClickListener(this);
    }

    public void editIntro() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("_id",userBean.get_id());
        jsonObject1.put("name",userBean.getName());
        jsonObject1.put("username",userBean.getUsername());
        jsonObject1.put("describe",et_intro.getText().toString());
        jsonObject.put("userInfo",jsonObject1);

        MediaType json = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_user/update_user_info")
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(EditIntroActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject resultObject = JSON.parseObject(result);
                        if (resultObject.getString("error") == null && resultObject.getInteger("result") == 1) {
                            Toast.makeText(EditIntroActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                            userBean.setDescribe(et_intro.getText().toString());
                            CommonUtils.saveUserBean(EditIntroActivity.this,userBean);

                            Intent intent = new Intent();
                            intent.putExtra("intro",et_intro.getText().toString());
                            setResult(RESULT_OK,intent);
                            finish();


                        }
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_commit:
                if (!TextUtils.isEmpty(et_intro.getText().toString())) {
                    editIntro();
                } else {
                    Toast.makeText(EditIntroActivity.this,"输入内容不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
