package com.jsyrdb.yiren.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.cases.BusinessActivity;
import com.jsyrdb.yiren.activity.cases.CommunicationDetailsActivity;
import com.jsyrdb.yiren.model.CaseCommunication;
import com.jsyrdb.yiren.utils.DateUtils;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

public class CaseCommunicationAdapter extends BaseAdapter {
    public Context context;
    public List<CaseCommunication.ResultBean> list;
    public LayoutInflater inflater;

    public CaseCommunicationAdapter(Context context,List<CaseCommunication.ResultBean> list) {
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
            convertView = inflater.inflate(R.layout.item_case_communication,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //String creteDate = DateUtils.getDate2String(Long.valueOf(list.get(position).getCreat_timestamp()),"yyyy-MM-dd HH:mm");
        viewHolder.tv_unread_num.setText(list.get(position).getUnread_count() + "");
        viewHolder.tv_name.setText(list.get(position).getGroup_name());


        return convertView;
    }

    class ViewHolder{
        private TextView tv_name,tv_unread_num,tv_time;
        private LinearLayout ll_contentItem;

        public ViewHolder(View view) {
            tv_name = view.findViewById(R.id.tv_name);
            tv_unread_num = view.findViewById(R.id.tv_unread_num);
            ll_contentItem = view.findViewById(R.id.ll_contentItem);
            tv_time = view.findViewById(R.id.tv_time);

        }
    }
}
