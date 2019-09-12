package com.jsyrdb.yiren.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.model.ScheduleCase;
import com.jsyrdb.yiren.utils.DateUtils;

import java.util.List;

public class ScheduleTaskAdapter extends BaseAdapter {
    public Context context;
    public List<ScheduleCase.ResultBean.JobListBean> list;
    public LayoutInflater inflater;

    public ScheduleTaskAdapter(Context context,List<ScheduleCase.ResultBean.JobListBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (list != null) {
            return  list.size();
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
            convertView = inflater.inflate(R.layout.item_schedule_task,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String creteDate = DateUtils.getDate2String(Long.valueOf(list.get(position).getCreat_timestamp()),"yyyy-MM-dd HH:mm");
        viewHolder.tv_case_name.setText(list.get(position).getCasename());
        viewHolder.tv_task_name.setText(list.get(position).getName());
        viewHolder.tv_time.setText(creteDate);
        return convertView;
    }

    class ViewHolder{
        private TextView tv_case_name,tv_task_name,tv_time;

        public ViewHolder(View view) {
            tv_case_name = view.findViewById(R.id.tv_case_name);
            tv_task_name = view.findViewById(R.id.tv_task_name);
            tv_time = view.findViewById(R.id.tv_time);
        }
    }
}
