package com.jsyrdb.yiren.activity.add;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.cases.CustomActivity;
import com.jsyrdb.yiren.activity.cases.SelectLinkMainActivity;
import com.jsyrdb.yiren.activity.main.MainNewsActivity;
import com.jsyrdb.yiren.activity.main.MainSearchActivity;
import com.jsyrdb.yiren.adapter.CustomerAdapter;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.model.CustomLinkMan;
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

public class CustomerSetupActivity extends BaseActivity implements View.OnClickListener {

    //标题栏
//    private TextView tv_title;
//    private ImageView iv_message,iv_search;
//    private LinearLayout ll_back;

    private ListView lv_customer;
    private List<Customer.ResultBean> list;
    private CustomerAdapter adapter;

    private Button btn_next;

    private int index;

    private String link_man = "";
    private String link_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_setup);

        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        initView();
        initListener();
        setTitle("客户设置");
        getData();
    }

    public void initView() {
//        tv_title = findViewById(R.id.tv_title);
//        tv_title.setText("客户设置");
//        iv_message = findViewById(R.id.iv_message);
//        iv_search = findViewById(R.id.iv_search);
//        ll_back = findViewById(R.id.ll_back);
        lv_customer = findViewById(R.id.lv_customer);
        btn_next = findViewById(R.id.btn_next);

//        lv_customer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                index = position;
//                Intent intent = new Intent(CustomerSetupActivity.this, SelectLinkMainActivity.class);
//                intent.putExtra("customerid",list.get(position).get_id());
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
                .url(CommonData.server_url+"action_customer/get_customer_list?"+json)
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
                        Log.e("Customer",result);
                        Customer customer = JSON.parseObject(result,Customer.class);
                        list = customer.getResult();
                        if (list != null && list.size() > 0) {
                            adapter = new CustomerAdapter(CustomerSetupActivity.this,list);
                            lv_customer.setAdapter(adapter);
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
                finish();
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                CustomLinkMan.ResultBean resultBean = (CustomLinkMan.ResultBean) data.getSerializableExtra("link");
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
                        Intent intent2 = new Intent(CustomerSetupActivity.this,CaseSourceActivity.class);
                        intent2.putExtra("customer",list.get(i));
                        startActivityForResult(intent2,1);
                        return;
                    }
                }
                break;
        }
    }
}
