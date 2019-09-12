package com.jsyrdb.yiren.activity.mine;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.cases.EditIntroActivity;
import com.jsyrdb.yiren.activity.main.MainNewsActivity;
import com.jsyrdb.yiren.activity.main.MainSearchActivity;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.glide.GlideUtils;
import com.jsyrdb.yiren.model.LoginResult;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.jsyrdb.yiren.utils.FromAlbumToPathUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EditDataActivity extends BaseActivity implements View.OnClickListener {

    //标题栏
//    private TextView tv_title;
//    private ImageView iv_message,iv_search;
//    private LinearLayout ll_back;

    private RelativeLayout rl_head,rl_phone,rl_email;
    private LinearLayout ll_introduce;
    private ImageView iv_head;
    private TextView tv_phone,tv_email,tv_introduce;

    private LoginResult.ResultBean.UserBean userBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        initView();
        setTitle("个人资料");
        userBean = CommonUtils.getUserBean(this);
        if (userBean != null) {
            tv_phone.setText(userBean.getMobile());
            tv_email.setText(userBean.getEmail());
            tv_introduce.setText(userBean.getDescribe());
        }

    }

    public void initView() {
//        tv_title = findViewById(R.id.tv_title);
//        tv_title.setText("个人资料");
//        iv_message = findViewById(R.id.iv_message);
//        iv_search = findViewById(R.id.iv_search);
//        ll_back = findViewById(R.id.ll_back);
        rl_head = findViewById(R.id.rl_head);
        rl_phone = findViewById(R.id.rl_phone);
        rl_email = findViewById(R.id.rl_email);
        ll_introduce = findViewById(R.id.ll_introduce);
        iv_head = findViewById(R.id.iv_head);
        tv_phone = findViewById(R.id.tv_phone);
        tv_email = findViewById(R.id.tv_email);
        tv_introduce = findViewById(R.id.tv_introduce);

//        ll_back.setOnClickListener(this);
//        iv_message.setOnClickListener(this);
//        iv_search.setOnClickListener(this);
        rl_head.setOnClickListener(this);
        rl_phone.setOnClickListener(this);
        rl_email.setOnClickListener(this);
        ll_introduce.setOnClickListener(this);
    }

    public void editDialog(String title,String param,String info) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_edit,null);
        TextView tv_title = view.findViewById(R.id.tv_title);
        EditText et_info = view.findViewById(R.id.et_info);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        TextView tv_confirm = view.findViewById(R.id.tv_confirm);

        final Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setContentView(view);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);


        tv_title.setText(title);
        et_info.setText(info);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager manager= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow( dialog.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_info.getText().toString())) {
                    InputMethodManager manager= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    manager.hideSoftInputFromWindow( dialog.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    changeInfo(param,et_info.getText().toString());
                } else {
                    T("输入内容不能为空");
                }
            }
        });
    }

    public void changeInfo(String param,String info) {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("_id",userBean.get_id());
        jsonObject1.put("name",userBean.getName());
        jsonObject1.put("username",userBean.getUsername());
        jsonObject1.put(param,info);
        jsonObject.put("userInfo",jsonObject1);

        MediaType json = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(this);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_user/update_user_info")
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
                        Log.e("edit",result);
                        JSONObject resultObject = JSON.parseObject(result);
                        if (resultObject.getString("error") == null && resultObject.getInteger("result") == 1) {
                            T("修改成功");
                            if (param.equals("mobile")) {
                                userBean.setMobile(info);
                                CommonUtils.saveUserBean(EditDataActivity.this,userBean);
                                tv_phone.setText(info);
                            } else if (param.equals("email")) {
                                userBean.setEmail(info);
                                CommonUtils.saveUserBean(EditDataActivity.this,userBean);
                                tv_email.setText(info);
                            }

                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String photoPath = FromAlbumToPathUtils.getRealPathFromUri(this,data.getData());
            GlideUtils.loadPic(this,photoPath,iv_head,R.drawable.logo,R.drawable.logo);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            String intro = data.getStringExtra("intro");
            tv_introduce.setText(intro);
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
            case R.id.rl_head:
                Intent intent2 = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                intent2.setType("image/*");
                startActivityForResult(intent2,1);
                break;
            case R.id.rl_phone:
                editDialog("修改手机号","mobile",tv_phone.getText().toString());
                break;
            case R.id.rl_email:
                editDialog("修改邮箱","email",tv_email.getText().toString());
                break;
            case R.id.ll_introduce:
                Intent intent3 = new Intent(this, EditIntroActivity.class);
                intent3.putExtra("intro",tv_introduce.getText().toString());
                startActivityForResult(intent3,2);
                break;
        }
    }
}
