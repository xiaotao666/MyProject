package com.jsyrdb.yiren.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.cases.ApplyDelayActivity;
import com.jsyrdb.yiren.activity.cases.ApprovalActivity;
import com.jsyrdb.yiren.activity.cases.CaseDetailsActivity;
import com.jsyrdb.yiren.activity.cases.SetupPeopleActivity;
import com.jsyrdb.yiren.activity.cases.TaskActivity;
import com.jsyrdb.yiren.model.Approval;
import com.jsyrdb.yiren.model.DelayTime;
import com.jsyrdb.yiren.model.LoginResult;
import com.jsyrdb.yiren.model.Task;
import com.jsyrdb.yiren.utils.ClearEditText;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonPopupWindow;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.jsyrdb.yiren.utils.DateUtils;
import com.jsyrdb.yiren.widget.ExpandListView;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TaskAdapter extends BaseAdapter {

    private Context context;
    private List<Task.ResultBean> list;
    private LayoutInflater inflater;
    public UpdateViewListener listener;

    // 用来控制CheckBox的选中状况
    private static HashMap<Integer, Boolean> isSelected;

    private boolean isEidt = false;//判断长按后出现复选框

    //延期的批复人员
    private String to_user_id = "";
    private String to_user_name = "";

    //退案的批复人员
    private String break_to_user_id = "";
    private String break_to_user_name = "";

    private CommonPopupWindow popupWindow;
    private CommonPopupWindow breakPopupWindow;

    public TaskAdapter(Context context,List<Task.ResultBean> list,HashMap<Integer,Boolean> isSelected) {
        this.context = context;
        this.list = list;
        TaskAdapter.isSelected = isSelected;
        inflater = LayoutInflater.from(context);
        initData();
    }

    // 初始化isSelected的数据
    private void initData() {
        for (int i = 0; i < list.size(); i++) {
            getIsSelected().put(i, false);
        }
    }

    @Override
    public int getCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_task,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ViewHolder finalViewHolder = viewHolder;// listView的itemView

        String creteDate = DateUtils.getDate2String(Long.valueOf(list.get(position).getCreat_timestamp()),"yyyy-MM-dd");
        viewHolder.tv_name.setText(list.get(position).getName());
        viewHolder.tv_time.setText(creteDate);
        if (!TextUtils.isEmpty(list.get(position).getJob_manager_name())) {
            viewHolder.tv_manager.setText("负责人: " + list.get(position).getJob_manager_name());
        } else {
            viewHolder.tv_manager.setText("负责人: 暂无");
        }

        viewHolder.cb_people.setChecked(getIsSelected().get(position));

        if (list.get(position).getTime() != null) {
            if (list.get(position).getTime().getType().equals("time_noneed")) {
                viewHolder.btn_delay.setVisibility(View.GONE);
            } else {
                viewHolder.btn_delay.setVisibility(View.VISIBLE);
            }
        } else {
            viewHolder.btn_delay.setVisibility(View.GONE);
        }


        if (isEidt) {
            viewHolder.cb_people.setVisibility(View.VISIBLE);
        } else {
            viewHolder.cb_people.setVisibility(View.GONE);
        }

        viewHolder.cb_people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected.get(position)) {
                    isSelected.put(position, false);
                    setIsSelected(isSelected);
                } else {
                    isSelected.put(position, true);
                    setIsSelected(isSelected);
                }
                notifyDataSetChanged();
            }
        });

        viewHolder.btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalViewHolder.swipeLayout.quickClose();// 关闭侧滑菜单：需要将itemView强转，然后调用quickClose()方法
                Intent intent = new Intent(context, SetupPeopleActivity.class);
                intent.putExtra("step",3);
                intent.putExtra("_id",list.get(position).get_id());
                ((Activity)context).startActivityForResult(intent,2);
            }
        });

        viewHolder.btn_delay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalViewHolder.swipeLayout.quickClose();// 关闭侧滑菜单：需要将itemView强转，然后调用quickClose()方法

                LoginResult.ResultBean.UserBean userBean = CommonUtils.getUserBean(context);
                String user_id = userBean.get_id();

                if (!TextUtils.isEmpty(list.get(position).getJob_manager_id())) {
                    if (list.get(position).getJob_manager_id().equals(user_id)) {
                        getApplyDelayInfo(position,v);
                    } else {
                        Toast.makeText(context,"抱歉,您不是该任务的负责人,没有权限申请延期",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context,"该任务还没有设置负责人,您暂时还不能进行延期操作",Toast.LENGTH_SHORT).show();
                }

//                Intent intent = new Intent(context, ApplyDelayActivity.class);
//                intent.putExtra("caseid",list.get(position).getCaseid());
//                intent.putExtra("processid",list.get(position).getProcessid());
//                intent.putExtra("jobid",list.get(position).get_id());
//                context.startActivity(intent);

            }
        });

        viewHolder.btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalViewHolder.swipeLayout.quickClose();// 关闭侧滑菜单：需要将itemView强转，然后调用quickClose()方法
                getPlayPermission(position,v);
