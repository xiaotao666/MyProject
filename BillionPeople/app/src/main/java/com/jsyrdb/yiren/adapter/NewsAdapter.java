package com.jsyrdb.yiren.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.model.DelayNews;
import com.jsyrdb.yiren.model.DelayTime;
import com.jsyrdb.yiren.utils.ClearEditText;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.jsyrdb.yiren.utils.DateUtils;
import com.jsyrdb.yiren.utils.GlobalParam;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NewsAdapter extends BaseAdapter {

    private List<DelayNews.ResultBean> list;
    private Context context;
    private LayoutInflater inflater;

    public OnNotifyListener onNotifyListener;

    public NewsAdapter(Context context,List<DelayNews.ResultBean> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (list != null) {
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
            convertView = inflater.inflate(R.layout.item_news,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (list.get(position).getAction_id().equals("set_job_delay")) {
            viewHolder.tv_name.setText("任务:  "+list.get(position).getTarget_name());
        } else if (list.get(position).getAction_id().equals("set_case_break")) {
            viewHolder.tv_name.setText("案件:  "+list.get(position).getTarget_name());
        }

        viewHolder.tv_describe.setText("描述:  " +list.get(position).getDescribe());
        viewHolder.tv_apply_user.setText("申请人: "+list.get(position).getFrom_user_name());
        viewHolder.tv_apply_name.setText("申请:  " + list.get(position).getAction_name());

        viewHolder.tv_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getAction_id().equals("set_job_delay")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    View view = inflater.inflate(R.layout.dialog_reply,null);
                    ListView lv_time = view.findViewById(R.id.lv_time);
                    TextView tv_confirm = view.findViewById(R.id.tv_confirm);
                    TextView tv_cancel = view.findViewById(R.id.tv_cancel);
                    final Dialog dialog = builder.create();
                    dialog.show();

//                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
//                params.width = 460;
//                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//                dialog.getWindow().setAttributes(params);
                    dialog.getWindow().setContentView(view);
                    dialog.getWindow().setGravity(Gravity.CENTER);



                    List<DelayTime> list1 = new ArrayList<>();
                    DelayTime delayTime1 = new DelayTime("一天",true);
                    DelayTime delayTime2 = new DelayTime("两天",false);
                    DelayTime delayTime3 = new DelayTime("三天",false);
                    DelayTime delayTime4 = new DelayTime("四天",false);
                    DelayTime delayTime5 = new DelayTime("五天",false);
                    DelayTime delayTime6 = new DelayTime("六天",false);
                    DelayTime delayTime7 = new DelayTime("七天",false);

                    list1.add(delayTime1);
                    list1.add(delayTime2);
                    list1.add(delayTime3);
                    list1.add(delayTime4);
                    list1.add(delayTime5);
                    list1.add(delayTime6);
                    list1.add(delayTime7);

                    ChooseDelayTimeAdapter adapter = new ChooseDelayTimeAdapter(context,list1);
                    lv_time.setAdapter(adapter);

                    lv_time.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            for (DelayTime bean : list1) {//全部设为未选中
                                bean.setCheckd(false);
                            }
                            list1.get(position).setCheckd(true);//点击的设为选中
                            adapter.notifyDataSetChanged();
                        }
                    });

                    tv_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            replyDelay(position,false,0);
                        }
                    });

                    tv_confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            for (int i = 0;i < list1.size();i++) {
                                if (list1.get(i).isCheckd()) {
                                    replyDelay(position,true,(i + 1)*24);
                                    return;
                                }
                            }

                        }
                    });
                } else if (list.get(position).getAction_id().equals("set_case_break")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    View view = inflater.inflate(R.layout.dialog_delay,null);
                    ClearEditText et_describe = view.findViewById(R.id.et_describe);
                    TextView tv_confirm = view.findViewById(R.id.tv_confirm);
                    TextView tv_cancel = view.findViewById(R.id.tv_cancel);
                    TextView tv_title = view.findViewById(R.id.tv_title);
                    final Dialog dialog = builder.create();
                    dialog.show();

                    dialog.getWindow().setContentView(view);
                    dialog.getWindow().setGravity(Gravity.CENTER);
                    dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

                    tv_title.setText("批复退案");
                    tv_confirm.setText("同意");
                    tv_cancel.setText("拒绝");
                    tv_cancel.setTextColor(Color.RED);

                    tv_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            replyBreak(position,et_describe.getText().toString(),false);
                        }
                    });

                    tv_confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            replyBreak(position,et_describe.getText().toString(),true);
                        }
                    });
                }

            }
        });
        return convertView;
    }

    public void replyBreak(int position,String describe,boolean isAgree) {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();

        if (isAgree) {
            jsonObject1.put("_id",list.get(position).get_id());
            jsonObject1.put("reply_describe",describe);
            jsonObject1.put("reply_result","allow");
            jsonObject1.put("reply_result_name","同意");
            jsonObject1.put("allowtime_span",8);
        } else {
            jsonObject1.put("_id",list.get(position).get_id());
            jsonObject1.put("reply_describe",describe);
            jsonObject1.put("reply_result","deny");
            jsonObject1.put("reply_result_name","拒绝");
            jsonObject1.put("allowtime_span",8);
        }


        jsonObject.put("applyInfo",jsonObject1);

        MediaType json = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(context);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_apply/do_reply")
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
                        Log.e("replyBreak",result);
                        JSONObject object = JSON.parseObject(result);
                        if (object.getString("error") == null) {
                            if (isAgree) {
                                executeBreak(position);
                            } else {
                                if (onNotifyListener != null) {
                                    onNotifyListener.notifyData();
                                }
                                Intent intent1 = new Intent(GlobalParam.ACTION_NOTIFICATION);
                                context.sendBroadcast(intent1);
                                Toast.makeText(context,"批复成功",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }

    public void replyDelay(int position,boolean isAgree,int time) {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();

        if (isAgree) {
            jsonObject1.put("_id",list.get(position).get_id());
            jsonObject1.put("reply_describe","");
            jsonObject1.put("reply_result","execute");
            jsonObject1.put("reply_result_name","执行");
            jsonObject1.put("allowtime_span",time);
        } else {
            jsonObject1.put("_id",list.get(position).get_id());
            jsonObject1.put("reply_describe","");
            jsonObject1.put("reply_result","deny");
            jsonObject1.put("reply_result_name","拒绝");
        }
        jsonObject.put("applyInfo",jsonObject1);

        MediaType json = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(context);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_apply/do_reply")
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
                        Log.e("reply",result);
                        JSONObject object = JSON.parseObject(result);
                        if (object.getString("error") == null) {
                            if (isAgree) {
                                executeDelay(position,time);
                            } else {
                                if (onNotifyListener != null) {
                                    onNotifyListener.notifyData();
                                }
                                Intent intent1 = new Intent(GlobalParam.ACTION_NOTIFICATION);
                                context.sendBroadcast(intent1);
                                Toast.makeText(context,"批复成功",Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
            }
        });

    }

    public void executeBreak(int position) {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("_id",list.get(position).getTarget_id());

        jsonObject.put("caseInfo",jsonObject1);

        MediaType json = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(context);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_case/set_case_break")
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
                        Log.e("reply2",result);
                        JSONObject object = JSON.parseObject(result);
                        if (object.getString("error") == null) {
                            if (onNotifyListener != null) {
                                onNotifyListener.notifyData();
                            }
                            Intent intent1 = new Intent(GlobalParam.ACTION_NOTIFICATION);
                            context.sendBroadcast(intent1);
                            Toast.makeText(context,"执行成功",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void executeDelay(int position,int time) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jobid",list.get(position).getTarget_id());
        jsonObject.put("time_span",time);

        MediaType json = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(context);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_job/set_job_delay")
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
                        Log.e("reply1",result);
                        JSONObject object = JSON.parseObject(result);
                        if (object.getString("error") == null) {
                            if (onNotifyListener != null) {
                                onNotifyListener.notifyData();
                            }
                            Intent intent1 = new Intent(GlobalParam.ACTION_NOTIFICATION);
                            context.sendBroadcast(intent1);
                            Toast.makeText(context,"执行成功",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public interface OnNotifyListener{
        void notifyData();
    }

    public void setOnNotifyListener(OnNotifyListener onNotifyListener) {
        this.onNotifyListener = onNotifyListener;
    }

    class ViewHolder{
        private TextView tv_name,tv_apply_user,tv_describe,tv_apply_name,tv_reply;

        public ViewHolder(View view) {
            tv_name = view.findViewById(R.id.tv_name);
            tv_apply_user = view.findViewById(R.id.tv_apply_user);
            tv_describe = view.findViewById(R.id.tv_describe);
            tv_apply_name = view.findViewById(R.id.tv_apply_name);
            tv_reply = view.findViewById(R.id.tv_reply);
        }
    }
}
