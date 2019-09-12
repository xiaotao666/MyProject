package com.jsyrdb.yiren.fragment.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.main.LoginActivity;
import com.jsyrdb.yiren.activity.mine.AboutActivity;
import com.jsyrdb.yiren.activity.mine.EditDataActivity;
import com.jsyrdb.yiren.app.MyApplication;
import com.jsyrdb.yiren.base.BaseFragment;
import com.jsyrdb.yiren.model.LoginResult;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.jsyrdb.yiren.utils.DataCleanManager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyFragment extends BaseFragment implements View.OnClickListener {

    private View view;
    private RelativeLayout rl_edit,rl_clean,rl_about,rl_exit;
    private TextView tv_name,tv_position,tv_cache;

    private LoginResult.ResultBean.UserBean userBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();

        userBean = CommonUtils.getUserBean(getActivity());
        if (userBean != null) {
            tv_name.setText(userBean.getUsername());
            tv_position.setText(!TextUtils.isEmpty(userBean.getDescribe())?userBean.getDescribe():"这个人很懒什么都没有留下");
        }
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
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_mine,null);

        rl_edit = view.findViewById(R.id.rl_edit);
        rl_clean = view.findViewById(R.id.rl_clean);
        rl_about = view.findViewById(R.id.rl_about);
        rl_exit = view.findViewById(R.id.rl_exit);
        tv_name = view.findViewById(R.id.tv_name);
        tv_position = view.findViewById(R.id.tv_position);
        tv_cache = view.findViewById(R.id.tv_cache);

        try {
            String cacheSize = DataCleanManager.getTotalCacheSize(getActivity());
            tv_cache.setText(cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initListener() {
        rl_edit.setOnClickListener(this);
        rl_clean.setOnClickListener(this);
        rl_about.setOnClickListener(this);
        rl_exit.setOnClickListener(this);
    }

    public void createDialog() {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("清除缓存")
                .setMessage("确定要清除缓存吗?")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataCleanManager.clearAllCache(getActivity());
                        try {
                            String cacheSize2 = DataCleanManager.getTotalCacheSize(getActivity());
                            tv_cache.setText(cacheSize2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_edit:
                Intent intent = new Intent(getActivity(), EditDataActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_clean:
                createDialog();
                break;
            case R.id.rl_about:
                Intent intent1 = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_exit:
                CommonUtils.clearToken(getActivity());
                CommonUtils.clearUserBean(getActivity());
                Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
                break;
        }
    }
}
