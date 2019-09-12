package com.jsyrdb.yiren.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.model.StaffCommunication;

import java.util.List;

public class StaffCommunicationAdapter extends BaseAdapter {

    public Context context;
    public List<StaffCommunication.ResultBean> list;
    public LayoutInflater inflater;

    public StaffCommunicationAdapter(Context context,List<StaffCommunication.ResultBean> list) {
        this.context = context;
        this.list = list;
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
            convertView = inflater.inflate(R.layout.item_staff_communication,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_name.setText(list.get(position).getGroup_name());
        viewHolder.tv_unread_num.setText(list.get(position).getUnread_count()+"");
        return convertView;
    }

    class ViewHolder{
        private TextView tv_name,tv_unread_num;
        private ImageView iv_head;

        public ViewHolder(View view) {
            tv_name = view.findViewById(R.id.tv_name);
            tv_unread_num = view.findViewById(R.id.tv_unread_num);
            iv_head = view.findViewById(R.id.iv_head);
        }
    }
}
