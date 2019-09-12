package com.jsyrdb.yiren.activity.add;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.main.MainNewsActivity;
import com.jsyrdb.yiren.activity.main.MainSearchActivity;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.model.CaseSource;
import com.jsyrdb.yiren.model.Customer;
import com.jsyrdb.yiren.model.FollowPath;
import com.jsyrdb.yiren.model.LoginResult;
import com.jsyrdb.yiren.utils.ClearEditText;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CreateCaseActivity extends BaseActivity implements View.OnClickListener {

    //标题栏
//    private TextView tv_title;
//    private ImageView iv_message,iv_search;
//    private LinearLayout ll_back;

    private ClearEditText et_name,et_describe;
    private Button btn_create;

    private Customer.ResultBean customer;
    private CaseSource.ResultBean caseSource;
    private FollowPath.ResultBean process;

    private String user_id = "";
    private String user_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_case);

        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        LoginResult.ResultBean.UserBean userBean = CommonUtils.getUserBean(this);
        if (userBean != null) {
            user_id = userBean.get_id();
            user_name = userBean.getUsername();
        }
        customer = (Customer.ResultBean) getIntent().getSerializableExtra("customer");
        caseSource = (CaseSource.ResultBean) getIntent().getSerializableExtra("caseSource");
        process = (FollowPath.ResultBean) getIntent().getSerializableExtra("process");
        initView();
        initListener();
        setTitle("创建项目");
    }

    public void initView() {
//        tv_title = findViewById(R.id.tv_title);
//        tv_title.setText("创建案件");
//        iv_message = findViewById(R.id.iv_message);
//        iv_search = findViewById(R.id.iv_search);
//        ll_back = findViewById(R.id.ll_back);
        et_name = findViewById(R.id.et_name);
        et_describe = findViewById(R.id.et_describe);
        btn_create = findViewById(R.id.btn_create);
    }

    public void initListener() {
//        ll_back.setOnClickListener(this);
//        iv_message.setOnClickListener(this);
//        iv_search.setOnClickListener(this);
        btn_create.setOnClickListener(this);
    }

    public void createCase() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();

        jsonObject1.put("case_source_id",caseSource.get_id());
        jsonObject1.put("case_source_name",caseSource.getName());
        jsonObject1.put("case_source_linkmanid",caseSource.getLink_id());
        jsonObject1.put("case_source_linkmanname",caseSource.getLink_man());
        jsonObject1.put("customerid",customer.get_id());
        jsonObject1.put("customername",customer.getName());
        jsonObject1.put("customer_linkmanid",customer.getLink_id());
        jsonObject1.put("customer_linkmanname",customer.getLink_man());
        jsonObject1.put("name",et_name.getText().toString());
        jsonObject1.put("describe",et_describe.getText().toString());
        jsonObject2 = (JSONObject) JSONObject.toJSON(process);
        jsonObject3.put("_id",user_id);
        jsonObject3.put("name",user_name);

        jsonObject.put("caseInfo",jsonObject1);
        jsonObject.put("processInfo",jsonObject2);
        jsonObject.put("userInfo",jsonObject3);

        Log.e("gggg",String.valueOf(jsonObject));

        MediaType json = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_case/case_creat")
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
                        Log.e("creatCase",result);
                        JSONObject resultObject = JSON.parseObject(result);
                        if (resultObject.getString("error") == null) {
                            T("新建成功");
                            setResult(RESULT_OK);
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
//            case R.id.ll_back:
//                finish();
//                break;
//            case R.id.iv_message:
//                Intent intent2 = new Intent(this, MainNewsActivity.class);
//                startActivity(intent2);
//                break;
//            case R.id.iv_search:
//                Intent intent3 = new Intent(this, MainSearchActivity.class);
//                startActivity(intent3);
//                break;
            case R.id.btn_create:
                if (!TextUtils.isEmpty(et_name.getText().toString())) {
                    createCase();
                } else {
                    T("请输入案件名称");
                }

                break;
        }
    }
}
