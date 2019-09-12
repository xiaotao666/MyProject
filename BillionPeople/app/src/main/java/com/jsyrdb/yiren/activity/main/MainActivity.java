package com.jsyrdb.yiren.activity.main;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.add.CustomerSetupActivity;
import com.jsyrdb.yiren.adapter.StaffCommunicationAdapter;
import com.jsyrdb.yiren.base.BaseFragmentActivity;
import com.jsyrdb.yiren.fragment.main.CommunicationFragment;
import com.jsyrdb.yiren.fragment.main.MyFragment;
import com.jsyrdb.yiren.fragment.main.ScheduleFragment;
import com.jsyrdb.yiren.fragment.main.WorkbenchFragment;
import com.jsyrdb.yiren.glide.GlideApp;
import com.jsyrdb.yiren.model.CaseCommunication;
import com.jsyrdb.yiren.model.LoginResult;
import com.jsyrdb.yiren.model.StaffCommunication;
import com.jsyrdb.yiren.utils.AreaDarkPopupWindow;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonPopupWindow;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.jsyrdb.yiren.utils.GlobalParam;
import com.jsyrdb.yiren.utils.StatusBarUtils;
import com.jsyrdb.yiren.utils.WsManager;
import com.jsyrdb.yiren.widget.BadgeView;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener{

    private LinearLayout ll_main;
    private FrameLayout framelayout;
    private RadioGroup radioGroup;
    private RadioButton radio_communication;
    private RadioButton radio_workbench;
    private RadioButton radio_schedule;
    private RadioButton radio_mine;
    private ImageView iv_add;

    private CommunicationFragment communicationFragment;
    private WorkbenchFragment workbenchFragment;
    private ScheduleFragment scheduleFragment;
    private MyFragment myFragment;

    private List<Fragment> fragmentList;

    private FragmentManager fragmentManager;

    //点击加号弹出框
    private CommonPopupWindow addPopupWindow;
    private AreaDarkPopupWindow areaDarkPopupWindow;//暂时没用到
    private TextView tv_case,tv_loan,tv_saved,tv_archives;
    private ImageView iv_exit;

    private boolean isRotation = false;//判断加号的旋转状态

    //判断是否登录
    private LoginResult.ResultBean.UserBean loginResult;

    //按两次返回按钮回退到桌面
    private long mExitTime;

    private Socket mSocket;

    private int position = 0;

    private List<CaseCommunication.ResultBean> caseList = new ArrayList<>();//案件交流列表
    private List<StaffCommunication.ResultBean> staffList = new ArrayList<>();//员工交流列表

    private Button btn_msg;

    private  BadgeView badge1;

    private QBadgeView qBadgeView;



    public BroadcastReceiver loginReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(GlobalParam.ACTION_LOGIN_IN)) {
                initData();
            } else if (intent.getAction().equals(GlobalParam.ACTION_MESSAGE)) {
                getCaseMessage();
                //qBadgeView.bindTarget(btn_msg).setBadgeText("").setBadgeGravity(Gravity.END|Gravity.TOP).setGravityOffset(20,0,true);
                //badge1.show();
                //iv_unread.setVisibility(View.VISIBLE);
            } else if (intent.getAction().equals(GlobalParam.READ_MESSAGE)) {
                getCaseMessage();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //StatusBarUtils.setRootViewFitsSystemWindows(this, true);

        initView();
        try {
            //1.初始化socket.io，设置链接
            IO.Options options = new IO.Options();
            options.secure = false;
            options.transports =  new String[] {"websocket", "flashsocket", "htmlfile", "xhr-multipart", "xhr-polling", "jsonp-polling"};
            options.timeout = 10000;
            options.multiplex = true;
            options.reconnection = true;
            options.reconnectionDelay = 1000;
            options.reconnectionAttempts = 10;
            options.policyPort = 10843;
            options.query = "token=" + CommonUtils.getToken(this);
            mSocket = IO.socket("http://api.jsyrdb.com:84",options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //initData();
        checkMainPermission();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //WsManager.getInstance().disconnect();
        mSocket.disconnect();
        mSocket.off();

        if (loginReceiver != null) {
            unregisterReceiver(loginReceiver);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt("position", position);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        position = savedInstanceState.getInt("position");
        switchfragment(position);
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void initView() {
        framelayout = findViewById(R.id.framelayout);
        ll_main = findViewById(R.id.ll_main);
        radioGroup = findViewById(R.id.radioGroup);
        radio_communication = findViewById(R.id.radio_communication);
        radio_workbench = findViewById(R.id.radio_workbench);
        radio_schedule = findViewById(R.id.radio_schedule);
        radio_mine = findViewById(R.id.radio_mine);
        iv_add = findViewById(R.id.iv_add);
        btn_msg = findViewById(R.id.btn_msg);
        //iv_unread = findViewById(R.id.iv_unread);
        iv_add.setOnClickListener(this);

        qBadgeView = new QBadgeView(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction(GlobalParam.ACTION_LOGIN_IN);
        filter.addAction(GlobalParam.ACTION_MESSAGE);
        filter.addAction(GlobalParam.READ_MESSAGE);
        registerReceiver(loginReceiver, filter);
    }

    public void getCaseMessage() {
        String json = "group_type=case";
        json = CommonUtils.getParameter(json);

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_message/get_message_group?"+json)
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
                String result =response.body().string();
               runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        caseList.clear();
                        Log.e("casegroup",result);
                        CaseCommunication caseCommunication = JSON.parseObject(result,CaseCommunication.class);
                        caseList = caseCommunication.getResult();

                        getStaffMassage();

                    }
                });
            }
        });
    }

    public void getStaffMassage() {
        String json = "group_type=user";
        json = CommonUtils.getParameter(json);

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_message/get_message_group?"+json)
                .get()
                .addHeader("authorization",CommonUtils.getToken(this))
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
                        Log.e("staffgroup",result);
                        staffList.clear();
                        StaffCommunication staffCommunication = JSON.parseObject(result,StaffCommunication.class);
                        staffList = staffCommunication.getResult();

                        if ((caseList != null && caseList.size() > 0) || (staffList != null && staffList.size() > 0)) {
                            qBadgeView.bindTarget(btn_msg).setBadgeText("").setBadgeGravity(Gravity.END|Gravity.TOP).setGravityOffset(20,0,true);
                            //badge1.show();
                            //iv_unread.setVisibility(View.VISIBLE);
                        } else {
                            qBadgeView.hide(false);
                            //badge1.hide();
                            //iv_unread.setVisibility(View.GONE);
                        }

                    }
                });
            }
        });
    }


    public void initData() {
        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        getCaseMessage();


        switchfragment(0);
        radioGroup.check(R.id.radio_schedule);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_workbench:
                        switchfragment(1);
                        setStatusBarColor(getResources().getColor(R.color.green_item));
                        setStatusBarDarkTheme(false);
                        break;
                    case R.id.radio_schedule:
                        switchfragment(0);
                        setStatusBarColor(getResources().getColor(R.color.green_item));
                        setStatusBarDarkTheme(false);
                        break;
                    case R.id.radio_communication:
                        switchfragment(2);
                        setStatusBarColor(getResources().getColor(R.color.green_item));
                        setStatusBarDarkTheme(false);
                        break;
                    case R.id.radio_mine:
                        switchfragment(3);
                        setStatusBarColor(Color.parseColor("#00ADFA"));
                        setStatusBarDarkTheme(false);
                        break;
                }
            }
        });
    }

    protected void switchfragment(int position) {
        this.position = position;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);
        transaction = getSupportFragmentManager().beginTransaction();

        switch (position) {
            case 0:
                if (scheduleFragment == null) {
                    scheduleFragment = new ScheduleFragment();
                    transaction.add(R.id.framelayout,scheduleFragment,"f1");
                } else {
                    transaction.show(scheduleFragment);
                }
                break;
            case 1:
                if (workbenchFragment == null) {
                    workbenchFragment = new WorkbenchFragment();
                    transaction.add(R.id.framelayout,workbenchFragment,"f2");
                } else {
                    transaction.show(workbenchFragment);
                }
                break;
            case 2:
                if (communicationFragment == null) {
                    communicationFragment = new CommunicationFragment();
                    transaction.add(R.id.framelayout,communicationFragment,"f3");
                } else {
                    transaction.show(communicationFragment);
                }
                break;
            case 3:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    transaction.add(R.id.framelayout,myFragment,"f4");
                } else {
                    transaction.show(myFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if(workbenchFragment != null)
            transaction.hide(workbenchFragment);
        if(scheduleFragment != null)
            transaction.hide(scheduleFragment);
        if(communicationFragment != null)
            transaction.hide(communicationFragment);
        if(myFragment != null)
            transaction.hide(myFragment);
        transaction.commit();
    }


    //申请权限
    public void checkMainPermission() {
        checkPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, new OnPermissionResultListener() {
            @Override
            public void onAllow() {
                loginResult = CommonUtils.getUserBean(MainActivity.this);
                if (loginResult != null) {
                    initData();

                    mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                        @Override
                        public void call(Object... args) {
                            Log.e("socket_conn","connect");
                        }
                    }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                        @Override
                        public void call(Object... args) {
                            Log.e("socket_dis",args[0].toString());
                        }
                    }).on("notification", new Emitter.Listener() {
                        @Override
                        public void call(Object... args) {
                            //Log.e("socket_notif", args[0].toString());
                            Intent intent = new Intent(GlobalParam.ACTION_NOTIFICATION);
                            sendBroadcast(intent);
                        }
                    }).on("user_connect", new Emitter.Listener() {
                        @Override
                        public void call(Object... args) {
                            //Log.e("socket_userconn", ((JSONObject) args[0]).toString());
                        }
                    }).on("user_disconnect", new Emitter.Listener() {
                        @Override
                        public void call(Object... args) {
                            //Log.e("socket_user_dis", ((JSONObject) args[0]).toString());
                        }
                    }).on("group_message", new Emitter.Listener() {
                        @Override
                        public void call(Object... args) {
                            //Log.e("socket_group", ((JSONObject) args[0]).toString());
                            Intent intent = new Intent(GlobalParam.ACTION_MESSAGE);
                            sendBroadcast(intent);
                        }
                    });
                    mSocket.connect();

                    //WsManager.getInstance().init();
                } else {
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onReject() {
                finish();
            }
        });
    }

    //点击加号弹出框
    public void initPopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.add_popup,null);
        addPopupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.add_popup)
                .setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                .setBackGroundLevel(0.5f)
                //设置动画
                .setAnimationStyle(R.style.popup_animation)
                //设置PopupWindow里的子View及点击事件
                .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        tv_case = view.findViewById(R.id.tv_case);
                        tv_loan = view.findViewById(R.id.tv_loan);
                        tv_saved = view.findViewById(R.id.tv_saved);
                        tv_archives = view.findViewById(R.id.tv_archives);
                        iv_exit = view.findViewById(R.id.iv_exit);

                        tv_case.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, CustomerSetupActivity.class);
                                startActivity(intent);

                                if (addPopupWindow != null) {
                                    addPopupWindow.dismiss();
                                }
                            }
                        });

                        tv_loan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, CustomerSetupActivity.class);
                                startActivity(intent);

                                if (addPopupWindow != null) {
                                    addPopupWindow.dismiss();
                                }
                            }
                        });

                        tv_saved.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, CustomerSetupActivity.class);
                                startActivity(intent);

                                if (addPopupWindow != null) {
                                    addPopupWindow.dismiss();
                                }
                            }
                        });

                        tv_archives.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, CustomerSetupActivity.class);
                                startActivity(intent);

                                if (addPopupWindow != null) {
                                    addPopupWindow.dismiss();
                                }
                            }
                        });

                        iv_exit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (addPopupWindow != null) {
                                    addPopupWindow.dismiss();
                                }
                            }
                        });
                    }
                })
                .setOutsideTouchable(true)
                .create();

        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupHeight = view.getMeasuredHeight();
        int popupWidth = view.getMeasuredWidth();

        int[] location = new int[2];
        // 获得位置 这里的v是目标控件，就是你要放在这个v的上面还是下面
        radioGroup.getLocationOnScreen(location);
        addPopupWindow.showAtLocation(radioGroup,Gravity.BOTTOM, 0, 0);
        //设置显示在v上方(以v的左边距为开始位置)
        //addPopupWindow.showAtLocation(radioGroup,Gravity.NO_GRAVITY, (location[0]) - popupWidth / 2, location[1] - popupHeight);
        //设置显示在v上方（以v的中心位置为开始位置）
        //addPopupWindow.showAtLocation(radioGroup,Gravity.NO_GRAVITY,  (location[0] + v.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);

        addPopupWindow.setChangeStyleListener(new CommonPopupWindow.OnChangeStyleListener() {
            @Override
            public void change() {
                isRotation = true;
                rotateAnimation();
            }
        });
    }

    public void initDarkPopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.add_popup,null);
        areaDarkPopupWindow = new AreaDarkPopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        areaDarkPopupWindow.setFocusable(true);
        areaDarkPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        areaDarkPopupWindow.setAnimationStyle(R.style.popup_animation);
        areaDarkPopupWindow.setDarkStyle(-1);
        areaDarkPopupWindow.resetDarkPosition();
        areaDarkPopupWindow.darkAbove(radioGroup);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupHeight = view.getMeasuredHeight();
        int popupWidth = view.getMeasuredWidth();

        int[] location = new int[2];
        // 获得位置 这里的v是目标控件，就是你要放在这个v的上面还是下面
        radioGroup.getLocationOnScreen(location);

        //设置显示在v上方(以v的左边距为开始位置)
        areaDarkPopupWindow.showAtLocation(radioGroup,Gravity.NO_GRAVITY, (location[0]) - popupWidth / 2, location[1] - popupHeight);

    }

    public void rotateAnimation() {
        if (!isRotation) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(iv_add,"rotation",0f,-45f);
            animator.setDuration(200);
            animator.start();
        } else {
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(iv_add,"rotation",-45f,0f);
            animator1.setDuration(200);
            animator1.start();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                isRotation = false;
                rotateAnimation();
                //initDarkPopupWindow();
                initPopupWindow();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
