package com.jsyrdb.yiren.activity.add;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.cases.CaseSourceLinkManActivity;
import com.jsyrdb.yiren.activity.cases.FlowPathSetupActivity;
import com.jsyrdb.yiren.activity.main.MainNewsActivity;
import com.jsyrdb.yiren.activity.main.MainSearchActivity;
import com.jsyrdb.yiren.adapter.CaseSourceAdapter;
import com.jsyrdb.yiren.adapter.CustomerAdapter;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.model.CaseSource;
import com.jsyrdb.yiren.model.CaseSourceLinkMan;
import com.jsyrdb.yiren.model.Customer;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CaseSourceActivity extends BaseActivity implements View.OnClickListener {

    //标题栏
//    private TextView tv_title;
//    private ImageView iv_message,iv_search;
//    private LinearLayout ll_back;

    private ListView lv_case_source;
    private List<CaseSource.ResultBean> list;
    private CaseSourceAdapter adapter;

    private Customer.ResultBean customer;

    private Button btn_next;

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_source);

        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        customer = (Customer.ResultBean) getIntent().getSerializableExtra("customer");
        initView();
        initListener();
        setTitle("来源设置");
        getData();
    }

    private void initView() {
//        tv_title = findViewById(R.id.tv_title);
//        tv_title.setText("案源设置");
//        iv_message = findViewById(R.id.iv_message);
//        iv_search = findViewById(R.id.iv_search);
//        ll_back = findViewById(R.id.ll_back);
        lv_case_source = findViewById(R.id.lv_case_source);
        btn_next = findViewById(R.id.btn_next);

//        lv_case_source.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                index = position;
//                Intent intent = new Intent(CaseSourceActivity.this, CaseSourceLinkManActivity.class);
//                intent.putExtra("caseSourceId",list.get(position).get_id());
//                startActivityForResult(intent,2);
//            }
//        });
    }

    public void initListener() {
//        ll_back.setOnClickListener(this);
//        iv_message.setOnClickListener(this);
//        iv_search.setOnClickListener(this);
        btn_next.setOnClickListener(this);
    }

    public void getData() {
        String json = "page=1&limit=0&sort[0][]=creat_timestamp&sort[0][]=-1&sort[1][]=name&sort[1][]=1";
        json = CommonUtils.getParameter(json);

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_case_source/get_case_source_list?"+json)
                .get()
                //.addHeader("authorization",CommonUtils.getToken(this))
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
                String result =response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("Casesource",result);
                        try {
                            CaseSource caseSource = JSON.parseObject(result,CaseSource.class);
                            list = caseSource.getResult();
                            if (list != null && list.size() > 0) {
                                adapter = new CaseSourceAdapter(CaseSourceActivity.this,list);
                                lv_case_source.setAdapter(adapter);
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
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK);
                finish();
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                CaseSourceLinkMan.ResultBean resultBean = (CaseSourceLinkMan.ResultBean) data.getSerializableExtra("link");
                index = data.getIntExtra("index",0);
                list.get(index).setLink_man(resultBean.getName());
                list.get(index).setLink_id(resultBean.get_id());

                adapter.notifyDataSetChanged();
            }
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
            case R.id.btn_next:
                for (int i = 0;i < list.size();i++) {
                    if (list.get(i).isChecked()) {
                        Intent intent2 = new Intent(CaseSourceActivity.this, FlowPathSetupActivity.class);
                        intent2.putExtra("isNewBuild",true);
                        intent2.putExtra("customer",customer);
                        intent2.putExtra("caseSource",list.get(i));
                        startActivityForResult(intent2,1);
                        return;
                    }
                }
                break;
        }
    }
}
