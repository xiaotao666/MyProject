package com.jsyrdb.yiren.activity.cases;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.main.MainNewsActivity;
import com.jsyrdb.yiren.activity.main.MainSearchActivity;
import com.jsyrdb.yiren.adapter.CheckItemAdapter;
import com.jsyrdb.yiren.adapter.CommentAdapter;
import com.jsyrdb.yiren.app.MyApplication;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.model.CommentList;
import com.jsyrdb.yiren.utils.ClearEditText;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

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

public class CommentListActivity extends BaseActivity implements View.OnClickListener {

    //标题栏
//    private TextView tv_title;
//    private ImageView iv_message,iv_search;
//    private LinearLayout ll_back;

    private ListView lv_comment;
    private List<CommentList.ResultBean> list = new ArrayList<>();
    private CommentAdapter adapter;

    private ClearEditText et_comment;
    private Button btn_add;

    private SmartRefreshLayout refreshLayout;

    private LinearLayout layout_nothing;//数据为空时显示的view

    private String _id;
    private String user_id,user_name,comment_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);

        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        _id = getIntent().getStringExtra("commentid");
        initView();
        initListener();
        setTitle("评论");
        getData(_id);
    }

    public void initView() {
//        tv_title = findViewById(R.id.tv_title);
//        tv_title.setText("评论");
//        iv_message = findViewById(R.id.iv_message);
//        iv_search = findViewById(R.id.iv_search);
//        ll_back = findViewById(R.id.ll_back);
        lv_comment = findViewById(R.id.lv_comment);
        et_comment = findViewById(R.id.et_comment);
        btn_add = findViewById(R.id.btn_add);
        refreshLayout = findViewById(R.id.refreshLayout);
        layout_nothing = findViewById(R.id.layout_nothing);

        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData(_id);
                refreshLayout.finishRefresh();

            }
        });
    }

    public void initListener() {
//        ll_back.setOnClickListener(this);
//        iv_message.setOnClickListener(this);
//        iv_search.setOnClickListener(this);
        btn_add.setOnClickListener(this);
    }

    public void getData(String commentid) {
        String param = "filter[0][commentid]="+commentid+"&page=1&limit=0&sort[0][]=creat_timestamp&sort[0][]=1&sort[1][]=name&sort[1][]=1";
        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_job_comment/get_job_comment_resource_list?"+param)
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
                        try {
                            Log.e("commentList",result);
                            list.clear();
                            CommentList commentList = JSON.parseObject(result,CommentList.class);
                            list = commentList.getResult();
                            if (list != null && list.size() > 0) {
                                lv_comment.setVisibility(View.VISIBLE);
                                layout_nothing.setVisibility(View.GONE);
                                user_id = list.get(0).getUserid();
                                user_name = list.get(0).getUsername();
                                comment_name = list.get(0).getCommentname();
                                adapter = new CommentAdapter(CommentListActivity.this,list);
                                lv_comment.setAdapter(adapter);

                                lv_comment.setSelection(adapter.getCount() -1);
                            } else {
                                lv_comment.setVisibility(View.GONE);
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

    public void addComment() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject1.put("commentid",_id);
            jsonObject1.put("commentname", comment_name);
            jsonObject1.put("value", et_comment.getText().toString());
            jsonObject2.put("_id",user_id);
            jsonObject2.put("name",user_name);
            jsonObject.put("commentInfo",jsonObject1);
            jsonObject.put("userInfo",jsonObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType json = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_job_comment/insert_job_comment_resource")
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
                        Log.e("insertComment",result);
                        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(result);
                        if (jsonObject.getString("error") == null) {
                           T("添加成功");
                           et_comment.setText(null);
                           getData(_id);

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
//                Intent intent = new Intent(this, MainNewsActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.iv_search:
//                Intent intent1 = new Intent(this, MainSearchActivity.class);
//                startActivity(intent1);
//                break;
            case R.id.btn_add:
                if (et_comment.getText().toString() != null && et_comment.getText().toString().length() > 0) {
                    addComment();
                } else {
                    T("请先输入内容");
                }
                break;
        }
    }
}
