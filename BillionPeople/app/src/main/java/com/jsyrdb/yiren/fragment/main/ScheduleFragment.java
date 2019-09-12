package com.jsyrdb.yiren.fragment.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.cases.CaseDetailsActivity;
import com.jsyrdb.yiren.activity.main.MainNewsActivity;
import com.jsyrdb.yiren.activity.main.MainSearchActivity;
import com.jsyrdb.yiren.adapter.ScheduleCaseAdapter;
import com.jsyrdb.yiren.adapter.ScheduleTaskAdapter;
import com.jsyrdb.yiren.base.BaseFragment;
import com.jsyrdb.yiren.glide.GlideApp;
import com.jsyrdb.yiren.model.LoginResult;
import com.jsyrdb.yiren.model.ScheduleCase;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonPopupWindow;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.jsyrdb.yiren.utils.DateUtils;
import com.jsyrdb.yiren.utils.GlobalParam;
import com.jsyrdb.yiren.widget.ExpandListView;
import com.jsyrdb.yiren.widget.MyRefreshLayout;
import com.jsyrdb.yiren.widget.wheelview.JudgeDate;
import com.jsyrdb.yiren.widget.wheelview.ScreenInfo;
import com.jsyrdb.yiren.widget.wheelview.WheelMain;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

public class ScheduleFragment extends BaseFragment implements  CalendarView.OnMonthChangeListener,
        CalendarView.OnYearChangeListener,CalendarView.OnCalendarSelectListener, CalendarView.OnViewChangeListener,View.OnClickListener, OnBannerListener {

    //标题栏
    private TextView tv_title,tv_unread_count;
    private ImageView iv_message,iv_search,iv_back;

    private View view;
    private TextView tv_current_day;
    private TextView tv_week;

    private LinearLayout ll_day;

    private CalendarLayout calendarLayout;
    private CalendarView calendarView;

    private Banner banner;//轮播图
    private List<Integer> images = new ArrayList<>();

    private ImageView iv_arrow;
//    private RecyclerView lv_case;
//    private List<ScheduleCase.ResultBean.JobListBean> allList = new ArrayList<>();//某个月份所有任务的数据,为list1,2,3的总和
//    private List<ScheduleCase.ResultBean.JobListBean> dayList = new ArrayList<>();//某个月份的某一天所有任务的数据

    private List<String> taskList = new ArrayList<>();//有任务的日期列表
    //private ScheduleCaseAdapter adapter;

    private MyRefreshLayout refreshLayout;
    private ExpandListView lv_start;
    private ExpandListView lv_be_complete;
    private ExpandListView lv_not_start;

    private LinearLayout layout_nothing;//数据为空时显示的view

    private List<ScheduleCase.ResultBean.JobListBean> list1 = new ArrayList<>();//
    private List<ScheduleCase.ResultBean.JobListBean> list2 = new ArrayList<>();
    private List<ScheduleCase.ResultBean.JobListBean> list3 = new ArrayList<>();
    private List<ScheduleCase.ResultBean.JobListBean> allList1 = new ArrayList<>();//一个月的已开始的所有的任务
    private List<ScheduleCase.ResultBean.JobListBean> allList2 = new ArrayList<>();//一个月的应完成的所有的任务
    private List<ScheduleCase.ResultBean.JobListBean> allList3 = new ArrayList<>();//一个月的未开始的所有的任务
    private List<ScheduleCase.ResultBean.JobListBean> dayList1 = new ArrayList<>();//某一天的已开始所有任务的数据
    private List<ScheduleCase.ResultBean.JobListBean> dayList2 = new ArrayList<>();//某一天的应完成所有任务的数据
    private List<ScheduleCase.ResultBean.JobListBean> dayList3 = new ArrayList<>();//某一天的未开始所有任务的数据

    private ScheduleTaskAdapter startAdaper;
    private ScheduleTaskAdapter beCompleteAdaper;
    private ScheduleTaskAdapter notStartAdaper;

    private LinearLayout ll_start;
    private LinearLayout ll_be_complete;
    private LinearLayout ll_not_start;

    private String queryMonth = "";//要查询的某月

    private String selectDate = "";//点击的某一天

    private boolean isChange = false;//判断是否切换了月份,是的话列表就不做处理，点击的时候在做处理

    private boolean isTimeSelect = true;

    private String user_id;

    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(GlobalParam.ACTION_NOTIFICATION)) {
                getApplyCount();
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoginResult.ResultBean.UserBean userBean = CommonUtils.getUserBean(getActivity());
        if (userBean != null) {
            user_id = userBean.get_id();
        }
        initView();
        initListener();

        getApplyCount();

        IntentFilter filter = new IntentFilter();
        filter.addAction(GlobalParam.ACTION_NOTIFICATION);
        getActivity().registerReceiver(receiver, filter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeAllViewsInLayout();
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            getActivity().unregisterReceiver(receiver);
        }
    }

    public void initView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_schedule,null);

        tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText("日程");
        iv_message = view.findViewById(R.id.iv_message);
        iv_search = view.findViewById(R.id.iv_search);
        tv_unread_count = view.findViewById(R.id.tv_unread_count);
        iv_back = view.findViewById(R.id.iv_back);
        iv_back.setVisibility(View.GONE);
        tv_current_day = view.findViewById(R.id.tv_current_day);
        tv_week = view.findViewById(R.id.tv_week);
        calendarLayout = view.findViewById(R.id.calendarLayout);
        calendarView = view.findViewById(R.id.calendarView);
        //lv_case = view.findViewById(R.id.lv_case);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        lv_start = view.findViewById(R.id.lv_start);
        lv_be_complete = view.findViewById(R.id.lv_be_complete);
        lv_not_start = view.findViewById(R.id.lv_not_start);
        ll_start = view.findViewById(R.id.ll_start);
        ll_be_complete = view.findViewById(R.id.ll_be_complete);
        ll_not_start = view.findViewById(R.id.ll_not_start);
        ll_day = view.findViewById(R.id.ll_day);
        banner = view.findViewById(R.id.banner);
        iv_arrow = view.findViewById(R.id.iv_arrow);
        layout_nothing = view.findViewById(R.id.layout_nothing);

        //iv_arrow.setActivated(false);

        lv_start.setExpanded(true);
        lv_be_complete.setExpanded(true);
        lv_not_start.setExpanded(true);

        calendarView.setOnYearChangeListener(this);
        calendarView.setOnMonthChangeListener(this);
        calendarView.setOnCalendarSelectListener(this);
        calendarView.setOnViewChangeListener(this);

        refreshLayout.setExpand(false);
        refreshLayout.setEnableLoadMore(false);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                allList1.clear();
                allList2.clear();
                allList3.clear();
                taskList.clear();
                isChange = false;
                getData1();
                refreshLayout.finishRefresh();
            }
        });

        lv_start.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CaseDetailsActivity.class);
                intent.putExtra("jobid",dayList1.get(position).get_id());
                intent.putExtra("processid",dayList1.get(position).getProcessid());
                intent.putExtra("status",dayList1.get(position).getStatus());
                intent.putExtra("nodename",dayList1.get(position).getNodename());
                getActivity().startActivity(intent);
            }
        });

        lv_be_complete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CaseDetailsActivity.class);
                intent.putExtra("jobid",dayList2.get(position).get_id());
                intent.putExtra("processid",dayList2.get(position).getProcessid());
                intent.putExtra("status",dayList2.get(position).getStatus());
                intent.putExtra("nodename",dayList2.get(position).getNodename());
                getActivity().startActivity(intent);
            }
        });

        lv_not_start.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CaseDetailsActivity.class);
                intent.putExtra("jobid",dayList3.get(position).get_id());
                intent.putExtra("processid",dayList3.get(position).getProcessid());
                intent.putExtra("status",dayList3.get(position).getStatus());
                intent.putExtra("nodename",dayList3.get(position).getNodename());
                getActivity().startActivity(intent);
            }
        });


        tv_current_day.setText(calendarView.getCurYear() + "年" + calendarView.getCurMonth() + "月");

        Calendar calendar = new Calendar();
        calendar.setYear(calendarView.getCurYear());
        calendar.setMonth(calendarView.getCurMonth());
        calendar.setDay(calendarView.getCurDay());
        queryMonth = DateUtils.getDate2String(calendar.getTimeInMillis(),"yyyy-MM");
        Log.e("month",queryMonth);

        selectDate = DateUtils.getDate2String(calendar.getTimeInMillis(),"yyyy-MM-dd");

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合

        images.add(R.drawable.test);
        images.add(R.drawable.test);
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        //banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        getData1();


    }

    public void initListener() {
        iv_message.setOnClickListener(this);
        iv_search.setOnClickListener(this);
        ll_day.setOnClickListener(this);
        iv_arrow.setOnClickListener(this);
    }

    //获取标题通知栏收到的申请的数量
    public void getApplyCount() {
        String json = "filter[0][status]=unreplied&filter[1][to_user_id]="+user_id;
        json = CommonUtils.getParameter(json);


        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(getActivity());
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_apply/get_apply_count?"+json)
                .get()
                //.addHeader("authorization",CommonUtils.getToken(this))
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result =response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("count",result);
                        JSONObject jsonObject = JSON.parseObject(result);
                        if (jsonObject.getString("error") == null && jsonObject.getInteger("result") > 0) {
                            tv_unread_count.setVisibility(View.VISIBLE);
                            tv_unread_count.setText(jsonObject.getInteger("result") + "");
                        } else {
                            tv_unread_count.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }

    //获取标题栏通知提醒的数量
    public void getNotificationCount() {
        String json = "filter[0][status]=unread";
        json = CommonUtils.getParameter(json);


        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(getActivity());
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_notice/get_notic_count?"+json)
                .get()
                //.addHeader("authorization",CommonUtils.getToken(this))
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result =response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject jsonObject = JSON.parseObject(result);
                        if (jsonObject.getString("error") == null && jsonObject.getInteger("result") > 0) {
                            tv_unread_count.setVisibility(View.VISIBLE);
                            tv_unread_count.setText(jsonObject.getInteger("result") + "");
                        } else {
                            tv_unread_count.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }

    public void initScheme() {
        Map<String,Calendar> schemes = new HashMap<>();
        Log.e("tasklist",taskList.size() + "");
        for (int i = 0; i < taskList.size();i++) {
            java.util.Calendar cd = java.util.Calendar.getInstance();
            cd.setTime(new Date(DateUtils.getString2Date(taskList.get(i),"yyyy-MM-dd")));
            int year  = cd.get(java.util.Calendar.YEAR); //获取年份
            int month = cd.get(java.util.Calendar.MONTH)+1; //获取月份
            int day   = cd.get(java.util.Calendar.DAY_OF_MONTH); //获取日期

            Calendar calendar = new Calendar();
            calendar.setYear(year);
            calendar.setMonth(month);
            calendar.setDay(day);
            calendar.setScheme("");
            calendar.setSchemeColor(0);

            schemes.put(calendar.toString(),calendar);
        }
        calendarView.setSchemeDate(schemes);
    }


    @Override
    public void onYearChange(int year) {

    }

    @Override
    public void onMonthChange(int year, int month) {
        if (isTimeSelect) {
            Calendar calendar = new Calendar();
            calendar.setYear(year);
            calendar.setMonth(month+1);
            queryMonth = DateUtils.getDate2String(calendar.getTimeInMillis(),"yyyy-MM");
            tv_current_day.setText(year + "年" + month + "月");

            isChange = true;
            taskList.clear();
            //dayList.clear();
            //allList.clear();
            allList1.clear();
            allList2.clear();
            allList3.clear();
            getData1();
        }
    }


    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        isTimeSelect = true;
        isChange  = false;
        taskList.clear();
        //dayList.clear();
        //allList.clear();
        allList1.clear();
        allList2.clear();
        allList3.clear();
        selectDate = DateUtils.getDate2String(calendar.getTimeInMillis(),"yyyy-MM-dd");
        getData1();

//        dayList.clear();
//        Log.e("daylist2",selectDate);
//        if (allList != null && allList.size() > 0) {
//            for (int i = 0;i < allList.size();i++) {
//                if (allList.get(i).getDateDay().equals(selectDate)) {
//                    dayList.add(allList.get(i));
//                }
//            }
//        }
//
//        if (adapter == null) {
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//            linearLayoutManager.setSmoothScrollbarEnabled(true);
//            lv_case.setLayoutManager(linearLayoutManager);
//            lv_case.setHasFixedSize(true);
//            lv_case.setNestedScrollingEnabled(false);
//            adapter = new ScheduleCaseAdapter(getActivity(),dayList);
//            lv_case.setAdapter(adapter);
//
//        } else {
//            adapter.notifyDataSetChanged();
//        }

//        dayList1.clear();
//        dayList2.clear();
//        dayList3.clear();
//
//        if (allList1 != null && allList1.size() > 0) {
//            for (int i = 0;i < allList1.size();i++) {
//                if (allList1.get(i).getDateDay().equals(selectDate)) {
//                    dayList1.add(allList1.get(i));
//                }
//            }
//            if (dayList1 != null && dayList1.size() > 0) {
//                ll_start.setVisibility(View.VISIBLE);
//                startAdaper = new ScheduleTaskAdapter(getActivity(),allList1);
//                lv_start.setAdapter(startAdaper);
//            } else {
//                ll_start.setVisibility(View.GONE);
//            }
//        } else {
//            ll_start.setVisibility(View.GONE);
//        }
//
//        if (allList2 != null && allList2.size() > 0) {
//            for (int i = 0;i < allList2.size();i++) {
//                if (allList2.get(i).getDateDay().equals(selectDate)) {
//                    dayList2.add(allList2.get(i));
//                }
//            }
//            if (dayList2 != null && dayList2.size() > 0) {
//                ll_be_complete.setVisibility(View.VISIBLE);
//                beCompleteAdaper = new ScheduleTaskAdapter(getActivity(),allList2);
//                lv_be_complete.setAdapter(beCompleteAdaper);
//            } else {
//                ll_be_complete.setVisibility(View.GONE);
//            }
//        } else {
//            ll_be_complete.setVisibility(View.GONE);
//        }
//
//        if (allList3 != null && allList3.size() > 0) {
//            for (int i = 0;i < allList3.size();i++) {
//                if (allList3.get(i).getDateDay().equals(selectDate)) {
//                    dayList3.add(allList3.get(i));
//                }
//            }
//            if (dayList3 != null && dayList3.size() > 0) {
//                ll_not_start.setVisibility(View.VISIBLE);
//                notStartAdaper = new ScheduleTaskAdapter(getActivity(),allList3);
//                lv_not_start.setAdapter(notStartAdaper);
//            } else {
//                ll_not_start.setVisibility(View.GONE);
//            }
//        } else {
//            ll_not_start.setVisibility(View.GONE);
//        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_message:
                //setNotificationRead();
                Intent intent = new Intent(getActivity(), MainNewsActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_search:
                Intent intent1 = new Intent(getActivity(), MainSearchActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_day:
                initPopupWindow();
                break;
//            case R.id.ll_arrow:
//                if (calendarLayout.isExpand()) {
//                    calendarLayout.shrink();
//                    iv_arrow.setActivated(false);
//                } else {
//                    calendarLayout.expand();
//                    iv_arrow.setActivated(true);
//                }
//                break;
        }
    }

    //获取某月已开始任务数据， 按照开始日期分组
    public void getData1() {
        String json = "queryDateM="+queryMonth+"&groupDateType=actual_start&jobStatus=running";
        json = CommonUtils.getParameter(json);

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_job/get_my_job_schedule_m?"+json)
                .get()
                .addHeader("authorization",CommonUtils.getToken(getActivity()))
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        T("请求失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result =response.body().string();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("schedule",result);
                            try {
                                list1.clear();
                                ScheduleCase scheduleCase = JSON.parseObject(result,ScheduleCase.class);
                                if (scheduleCase.getResult() != null && scheduleCase.getResult().size() > 0) {
                                    for (int i = 0;i < scheduleCase.getResult().size();i++) {
                                        taskList.add(scheduleCase.getResult().get(i).getDate());
                                        list1 = scheduleCase.getResult().get(i).getJobList();
                                        if (list1 != null && list1.size() > 0) {
                                            allList1.addAll(list1);
                                            Log.e("allList11",allList1.size()+ "");
                                        }
                                    }

                                }
                                getData2();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }

            }
        });

    }

    //获取某月已开始任务数据， 按照应完成日期分组
    public void getData2() {
        String json = "queryDateM="+queryMonth+"&groupDateType=should_stop&jobStatus=running";
        json = CommonUtils.getParameter(json);


        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_job/get_my_job_schedule_m?"+json)
                .get()
                .addHeader("authorization",CommonUtils.getToken(getActivity()))
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        T("请求失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result =response.body().string();
                try {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("schedule1",result);
                            try {
                                list2.clear();
                                ScheduleCase scheduleCase = JSON.parseObject(result,ScheduleCase.class);
                                if (scheduleCase.getResult() != null && scheduleCase.getResult().size() > 0) {
                                    for (int i = 0;i < scheduleCase.getResult().size();i++) {

                                        if (!taskList.contains(scheduleCase.getResult().get(i).getDate())) {
                                            taskList.add(scheduleCase.getResult().get(i).getDate());
                                        }

                                        list2 = scheduleCase.getResult().get(i).getJobList();
                                        if (list2 != null && list2.size() > 0) {
//                                        for (int j = 0;j < list2.size();j++) {
//                                            boolean isExit = true;
//                                            for (int k = 0;k < allList.size();k++) {
//                                                if (list2.get(j).get_id().equals(allList.get(k).get_id())) {
//                                                    isExit = true;
//                                                    break;
//                                                } else {
//                                                    isExit = false;
//                                                }
//                                            }
//                                            if (!isExit) {
//                                                allList.add(list2.get(j));
//                                            }
//                                        }
                                            allList2.addAll(list2);
                                        }

                                    }
                                }
                                getData3();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    //获取某月还未始任务数据， 按照创建日期分组，
    public void getData3() {
        String json = "queryDateM="+queryMonth+"&groupDateType=creat_timestamp&jobStatus=waiting";
        json = CommonUtils.getParameter(json);

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_job/get_my_job_schedule_m?"+json)
                .get()
                .addHeader("authorization",CommonUtils.getToken(getActivity()))
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        T("请求失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result =response.body().string();
                try {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("schedule2",result);
                            try {
                                list3.clear();
                                ScheduleCase scheduleCase = JSON.parseObject(result,ScheduleCase.class);
                                if (scheduleCase.getResult() != null && scheduleCase.getResult().size() > 0) {
                                    for (int i = 0;i < scheduleCase.getResult().size();i++) {

                                        if (!taskList.contains(scheduleCase.getResult().get(i).getDate())) {
                                            taskList.add(scheduleCase.getResult().get(i).getDate());
                                        }
//                                        if (!scheduleCase.getResult().get(i).getDate().equals(taskList.get(j))) {
//                                            taskList.add(scheduleCase.getResult().get(i).getDate());
//                                        }

                                        list3 = scheduleCase.getResult().get(i).getJobList();
                                        if (list3 != null && list3.size() > 0) {
//                                        for (int j = 0;j < list3.size();j++) {
//                                            boolean isExit = true;
//                                            for (int k = 0;k < allList.size();k++) {
//                                                if (list3.get(j).get_id().equals(allList.get(k).get_id())) {
//                                                    isExit = true;
//                                                    break;
//                                                } else {
//                                                    isExit = false;
//                                                }
//                                            }
//                                            if (!isExit) {
//                                                allList.add(list3.get(j));
//                                            }
//                                        }
                                            allList3.addAll(list3);
                                        }
                                    }
                                }


                                initScheme();

                                if (!isChange) {
                                    dayList1.clear();
                                    dayList2.clear();
                                    dayList3.clear();
//                                if (allList != null && allList.size() > 0) {
//                                    Log.e("daylist3",allList.size() + "");
//                                    for (int i = 0;i < allList.size();i++) {
//                                        if (allList.get(i).getDateDay().equals(selectDate)) {
//                                            dayList.add(allList.get(i));
//                                        }
//                                    }
//
//                                    if (dayList != null && dayList.size() > 0) {
//                                        Log.e("daylist",dayList.size() + "");
//                                        if (adapter == null) {
//                                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//                                            linearLayoutManager.setSmoothScrollbarEnabled(true);
//                                            lv_case.setLayoutManager(linearLayoutManager);
//                                            lv_case.setHasFixedSize(true);
//                                            lv_case.setNestedScrollingEnabled(false);
//                                            adapter = new ScheduleCaseAdapter(getActivity(),dayList);
//                                            lv_case.setAdapter(adapter);
//                                        } else {
//                                            adapter.notifyDataSetChanged();
//                                        }
//                                    }
//                                }

                                    if (allList1 != null && allList1.size() > 0) {
                                        for (int i = 0;i < allList1.size();i++) {
                                            if (allList1.get(i).getDateDay().equals(selectDate)) {
                                                dayList1.add(allList1.get(i));
                                            }
                                        }
                                        if (dayList1 != null && dayList1.size() > 0) {
                                            ll_start.setVisibility(View.VISIBLE);
                                            if (startAdaper == null) {
                                                startAdaper = new ScheduleTaskAdapter(getActivity(),dayList1);
                                                lv_start.setAdapter(startAdaper);
                                            } else {
                                                startAdaper.notifyDataSetChanged();
                                            }
                                        } else {
                                            ll_start.setVisibility(View.GONE);
                                        }
                                    } else {
                                        ll_start.setVisibility(View.GONE);
                                    }

                                    if (allList2 != null && allList2.size() > 0) {
                                        for (int i = 0;i < allList2.size();i++) {
                                            if (allList2.get(i).getDateDay().equals(selectDate)) {
                                                dayList2.add(allList2.get(i));
                                            }
                                        }
                                        if (dayList2 != null && dayList2.size() > 0) {
                                            ll_be_complete.setVisibility(View.VISIBLE);
                                            if (beCompleteAdaper == null) {
                                                beCompleteAdaper = new ScheduleTaskAdapter(getActivity(),dayList2);
                                                lv_be_complete.setAdapter(beCompleteAdaper);
                                            } else {
                                                beCompleteAdaper.notifyDataSetChanged();
                                            }
                                        } else {
                                            ll_be_complete.setVisibility(View.GONE);
                                        }
                                    } else {
                                        ll_be_complete.setVisibility(View.GONE);
                                    }

                                    if (allList3 != null && allList3.size() > 0) {
                                        for (int i = 0;i < allList3.size();i++) {
                                            if (allList3.get(i).getDateDay().equals(selectDate)) {
                                                dayList3.add(allList3.get(i));
                                            }
                                        }
                                        if (dayList3 != null && dayList3.size() > 0) {
                                            ll_not_start.setVisibility(View.VISIBLE);
                                            if (notStartAdaper == null) {
                                                notStartAdaper = new ScheduleTaskAdapter(getActivity(),dayList3);
                                                lv_not_start.setAdapter(notStartAdaper);
                                            } else {
                                                notStartAdaper.notifyDataSetChanged();
                                            }

                                        } else {
                                            ll_not_start.setVisibility(View.GONE);
                                        }
                                    } else {
                                        ll_not_start.setVisibility(View.GONE);
                                    }

                                    if ((dayList1 != null && dayList1.size() > 0) || (dayList2 != null && dayList2.size() > 0)
                                    || (dayList3 != null && dayList3.size() > 0)){
                                        layout_nothing.setVisibility(View.GONE);
                                    } else {
                                        layout_nothing.setVisibility(View.VISIBLE);
                                    }
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    //设置通知消息为已读
    public void setNotificationRead() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("status","unread");
        jsonArray.add(jsonObject1);

        jsonObject.put("filter",jsonArray);
        jsonObject.put("status","read");

        MediaType json = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(getActivity());
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_notice/set_all_notic_status")
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        T("请求失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("setRead",result);
                    }
                });
            }
        });

    }

    private String getTime(Date date,String formats) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat(formats);
        return format.format(date);
    }


    public void initPopupWindow() {

        //时间选择器
        TimePickerView pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                isTimeSelect = false;
                queryMonth = getTime(date,"yyyy-MM");
                selectDate = getTime(date,"yyyy-MM-dd");
                java.util.Calendar cd = java.util.Calendar.getInstance();
                cd.setTime(date);
                int year  = cd.get(java.util.Calendar.YEAR); //获取年份
                int month = cd.get(java.util.Calendar.MONTH) + 1; //获取月份
                int day   = cd.get(java.util.Calendar.DAY_OF_MONTH); //获取日期

                calendarView.scrollToCalendar(year,month,day);

                tv_current_day.setText(year + "年" + month + "月");


//                isChange = false;
//                taskList.clear();
//                allList1.clear();
//                allList2.clear();
//                allList3.clear();
//                getData1();

            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setTitleText("选择日期")
                .setCancelColor(getResources().getColor(R.color.green_item))
                .setSubmitColor(getResources().getColor(R.color.green_item))
                .isCyclic(true)
                .build();
        pvTime.show();

    }

    @Override
    public void OnBannerClick(int position) {

    }

    @Override
    public void onViewChange(boolean isMonthView) {
        if (isMonthView) {
            refreshLayout.setExpand(true);
            iv_arrow.setActivated(true);
        } else {
            refreshLayout.setExpand(false);
            iv_arrow.setActivated(false);
        }
    }

    public class GlideImageLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            GlideApp.with(context)
                    .load(path)
                    .placeholder(R.drawable.palceholder)
                    .error(R.drawable.palceholder)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .centerInside()
                    .into(imageView);
        }
    }
}
