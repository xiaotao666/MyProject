package com.jsyrdb.yiren.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.model.CaseSourceLinkMan;

import java.util.List;

public class CaseSourceLinkAdapter extends BaseAdapter {
    private Context context;
    private List<CaseSourceLinkMan.ResultBean> list;
    private LayoutInflater inflater;

    public CaseSourceLinkAdapter(Context context,List<CaseSourceLinkMan.ResultBean> list) {
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
            convertView = inflater.inflate(R.layout.item_link,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_link.setText(list.get(position).getName());
        return convertView;
    }

    class ViewHolder{
        private TextView tv_link;

        public ViewHolder(View view) {
            tv_link = view.findViewById(R.id.tv_link);
        }
    }
}
