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
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.main.MainNewsActivity;
import com.jsyrdb.yiren.activity.main.MainSearchActivity;
import com.jsyrdb.yiren.adapter.CaseSourceLinkAdapter;
import com.jsyrdb.yiren.adapter.CustomerLinkAdapter;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.model.CaseSourceLinkMan;
import com.jsyrdb.yiren.model.CustomLinkMan;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CaseSourceLinkManActivity extends BaseActivity implements View.OnClickListener {


    //标题栏
//    private TextView tv_title;
//    private ImageView iv_message,iv_search;
//    private LinearLayout ll_back;

    private ListView lv_link_man;
    private List<CaseSourceLinkMan.ResultBean> list;
    private CaseSourceLinkAdapter adapter;

    private LinearLayout layout_nothing;//数据为空时显示的view

    private String case_sourse_id;

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_source_link_man);

        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        case_sourse_id = getIntent().getStringExtra("caseSourceId");
        index = getIntent().getIntExtra("index",0);

        initView();
        initListener();
        setTitle("选择联系人");
        getData();
    }

    public void initView() {
//        tv_title = findViewById(R.id.tv_title);
//        tv_title.setText("选择联系人");
//        iv_message = findViewById(R.id.iv_message);
//        iv_search = findViewById(R.id.iv_search);
//        ll_back = findViewById(R.id.ll_back);
        lv_link_man = findViewById(R.id.lv_link_man);
        layout_nothing = findViewById(R.id.layout_nothing);

        lv_link_man.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("link",list.get(position));
                intent.putExtra("index",index);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    public void initListener() {
//        ll_back.setOnClickListener(this);
//        iv_message.setOnClickListener(this);
//        iv_search.setOnClickListener(this);
    }

    public void getData() {
        String json = "filter[0][case_sourceid]="+case_sourse_id+"&page=1&limit=0";
        json = CommonUtils.getParameter(json);

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_case_source/get_case_source_linkman_list?"+json)
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
                        Log.e("linkman",result);
                        CaseSourceLinkMan caseSourceLinkMan = JSON.parseObject(result,CaseSourceLinkMan.class);
                        list = caseSourceLinkMan.getResult();
                        if (list != null && list.size() > 0) {
                            lv_link_man.setVisibility(View.VISIBLE);
                            layout_nothing.setVisibility(View.GONE);
                            adapter = new CaseSourceLinkAdapter(CaseSourceLinkManActivity.this,list);
                            lv_link_man.setAdapter(adapter);
                        } else {
                            lv_link_man.setVisibility(View.GONE);
                            layout_nothing.setVisibility(View.VISIBLE);
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
