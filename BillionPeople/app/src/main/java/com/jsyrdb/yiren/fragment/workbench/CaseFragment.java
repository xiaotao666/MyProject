package com.jsyrdb.yiren.fragment.workbench;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.adapter.CaseAdapter;
import com.jsyrdb.yiren.base.BaseFragment;
import com.jsyrdb.yiren.fragment.main.WorkbenchFragment;
import com.jsyrdb.yiren.model.Case;
import com.jsyrdb.yiren.model.Labels;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonPopupWindow;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CaseFragment extends BaseFragment implements View.OnClickListener{//案件

    private View view;
    private ListView lv_case;
    private List<Case.ResultBean> list = new ArrayList<>();
    private CaseAdapter adapter;

    private LinearLayout ll_sort;

    private TextView tv_time_sort;

    private LinearLayout layout_sort;
    private LinearLayout layout_screen;
    private TextView tv_sort,tv_screen;
    private ImageView img_sort,img_screen;

    private int sortFlag = 0;//默认排序标记
    private int screenFlag = 0;//筛选标记

    //排序
    private CommonPopupWindow sortPopupWindow;
    private RelativeLayout rl_name,rl_time;
    private ImageView iv_name,iv_time;
    private TextView tv_time,tv_name;
    //筛选
    private CommonPopupWindow screenPopupWindow;

    private SmartRefreshLayout refreshLayout;

    private String sort = "";

    private String TAG = "CaseFragment";

    private int page = 1;

    private Labels.ResultBean resultBean;
    private String key;

    private int timeSort = -1;//时间排序(-1正序 1倒序)
    private boolean isTimeSort = false;

    private LinearLayout layout_nothing;//数据为空时显示的view

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resultBean = (Labels.ResultBean) getArguments().getSerializable("label");
        key = resultBean.getKey();
        Log.e(TAG,resultBean.getKey());
        initView();
        getData(timeSort);
        initListener();
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

    public void initView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_case,null);
        lv_case = view.findViewById(R.id.lv_case);
        ll_sort = view.findViewById(R.id.ll_sort);
        tv_time_sort = view.findViewById(R.id.tv_time_sort);
        layout_sort = view.findViewById(R.id.layout_sort);
        layout_screen = view.findViewById(R.id.layout_screen);
        tv_sort = view.findViewById(R.id.tv_sort);
        tv_screen = view.findViewById(R.id.tv_screen);
        img_sort = view.findViewById(R.id.img_sort);
        img_screen = view.findViewById(R.id.img_screen);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        layout_nothing = view.findViewById(R.id.layout_nothing);

        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData(timeSort);
                //refreshLayout.finishRefresh();
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getMoreData(timeSort);
            }
        });
    }

    public void getData(int timeSort) {
        page = 1;
        String json = "filter[0][status]=running&filter[1][current_process_type]="+key+"&page=1&limit=10&sort[0][]=creat_timestamp&sort[0][]="+timeSort;
        json = CommonUtils.getParameter(json);

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(getActivity());
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_case/get_case_list?"+json)
                .get()
                //.addHeader("authorization",CommonUtils.getToken(getActivity()))
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        T("请求失败");
                        refreshLayout.finishRefresh(false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result =response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Log.e(TAG,result);
                            refreshLayout.finishRefresh();
                            refreshLayout.setNoMoreData(false);//恢复有数据的原始状态,保证能够再次下拉
                            list.clear();
                            Case cases = JSON.parseObject(result,Case.class);
                            list = cases.getResult();
                            if (list != null && list.size() > 0) {
                                lv_case.setVisibility(View.VISIBLE);
                                layout_nothing.setVisibility(View.GONE);
                                adapter = new CaseAdapter(getActivity(),list);
                                lv_case.setAdapter(adapter);
                            } else {
                                lv_case.setVisibility(View.GONE);
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

    public void getMoreData(int timeSort) {
        page = page + 1;
        String json = "filter[0][status]=running&filter[1][current_process_type]="+key+"&page="+page+"&limit=10&sort[0][]=creat_timestamp&sort[0][]="+timeSort;
        json = CommonUtils.getParameter(json);

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(getActivity());
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_case/get_case_list?"+json)
                .get()
                //.addHeader("authorization",CommonUtils.getToken(getActivity()))
                .build();
        Log.e(TAG,CommonUtils.getToken(getActivity()));
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        T("请求失败");
                        refreshLayout.finishLoadMore(false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result =response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Log.e(TAG,result);
                            Case cases = JSON.parseObject(result,Case.class);
                            List<Case.ResultBean> moreList = cases.getResult();
                            if (moreList != null && moreList.size() > 0) {
                                list.addAll(moreList);
                                adapter.notifyDataSetChanged();
                                refreshLayout.finishLoadMore();
                            } else {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });


            }
        });
    }

    public void initListener() {
        layout_sort.setOnClickListener(this);
        layout_screen.setOnClickListener(this);
        tv_time_sort.setOnClickListener(this);
    }

    public void setSortView() {
        if (sortFlag == 0) {
            tv_sort.setActivated(true);
            img_sort.setActivated(true);
            sortFlag = 1;
        } else if (sortFlag == 1) {
            tv_sort.setActivated(false);
            img_sort.setActivated(false);
            sortFlag = 0;
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_sort:
               setSortView();

                sortPopupWindow = new CommonPopupWindow.Builder(getActivity())
                        .setView(R.layout.sort_pop)
                        .setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                        .setBackGroundLevel(0.5f)
                        //设置PopupWindow里的子View及点击事件
                        .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                            @Override
                            public void getChildView(View view, int layoutResId) {
                                rl_name = view.findViewById(R.id.rl_name);
                                rl_time = view.findViewById(R.id.rl_time);
                                iv_name = view.findViewById(R.id.iv_name);
                                iv_time = view.findViewById(R.id.iv_time);
                                tv_time = view.findViewById(R.id.tv_time);
                                tv_name = view.findViewById(R.id.tv_name);

                                if (tv_sort.getText().toString().equals("名称排序")) {
                                    tv_time.setTextColor(getResources().getColor(R.color.text_color));
                                    iv_time.setVisibility(View.GONE);
                                    tv_name.setTextColor(getResources().getColor(R.color.green_item));
                                    iv_name.setVisibility(View.VISIBLE);
                                } else if (tv_sort.getText().toString().equals("时间排序")) {
                                    tv_time.setTextColor(getResources().getColor(R.color.green_item));
                                    iv_time.setVisibility(View.VISIBLE);
                                    tv_name.setTextColor(getResources().getColor(R.color.text_color));
                                    iv_name.setVisibility(View.GONE);
                                }

                                rl_name.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        tv_sort.setText("名称排序");
                                        if (sortPopupWindow != null) {
                                            sortPopupWindow.dismiss();
                                            sortPopupWindow = null;
                                        }
                                    }
                                });

                                rl_time.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        tv_sort.setText("时间排序");
                                        if (sortPopupWindow != null) {
                                            sortPopupWindow.dismiss();
                                            sortPopupWindow = null;
                                        }
                                    }
                                });
                            }
                        })
                        .setOutsideTouchable(true)
                        .create();
                sortPopupWindow.showAsDropDown(ll_sort,0,0);

                sortPopupWindow.setChangeStyleListener(new CommonPopupWindow.OnChangeStyleListener() {
                    @Override
                    public void change() {
                       setSortView();
                    }
                });
                break;
            case R.id.layout_screen:
                if (screenFlag == 0) {
                    tv_screen.setActivated(true);
                    img_screen.setActivated(true);
                    screenFlag = 1;
                } else if (screenFlag == 1) {
                    tv_screen.setActivated(false);
                    img_screen.setActivated(false);
                    screenFlag = 0;
                }
                break;
            case R.id.tv_time_sort:
                if (isTimeSort) {
                    isTimeSort = false;
                    tv_time_sort.setText("时间正序");
                    tv_time_sort.setTextColor(getResources().getColor(R.color.text_color));
                    timeSort = -1;
                    //page = 1;
                    getData(timeSort);
                } else {
                    isTimeSort = true;
                    tv_time_sort.setText("时间倒序");
                    tv_time_sort.setTextColor(getResources().getColor(R.color.green_item));
                    timeSort = 1;
                    //page = 1;
                    getData(timeSort);
                }
                break;
        }
    }
}
