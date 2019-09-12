package com.jsyrdb.yiren.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.model.DelayTime;

import java.util.List;

public class ChooseDelayTimeAdapter extends BaseAdapter {
    private Context context;
    private List<DelayTime> list;
    private LayoutInflater inflater;

    public ChooseDelayTimeAdapter(Context context,List<DelayTime> list) {
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
            convertView = inflater.inflate(R.layout.item_delay,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_time.setText(list.get(position).getTime());
        if (list.get(position).isCheckd()) {
            viewHolder.cb_time.setChecked(true);
            viewHolder.tv_time.setActivated(true);
        } else {
            viewHolder.cb_time.setChecked(false);
            viewHolder.tv_time.setActivated(false);
        }
        return convertView;
    }

    class ViewHolder{
        private RelativeLayout rl_time;
        private TextView tv_time;
        private CheckBox cb_time;

        public ViewHolder(View view) {
            rl_time = view.findViewById(R.id.rl_time);
            tv_time = view.findViewById(R.id.tv_time);
            cb_time = view.findViewById(R.id.cb_time);
        }
    }
}
