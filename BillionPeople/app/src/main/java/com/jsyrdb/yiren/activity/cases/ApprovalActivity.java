package com.jsyrdb.yiren.activity.cases;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.utils.StatusBarUtils;

public class ApprovalActivity extends Activity implements View.OnClickListener {

    //标题栏
    private TextView tv_title,tv_commit;
    private LinearLayout ll_back;

    private RelativeLayout rl_agree,rl_refuse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_approval);

        StatusBarUtils.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarUtils.setTranslucentStatus(this);

        StatusBarUtils.setStatusBarColor(this, Color.parseColor("#32B16C"));
        if (!StatusBarUtils.setStatusBarDarkTheme(this, false)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtils.setStatusBarColor(this, 0x55000000);
        }

        initView();
    }

    public void initView() {
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("批复退案");
        tv_commit = findViewById(R.id.tv_commit);
        tv_commit.setVisibility(View.GONE);
        ll_back = findViewById(R.id.ll_back);
        rl_agree = findViewById(R.id.rl_agree);
        rl_refuse = findViewById(R.id.rl_refuse);
        ll_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }
}
