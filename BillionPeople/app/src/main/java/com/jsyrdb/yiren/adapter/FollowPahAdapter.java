package com.jsyrdb.yiren.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.glide.GlideUtils;
import com.jsyrdb.yiren.model.FollowPath;
import com.jsyrdb.yiren.utils.DateUtils;

import org.w3c.dom.Text;

import java.util.List;

public class FollowPahAdapter extends BaseAdapter {
    private List<FollowPath.ResultBean> list;
    private Context context;
    private LayoutInflater inflater;

    public FollowPahAdapter(Context context,List<FollowPath.ResultBean> list) {
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
            convertView = inflater.inflate(R.layout.item_follow_path,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String creteDate = DateUtils.getDate2String(Long.valueOf(list.get(position).getCreat_timestamp()),"yyyy-MM-dd");
        viewHolder.tv_time.setText(creteDate);
        viewHolder.tv_follow_path.setText(list.get(position).getName());

        return convertView;
    }

    class ViewHolder{
        private TextView tv_follow_path,tv_time;
        public ViewHolder(View view) {
            tv_follow_path = view.findViewById(R.id.tv_follow_path);
            tv_time = view.findViewById(R.id.tv_time);
        }
    }
}
