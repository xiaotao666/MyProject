package com.jsyrdb.yiren.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.adapter.NewsAdapter;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.model.LoginResult;
import com.jsyrdb.yiren.model.DelayNews;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainNewsActivity extends BaseActivity {

    //标题栏
//    private TextView tv_title;
//    private ImageView iv_message,iv_search;
//    private LinearLayout ll_back;

    private ListView lv_news;
    private NewsAdapter adapter;
    private List<DelayNews.ResultBean> list = new ArrayList<>();

    private String user_id = "";
    private String user_name = "";

    private SmartRefreshLayout refreshLayout;

    private LinearLayout layout_nothing;//数据为空时显示的view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_news);

        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        LoginResult.ResultBean.UserBean userBean = CommonUtils.getUserBean(this);
        if (userBean != null) {
            user_id = userBean.get_id();
            user_name = userBean.getUsername();
        }

        initView();
        setTitle("提醒");
        setControlVisible(View.GONE,View.VISIBLE);
        getNews();
    }

    public void initView() {
//        tv_title = findViewById(R.id.tv_title);
//        tv_title.setText("提醒");
//        iv_message = findViewById(R.id.iv_message);
//        iv_message.setVisibility(View.GONE);
//        iv_search = findViewById(R.id.iv_search);
//        ll_back = findViewById(R.id.ll_back);
        lv_news = findViewById(R.id.lv_news);
        refreshLayout = findViewById(R.id.refreshLayout);
        layout_nothing = findViewById(R.id.layout_nothing);

//        ll_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setResult(RESULT_OK);
//                finish();
//            }
//        });
//
//        iv_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(MainNewsActivity.this, MainSearchActivity.class);
//                startActivity(intent1);
//            }
//        });

        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getNews();
            }
        });
    }

    public void getNews() {
        String param = "filter[0][status]=unreplied&filter[1][to_user_id]="+user_id+"&page=1&limit=0&sort[0][]=creat_timestamp&sort[0][]=-1";
        param = CommonUtils.getParameter(param);

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_apply/get_apply_list?"+param)
                .get()
                .build();
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
                String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("news",result);
                        refreshLayout.finishRefresh();
                        list.clear();
                        DelayNews delayNews = JSON.parseObject(result,DelayNews.class);
                        list = delayNews.getResult();
                        if (list != null && list.size() > 0) {
                            lv_news.setVisibility(View.VISIBLE);
                            layout_nothing.setVisibility(View.GONE);
                            adapter = new NewsAdapter(MainNewsActivity.this,list);
                            lv_news.setAdapter(adapter);
                            adapter.setOnNotifyListener(new NewsAdapter.OnNotifyListener() {
                                @Override
                                public void notifyData() {
                                    getNews();
                                }
                            });
                        } else {
                            lv_news.setVisibility(View.GONE);
                            layout_nothing.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });


    }

//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        if (event.getAction() == KeyEvent.ACTION_DOWN
//                && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
//            setResult(RESULT_OK);
//            finish();
//            return true;
//        } else {
//            return super.dispatchKeyEvent(event);
//        }
//    }
}
