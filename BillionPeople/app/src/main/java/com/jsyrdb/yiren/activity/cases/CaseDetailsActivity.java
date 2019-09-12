package com.jsyrdb.yiren.activity.cases;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.main.MainNewsActivity;
import com.jsyrdb.yiren.activity.main.MainSearchActivity;
import com.jsyrdb.yiren.adapter.CheckItemAdapter;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.glide.GlideUtils;
import com.jsyrdb.yiren.model.Attribute;
import com.jsyrdb.yiren.model.Comment;
import com.jsyrdb.yiren.model.LoginResult;
import com.jsyrdb.yiren.utils.ClearEditText;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonPopupWindow;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.jsyrdb.yiren.utils.DateUtils;
import com.jsyrdb.yiren.model.File;
import com.jsyrdb.yiren.utils.FromAlbumToPathUtils;
import com.jsyrdb.yiren.widget.ExpandListView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CaseDetailsActivity extends BaseActivity implements View.OnClickListener{


    //标题栏
//    private TextView tv_title;
//    private ImageView iv_message,iv_search;
//    private LinearLayout ll_back;

    private SmartRefreshLayout refreshLayout;

    private LinearLayout ll_attribute;
    //属性信息
    private List<Attribute.ResultBean> attributeList = new ArrayList<>();//属性信息列表
    //勾选标签
    private LinearLayout ll_check;
    private TextView tv_save_check;
    private ImageView iv_save_check;
    private ExpandListView lv_check;
    private LinearLayout ll_check_tag;
    private CheckItemAdapter adapter;
    private List<Attribute.ResultBean> checkOutList = new ArrayList<>();//勾选标签
    private HashMap<Integer, Boolean> isSelected = new HashMap<>();
    private List<Attribute.ResultBean> isCheckList = new ArrayList<>();//选中的列表
    //选择属性标签
    private LinearLayout ll_select;
    private TextView tv_select;
    private TextView tv_save_select;
    private ImageView iv_save_select;
    private List<Attribute.ResultBean.SelectOptionsBean> select_options;
    private String select_id;
    //文本属性
    private LinearLayout ll_text;
    private ClearEditText et_text;
    private TextView tv_save_text;
    private ImageView iv_save_text;
    private String text_id;

    //日期范围时间选择弹出框
    private CommonPopupWindow dateRangePopup;
    private boolean isFirst = true;

    //日期范围
    private LinearLayout ll_range;
    private LinearLayout ll_date_range;
    private TextView tv_save_date_range;
    private ImageView iv_save_date_range;
    private TextView tv_start_date;
    private TextView tv_end_date;
    private List<String> dateList;
    private String range_id;
    //日期属性
    private LinearLayout ll_date;
    private CommonPopupWindow datePopup;
    private TextView tv_attribute_date;
    private TextView tv_save_date;
    private ImageView iv_save_date;
    private String date_id;
    //数字属性
    private LinearLayout ll_num;
    private TextView tv_save_num;
    private ImageView iv_save_num;
    private EditText et_num;
    private String num_id;

    //评论
    private TagFlowLayout tagFlow_comment;
    private TagAdapter<Comment.ResultBean> commentAdapter;
    private List<Comment.ResultBean> commentList = new ArrayList<>();
    //文件
    private TagFlowLayout tagFlow_file;
    private TagAdapter<File.ResultBean> fileAdapter;
    private List<File.ResultBean> fileList = new ArrayList<>();

    //企业地址
    private ImageView iv_address;
    //文件
    private Button btn_commit_file;
    private  CommonPopupWindow popupWindow;
    //执照
    private Button btn_commit_img;
    //标签
    private TagFlowLayout tagFlowLayout;
    private TagAdapter<String> tagAdapter;
    private List<String> tagList;
    private Button btn_custom;//自定义标签

    private Button btn_complete;//完成任务
    private Button btn_start;//开始任务

    private boolean isEdit = false;//是否是编辑模式

    private String endServerUrl;//设置任务完成的接口地址(流程开始任务：set_start_job_end，其他:set_job_end)

    private String jobid;
    private String processid;
    private String nodename;
    private String status;

    private String user_id = "";
    private String user_name = "";

    //是否可以编辑
    private boolean isSave1 = false;
    private boolean isSave2 = false;
    private boolean isSave3 = false;
    private boolean isSave4 = false;
    private boolean isSave5 = false;
    private boolean isSave6 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_details);

        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        LoginResult.ResultBean.UserBean userBean = CommonUtils.getUserBean(this);
        if (userBean != null) {
            user_id = userBean.get_id();
            user_name = userBean.getUsername();
        }
        jobid = getIntent().getStringExtra("jobid");
        processid = getIntent().getStringExtra("processid");
        status = getIntent().getStringExtra("status");
        nodename = getIntent().getStringExtra("nodename");

        initView();
        initListener();
        setTitle("详情");
        //initTagData();
        getAttributeData();
        getCommentData();
        getFileData();
        getTiemData();
    }

    //获取属性信息
    public void getAttributeData() {
        String param = "filter[0][jobid]="+jobid+"&filter[1][plugin_type]=attributetag&page=1&limit=0&sort[0][]=tagidx&sort[0][]=1&sort[1][]=jobidx&sort[1][]=1";
        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_job/get_job_attribute_list?"+param)
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
                        Log.e("CaseDetail111",result);
                        try {
                            attributeList.clear();
                            Attribute attribute = JSON.parseObject(result,Attribute.class);
                            attributeList = attribute.getResult();
                            if (attributeList != null && attributeList.size() > 0) {
                                ll_attribute.setVisibility(View.VISIBLE);
//                                user_id = attributeList.get(0).getUserid();
//                                user_name = attributeList.get(0).getUsername();
                                for (int i = 0;i < attributeList.size();i++) {
                                    if (attributeList.get(i).getInput_type().equals("check")) {
                                        if (attributeList.get(i).getValue() != null) {
                                            ll_check.setVisibility(View.VISIBLE);
                                            checkOutList.clear();
                                            isSelected.put(i, (Boolean) attributeList.get(i).getValue());
                                            checkOutList.add(attributeList.get(i));
                                            adapter = new CheckItemAdapter(CaseDetailsActivity.this,checkOutList,isSelected);
                                            lv_check.setAdapter(adapter);
                                            adapter.disableAllItemChoser();
                                        }
                                    }
                                    if (attributeList.get(i).getInput_type().equals("select")) {
                                        if (attributeList.get(i).getValue() != null) {
                                            ll_select.setVisibility(View.VISIBLE);
                                            tv_select.setText((String)attributeList.get(i).getValue());
                                            select_options = attributeList.get(i).getSelect_options();
                                            select_id = attributeList.get(i).get_id();
                                        }
                                    }
                                    if (attributeList.get(i).getInput_type().equals("text")) {
                                        if (attributeList.get(i).getValue() != null) {
                                            ll_text.setVisibility(View.VISIBLE);
                                            et_text.setText((String)attributeList.get(i).getValue());
                                            text_id = attributeList.get(i).get_id();
                                        }
                                    }
                                    if (attributeList.get(i).getInput_type().equals("date_range")) {
                                        if (attributeList.get(i).getValue() != null) {
                                            ll_range.setVisibility(View.VISIBLE);
                                            range_id = attributeList.get(i).get_id();
                                            dateList = (List<String>) attributeList.get(i).getValue();
                                            if (dateList != null && dateList.size() > 0) {
                                                tv_start_date.setText(dateList.get(0));
                                                tv_end_date.setText(dateList.get(1));
                                            }
                                        }

                                    }

                                    if (attributeList.get(i).getInput_type().equals("date")) {
                                        if (attributeList.get(i).getValue() != null) {
                                            ll_date.setVisibility(View.VISIBLE);
                                            tv_attribute_date.setText(String.valueOf(attributeList.get(i).getValue()));
                                            date_id = attributeList.get(i).get_id();
                                        }

                                    }
                                    if (attributeList.get(i).getInput_type().equals("number")) {
                                        if (attributeList.get(i).getValue() != null) {
                                            ll_num.setVisibility(View.VISIBLE);
                                            et_num.setText(attributeList.get(i).getValue() + "");
                                            num_id = attributeList.get(i).get_id();
                                        }
                                    }
                                }
                            } else {
                                ll_attribute.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                });
            }
        });
    }


    //获取评论信息
    public void getCommentData() {
        String param = "filter[0][jobid]="+jobid+"&filter[1][plugin_type]=commenttag&page=1&limit=0&sort[0][]=tagidx&sort[0][]=1&sort[1][]=jobidx&sort[1][]=1";
        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_job/get_job_comment_list?"+param)
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
                        Log.e("CaseDetail222",result);
                        commentList.clear();
                        Comment comment = JSON.parseObject(result,Comment.class);
                        commentList = comment.getResult();
                        if (commentList != null && commentList.size() > 0) {
                            tagFlow_comment.setVisibility(View.VISIBLE);
                            commentAdapter = new TagAdapter<Comment.ResultBean>(commentList) {
                                @Override
                                public View getView(FlowLayout parent, int position, Comment.ResultBean resultBean) {
                                    View view = LayoutInflater.from(CaseDetailsActivity.this).inflate(R.layout.case_tag,tagFlowLayout,false);
                                    TextView tv_tag = view.findViewById(R.id.tv_tag);
                                    LinearLayout ll_tag = view.findViewById(R.id.ll_tag);
                                    tv_tag.setText(resultBean.getName());

                                    return view;
                                }
                            };
                            tagFlow_comment.setAdapter(commentAdapter);

                            tagFlow_comment.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                                @Override
                                public boolean onTagClick(View view, int position, FlowLayout parent) {
                                    Intent intent = new Intent(CaseDetailsActivity.this,CommentListActivity.class);
                                    intent.putExtra("commentid",commentList.get(position).get_id());
                                    startActivity(intent);
                                    return true;
                                }
                            });

                        }

                    }
                });
            }
        });
    }


    //获取文件信息
    public void getFileData() {
        String param = "filter[0][jobid]="+jobid+"&filter[1][plugin_type]=filetag&page=1&limit=0&sort[0][]=tagidx&sort[0][]=1&sort[1][]=jobidx&sort[1][]=1";
        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_job/get_job_file_list?"+param)
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
                        Log.e("CaseDetail333",result);
                        fileList.clear();
                        File file = JSON.parseObject(result,File.class);
                        fileList = file.getResult();
                        if (fileList != null && fileList.size() > 0) {
                            tagFlow_file.setVisibility(View.VISIBLE);
                            fileAdapter = new TagAdapter<File.ResultBean>(fileList) {
                                @Override
                                public View getView(FlowLayout parent, int position, File.ResultBean resultBean) {
                                    View view = LayoutInflater.from(CaseDetailsActivity.this).inflate(R.layout.case_tag,tagFlowLayout,false);
                                    TextView tv_tag = view.findViewById(R.id.tv_tag);
                                    LinearLayout ll_tag = view.findViewById(R.id.ll_tag);
                                    tv_tag.setText(resultBean.getName());
                                    return view;
                                }
                            };

                            tagFlow_file.setAdapter(fileAdapter);
                            tagFlow_file.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                                @Override
                                public boolean onTagClick(View view, int position, FlowLayout parent) {
                                    Intent intent = new Intent(CaseDetailsActivity.this,FileListActivity.class);
                                    //intent.putExtra("fileid",fileList.get(position).get_id());
                                    intent.putExtra("file",fileList.get(position));
                                    startActivity(intent);
                                    return true;
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    public void getTiemData() {
        String param = "filter[0][jobid]="+jobid+"&filter[1][plugin_type]=time&page=1&limit=0&sort[0][]=tagidx&sort[0][]=1&sort[1][]=jobidx&sort[1][]=1";
        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_job_time/get_job_time?"+param)
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
                        Log.e("CaseDetail444",result);
                    }
                });
            }
        });
    }

    public void initView() {
//        tv_title = findViewById(R.id.tv_title);
//        tv_title.setText("详情");
//        iv_message = findViewById(R.id.iv_message);
//        iv_search = findViewById(R.id.iv_search);
//        ll_back = findViewById(R.id.ll_back);
        tagFlowLayout = findViewById(R.id.flowLayout);
        lv_check = findViewById(R.id.lv_check);
        btn_commit_file = findViewById(R.id.btn_commit_file);
        btn_commit_img = findViewById(R.id.btn_commit_img);
        iv_address = findViewById(R.id.iv_address);
        btn_custom = findViewById(R.id.btn_custom);

        ll_attribute = findViewById(R.id.ll_attribute);
        ll_check = findViewById(R.id.ll_check);
        ll_select = findViewById(R.id.ll_select);
        ll_text = findViewById(R.id.ll_text);
        ll_range = findViewById(R.id.ll_range);
        ll_date = findViewById(R.id.ll_date);
        ll_num = findViewById(R.id.ll_num);
        et_text = findViewById(R.id.et_text);
        tv_select = findViewById(R.id.tv_select);
        ll_date_range = findViewById(R.id.ll_date_range);
        tv_start_date = findViewById(R.id.tv_start_date);
        tv_end_date = findViewById(R.id.tv_end_date);
        tv_attribute_date = findViewById(R.id.tv_attribute_date);
        et_num = findViewById(R.id.et_num);
        tv_save_check = findViewById(R.id.tv_save_check);
        tv_save_text = findViewById(R.id.tv_save_text);
        tv_save_date_range = findViewById(R.id.tv_save_date_range);
        tv_save_date = findViewById(R.id.tv_save_date);
        tv_save_num = findViewById(R.id.tv_save_num);
        tv_save_select = findViewById(R.id.tv_save_select);
        tagFlow_comment = findViewById(R.id.tagFlow_comment);
        tagFlow_file = findViewById(R.id.tagFlow_file);
        refreshLayout = findViewById(R.id.refreshLayout);
        ll_check_tag = findViewById(R.id.ll_check_tag);
        iv_save_check = findViewById(R.id.iv_save_check);
        iv_save_select = findViewById(R.id.iv_save_select);
        iv_save_text = findViewById(R.id.iv_save_text);
        iv_save_date_range = findViewById(R.id.iv_save_date_range);
        iv_save_date = findViewById(R.id.iv_save_date);
        iv_save_num = findViewById(R.id.iv_save_num);
        btn_complete = findViewById(R.id.btn_complete);
        btn_start = findViewById(R.id.btn_start);

        tv_select.setEnabled(false);
        tv_select.setClickable(false);

        et_text.setEnabled(false);
        et_text.setClickable(false);

        ll_date_range.setEnabled(false);
        ll_date_range.setClickable(false);

        tv_attribute_date.setEnabled(false);
        tv_attribute_date.setClickable(false);

        et_num.setEnabled(false);
        et_num.setClickable(false);

        iv_save_check.setActivated(false);
        iv_save_select.setActivated(false);
        iv_save_text.setActivated(false);
        iv_save_date_range.setActivated(false);
        iv_save_date.setActivated(false);
        iv_save_num.setActivated(false);

        if (!TextUtils.isEmpty(status)) {
            if (status.equals("waiting")) {
                btn_start.setVisibility(View.VISIBLE);
                btn_complete.setVisibility(View.GONE);
            } else if (status.equals("ending")) {
                btn_start.setVisibility(View.GONE);
                btn_complete.setVisibility(View.VISIBLE);
                btn_complete.setText("任务已完成");
                btn_complete.setTextColor(Color.GRAY);
                btn_complete.setBackgroundResource(R.drawable.btn_gray_small_circle);
                btn_complete.setEnabled(false);
                btn_complete.setClickable(false);
            } else {
                btn_start.setVisibility(View.GONE);
                btn_complete.setVisibility(View.VISIBLE);
                btn_complete.setText("完成任务");
                btn_complete.setTextColor(getResources().getColor(R.color.white));
                btn_complete.setBackgroundResource(R.drawable.btn_green_small_circle);
                btn_complete.setEnabled(true);
                btn_complete.setClickable(true);
            }
        } else {
            btn_start.setVisibility(View.VISIBLE);
            btn_complete.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(nodename)) {
            if (nodename.equals("startnode")) {
                btn_start.setVisibility(View.GONE);
                //btn_complete.setVisibility(View.VISIBLE);
            }
        }



        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getAttributeData();
                getCommentData();
                getFileData();
                getTiemData();
                refreshLayout.finishRefresh();
            }
        });
    }

    public void initListener() {
        btn_commit_file.setOnClickListener(this);
        btn_commit_img.setOnClickListener(this);
        btn_custom.setOnClickListener(this);
        btn_complete.setOnClickListener(this);
        btn_start.setOnClickListener(this);
//        ll_back.setOnClickListener(this);
//        iv_message.setOnClickListener(this);
//        iv_search.setOnClickListener(this);

        tv_attribute_date.setOnClickListener(this);
        tv_save_check.setOnClickListener(this);
        tv_save_text.setOnClickListener(this);
        tv_save_date_range.setOnClickListener(this);
        tv_save_select.setOnClickListener(this);
        tv_save_date.setOnClickListener(this);
        tv_save_num.setOnClickListener(this);
        ll_date_range.setOnClickListener(this);
        tv_select.setOnClickListener(this);
        iv_save_check.setOnClickListener(this);
        iv_save_select.setOnClickListener(this);
        iv_save_text.setOnClickListener(this);
        iv_save_date_range.setOnClickListener(this);
        iv_save_date.setOnClickListener(this);
        iv_save_num.setOnClickListener(this);
    }

    public void initTagData() {
        tagList = new ArrayList<>();
        tagList.add("标签");
        tagList.add("新的标签2222");
        tagList.add("新的标签");
        tagList.add("标签11111111");
        tagList.add("新的标签555555555");
        tagList.add("新的标");
        tagList.add("新的标签");
        tagList.add("标签");

        tagAdapter = new TagAdapter<String>(tagList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                View view = LayoutInflater.from(CaseDetailsActivity.this).inflate(R.layout.case_tag,tagFlowLayout,false);
                TextView tv_tag = view.findViewById(R.id.tv_tag);
                ImageView iv_delete = view.findViewById(R.id.iv_delete);
                LinearLayout ll_delete = view.findViewById(R.id.ll_delete);
                LinearLayout ll_tag = view.findViewById(R.id.ll_tag);
                tv_tag.setText(s);

                ll_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tagList.remove(position);
                        tagAdapter.notifyDataChanged();
                    }
                });

                if (isEdit) {
                    ll_delete.setVisibility(View.VISIBLE);
                } else {
                    ll_delete.setVisibility(View.GONE);
                }

                ll_tag.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        isEdit = true;
                        tagAdapter.notifyDataChanged();
                        return true;
                    }
                });

                return view;
            }
        };

        tagFlowLayout.setAdapter(tagAdapter);

    }



