package com.jsyrdb.yiren.fragment.communication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.cases.CommunicationDetailsActivity;
import com.jsyrdb.yiren.adapter.StaffCommunicationAdapter;
import com.jsyrdb.yiren.adapter.StaffSortAdapter;
import com.jsyrdb.yiren.base.BaseFragment;
import com.jsyrdb.yiren.model.LoginResult;
import com.jsyrdb.yiren.model.PeopleSetup;
import com.jsyrdb.yiren.model.StaffCommunication;
import com.jsyrdb.yiren.utils.CasePinyinComparator;
import com.jsyrdb.yiren.utils.CharacterParser;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.jsyrdb.yiren.utils.GlobalParam;
import com.jsyrdb.yiren.utils.StaffPinyinComparator;
import com.jsyrdb.yiren.widget.ExpandListView;
import com.jsyrdb.yiren.widget.SideBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

public class StaffCommunicationFragment extends BaseFragment {

    private View view;
    private View headView;
    private ExpandListView lv_staff_communication;
    private List<StaffCommunication.ResultBean> list = new ArrayList<>();
    private StaffCommunicationAdapter adapter;
    private SmartRefreshLayout refreshLayout;

    private FrameLayout layout_sort;
    private ListView lv_all_staff;//所有的案件列表
    private SideBar sideBar;//右侧索引栏
    private TextView dialog;//点击右侧的索引栏显示的dialog
    private StaffSortAdapter sortAdapter;
    private List<PeopleSetup.ResultBean> sortList = new ArrayList<>();

    private CharacterParser characterParser;
    private StaffPinyinComparator pinyinComparator;

    private LinearLayout layout_nothing;//数据为空时显示的view

    private String user_id = "";


    public BroadcastReceiver loginReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(GlobalParam.ACTION_MESSAGE)) {
                getData();
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
        getData();
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
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_staff_communication,null);
        headView = LayoutInflater.from(getActivity()).inflate(R.layout.staff_header,null);
        layout_sort = view.findViewById(R.id.layout_sort);
        lv_all_staff = view.findViewById(R.id.lv_all_staff);
        sideBar = view.findViewById(R.id.sidebar);
        dialog = view.findViewById(R.id.dialog);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        layout_nothing = view.findViewById(R.id.layout_nothing);

        lv_staff_communication = headView.findViewById(R.id.lv_staff_communication);
        lv_staff_communication.setExpanded(true);

        sideBar.setTextView(dialog);

        lv_all_staff.addHeaderView(headView);

        characterParser = new CharacterParser();
        pinyinComparator = new StaffPinyinComparator();

        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //根据侧边栏上的文字做改变
                if (s.equals("")) {
                    lv_all_staff.setSelection(0);
                }
                int position = sortAdapter.getPositionForSection(s.charAt(0));

                if (position != -1) {
                    lv_all_staff.setSelection(position);
                }
            }

            @Override
            public void setRefreshLayoutEnable(boolean isRefresh) {
                refreshLayout.setEnableRefresh(isRefresh);
            }
        });

        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
                refreshLayout.finishRefresh();
            }
        });

        lv_all_staff.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CommunicationDetailsActivity.class);
                intent.putExtra("group_id",sortList.get(position-1).get_id());
                intent.putExtra("group_name",sortList.get(position-1).getName());
                intent.putExtra("type",1);
                startActivity(intent);
            }
        });

        lv_staff_communication.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setMessageRead(list.get(position).get_id());
                Intent intent1 = new Intent(GlobalParam.READ_MESSAGE);
                getActivity().sendBroadcast(intent1);
                Intent intent = new Intent(getActivity(), CommunicationDetailsActivity.class);
                intent.putExtra("group_id",list.get(position).get_id());
                intent.putExtra("group_name",list.get(position).getGroup_name());
                intent.putExtra("type",1);
                startActivityForResult(intent,1);
            }
        });

        IntentFilter filter = new IntentFilter();
        filter.addAction(GlobalParam.ACTION_MESSAGE);
        getActivity().registerReceiver(loginReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (loginReceiver != null) {
            getActivity().unregisterReceiver(loginReceiver);
        }
    }

    public void getAllStaff() {
        String param = "page=1&limit=0";
        param = CommonUtils.getParameter(param);
        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(getActivity());
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_user/get_user_list?"+param)
                .get()
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
                        try {
                            sortList.clear();
                            PeopleSetup peopleSetup = JSON.parseObject(result,PeopleSetup.class);
                            sortList = peopleSetup.getResult();
                            if (sortList != null && sortList.size() > 0) {
                                layout_sort.setVisibility(View.VISIBLE);
                                layout_nothing.setVisibility(View.GONE);
                                for (int i = 0;i < sortList.size();i++) {
                                    if (sortList.get(i).get_id().equals(user_id)) {
                                        sortList.remove(i);
                                    }
                                }
                                if (list != null && list.size() > 0) {
                                    for (int i = 0;i < list.size();i++) {
                                        for (int j = 0;j < sortList.size();j++) {
                                            if (list.get(i).get_id().equals(sortList.get(j).get_id())) {
                                                sortList.remove(j);
                                            }
                                        }
                                    }
                                }

                                for (int i = 0;i < sortList.size();i++) {
                                    String pinyin = characterParser.getSelling(sortList.get(i).getName());
                                    String sortString = pinyin.substring(0, 1).toUpperCase();
                                    if (sortString.matches("[A-Z]")) {
                                        sortList.get(i).setSortLetters(sortString.toUpperCase());
                                    } else {
                                        sortList.get(i).setSortLetters("#");
                                    }
                                }
                                Collections.sort(sortList, pinyinComparator);
                                sortAdapter = new StaffSortAdapter(getActivity(),sortList);
                                lv_all_staff.setAdapter(sortAdapter);
                            } else {
                                layout_sort.setVisibility(View.GONE);
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

    public void getData() {
        String json = "group_type=user";
        json = CommonUtils.getParameter(json);

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(getActivity());
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_message/get_message_group?"+json)
                .get()
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("staffgroup",result);
                        list.clear();
                        StaffCommunication staffCommunication = JSON.parseObject(result,StaffCommunication.class);
                        list = staffCommunication.getResult();
                        if (list != null && list.size() > 0) {
                            lv_staff_communication.setVisibility(View.VISIBLE);
                            adapter = new StaffCommunicationAdapter(getActivity(),list);
                            lv_staff_communication.setAdapter(adapter);
                        } else {
                            lv_staff_communication.setVisibility(View.GONE);
                        }

                        getAllStaff();
                    }
                });
            }
        });
    }

    //设置消息为已读
    public void setMessageRead(String group_id) {
        String json = "filter[0][group_id]="+group_id;
        json = CommonUtils.getParameter(json);

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(getActivity());
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_message/set_all_message_read?"+json)
                .get()
                //.addHeader("authorization",CommonUtils.getToken(this))
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("setread",result);
                        JSONObject jsonObject = JSON.parseObject(result);
                        if (jsonObject.getString("error") == null) {

                        }
                    }
                });
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            getData();
        }
    }
}
