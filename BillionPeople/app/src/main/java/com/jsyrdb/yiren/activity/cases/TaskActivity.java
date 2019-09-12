package com.jsyrdb.yiren.activity.cases;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
import com.jsyrdb.yiren.adapter.ManagerAdapter;
import com.jsyrdb.yiren.adapter.TaskAdapter;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.model.Task;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TaskActivity extends BaseActivity implements View.OnClickListener {

    //标题栏
//    private TextView tv_title;
//    private ImageView iv_message,iv_search;
//    private LinearLayout ll_back;

    private TextView company_name;
    private Button btn_set;
    private ListView lv_task;
    private SmartRefreshLayout refreshLayout;
    private Button btn_complete;

    private List<Task.ResultBean> list = new ArrayList<>();
    private List<Task.ResultBean> checkList = new ArrayList<>();
    private TaskAdapter adapter;
    private HashMap<Integer, Boolean> isSelected;

    private String processid;//传过来的流程id
    private String processName;//传过来的流程名称
    private String node;//传过来的节点名称

    private String status;//传过来的流程完成状态(ending为已完成)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        processid = getIntent().getStringExtra("processid");
        node = getIntent().getStringExtra("node");
        processName = getIntent().getStringExtra("processname");
        status = getIntent().getStringExtra("status");

        initView();
        initListener();
        if (processName != null) {
            setTitle(processName);
        }
        if (!TextUtils.isEmpty(status)) {
            if (status.equals("ending")) {
                btn_complete.setText("流程已完成");
                btn_complete.setTextColor(Color.GRAY);
                btn_complete.setBackgroundResource(R.drawable.btn_gray_small_circle);
                btn_complete.setEnabled(false);
                btn_complete.setClickable(false);
            } else {
                btn_complete.setText("完成流程");
                btn_complete.setTextColor(getResources().getColor(R.color.white));
                btn_complete.setBackgroundResource(R.drawable.btn_green_small_circle);
                btn_complete.setEnabled(true);
                btn_complete.setClickable(true);
            }
        }
        getJobData();
    }

    public void initView() {
//        tv_title = findViewById(R.id.tv_title);
//        if (processName != null) {
//            tv_title.setText(processName);
//        }
//        iv_message = findViewById(R.id.iv_message);
//        iv_search = findViewById(R.id.iv_search);
//        ll_back = findViewById(R.id.ll_back);
        lv_task = findViewById(R.id.lv_task);
        btn_set = findViewById(R.id.btn_set);
        company_name = findViewById(R.id.company_name);
        refreshLayout = findViewById(R.id.refreshLayout);
        btn_complete = findViewById(R.id.btn_complete);

        refreshLayout.setEnableLoadMore(false);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getJobData();
            }
        });
    }

    public void initListener() {
        //ll_back.setOnClickListener(this);
        btn_set.setOnClickListener(this);
        btn_complete.setOnClickListener(this);
//        iv_message.setOnClickListener(this);
//        iv_search.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK) {
            getJobData();
        } else if (requestCode == 4 && resultCode == RESULT_OK) {
            getJobData();
        }
    }

    public void getJobData() {
        String param = "filter[0][processid]="+processid+"&page=1&limit=0&sort[0][]=nodeidx&sort[0][]=1&sort[1][]=jobidx&sort[1][]=1";
        param = CommonUtils.getParameter(param);
        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_job/get_job_list?"+param)
                .get()
                .build();
        Log.e("taskparam",param);
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
                        Log.e("taskActivity",result);

                        btn_set.setVisibility(View.GONE);
                            refreshLayout.finishRefresh();
                            list.clear();
                            Task task = JSON.parseObject(result,Task.class);
                            list = task.getResult();
                            if (list != null && list.size() > 0) {
                                company_name.setText(list.get(0).getCasename());
                                isSelected = new HashMap<>();
                                adapter = new TaskAdapter(TaskActivity.this,list,isSelected);

                                adapter.setUpdateViewListener(new TaskAdapter.UpdateViewListener() {
                                    @Override
                                    public void updateView() {
                                        btn_set.setVisibility(View.VISIBLE);
                                    }
                                });
                                lv_task.setAdapter(adapter);
                            }


                    }
                });
            }
        });
    }



    public void saveCheckList() {
        checkList.clear();
        isSelected = TaskAdapter.getIsSelected();
        if (isSelected != null && isSelected.size() > 0) {
            for (int i = 0; i < isSelected.size(); i++) {
                if (isSelected.get(i).equals(true)) {
                    checkList.add(list.get(i));
                }
            }
            Log.e("check", checkList.size() + "");
        }
    }

    public void setProcessEnd() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();

        jsonObject1.put("_id",processid);
        jsonObject.put("processInfo",jsonObject1);

        MediaType json = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_process/set_process_end")
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
//                        JSONObject jsonObject2 = JSON.parseObject(result);
//                        if (jsonObject2.getString("error") == null) {
//                            T("设置成功");
//                            btn_complete.setText("已完成");
//                            btn_complete.setTextColor(Color.GRAY);
//                            btn_complete.setBackgroundResource(R.drawable.btn_gray_small_circle);
//                            btn_complete.setEnabled(false);
//                            btn_complete.setClickable(false);
//                        }

                        try {
                            JSONObject jsonObject2 = JSON.parseObject(result);
                            if (jsonObject2.getString("error") == null) {
                                T("设置成功");
                                btn_complete.setText("流程已完成");
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.ll_back:
//                finish();
//                break;
            case R.id.btn_set:
                saveCheckList();
                if (checkList != null && checkList.size() > 0) {
                    Intent intent = new Intent(this,SetupPeopleActivity.class);
                    intent.putExtra("step",3);
                    intent.putExtra("checkList", (Serializable) checkList);
                    intent.putExtra("isMultipleSelection", true);
                    startActivityForResult(intent,4);
                } else {
                    T("请至少选择一个");
                }
                break;
            case R.id.btn_complete:
                setProcessEnd();
                break;
//            case R.id.iv_message:
//                Intent intent1 = new Intent(this, MainNewsActivity.class);
//                startActivity(intent1);
//                break;
//            case R.id.iv_search:
//                Intent intent2 = new Intent(this, MainSearchActivity.class);
//                startActivity(intent2);
//                break;
        }
    }
}