//    public void saveCheckList() {
//        isCheckList.clear();
//        isSelected = CheckItemAdapter.getIsSelected();
//        if (isSelected != null && isSelected.size() > 0) {
//            for (int i = 0; i < isSelected.size(); i++) {
//                if (isSelected.get(i).equals(true)) {
//                    isCheckList.add(checkOutList.get(i));
//                }
//            }
//            Log.e("check", isCheckList.size() + "");
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.ll_back:
//                finish();
//                break;
            case R.id.btn_commit_img:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent,1);
                break;
            case R.id.btn_commit_file:
                initPopupWindow();
               break;
            case R.id.btn_custom:
                Intent intent1 = new Intent(this,CustomActivity.class);
                startActivityForResult(intent1,3);
                break;
//            case R.id.iv_message:
//                Intent intent2 = new Intent(this, MainNewsActivity.class);
//                startActivity(intent2);
//                break;
//            case R.id.iv_search:
//                Intent intent3 = new Intent(this, MainSearchActivity.class);
//                startActivity(intent3);
//                break;
            case R.id.tv_select:
                Intent intent4 = new Intent(this,SelectTagActivity.class);
                intent4.putExtra("select_options", (Serializable) select_options);
                startActivityForResult(intent4,3);
                break;
            case R.id.ll_date_range:
                initDateRangePopup();
                break;
            case R.id.tv_attribute_date:
                initDatePopup();
                break;
            case R.id.iv_save_check:
                if (isSave1) {
                    isSave1 = false;
                    iv_save_check.setActivated(false);

                    adapter.disableAllItemChoser();

                    JSONObject jsonObject = new JSONObject();
                    JSONObject jsonObject1 = new JSONObject();
                    JSONObject jsonObject2 = new JSONObject();
                    try {
                        jsonObject1.put("_id",checkOutList.get(0).get_id());
                        jsonObject1.put("value",CheckItemAdapter.getIsSelected().get(0));
                        jsonObject2.put("_id",user_id);
                        jsonObject2.put("name",user_name);
                        jsonObject.put("attributeInfo",jsonObject1);
                        jsonObject.put("userInfo",jsonObject2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.e("CaseDetailParam",String.valueOf(jsonObject));
                    saveCheck(String.valueOf(jsonObject));
                } else {
                    isSave1 = true;
                    iv_save_check.setActivated(true);
                    adapter.enableItemChoser();
                }


                break;
            case R.id.iv_save_select:
                if (isSave2) {
                    isSave2 = false;
                    iv_save_select.setActivated(false);
                    tv_select.setEnabled(false);
                    tv_select.setClickable(false);

                    JSONObject selectJsonObject = new JSONObject();
                    JSONObject selectJsonObject1 = new JSONObject();
                    JSONObject selectJsonObject2 = new JSONObject();
                    try {
                        selectJsonObject1.put("_id",select_id);
                        selectJsonObject1.put("value",tv_select.getText().toString());
                        selectJsonObject2.put("_id",user_id);
                        selectJsonObject2.put("name",user_name);
                        selectJsonObject.put("attributeInfo",selectJsonObject1);
                        selectJsonObject.put("userInfo",selectJsonObject2);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    saveCheck(String.valueOf(selectJsonObject));
                } else {
                    isSave2 = true;
                    iv_save_select.setActivated(true);
                    tv_select.setEnabled(true);
                    tv_select.setClickable(true);
                }

                break;
            case R.id.iv_save_text:
                if (isSave3) {
                    isSave3 = false;
                    iv_save_text.setActivated(false);
                    et_text.setEnabled(false);
                    et_text.setClickable(false);

                    JSONObject textJsonObject = new JSONObject();
                    JSONObject textJsonObject1 = new JSONObject();
                    JSONObject textJsonObject2 = new JSONObject();
                    try {
                        textJsonObject1.put("_id",text_id);
                        textJsonObject1.put("value",et_text.getText().toString());
                        textJsonObject2.put("_id",user_id);
                        textJsonObject2.put("name",user_name);
                        textJsonObject.put("attributeInfo",textJsonObject1);
                        textJsonObject.put("userInfo",textJsonObject2);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    saveCheck(String.valueOf(textJsonObject));
                } else {
                    isSave3 = true;
                    iv_save_text.setActivated(true);
                    et_text.setEnabled(true);
                    et_text.setClickable(true);
                }

                break;
            case R.id.iv_save_date_range:
                if (isSave4) {
                    isSave4 = false;
                    iv_save_date_range.setActivated(false);
                    ll_date_range.setEnabled(false);
                    ll_date_range.setClickable(false);

                    JSONObject rangeJsonObject = new JSONObject();
                    JSONObject rangeJsonObject1 = new JSONObject();
                    JSONObject rangeJsonObject2 = new JSONObject();
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.put(tv_start_date.getText().toString());
                    jsonArray.put(tv_end_date.getText().toString());
                    try {
                        rangeJsonObject1.put("_id",range_id);
                        rangeJsonObject1.put("value",jsonArray);
                        rangeJsonObject2.put("_id",user_id);
                        rangeJsonObject2.put("name",user_name);
                        rangeJsonObject.put("attributeInfo",rangeJsonObject1);
                        rangeJsonObject.put("userInfo",rangeJsonObject2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.e("CaseDetailParam",String.valueOf(rangeJsonObject));
                    saveCheck(String.valueOf(rangeJsonObject));
                } else {
                    isSave4 = true;
                    iv_save_date_range.setActivated(true);
                    ll_date_range.setEnabled(true);
                    ll_date_range.setClickable(true);
                }

                break;
            case R.id.iv_save_num:
                if (isSave6) {
                    isSave6 = false;
                    iv_save_num.setActivated(false);
                    et_num.setEnabled(false);
                    et_num.setClickable(false);

                    JSONObject numJsonObject = new JSONObject();
                    JSONObject numJsonObject1 = new JSONObject();
                    JSONObject numJsonObject2 = new JSONObject();
                    try {
                        numJsonObject1.put("_id",num_id);
                        numJsonObject1.put("value",et_num.getText().toString());
                        numJsonObject2.put("_id",user_id);
                        numJsonObject2.put("name",user_name);
                        numJsonObject.put("attributeInfo",numJsonObject1);
                        numJsonObject.put("userInfo",numJsonObject2);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    saveCheck(String.valueOf(numJsonObject));
                } else {
                    isSave6 = true;
                   iv_save_num.setActivated(true);
                    et_num.setEnabled(true);
                    et_num.setClickable(true);
                }

                break;
            case R.id.iv_save_date:
                if (isSave5) {
                    isSave5 = false;
                    iv_save_date.setActivated(false);
                    tv_attribute_date.setEnabled(false);
                    tv_attribute_date.setClickable(false);

                    JSONObject dateJsonObject = new JSONObject();
                    JSONObject dateJsonObject1 = new JSONObject();
                    JSONObject dateJsonObject2 = new JSONObject();
                    try {
                        dateJsonObject1.put("_id",date_id);
                        dateJsonObject1.put("value",tv_attribute_date.getText().toString());
                        dateJsonObject2.put("_id",user_id);
                        dateJsonObject2.put("name",user_name);
                        dateJsonObject.put("attributeInfo",dateJsonObject1);
                        dateJsonObject.put("userInfo",dateJsonObject2);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    saveCheck(String.valueOf(dateJsonObject));
                } else {
                    isSave5 = true;
                    iv_save_date.setActivated(true);
                    tv_attribute_date.setEnabled(true);
                    tv_attribute_date.setClickable(true);
                }

                break;
            case R.id.btn_complete:
                setJobEnd();
                break;
            case R.id.btn_start:
                setJobStart();
                break;
        }
    }

    //保存属性
    public void saveCheck(String jsonObject) {
        MediaType json = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        RequestBody requestBody = RequestBody.create(json,jsonObject);
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_job_attribute/set_job_attribute")
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
                        Log.e("CaseDetailSave",result);
                        com.alibaba.fastjson.JSONObject resultObject = JSON.parseObject(result);
                        if (resultObject.getString("error") == null) {
                            T("保存成功");
                        }
                    }
                });
            }
        });
    }

    //设置任务开始
    public void setJobStart() {
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        com.alibaba.fastjson.JSONObject jsonObject1 = new com.alibaba.fastjson.JSONObject();

        jsonObject1.put("_id",jobid);
        jsonObject.put("jobInfo",jsonObject1);

        MediaType json = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Log.e("processidurl",String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_job/set_job_start")
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
//                        com.alibaba.fastjson.JSONObject jsonObject2 = JSON.parseObject(result);
//                        if (jsonObject2.getString("error") == null) {
//                            T("设置成功");
//                            btn_start.setVisibility(View.GONE);
//                            btn_complete.setVisibility(View.VISIBLE);
//                            btn_complete.setText("完成任务");
//                            btn_complete.setTextColor(getResources().getColor(R.color.white));
//                            btn_complete.setBackgroundResource(R.drawable.btn_green_small_circle);
//                            btn_complete.setEnabled(true);
//                            btn_complete.setClickable(true);
//                        }

                        try {
                            com.alibaba.fastjson.JSONObject jsonObject2 = JSON.parseObject(result);
                            if (jsonObject2.getString("error") == null) {
                                T("设置成功");
                                btn_start.setVisibility(View.GONE);
                                btn_complete.setVisibility(View.VISIBLE);
                                btn_complete.setText("完成任务");
                                btn_complete.setTextColor(getResources().getColor(R.color.white));
                                btn_complete.setBackgroundResource(R.drawable.btn_green_small_circle);
                                btn_complete.setEnabled(true);
                                btn_complete.setClickable(true);
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

    //设置任务完成
    public void setJobEnd() {
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        if (!TextUtils.isEmpty(nodename)) {
            if (nodename.equals("startnode")) {
                endServerUrl = "action_job/set_start_job_end";
                com.alibaba.fastjson.JSONObject jsonObject1 = new com.alibaba.fastjson.JSONObject();
                jsonObject1.put("_id",jobid);
                jsonObject1.put("processid",processid);
                jsonObject.put("jobInfo",jsonObject1);
            } else {
                endServerUrl = "action_job/set_job_end";
                com.alibaba.fastjson.JSONObject jsonObject1 = new com.alibaba.fastjson.JSONObject();
                jsonObject1.put("_id",jobid);
                jsonObject.put("jobInfo",jsonObject1);
            }

            MediaType json = MediaType.parse("application/json; charset=utf-8");
            OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
            RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
            Log.e("processidurl",String.valueOf(jsonObject));
            Request request = new Request.Builder()
                    .url(CommonData.server_url + endServerUrl)
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
                    Log.e("jobend",result);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            com.alibaba.fastjson.JSONObject jsonObject2 = JSON.parseObject(result);
//                            if (jsonObject2.getString("error") == null) {
//                                T("设置成功");
//                                btn_complete.setText("已完成");
//                                btn_complete.setTextColor(Color.GRAY);
//                                btn_complete.setBackgroundResource(R.drawable.btn_gray_small_circle);
//                                btn_complete.setEnabled(false);
//                                btn_complete.setClickable(false);
//                            }
                            try {
                                com.alibaba.fastjson.JSONObject jsonObject2 = JSON.parseObject(result);
                                if (jsonObject2.getString("error") == null) {
                                    T("设置成功");
                                    btn_complete.setText("任务已完成");
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

    }

    //比较两个时间的大小
    public int timeCompare(String startTime, String endTime){
        int i=0;
        //注意：传过来的时间格式必须要和这里填入的时间格式相同
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = dateFormat.parse(startTime);//开始时间
            Date date2 = dateFormat.parse(endTime);//结束时间
            // 1 结束时间小于开始时间 2 开始时间与结束时间相同 3 结束时间大于开始时间
            if (date2.getTime()<date1.getTime()){
                //结束时间小于开始时间

                i= 1;
            }else if (date2.getTime()==date1.getTime()){

                //开始时间与结束时间相同

                i= 2;
            }else if (date2.getTime()>date1.getTime()){
                //结束时间大于开始时间
                i= 3;
            }
        } catch (Exception e) {

        }
        return  i;
    }


    //日期范围选择弹出框
    public void initDateRangePopup() {
        dateRangePopup = new CommonPopupWindow.Builder(this)
                .setView(R.layout.time_choose_popup)
                .setBackGroundLevel(0.5f)
                .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        TextView tv_current_day = view.findViewById(R.id.tv_current_day);
                        CalendarView calendarView = view.findViewById(R.id.calendarView);
                        tv_current_day.setText(calendarView.getCurYear() + " 年 " + calendarView.getCurMonth() + " 月 " + calendarView.getCurDay() + " 日");
                        calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
                            @Override
                            public void onCalendarOutOfRange(Calendar calendar) {

                            }

                            @Override
                            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                                if (isFirst) {
                                    isFirst = false;
                                    tv_start_date.setText(DateUtils.getDate2String(calendar.getTimeInMillis(),"yyy-MM-dd"));
                                } else {
                                    if (timeCompare(tv_start_date.getText().toString(),DateUtils.getDate2String(calendar.getTimeInMillis(),"yyy-MM-dd"))==3){
                                        isFirst = true;
                                        tv_end_date.setText(DateUtils.getDate2String(calendar.getTimeInMillis(),"yyy-MM-dd"));
                                        if (dateRangePopup != null) {
                                            dateRangePopup.dismiss();
                                            dateRangePopup = null;
                                        }
                                    } else {
                                        T("截止日期要大于起始日期");
                                    }

                                }
                            }
                        });

                        calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
                            @Override
                            public void onMonthChange(int year, int month) {
                                tv_current_day.setText(year + " 年 " + month + " 月");
                            }
                        });
                    }
                })
                .setOutsideTouchable(false)
                .create();
        dateRangePopup.showAtLocation(findViewById(R.id.ll_parent),Gravity.CENTER,0,0);
    }

    public void initDatePopup() {
        datePopup = new CommonPopupWindow.Builder(this)
                .setView(R.layout.time_choose_popup)
                .setBackGroundLevel(0.5f)
                .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        TextView tv_current_day = view.findViewById(R.id.tv_current_day);
                        CalendarView calendarView = view.findViewById(R.id.calendarView);
                        tv_current_day.setText(calendarView.getCurYear() + " 年 " + calendarView.getCurMonth() + " 月 " + calendarView.getCurDay() + " 日");
                        calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
                            @Override
                            public void onCalendarOutOfRange(Calendar calendar) {

                            }

                            @Override
                            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                                tv_attribute_date.setText(DateUtils.getDate2String(calendar.getTimeInMillis(),"yyy-MM-dd"));
                                if (datePopup != null) {
                                    datePopup.dismiss();
                                    datePopup = null;
                                }
                            }
                        });

                        calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
                            @Override
                            public void onMonthChange(int year, int month) {
                                tv_current_day.setText(year + " 年 " + month + " 月");
                            }
                        });
                    }
                })
                .setOutsideTouchable(true)
                .create();
        datePopup.showAtLocation(findViewById(R.id.ll_parent),Gravity.CENTER,0,0);
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
                                Intent intent = new Intent(CaseDetailsActivity.this,SelectRemoteFileActivity.class);
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
                                startActivityForResult(intent1,2);
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
            String photoPath = FromAlbumToPathUtils.getRealPathFromUri(this,data.getData());
            GlideUtils.loadPic(this,photoPath,iv_address,R.drawable.logo,R.drawable.logo);
        } else if (requestCode == 2 && resultCode == RESULT_OK){
            String filePath = FromAlbumToPathUtils.getRealPathFromUri(this,data.getData());
//            Intent intent = new Intent(this,PreviewFileActivity.class);
//            intent.putExtra("path",filePath);
//            startActivity(intent);

        } else if (requestCode == 3 && resultCode == RESULT_OK) {
            String value = data.getStringExtra("selectValue");
            if (!TextUtils.isEmpty(value)) {
                tv_select.setText(value);
            }
        }
    }
}
