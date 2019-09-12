package com.jsyrdb.yiren.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.model.License;

import java.util.List;

public class LicenseAdapter extends BaseAdapter {
    private Context context;
    private List<License> list;
    private LayoutInflater inflater;

    public LicenseAdapter(Context context,List<License> list) {
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
            convertView = inflater.inflate(R.layout.item_license,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_imgName.setText(list.get(position).getName());
        viewHolder.tv_time.setText(list.get(position).getTime());
        return convertView;
    }

    class ViewHolder{
        private ImageView iv_img;
        private TextView tv_imgName,tv_time;

        public ViewHolder(View view) {
            iv_img = view.findViewById(R.id.iv_img);
            tv_imgName = view.findViewById(R.id.tv_imgName);
            tv_time = view.findViewById(R.id.tv_time);
        }
    }
}
