package com.jsyrdb.yiren.activity.cases;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.main.MainNewsActivity;
import com.jsyrdb.yiren.activity.main.MainSearchActivity;
import com.jsyrdb.yiren.adapter.BusinessAdapter;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.model.Business;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BusinessActivity extends BaseActivity implements View.OnClickListener {

    //标题栏
//    private TextView tv_title;
//    private ImageView iv_message,iv_search;
//    private LinearLayout ll_back;

    private TextView tv_name;
    private ListView lv_case;
    private SmartRefreshLayout refreshLayout;
    private List<Business.ResultBean> list = new ArrayList<>();
    private BusinessAdapter adapter;

    //private SmartRefreshLayout refreshLayout;
    private String caseid = "";
    private String name;
    private String status;

    private Button btn_complete;

    private String TAG = "BusinessActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        caseid = getIntent().getStringExtra("caseid");
        name = getIntent().getStringExtra("name");
        status = getIntent().getStringExtra("status");

        initView();
        initListener();
        setTitle("业务流程");
        initData();

    }

    public void initView() {
//        tv_title = findViewById(R.id.tv_title);
//        tv_title.setText("业务流程");
//        iv_message = findViewById(R.id.iv_message);
        tv_name = findViewById(R.id.tv_name);
//        iv_search = findViewById(R.id.iv_search);
//        ll_back = findViewById(R.id.ll_back);
        lv_case = findViewById(R.id.lv_case);
        btn_complete = findViewById(R.id.btn_complete);
        refreshLayout = findViewById(R.id.refreshLayout);

        if (name != null) {
            tv_name.setText(name);
        }

        if (!TextUtils.isEmpty(status)) {
            if (status.equals("ending")) {
                btn_complete.setText("案件已完成");
                btn_complete.setTextColor(Color.GRAY);
                btn_complete.setBackgroundResource(R.drawable.btn_gray_small_circle);
                btn_complete.setEnabled(false);
                btn_complete.setClickable(false);
            } else {
                btn_complete.setText("完成案件");
                btn_complete.setTextColor(getResources().getColor(R.color.white));
                btn_complete.setBackgroundResource(R.drawable.btn_green_small_circle);
                btn_complete.setEnabled(true);
                btn_complete.setClickable(true);
            }
        }

        refreshLayout.setEnableLoadMore(false);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initData();
            }
        });
    }

    public void initListener() {
        btn_complete.setOnClickListener(this);
//        ll_back.setOnClickListener(this);
//        iv_message.setOnClickListener(this);
//        iv_search.setOnClickListener(this);
    }

    public void setCaseEnd() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();

        jsonObject1.put("_id",caseid);
        jsonObject.put("caseInfo",jsonObject1);

        MediaType json = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_case/set_case_end")
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
                        try {
                            JSONObject jsonObject2 = JSON.parseObject(result);
                            if (jsonObject2.getString("error") == null) {
                                T("设置成功");
                                btn_complete.setText("案件已完成");
                                btn_complete.setTextColor(Color.GRAY);
                                btn_complete.setBackgroundResource(R.drawable.btn_gray_small_circle);
                                btn_complete.setEnabled(false);
                                btn_complete.setClickable(false);
                            } else {
                                String message = jsonObject2.getJSONObject("error").getString("message");
                                T(message);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }

    public void initData() {
        String json = "filter[0][caseid]="+caseid+"&filter[1][deleted][$not][$eq]=deleted&page=1&limit=0";
        json = CommonUtils.getParameter(json);

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_process/get_process_list?"+json)
                .get()
                //.addHeader("authorization",CommonUtils.getToken(this))
                .build();
        Log.e(TAG,CommonData.server_url+"action_process/get_process_list?"+json);
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        T("请求失败");
                        refreshLayout.finishRefresh(false);
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
                        try {
                            refreshLayout.finishRefresh();
                            list.clear();
                            Business business = JSON.parseObject(result,Business.class);
                            list = business.getResult();
                            if (list != null && list.size() > 0) {
                                adapter = new BusinessAdapter(BusinessActivity.this,list);
                                lv_case.setAdapter(adapter);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3 && resultCode == RESULT_OK) {
            initData();
        } else if (requestCode == 4 && resultCode == RESULT_OK) {
            initData();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
            case R.id.btn_complete:
                setCaseEnd();
                break;
        }
    }
}
