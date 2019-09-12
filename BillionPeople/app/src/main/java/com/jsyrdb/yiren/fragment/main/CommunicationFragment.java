package com.jsyrdb.yiren.fragment.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.main.MainNewsActivity;
import com.jsyrdb.yiren.activity.main.MainSearchActivity;
import com.jsyrdb.yiren.base.BaseFragment;
import com.jsyrdb.yiren.fragment.communication.CaseCommunicationFragment;
import com.jsyrdb.yiren.fragment.communication.StaffCommunicationFragment;
import com.jsyrdb.yiren.model.LoginResult;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.jsyrdb.yiren.utils.GlobalParam;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CommunicationFragment extends BaseFragment implements View.OnClickListener {

    //标题栏
    private TextView tv_title,tv_unread_count;
    private ImageView iv_message,iv_search,iv_back;

    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> titleList;
    private List<Fragment> fragmentList;
    private FragmentPagerAdapter adapter;

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
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_communication,null);


        tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText("交流");
        iv_message = view.findViewById(R.id.iv_message);
        iv_search = view.findViewById(R.id.iv_search);
        tv_unread_count = view.findViewById(R.id.tv_unread_count);
        iv_back = view.findViewById(R.id.iv_back);
        iv_back.setVisibility(View.GONE);
        iv_message.setOnClickListener(this);
        iv_search.setOnClickListener(this);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        reflexFixed(tabLayout);

        titleList = new ArrayList<>();
        titleList.add("项目");
        titleList.add("员工");


        fragmentList = new ArrayList<>();
        fragmentList.add(new CaseCommunicationFragment());
        fragmentList.add(new StaffCommunicationFragment());

        adapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }
        };

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
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

    public static void reflexFixed(final TabLayout tabLayout){
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);


                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);
                        TextView mTextView = (TextView) mTextViewField.get(tabView);
                        tabView.setPadding(0, 0, 0, 0);
                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }
                        int margin = (tabView.getWidth()-width)/2;
                        int dp10 = px2dip(tabLayout.getContext(), margin);
                        Log.e("fhxxMargin","text"+ width + "  tab " +tabView.getWidth() + " get" + margin + " 间距"+ dp10);
                        //设置tab左右间距  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width ;
                        params.leftMargin = margin;
                        params.rightMargin = margin;
                        tabView.setLayoutParams(params);
                        tabView.invalidate();
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
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
        }
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
}