//                Intent intent = new Intent(context, ApprovalActivity.class);
//                context.startActivity(intent);
            }
        });

        viewHolder.ll_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalViewHolder.swipeLayout.quickClose();// 关闭侧滑菜单：需要将itemView强转，然后调用quickClose()方法
                Intent intent = new Intent(context, CaseDetailsActivity.class);
                intent.putExtra("jobid",list.get(position).get_id());
                intent.putExtra("processid",list.get(position).getProcessid());
                intent.putExtra("status",list.get(position).getStatus());
                intent.putExtra("nodename",list.get(position).getNodename());
                context.startActivity(intent);
            }
        });

        viewHolder.ll_content.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                isEidt = true;
                if (listener != null) {
                    listener.updateView();
                }

                notifyDataSetChanged();

                return true;
            }
        });

        return convertView;
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        TaskAdapter.isSelected = isSelected;
    }

    public interface UpdateViewListener{
        void updateView();
    }

    public void setUpdateViewListener(UpdateViewListener listener) {
        this.listener = listener;
    }
    //检查退案授权权限
    public void getAuthPermission(int position) {
        String param = "action_model_name=action_case&action_id=set_case_break&target_id="+list.get(position).getCaseid();
        param = CommonUtils.getParameter(param);

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(context);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_permission/check_temp_permission?"+param)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result =response.body().string();
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("permision",result);
                        JSONObject jsonObject = JSON.parseObject(result);
                        if (jsonObject.getString("error") == null) {
                            //getPlayPermission(position);
                        } else {
                            Toast.makeText(context,"此操作未授权",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //检查自己是否是操作权限
    public void getPlayPermission(int position,View anchorView) {
        String param = "permissionid=case_break_apply&caseid="+list.get(position).getCaseid()+"&processid="+list.get(position).getProcessid()+"&jobid=";
        param = CommonUtils.getParameter(param);
        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(context);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_permission/check_permission?"+param)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result =response.body().string();
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("permision1",result);
                        JSONObject jsonObject = JSON.parseObject(result);
                        if (jsonObject.getString("error") == null) {
                            getApplyBreakInfo(position,anchorView);
                        } else {
                            Toast.makeText(context,"您没有该操作权限",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //获取申请退案信息(判断是否已经申请退案过了)
    public void getApplyBreakInfo(int position,View anchorView) {
        String param = "filter[0][action_model_name]=action_case&filter[1][action_id]=set_case_break&filter[2][target_id]="+list.get(position).getCaseid()+"&page=1&limit=0&sort[0][]=creat_timestamp&sort[0][]=-1";
        param = CommonUtils.getParameter(param);

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(context);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_apply/get_apply_list?"+param)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result =response.body().string();
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("breakinfo",result);
                        JSONObject jsonObject = JSON.parseObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("result");

                        if (jsonArray != null && jsonArray.size() > 0) {
                            Toast.makeText(context,"该案件已经退过案了",Toast.LENGTH_SHORT).show();

                        } else {
                            getBreakApproval(position,anchorView);

                        }
                    }
                });
            }
        });
    }

    //获取有批复退案申请权限的人员
    public void getBreakApproval(int positions,View anchorView) {
        String json = "permissionid=case_break_reply&caseid="+ list.get(positions).getCaseid() +"&processid=" + list.get(positions).getProcessid() + "&jobid=" + list.get(positions).get_id();
        json = CommonUtils.getParameter(json);

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(context);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_permission/get_user_list_bypermission?"+json)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result =response.body().string();
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Approval approval = JSON.parseObject(result,Approval.class);
                        List<Approval.ResultBean> list = approval.getResult();
                        if (list != null) {
                            if (list.size() > 1) {
                                breakPopupWindow = new CommonPopupWindow.Builder(context)
                                        .setView(R.layout.select_people_popup)
                                        //.setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                                        //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                                        .setBackGroundLevel(0.5f)
                                        //设置PopupWindow里的子View及点击事件
                                        .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                                            @Override
                                            public void getChildView(View view, int layoutResId) {
                                                ExpandListView lv_people = view.findViewById(R.id.lv_people);
                                                TextView tv_cancel = view.findViewById(R.id.tv_cancel);
                                                lv_people.setExpanded(true);
                                                SelectPeoplePopupAdapter adapter = new SelectPeoplePopupAdapter(context,list);
                                                lv_people.setAdapter(adapter);

                                                lv_people.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                        break_to_user_id = list.get(position).get_id();
                                                        break_to_user_name = list.get(position).getName();

                                                        if (breakPopupWindow != null) {
                                                            breakPopupWindow.dismiss();
                                                        }

                                                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                                        LayoutInflater inflater = LayoutInflater.from(context);
                                                        View view1 = inflater.inflate(R.layout.dialog_delay,null);
                                                        ClearEditText et_describe = view1.findViewById(R.id.et_describe);
                                                        TextView tv_confirm = view1.findViewById(R.id.tv_confirm);
                                                        TextView tv_cancel = view1.findViewById(R.id.tv_cancel);
                                                        TextView tv_title = view1.findViewById(R.id.tv_title);
                                                        final Dialog dialog = builder.create();
                                                        dialog.show();

                                                        dialog.getWindow().setContentView(view1);
                                                        dialog.getWindow().setGravity(Gravity.CENTER);
                                                        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

                                                        tv_title.setText("申请退案");

                                                        tv_cancel.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                if (dialog != null) {
                                                                    dialog.dismiss();
                                                                }
                                                            }
                                                        });

                                                        tv_confirm.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                if (dialog != null) {
                                                                    dialog.dismiss();
                                                                }

                                                                createBreak(et_describe.getText().toString(),break_to_user_id,break_to_user_name,positions);
                                                            }
                                                        });
                                                    }
                                                });

                                                tv_cancel.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        if (breakPopupWindow != null) {
                                                            breakPopupWindow.dismiss();
                                                        }
                                                    }
                                                });

                                            }
                                        })
                                        .setOutsideTouchable(true)
                                        .create();
                                breakPopupWindow.showAtLocation(anchorView,Gravity.BOTTOM,0,40);
                            } else {
                                break_to_user_id = approval.getResult().get(0).get_id();
                                break_to_user_name = approval.getResult().get(0).getName();

                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                LayoutInflater inflater = LayoutInflater.from(context);
                                View view1 = inflater.inflate(R.layout.dialog_delay,null);
                                ClearEditText et_describe = view1.findViewById(R.id.et_describe);
                                TextView tv_confirm = view1.findViewById(R.id.tv_confirm);
                                TextView tv_cancel = view1.findViewById(R.id.tv_cancel);
                                TextView tv_title = view1.findViewById(R.id.tv_title);
                                final Dialog dialog = builder.create();
                                dialog.show();

                                dialog.getWindow().setContentView(view1);
                                dialog.getWindow().setGravity(Gravity.CENTER);
                                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

                                tv_title.setText("申请退案");

                                tv_cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (dialog != null) {
                                            dialog.dismiss();
                                        }
                                    }
                                });

                                tv_confirm.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (dialog != null) {
                                            dialog.dismiss();
                                        }

                                        createBreak(et_describe.getText().toString(),break_to_user_id,break_to_user_name,positions);
                                    }
                                });
                            }
                        }
                    }
                });
            }
        });

    }

    //申请退案
    public void createBreak(String describe,String id,String name,int position) {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();

        jsonObject1.put("name","");
        jsonObject1.put("action_id","set_case_break");
        jsonObject1.put("action_model_name","action_case");
        jsonObject1.put("action_name","案件退案");
        jsonObject1.put("action_type","auth");
        jsonObject1.put("describe",describe);
        jsonObject1.put("location_level","process");
        jsonObject1.put("location_level_name","流程");
        jsonObject1.put("location_id",list.get(position).getProcessid());
        jsonObject1.put("location_name",list.get(position).getProcessname());
        jsonObject1.put("target_level","case");
        jsonObject1.put("target_level_name","案件");
        jsonObject1.put("target_id",list.get(position).getCaseid());
        jsonObject1.put("target_name",list.get(position).getCasename());
        jsonObject1.put("to_user_id",id);
        jsonObject1.put("to_user_name",name);

        jsonObject.put("applyInfo",jsonObject1);


        MediaType json = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(context);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_apply/creat_apply")
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result =response.body().string();
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("createApplybreak",result);
                        JSONObject object = JSON.parseObject(result);
                        if (object.getString("error") == null) {
                            Toast.makeText(context,"申请退案成功",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //申请延期
    public void createApply(String describe,String id,String name,int position) {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();

        jsonObject1.put("name","申请任务延期");
        jsonObject1.put("describe",describe);
        jsonObject1.put("action_type","auth");
        jsonObject1.put("action_model_name","action_job");
        jsonObject1.put("action_id","set_job_delay");
        jsonObject1.put("action_name","延长任务期限");
        jsonObject1.put("target_level","job");
        jsonObject1.put("target_level_name","任务");
        jsonObject1.put("target_id",list.get(position).get_id());
        jsonObject1.put("target_name",list.get(position).getName());
        jsonObject1.put("location_level","case");
        jsonObject1.put("location_level_name","案件");
        jsonObject1.put("location_id",list.get(position).getCaseid());
        jsonObject1.put("location_name",list.get(position).getCasename());
        jsonObject1.put("to_user_id",id);
        jsonObject1.put("to_user_name",name);

        jsonObject.put("applyInfo",jsonObject1);

        MediaType json = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(context);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_apply/creat_apply")
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result =response.body().string();
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("createApply",result);
                        JSONObject object = JSON.parseObject(result);
                        if (object.getString("error") == null) {
                            Toast.makeText(context,"申请延期成功",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //获取发送对象(批复人员)
    public void getDelayApproval(int positions,View anchorView) {
        String json = "permissionid=job_end_delay_reply&caseid="+ list.get(positions).getCaseid() +"&processid=" + list.get(positions).getProcessid() + "&jobid=" + list.get(positions).get_id();
        json = CommonUtils.getParameter(json);

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(context);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_permission/get_user_list_bypermission?"+json)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result =response.body().string();
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Approval approval = JSON.parseObject(result,Approval.class);
                        List<Approval.ResultBean> list = approval.getResult();
                        if (list != null) {
                            if (list.size() > 1) {
                                popupWindow = new CommonPopupWindow.Builder(context)
                                        .setView(R.layout.select_people_popup)
                                        //.setWidthAndHeight(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                                        //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                                        .setBackGroundLevel(0.5f)
                                        //设置PopupWindow里的子View及点击事件
                                        .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                                            @Override
                                            public void getChildView(View view, int layoutResId) {
                                                ExpandListView lv_people = view.findViewById(R.id.lv_people);
                                                TextView tv_cancel = view.findViewById(R.id.tv_cancel);
                                                lv_people.setExpanded(true);
                                                SelectPeoplePopupAdapter adapter = new SelectPeoplePopupAdapter(context,list);
                                                lv_people.setAdapter(adapter);

                                                lv_people.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                        to_user_id = list.get(position).get_id();
                                                        to_user_name = list.get(position).getName();

                                                        if (popupWindow != null) {
                                                            popupWindow.dismiss();
                                                        }

                                                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                                        LayoutInflater inflater = LayoutInflater.from(context);
                                                        View view1 = inflater.inflate(R.layout.dialog_delay,null);
                                                        ClearEditText et_describe = view1.findViewById(R.id.et_describe);
                                                        TextView tv_confirm = view1.findViewById(R.id.tv_confirm);
                                                        TextView tv_cancel = view1.findViewById(R.id.tv_cancel);
                                                        final Dialog dialog = builder.create();
                                                        dialog.show();

                                                        dialog.getWindow().setContentView(view1);
                                                        dialog.getWindow().setGravity(Gravity.CENTER);
                                                        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);


                                                        tv_cancel.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                if (dialog != null) {
                                                                    dialog.dismiss();
                                                                }
                                                            }
                                                        });

                                                        tv_confirm.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                if (dialog != null) {
                                                                    dialog.dismiss();
                                                                }

                                                                createApply(et_describe.getText().toString(),to_user_id,to_user_name,positions);
                                                            }
                                                        });
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
                                popupWindow.showAtLocation(anchorView,Gravity.BOTTOM,0,40);

                            } else {
                                to_user_id = list.get(0).get_id();
                                to_user_name = list.get(0).getName();

                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                LayoutInflater inflater = LayoutInflater.from(context);
                                View view = inflater.inflate(R.layout.dialog_delay,null);
                                ClearEditText et_describe = view.findViewById(R.id.et_describe);
                                TextView tv_confirm = view.findViewById(R.id.tv_confirm);
                                TextView tv_cancel = view.findViewById(R.id.tv_cancel);
                                final Dialog dialog = builder.create();
                                dialog.show();

                                dialog.getWindow().setContentView(view);
                                dialog.getWindow().setGravity(Gravity.CENTER);
                                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);


                                tv_cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (dialog != null) {
                                            dialog.dismiss();
                                        }
                                    }
                                });

                                tv_confirm.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (dialog != null) {
                                            dialog.dismiss();
                                        }

                                        createApply(et_describe.getText().toString(),to_user_id,to_user_name,positions);
                                    }
                                });
                            }
                        }
                    }
                });
            }
        });
    }

    //获取申请延期信息(判断是否已经延期过了)
    public void getApplyDelayInfo(int position,View anchorView) {
        String param = "filter[0][target_id]="+list.get(position).get_id()+"&filter[1][from_user_id]="+list.get(position).getJob_manager_id()+"&filter[2][action_id]=set_job_delay&page=1&limit=1&sort[0][]=creat_timestamp&sort[0][]=-1";
        param = CommonUtils.getParameter(param);

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(context);
        Request request = new Request.Builder()
                .url(CommonData.server_url+"action_apply/get_apply_list?"+param)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result =response.body().string();
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("apply",result);
                        JSONObject jsonObject = JSON.parseObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("result");

                        if (jsonArray != null && jsonArray.size() > 0) {
                            Toast.makeText(context,"该任务您已经延期过了",Toast.LENGTH_SHORT).show();

                        } else {
                            getDelayApproval(position,anchorView);

                        }

                    }
                });
            }
        });
    }

    class ViewHolder{
        private TextView tv_name,tv_time,tv_manager;
        private SwipeMenuLayout swipeLayout;
        private Button btn_delay,btn_set,btn_exit;
        private CheckBox cb_people;
        private LinearLayout ll_content;

        public ViewHolder(View view) {
            tv_name = view.findViewById(R.id.tv_name);
            tv_time = view.findViewById(R.id.tv_time);
            tv_manager = view.findViewById(R.id.tv_manager);
            swipeLayout = view.findViewById(R.id.swipeLayout);
            btn_delay = view.findViewById(R.id.btn_delay);
            btn_set = view.findViewById(R.id.btn_set);
            btn_exit = view.findViewById(R.id.btn_exit);
            cb_people = view.findViewById(R.id.cb_people);
            ll_content = view.findViewById(R.id.ll_content);
        }

    }
}
