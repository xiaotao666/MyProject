package com.jsyrdb.yiren.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.glide.GlideUtils;
import com.jsyrdb.yiren.model.PeopleSetup;

import java.util.List;

public class PeopleSetAdapter extends BaseAdapter {
    private Context context;
    private List<PeopleSetup.ResultBean> list;
    private LayoutInflater inflater;

    public PeopleSetAdapter(Context context,List<PeopleSetup.ResultBean> list) {
        this.context = context;
        this.list = list;
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
            convertView = inflater.inflate(R.layout.item_people_set,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        GlideUtils.loadPic(context,"",viewHolder.iv_head,R.drawable.logo,R.drawable.logo);
        viewHolder.tv_name.setText(list.get(position).getName());
        if (!TextUtils.isEmpty(list.get(position).getMobile())) {
            viewHolder.tv_phone.setVisibility(View.VISIBLE);
            viewHolder.tv_phone.setText(list.get(position).getMobile());
        } else {
            viewHolder.tv_phone.setVisibility(View.GONE);
        }

        return convertView;
    }

    class ViewHolder{
        private ImageView iv_head;
        private TextView tv_name,tv_phone;

        public ViewHolder(View view) {
            iv_head = view.findViewById(R.id.iv_head);
            tv_name = view.findViewById(R.id.tv_name);
            tv_phone = view.findViewById(R.id.tv_phone);
        }
    }
}
