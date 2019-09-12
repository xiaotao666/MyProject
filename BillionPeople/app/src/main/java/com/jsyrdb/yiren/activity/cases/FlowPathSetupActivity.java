package com.jsyrdb.yiren.activity.cases;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.add.CreateCaseActivity;
import com.jsyrdb.yiren.activity.main.MainNewsActivity;
import com.jsyrdb.yiren.activity.main.MainSearchActivity;
import com.jsyrdb.yiren.adapter.FollowPahAdapter;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.model.CaseSource;
import com.jsyrdb.yiren.model.Customer;
import com.jsyrdb.yiren.model.FollowPath;
import com.jsyrdb.yiren.model.LoginResult;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FlowPathSetupActivity extends BaseActivity implements View.OnClickListener {

    //标题栏
//    private TextView tv_title;
//    private ImageView iv_message,iv_search;
//    private LinearLayout ll_back;

    private ListView lv_follow_path;

    private List<FollowPath.ResultBean> list;
    private FollowPahAdapter adapter;

    private boolean isNewBuild = false;
    private Customer.ResultBean customer;
    private CaseSource.ResultBean caseSource;

    private String user_id = "";
    private String user_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_path_setup);

        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        LoginResult.ResultBean.UserBean userBean = CommonUtils.getUserBean(this);
        if (userBean != null) {
            user_id = userBean.get_id();
            user_name = userBean.getUsername();
        }

        isNewBuild = getIntent().getBooleanExtra("isNewBuild",false);
        customer = (Customer.ResultBean) getIntent().getSerializableExtra("customer");
        caseSource = (CaseSource.ResultBean) getIntent().getSerializableExtra("caseSource");

        initView();
        initListener();
        setTitle("申请模板");

        getData();
    }

    public void initView() {
//        tv_title = findViewById(R.id.tv_title);
//        tv_title.setText("申请模板");
//        iv_message = findViewById(R.id.iv_message);
//        iv_search = findViewById(R.id.iv_search);
//        ll_back = findViewById(R.id.ll_back);
        lv_follow_path = findViewById(R.id.lv_follow_path);

        lv_follow_path.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FlowPathSetupActivity.this, CreateCaseActivity.class);
                intent.putExtra("customer",customer);
                intent.putExtra("caseSource",caseSource);
                intent.putExtra("process", list.get(position));
                startActivityForResult(intent,5);
            }
        });
    }

    public void initListener() {
//        ll_back.setOnClickListener(this);
//        iv_message.setOnClickListener(this);
//        iv_search.setOnClickListener(this);
    }

    public void getData() {
        String json = "filter[0][process_type]=apply_process&page=1&limit=0";
        json = CommonUtils.getParameter(json);
        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_process/get_template_process_list?"+json)
                .get()
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
                        Log.e("FlowPath",result);
                        FollowPath followPath = JSON.parseObject(result,FollowPath.class);
                        list = followPath.getResult();
                        if (list != null && list.size() > 0);
                            adapter = new FollowPahAdapter(FlowPathSetupActivity.this,list);
                            lv_follow_path.setAdapter(adapter);
                    }
                });
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5 && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.ll_back:
//                finish();
//                break;
//            case R.id.iv_message:
//                Intent intent = new Intent(this, MainNewsActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.iv_search:
//                Intent intent1 = new Intent(this, MainSearchActivity.class);
//                startActivity(intent1);
//                break;
//        }
    }
}
