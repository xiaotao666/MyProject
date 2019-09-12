package com.jsyrdb.yiren.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.cases.BusinessActivity;
import com.jsyrdb.yiren.adapter.SearchCaseAdapter;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.model.Case;
import com.jsyrdb.yiren.utils.ClearEditText;
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

public class MainSearchActivity extends BaseActivity {

    //标题栏
//    private TextView tv_title;
//    private ImageView iv_message,iv_search;
//    private LinearLayout ll_back;

    private ListView lv_case_search;
    private List<Case.ResultBean> list = new ArrayList<>();
    private SearchCaseAdapter adapter;
    private LinearLayout ll_case;

    private ClearEditText et_search;

    private LinearLayout layout_nothing;//数据为空时显示的view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);

        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        initView();
        setTitle("搜索");
        setControlVisible(View.VISIBLE,View.GONE);
    }

    public void initView() {
        lv_case_search = findViewById(R.id.lv_case_search);
        ll_case = findViewById(R.id.ll_case);
        et_search = findViewById(R.id.et_search);
        layout_nothing = findViewById(R.id.layout_nothing);

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String searchContent = et_search.getText().toString();
                    if (!TextUtils.isEmpty(searchContent)) {
                        searchCase(searchContent);
                    } else {
                        T("请输入搜索内容");
                    }
                    return true;
                }
                return false;
            }
        });

        lv_case_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainSearchActivity.this, BusinessActivity.class);
                intent.putExtra("caseid",list.get(position).get_id());
                intent.putExtra("name",list.get(position).getName());
                startActivity(intent);
            }
        });
    }

    public void searchCase(String query) {
        String json = "filter[0][status]=running&filter[1][current_process_type]=apply_process&queryStr="+query+"&page=1&limit=0&sort[0][]=creat_timestamp&sort[0][]=-1";
        json = CommonUtils.getParameter(json);

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_case/get_case_list?"+json)
                .get()
                //.addHeader("authorization",CommonUtils.getToken(getActivity()))
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
                        try {
                            list.clear();
                            Case cases = JSON.parseObject(result,Case.class);
                            list = cases.getResult();
                            if (list != null && list.size() > 0) {
                                ll_case.setVisibility(View.VISIBLE);
                                layout_nothing.setVisibility(View.GONE);
                                adapter = new SearchCaseAdapter(MainSearchActivity.this,list);
                                lv_case_search.setAdapter(adapter);
                            } else {
                                ll_case.setVisibility(View.GONE);
                                layout_nothing.setVisibility(View.VISIBLE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
