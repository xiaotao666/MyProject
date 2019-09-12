package com.jsyrdb.yiren.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jsyrdb.yiren.activity.cases.BusinessActivity;
import com.jsyrdb.yiren.activity.cases.CaseDetailsActivity;
import com.jsyrdb.yiren.activity.cases.FlowPathSetupActivity;
import com.jsyrdb.yiren.activity.cases.NextProcessActivity;
import com.jsyrdb.yiren.activity.cases.PersonnelSettingActivity;
import com.jsyrdb.yiren.activity.cases.SetupPeopleActivity;
import com.jsyrdb.yiren.activity.cases.TaskActivity;
import com.jsyrdb.yiren.activity.main.MainActivity;
import com.jsyrdb.yiren.model.DelayTime;
import com.jsyrdb.yiren.utils.DateUtils;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.cases.ApplyRetirementActivity;
import com.jsyrdb.yiren.activity.cases.ApprovalActivity;
import com.jsyrdb.yiren.model.Business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BusinessAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Business.ResultBean> list;

    public BusinessAdapter(Context context,List<Business.ResultBean> list) {
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
            convertView = inflater.inflate(R.layout.item_business,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String creteDate = DateUtils.getDate2String(Long.valueOf(list.get(position).getCreat_timestamp()),"yyyy-MM-dd");

        viewHolder.tv_name.setText(list.get(position).getName());
        viewHolder.tv_time.setText(creteDate);
        if (!TextUtils.isEmpty(list.get(position).getProcess_manager_name())) {
            viewHolder.tv_manager.setText("负责人: " + list.get(position).getProcess_manager_name());
        } else {
            viewHolder.tv_manager.setText("负责人: 暂无");
        }

        final ViewHolder finalViewHolder = viewHolder;// listView的itemView

//        viewHolder.btn_delay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                LayoutInflater inflater = LayoutInflater.from(context);
//                View view = inflater.inflate(R.layout.dialog_delay,null);
//                ListView lv_time = view.findViewById(R.id.lv_time);
//                TextView tv_confirm = view.findViewById(R.id.tv_confirm);
//                TextView tv_cancel = view.findViewById(R.id.tv_cancel);
//                final Dialog dialog = builder.create();
//                dialog.show();
//
////                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
////                params.width = 460;
////                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
////                dialog.getWindow().setAttributes(params);
//                dialog.getWindow().setContentView(view);
//                dialog.getWindow().setGravity(Gravity.CENTER);
//
//
//
//                List<DelayTime> list1 = new ArrayList<>();
//                DelayTime delayTime1 = new DelayTime("一天",true);
//                DelayTime delayTime2 = new DelayTime("两天",false);
//                DelayTime delayTime3 = new DelayTime("三天",false);
//                DelayTime delayTime4 = new DelayTime("四天",false);
//                DelayTime delayTime5 = new DelayTime("五天",false);
//                DelayTime delayTime6 = new DelayTime("六天",false);
//                DelayTime delayTime7 = new DelayTime("七天",false);
//
//                list1.add(delayTime1);
//                list1.add(delayTime2);
//                list1.add(delayTime3);
//                list1.add(delayTime4);
//                list1.add(delayTime5);
//                list1.add(delayTime6);
//                list1.add(delayTime7);
//
//                ChooseDelayTimeAdapter adapter = new ChooseDelayTimeAdapter(context,list1);
//                lv_time.setAdapter(adapter);
//
//                lv_time.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        for (DelayTime bean : list1) {//全部设为未选中
//                            bean.setCheckd(false);
//                        }
//                        list1.get(position).setCheckd(true);//点击的设为选中
//                        adapter.notifyDataSetChanged();
//                    }
//                });
//
//                tv_cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (dialog != null) {
//                            dialog.dismiss();
//                        }
//                    }
//                });
//
//                tv_confirm.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (dialog != null) {
//                            dialog.dismiss();
//                        }
//                        for (int i = 0;i < list1.size();i++) {
//                            if (list1.get(i).isCheckd()) {
//                                Toast.makeText(context,list1.get(i).getTime(),Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                        }
//
//                    }
//                });
//
//                finalViewHolder.swipeLayout.quickClose();// 关闭侧滑菜单：需要将itemView强转，然后调用quickClose()方法
//            }
//        });

        viewHolder.btn_person_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalViewHolder.swipeLayout.quickClose();// 关闭侧滑菜单：需要将itemView强转，然后调用quickClose()方法
                Intent intent = new Intent(context, SetupPeopleActivity.class);
                intent.putExtra("step",2);
                intent.putExtra("_id",list.get(position).get_id());
                ((Activity)context).startActivityForResult(intent,3);
            }
        });

        viewHolder.btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalViewHolder.swipeLayout.quickClose();// 关闭侧滑菜单：需要将itemView强转，然后调用quickClose()方法
                Intent intent = new Intent(context, NextProcessActivity.class);
                intent.putExtra("next_process_list", (Serializable) list.get(position).getNext_process_list());
                intent.putExtra("customerid",list.get(position).getCustomerid());
                intent.putExtra("customername",list.get(position).getCustomername());
                intent.putExtra("_id",list.get(position).getCaseid());
                intent.putExtra("name",list.get(position).getCasename());
                ((Activity)context).startActivityForResult(intent,4);
            }
        });

//        viewHolder.btn_retire_case.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finalViewHolder.swipeLayout.quickClose();// 关闭侧滑菜单：需要将itemView强转，然后调用quickClose()方法
//                Intent intent = new Intent(context, ApprovalActivity.class);
//                context.startActivity(intent);
//            }
//        });

        viewHolder.rl_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.e("processid",list.get(position).get_id());
                finalViewHolder.swipeLayout.quickClose();// 关闭侧滑菜单：需要将itemView强转，然后调用quickClose()方法
                Intent intent = new Intent(context, TaskActivity.class);
                intent.putExtra("processid",list.get(position).get_id());
                intent.putExtra("node",list.get(position).getProcess_type());
                intent.putExtra("processname",list.get(position).getName());
                intent.putExtra("status",list.get(position).getStatus());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder{
        private TextView tv_name,tv_time,tv_manager;
        private SwipeMenuLayout swipeLayout;
        private Button btn_delay,btn_person_setup,btn_next;
        private RelativeLayout rl_content;

        public ViewHolder(View view) {
            tv_name = view.findViewById(R.id.tv_name);
            tv_time = view.findViewById(R.id.tv_time);
            tv_manager = view.findViewById(R.id.tv_manager);
            swipeLayout = view.findViewById(R.id.swipeLayout);
            //btn_delay = view.findViewById(R.id.btn_delay);
            btn_person_setup = view.findViewById(R.id.btn_person_setup);
            btn_next = view.findViewById(R.id.btn_next);
            rl_content = view.findViewById(R.id.rl_content);
        }
    }
}
