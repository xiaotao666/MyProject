package com.jsyrdb.yiren.activity.cases;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.base.BaseFragmentActivity;
import com.jsyrdb.yiren.fragment.personnelset.ManageFragment;

public class PersonnelSettingActivity extends BaseFragmentActivity implements View.OnClickListener{

    private FrameLayout framelayout;
    private RadioGroup radioGroup;
    private RadioButton rb_manager;
    private RadioButton rb_first;
    private RadioButton rb_second;
    private RadioButton rb_risk_manage;

    private ManageFragment manageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnel_setting);

        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        initView();
    }

    public void initView() {
        framelayout = findViewById(R.id.framelayout);
        radioGroup = findViewById(R.id.radioGroup);
        rb_manager = findViewById(R.id.rb_manager);
        rb_first = findViewById(R.id.rb_first);
        rb_second = findViewById(R.id.rb_second);
        rb_risk_manage = findViewById(R.id.rb_risk_manage);

        rb_manager.setOnClickListener(this);
        rb_first.setOnClickListener(this);
        rb_second.setOnClickListener(this);
        rb_risk_manage.setOnClickListener(this);

        manageFragment = new ManageFragment();

        radioGroup.check(R.id.rb_manager);
        addFragment(manageFragment);
    }

    //添加fragment
    public void addFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio_communication:
                addFragment(manageFragment);
                break;
            case R.id.radio_workbench:
                addFragment(manageFragment);
                break;
            case R.id.radio_schedule:
                addFragment(manageFragment);
                break;
            case R.id.radio_mine:
                addFragment(manageFragment);
                break;
        }
    }
}
