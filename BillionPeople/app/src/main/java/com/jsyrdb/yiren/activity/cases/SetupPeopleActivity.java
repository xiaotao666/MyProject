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
import com.jsyrdb.yiren.adapter.PeopleSetAdapter;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.model.LoginResult;
import com.jsyrdb.yiren.model.PeopleSetup;
import com.jsyrdb.yiren.model.SetResult;
import com.jsyrdb.yiren.model.Task;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SetupPeopleActivity extends BaseActivity implements View.OnClickListener {

    //标题栏
//    private TextView tv_title;
//    private ImageView iv_message,iv_search;
//    private LinearLayout ll_back;

    private ListView lv_people;
    private List<PeopleSetup.ResultBean> list;
    private PeopleSetAdapter adapter;

    private int step;//判断是设置哪个步骤的人员(1.设置案件 2.设置流程 3.设置任务)
    private List<Task.ResultBean> checkList;//设置任务人员多选时传过来的列表
    private boolean isMultipleSelection = false;//判断是否多选
    private String stepParam;//根据步骤设置接口地址
    private String infoParam;//根据步骤设置接口参数key值

    private String _id;//步骤id
    private String user_id = "";//创建者id
    private String user_name = "";//创建者名称

    private boolean isSetup = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_people);

        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        LoginResult.ResultBean.UserBean userBean = CommonUtils.getUserBean(this);
        if (userBean != null) {
            user_id = userBean.get_id();
            user_name = userBean.getUsername();
        }

        step = getIntent().getIntExtra("step",0);
        _id = getIntent().getStringExtra("_id");
        isMultipleSelection = getIntent().getBooleanExtra("isMultipleSelection",false);
        checkList = (List<Task.ResultBean>) getIntent().getSerializableExtra("checkList");

        if (step == 1) {
            stepParam = "action_case/set_case_manager";
            infoParam = "caseInfo";
        } else if (step == 2) {
            stepParam = "action_process/set_process_manager";
            infoParam = "processInfo";
        } else if (step == 3) {
            stepParam = "action_job/set_job_manager";
            infoParam = "jobInfo";
        } else {
            stepParam = "";
            infoParam = "";
        }

        initView();
        initListener();
        setTitle("人员设置");
        getPeople();
    }

    public void initView() {
//        tv_title = findViewById(R.id.tv_title);
//        tv_title.setText("人员设置");
//        iv_message = findViewById(R.id.iv_message);
//        iv_search = findViewById(R.id.iv_search);
//        ll_back = findViewById(R.id.ll_back);
        lv_people = findViewById(R.id.lv_people);

        lv_people.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isMultipleSelection) {
                    setMultiplePeople(position);
                } else {
                    setupPeople(position,_id);
                }

            }
        });
    }

    public void initListener() {
//        ll_back.setOnClickListener(this);
//        iv_message.setOnClickListener(this);
//        iv_search.setOnClickListener(this);
    }

    public void getPeople() {
        String param = "page=1&limit=0";
        param = CommonUtils.getParameter(param);
        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_user/get_user_list?"+param)
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
                        Log.e("setupPeople",result);
                        PeopleSetup peopleSetup = JSON.parseObject(result,PeopleSetup.class);
                        list = peopleSetup.getResult();
                        if (list != null && list.size() > 0) {
                            adapter = new PeopleSetAdapter(SetupPeopleActivity.this,list);
                            lv_people.setAdapter(adapter);
                        }
                    }
                });
            }
        });

    }

    //使用线程池执行多个任务并发
    public void setMultiplePeople(int position) {
        ExecutorService esPool = Executors.newFixedThreadPool(10);//线程池
        if (checkList != null && checkList.size() > 0) {
            for (int i = 0;i < checkList.size();i++) {
                int finalI = i;
                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        setupAllPeople(position,checkList.get(finalI).get_id());
                    }
                };
                esPool.submit(task);
            }
            esPool.shutdown();
            while (true) {//循环判断,一直当所有任务都执行完毕后esPool.isTerminated()才返回true
                if (esPool.isTerminated()) {//只有当线程池中所有线程完成任务时才会返回true，并且需要先调用线程池的shutdown方法或者shutdownNow方法
                    if (isSetup) {
                        T("设置成功");
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        T("请求失败");
                    }

                    break;
                }
            }
        }
    }

    public void setupAllPeople(int position,String id) {
        MediaType json = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject1.put("_id",id);
            jsonObject2.put("_id",list.get(position).get_id());
            jsonObject2.put("name",list.get(position).getName());
            jsonObject.put(infoParam,jsonObject1);
            jsonObject.put("userInfo",jsonObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("setupPeople11",String.valueOf(jsonObject));
        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + stepParam)
                .post(requestBody)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                isSetup = true;
                String result = response.body().string();
                com.alibaba.fastjson.JSONObject jsonObject3 = JSON.parseObject(result);
                String error = jsonObject3.getString("error");
                if (error == null) {

                } else {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            isSetup = false;
        }

    }

    public void setupPeople(int position,String id) {
        MediaType json = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject1.put("_id",id);
            jsonObject2.put("_id",list.get(position).get_id());
            jsonObject2.put("name",list.get(position).getName());
            jsonObject.put(infoParam,jsonObject1);
            jsonObject.put("userInfo",jsonObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("setupPeople11",String.valueOf(jsonObject));
        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + stepParam)
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
                        Log.e("setupPeople22",result);
                        try {
                            //SetResult setResult = JSON.parseObject(result,SetResult.class);
                            com.alibaba.fastjson.JSONObject jsonObject3 = JSON.parseObject(result);
                            String error = jsonObject3.getString("error");
                            if (error == null) {
                                T("设置成功");
                                setResult(RESULT_OK);
                                finish();
                            } else {
                                T("设置失败");
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
