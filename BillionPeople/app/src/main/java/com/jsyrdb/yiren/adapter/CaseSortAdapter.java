package com.jsyrdb.yiren.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.model.Case;
import com.jsyrdb.yiren.utils.DateUtils;

import java.util.List;

public class CaseSortAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Case.ResultBean> list;

    public CaseSortAdapter(Context context,List<Case.ResultBean> list) {
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
            convertView = inflater.inflate(R.layout.item_case_sort,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        int section = getSectionForPosition(position);
        if (position == getPositionForSection(section)) {
            viewHolder.ll_char_index.setVisibility(View.VISIBLE);
            viewHolder.catalog.setVisibility(View.VISIBLE);
            viewHolder.catalog.setText(list.get(position).getSortLetters());
        } else {
            viewHolder.catalog.setVisibility(View.GONE);
            viewHolder.ll_char_index.setVisibility(View.GONE);
        }

        String creteDate = DateUtils.getDate2String(Long.valueOf(list.get(position).getCreat_timestamp()),"yyyy-MM-dd HH:mm");
        viewHolder.tv_name.setText(list.get(position).getName());
        viewHolder.tv_time.setText(creteDate);
        return convertView;
    }

    public int getSectionForPosition(int position) {
        if (list.get(position).getSortLetters() != null) {
            return list.get(position).getSortLetters().charAt(0);
        }
        return -1;
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {

            String sortStr = list.get(i).getSortLetters();
            if (sortStr != null) {
                if (sortStr.toUpperCase() != null) {
                    char firstChar = sortStr.toUpperCase().charAt(0);
                    if (firstChar == section) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }

    class ViewHolder{
        private LinearLayout ll_char_index;
        private TextView tv_name,catalog,tv_time;

        public ViewHolder(View view) {
            tv_name = view.findViewById(R.id.tv_name);
            catalog = view.findViewById(R.id.catalog);
            ll_char_index = view.findViewById(R.id.ll_char_index);
            tv_time = view.findViewById(R.id.tv_time);

        }
    }
}
