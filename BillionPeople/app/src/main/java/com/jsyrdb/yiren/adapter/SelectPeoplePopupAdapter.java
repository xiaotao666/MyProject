package com.jsyrdb.yiren.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.model.Approval;

import java.util.List;

public class SelectPeoplePopupAdapter extends BaseAdapter {

    private Context context;
    private List<Approval.ResultBean> list;
    private LayoutInflater inflater;

    public SelectPeoplePopupAdapter (Context context,List<Approval.ResultBean> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
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
            convertView = inflater.inflate(R.layout.item_people_popup,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_name.setText(list.get(position).getName());
        return convertView;
    }

    class ViewHolder{
        private TextView tv_name;
        public ViewHolder(View view) {
            tv_name = view.findViewById(R.id.tv_name);
        }
    }
}
