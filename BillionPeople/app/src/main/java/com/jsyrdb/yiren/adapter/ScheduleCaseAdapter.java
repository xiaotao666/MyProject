package com.jsyrdb.yiren.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.cases.CaseDetailsActivity;
import com.jsyrdb.yiren.model.ScheduleCase;

import java.util.List;

public class ScheduleCaseAdapter extends RecyclerView.Adapter<ScheduleCaseAdapter.MyViewHolder> {
    public Context context;
    public List<ScheduleCase.ResultBean.JobListBean> list;
    public LayoutInflater inflater;


    public ScheduleCaseAdapter(Context context,List<ScheduleCase.ResultBean.JobListBean> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_schedule_case,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ScheduleCase.ResultBean.JobListBean scheduleCase = list.get(position);
        holder.tv_case_name.setText(scheduleCase.getCasename());
        holder.tv_task_name.setText(scheduleCase.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CaseDetailsActivity.class);
                intent.putExtra("jobid",list.get(position).get_id());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (list != null) {
            return  list.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_case_name,tv_task_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_case_name = itemView.findViewById(R.id.tv_case_name);
            tv_task_name = itemView.findViewById(R.id.tv_task_name);

        }
    }
}
