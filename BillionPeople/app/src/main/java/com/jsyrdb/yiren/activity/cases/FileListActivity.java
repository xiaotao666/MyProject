package com.jsyrdb.yiren.activity.cases;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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
import com.jsyrdb.yiren.adapter.FileAdapter;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.model.FileList;
import com.jsyrdb.yiren.model.LoginResult;
import com.jsyrdb.yiren.model.UploadResult;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonPopupWindow;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.jsyrdb.yiren.model.File;
import com.jsyrdb.yiren.utils.FromAlbumToPathUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FileListActivity extends BaseActivity implements View.OnClickListener {

    //标题栏
//    private TextView tv_title;
//    private ImageView iv_message,iv_search;
//    private LinearLayout ll_back;

    private LinearLayout ll_parent;
    private ListView lv_file;
    private FileAdapter adapter;
    private List<FileList.ResultBean> list = new ArrayList<>();

    private Button btn_upload;

    private SmartRefreshLayout refreshLayout;

    private LinearLayout layout_nothing;//数据为空时显示的view

    private CommonPopupWindow popupWindow;

    private String username;
    private String userid;
    private String customerid;
    private String customername;
    private String caseid;
    private String casename;
    private String processname;
    private String processid;
    private String jobid;
    private String jobname;

    private String file_id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);

        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        File.ResultBean resultBean = (File.ResultBean) getIntent().getSerializableExtra("file");
        if (resultBean != null) {
            file_id = resultBean.get_id();
            customerid = resultBean.getCustomerid();
            customername = resultBean.getCustomername();
            caseid = resultBean.getCaseid();
            casename = resultBean.getCasename();
            processid = resultBean.getProcessid();
            processname = resultBean.getProcessname();
            jobid = resultBean.getJobid();
            jobname = resultBean.getJobname();

        }

        LoginResult.ResultBean.UserBean userBean = CommonUtils.getUserBean(this);
        if (userBean != null) {
            userid = userBean.get_id();
            username = userBean.getUsername();
        }

        initView();
        initListener();
        setTitle("文件");
        getData(file_id);
    }

    public void initView() {
//        tv_title = findViewById(R.id.tv_title);
//        tv_title.setText("文件");
//        iv_message = findViewById(R.id.iv_message);
//        iv_search = findViewById(R.id.iv_search);
//        ll_back = findViewById(R.id.ll_back);
        lv_file = findViewById(R.id.lv_file);
        btn_upload = findViewById(R.id.btn_upload);
        ll_parent = findViewById(R.id.ll_parent);
        layout_nothing = findViewById(R.id.layout_nothing);
        refreshLayout = findViewById(R.id.refreshLayout);

        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData(file_id);
                refreshLayout.finishRefresh();
            }
        });

//        lv_file.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(FileListActivity.this,PreviewFileActivity.class);
//                intent.putExtra("file_id",list.get(position).get_id());
//                intent.putExtra("file_name",list.get(position).getFilename());
//                startActivity(intent);
//            }
//        });
    }

    public void initListener() {
//        ll_back.setOnClickListener(this);
//        iv_message.setOnClickListener(this);
//        iv_search.setOnClickListener(this);
        btn_upload.setOnClickListener(this);
    }

    public void getData(String fileId) {
        String param = "filter[0][jobfileid]="+fileId+"&page=1&limit=0&sort[0][]=creat_timestamp&sort[0][]=1&sort[1][]=name&sort[1][]=1";
        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_job_file/get_job_file_resource_list?"+param)
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
                            Log.e("fileList",result);
                            list.clear();
                            FileList fileList = JSON.parseObject(result,FileList.class);
                            list = fileList.getResult();
                            if (list != null && list.size() > 0) {
                                layout_nothing.setVisibility(View.GONE);
                                lv_file.setVisibility(View.VISIBLE);
                                adapter = new FileAdapter(FileListActivity.this,list);
                                lv_file.setAdapter(adapter);
                            } else {
                                layout_nothing.setVisibility(View.VISIBLE);
                                lv_file.setVisibility(View.GONE);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
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
        builder.addFormDataPart("username",username);
        builder.addFormDataPart("userid",userid);
        builder.addFormDataPart("jobfileid",file_id);
        builder.addFormDataPart("customerid",customerid);
        builder.addFormDataPart("customername",customername);
        builder.addFormDataPart("caseid",caseid);
        builder.addFormDataPart("casename",casename);
        builder.addFormDataPart("processname",processname);
        builder.addFormDataPart("processid", processid);
        builder.addFormDataPart("jobid",jobid);
        builder.addFormDataPart("jobname",jobname);
        builder.addFormDataPart("upfile", path, fileBody);

        MultipartBody requestBody = builder.build();
        Request request = new Request.Builder().url("http://api.jsyrdb.com:84/file/fileup").post(requestBody).build();
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
                        Log.e("gggg11",result);
                        UploadResult uploadResult = JSON.parseObject(result,UploadResult.class);
                        String success = uploadResult.getSucess();
                        if (success.equals("上传成功")) {
                            T("上传成功");
                            getData(file_id);
                        }
                    }
                });
            }
        });

    }

    public void initPopupWindow() {
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.select_file_popup)
                //.setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                .setBackGroundLevel(0.5f)
                //设置PopupWindow里的子View及点击事件
                .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        TextView tv_remote_file = view.findViewById(R.id.tv_remote_file);
                        TextView tv_local_file = view.findViewById(R.id.tv_local_file);
                        TextView tv_cancel = view.findViewById(R.id.tv_cancel);

                        tv_remote_file.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (popupWindow != null) {
                                    popupWindow.dismiss();
                                }
                                Intent intent = new Intent(FileListActivity.this,SelectRemoteFileActivity.class);
                                startActivity(intent);
                            }
                        });

                        tv_local_file.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (popupWindow != null) {
                                    popupWindow.dismiss();
                                }
                                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                                intent1.setType("*/*");
                                intent1.addCategory(Intent.CATEGORY_OPENABLE);
                                startActivityForResult(intent1,1);
                            }
                        });

                        tv_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (popupWindow != null) {
                                    popupWindow.dismiss();
                                }
                            }
                        });

                    }
                })
                .setOutsideTouchable(true)
                .create();
        popupWindow.showAtLocation(findViewById(R.id.ll_parent), Gravity.BOTTOM,0,40);
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
            case R.id.btn_upload:
                initPopupWindow();
                break;
        }
    }
}
