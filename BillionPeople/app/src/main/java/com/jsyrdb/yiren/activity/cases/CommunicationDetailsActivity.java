package com.jsyrdb.yiren.activity.cases;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.main.MainNewsActivity;
import com.jsyrdb.yiren.activity.main.MainSearchActivity;
import com.jsyrdb.yiren.adapter.CommunicationDetailsAdapter;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.model.CommunicationDetails;
import com.jsyrdb.yiren.model.LoginResult;
import com.jsyrdb.yiren.model.UploadResult;
import com.jsyrdb.yiren.model.UserUpload;
import com.jsyrdb.yiren.utils.ClearEditText;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.jsyrdb.yiren.utils.FromAlbumToPathUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CommunicationDetailsActivity extends BaseActivity implements View.OnClickListener {

    //标题栏
//    private TextView tv_title;
//    private ImageView iv_message,iv_search;
//    private LinearLayout ll_back;

    private ClearEditText et_text;
    private TextView tv_send;
    private ImageView iv_select_file;
    private TextView tv_name;
    private LinearLayout ll_name;


    private ListView lv_communication;
    private List<CommunicationDetails.ResultBean> list = new ArrayList<>();
    private CommunicationDetailsAdapter adapter;

    private String group_id = "";
    private String group_name = "";
    private int type;//判断是案件还是员工交流(0:案件 1:员工)

    private String user_id = "";
    private String user_name = "";

    private int page = 1;//分页页码

    private View headerView;//下拉加载更多数据显示的view

    private boolean isLoading = true;//判断是否没有数据了,没有数据则拉到顶部不显示headerView

    //发送文件先上传，上传成功后返回来的id和name
    private String file_id;
    private String file_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicatin_details);

        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        group_id = getIntent().getStringExtra("group_id");
        group_name = getIntent().getStringExtra("group_name");
        type = getIntent().getIntExtra("type",0);

        LoginResult.ResultBean.UserBean userBean = CommonUtils.getUserBean(this);
        if (userBean != null) {
            user_id = userBean.get_id();
            user_name = userBean.getUsername();
        }

        initView();
        if (!TextUtils.isEmpty(group_name)) {
            if (type == 0) {
                ll_name.setVisibility(View.VISIBLE);
                tv_name.setText(group_name);
                setTitle("交流");
            } else if (type == 1) {
                ll_name.setVisibility(View.GONE);
                setTitle(group_name);
            }
        }

        getData();
    }

    public void initView() {
        lv_communication = findViewById(R.id.lv_communication);
//        tv_title = findViewById(R.id.tv_title);
//        tv_title.setText("交流");
//        iv_message = findViewById(R.id.iv_message);
//        iv_search = findViewById(R.id.iv_search);
//        ll_back = findViewById(R.id.ll_back);
        et_text = findViewById(R.id.et_text);
        tv_send = findViewById(R.id.tv_send);
        iv_select_file = findViewById(R.id.iv_select_file);
        tv_name = findViewById(R.id.tv_name);
        ll_name = findViewById(R.id.ll_name);
//        ll_back.setOnClickListener(this);
//        iv_message.setOnClickListener(this);
//        iv_search.setOnClickListener(this);
        tv_send.setOnClickListener(this);
        iv_select_file.setOnClickListener(this);

        headerView = LayoutInflater.from(this).inflate(R.layout.loading_view,null);
        lv_communication.addHeaderView(headerView,null,false);
        headerView.setVisibility(View.GONE);

        lv_communication.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        if (view.getFirstVisiblePosition() == 0) {
                            if (isLoading) {
                                headerView.setVisibility(View.VISIBLE);
                                getMoreData();
                            }
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }

    public void getData() {
        isLoading = true;
        page = 1;
        String json = "filter[0][group_id]="+group_id+"&page=1&limit=10&sort[0][]=creat_timestamp&sort[0][]=-1";//
        json = CommonUtils.getParameter(json);

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_message/get_message_list?"+json)
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
                        Log.e("details",result);
                        list.clear();
                        CommunicationDetails communicationDetails = JSON.parseObject(result,CommunicationDetails.class);
                        list = communicationDetails.getResult();
                        if (list != null && list.size() > 0) {
                            Collections.reverse(list);//将list进行倒序排列
                            adapter = new CommunicationDetailsAdapter(CommunicationDetailsActivity.this,list);
                            lv_communication.setAdapter(adapter);
                            lv_communication.setSelection(adapter.getCount()-1);
                            if (list.size() < 10) {
                                isLoading = false;
                            }
                        }
                    }
                });
            }
        });
    }

    public void getMoreData() {
        page = page + 1;
        String json = "filter[0][group_id]="+group_id+"&page="+page+"&limit=10&sort[0][]=creat_timestamp&sort[0][]=-1";

        json = CommonUtils.getParameter(json);

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_message/get_message_list?"+json)
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
                        headerView.setVisibility(View.GONE);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result =response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("detailsmore",result);
                        CommunicationDetails communicationDetails = JSON.parseObject(result,CommunicationDetails.class);
                        List<CommunicationDetails.ResultBean> moreList = communicationDetails.getResult();
                        if (moreList != null && moreList.size() > 0) {
                            Collections.reverse(moreList);
                            list.addAll(0,moreList);
                            //lv_communication.smoothScrollToPosition(moreList.size());
                            adapter.notifyDataSetChanged();
                            lv_communication.setSelection(moreList.size());
                            headerView.setVisibility(View.GONE);
                        } else {
                            T("已经到顶了");
                            headerView.setVisibility(View.GONE);
                            isLoading = false;
                        }
                    }
                });
            }
        });
    }

    public void sendMessage(int contentType) {//contentType(发送内容类型1.文字 2.文件)
        JSONObject jsonObject = new JSONObject();
        if (type == 0) {
            if (contentType == 1) {
                JSONObject jsonObject1 = new JSONObject();

                jsonObject1.put("group_id",group_id);
                jsonObject1.put("group_name",group_name);
                jsonObject1.put("group_type","case");
                jsonObject1.put("content_type","text");
                jsonObject1.put("content_info",et_text.getText().toString());

                jsonObject.put("messageInfo",jsonObject1);
            } else if (contentType == 2) {
                JSONObject jsonObject1 = new JSONObject();
                JSONObject jsonObject2 = new JSONObject();

                jsonObject1.put("group_id",group_id);
                jsonObject1.put("group_name",group_name);
                jsonObject1.put("group_type","case");
                jsonObject1.put("content_type","file");
                jsonObject2.put("_id",file_id);
                jsonObject2.put("filename",file_name);
                jsonObject1.put("content_info",jsonObject2);

                jsonObject.put("messageInfo",jsonObject1);
            }
        } else if (type == 1){
            if (contentType == 1) {
                JSONObject jsonObject1 = new JSONObject();

                jsonObject1.put("group_id",user_id);
                jsonObject1.put("group_name",user_name);
                jsonObject1.put("group_type","user");
                jsonObject1.put("to_user_id",group_id);
                jsonObject1.put("to_user_name",group_name);
                jsonObject1.put("content_type","text");
                jsonObject1.put("content_info",et_text.getText().toString());

                jsonObject.put("messageInfo",jsonObject1);
            } else if (contentType == 2) {
                JSONObject jsonObject1 = new JSONObject();
                JSONObject jsonObject2 = new JSONObject();

                jsonObject1.put("group_id",user_id);
                jsonObject1.put("group_name",user_name);
                jsonObject1.put("group_type","user");
                jsonObject1.put("to_user_id",group_id);
                jsonObject1.put("to_user_name",group_name);
                jsonObject1.put("content_type","file");
                jsonObject2.put("_id",file_id);
                jsonObject2.put("filename",file_name);
                jsonObject1.put("content_info",jsonObject2);

                jsonObject.put("messageInfo",jsonObject1);
            }

        }


        MediaType json = MediaType.parse("application/json; charset=utf-8");

        Log.e("param",String.valueOf(jsonObject));

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_message/message_creat")
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
                        Log.e("detailsend",result);
                        JSONObject resultObject = JSON.parseObject(result);
                        if (resultObject.getString("error") == null && resultObject.getJSONObject("result") != null) {
                           et_text.setText(null);
                           getData();
                        }
                    }
                });
            }
        });
    }

    //上传文件
    public void uploadFile(String path) {
        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        java.io.File file = new java.io.File(path);
        RequestBody fileBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("username",user_name);
        builder.addFormDataPart("userid",user_id);
        builder.addFormDataPart("type","message");
        builder.addFormDataPart("upfile", path, fileBody);

        MultipartBody requestBody = builder.build();
        Request request = new Request.Builder().url("http://api.jsyrdb.com:84/file_user/fileup").post(requestBody).build();
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
                        Log.e("detailsupload",result);
                        UserUpload userUpload = JSON.parseObject(result,UserUpload.class);
                        String success = userUpload.getSucess();
                        if (success.equals("上传成功")) {
                            //T("上传成功");
                            file_id = userUpload.getInfo().get_id();
                            file_name = userUpload.getInfo().getFilename();
                            sendMessage(2);
                        } else {
                            T("文件上传失败");
                        }
                    }
                });
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String filePath = FromAlbumToPathUtils.getRealPathFromUri(this,data.getData());
            Log.e("ggggg",filePath);
            uploadFile(filePath);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.ll_back:
//                setResult(RESULT_OK);
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
            case R.id.tv_send:
                if (!TextUtils.isEmpty(et_text.getText().toString())) {
                    sendMessage(1);
                } else {
                    T("请输入内容");
                }
                break;
            case R.id.iv_select_file:
                Intent intent2 = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                intent2.setType("*/*");
                intent2.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent2,1);
                break;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            setResult(RESULT_OK);
            finish();
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }
}
