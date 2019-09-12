package com.jsyrdb.yiren.activity.cases;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.jsyrdb.yiren.activity.main.MainNewsActivity;
import com.jsyrdb.yiren.activity.main.MainSearchActivity;
import com.jsyrdb.yiren.adapter.FollowPahAdapter;
import com.jsyrdb.yiren.adapter.NextProcessAdapter;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.model.Business;
import com.jsyrdb.yiren.model.FollowPath;
import com.jsyrdb.yiren.model.NextProcess;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NextProcessActivity extends BaseActivity implements View.OnClickListener {

    //标题栏
//    private TextView tv_title;
//    private ImageView iv_message,iv_search;
//    private LinearLayout ll_back;

    private ListView lv_next_process;
    private List<NextProcess> list = new ArrayList<>();
    private NextProcessAdapter adapter;

    private List<Business.ResultBean.NextProcessListBean> listBeans;
    private String customerid;
    private String customername;
    private String _id;
    private String name;

    private JSONObject processJSONObject;

    private LinearLayout layout_nothing;//数据为空时显示的view


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_process);

        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        listBeans = (List<Business.ResultBean.NextProcessListBean>) getIntent().getSerializableExtra("next_process_list");
        customerid = getIntent().getStringExtra("customerid");
        customername = getIntent().getStringExtra("customername");

        _id = getIntent().getStringExtra("_id");
        name = getIntent().getStringExtra("name");

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
        lv_next_process = findViewById(R.id.lv_next_process);
        layout_nothing = findViewById(R.id.layout_nothing);

        lv_next_process.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getTemplateProcess(position);
            }
        });
    }

    public void initListener() {
//        ll_back.setOnClickListener(this);
//        iv_message.setOnClickListener(this);
//        iv_search.setOnClickListener(this);
    }

    public void getData() {
        if (listBeans != null && listBeans.size() > 0) {
            lv_next_process.setVisibility(View.VISIBLE);
            layout_nothing.setVisibility(View.GONE);
            for (int i = 0;i < listBeans.size();i++) {
                NextProcess nextProcess = new NextProcess();
                nextProcess.set_id(listBeans.get(i).get_id());
                nextProcess.setName(listBeans.get(i).getName());
                list.add(nextProcess);
            }
            adapter = new NextProcessAdapter(this,list);
            lv_next_process.setAdapter(adapter);
        } else {
            lv_next_process.setVisibility(View.GONE);
            layout_nothing.setVisibility(View.VISIBLE);
        }
    }

    public void getTemplateProcess(int position) {
        String json = "filter[0][_id]="+list.get(position).get_id();
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
                        Log.e("processinfo",result);
                        JSONObject jsonObject = JSON.parseObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("result");
                        if (jsonArray != null && jsonArray.size() > 0) {
                            processJSONObject = jsonArray.getJSONObject(0);
                            createProcess();
                        } else {
                            T("流程模板信息为空");
                        }
                    }
                });
            }
        });
    }

    public void createProcess() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();

        jsonObject1.put("customerid",customerid);
        jsonObject1.put("customername",customername);
        jsonObject1.put("_id",_id);
        jsonObject1.put("name",name);

        jsonObject.put("caseInfo",jsonObject1);
        jsonObject.put("processInfo",processJSONObject);
        String result = String.valueOf(jsonObject);
        Log.e("creatprocess1",String.valueOf(jsonObject));

        MediaType json = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_process/process_creat")
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
                        Log.e("creatprocess",result);
                        JSONObject resultObject = JSON.parseObject(result);
                        if (resultObject.getString("error") == null) {
                            T("设置成功");
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
