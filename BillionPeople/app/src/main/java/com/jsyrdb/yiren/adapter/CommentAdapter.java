package com.jsyrdb.yiren.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.model.CommentList;
import com.jsyrdb.yiren.utils.DateUtils;

import java.util.List;

public class CommentAdapter extends BaseAdapter {
    private Context context;
    private List<CommentList.ResultBean> list;
    private LayoutInflater inflater;

    public CommentAdapter(Context context,List<CommentList.ResultBean> list) {
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
            convertView = inflater.inflate(R.layout.item_comment,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String creteDate = DateUtils.getDate2String(Long.valueOf(list.get(position).getCreat_timestamp()),"yyyy-MM-dd");
        viewHolder.tv_comment.setText(list.get(position).getValue());
        viewHolder.tv_time.setText(creteDate);

        return convertView;
    }

    class ViewHolder{
        private TextView tv_comment,tv_time;
        public ViewHolder(View view) {
            tv_comment = view.findViewById(R.id.tv_comment);
            tv_time = view.findViewById(R.id.tv_time);

        }
    }
}
