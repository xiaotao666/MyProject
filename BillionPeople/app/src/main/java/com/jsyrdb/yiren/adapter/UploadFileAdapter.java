package com.jsyrdb.yiren.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.model.RemoteFile;

import java.util.HashMap;
import java.util.List;

public class UploadFileAdapter extends BaseAdapter {
    public Context context;
    public List<RemoteFile> list;
    public LayoutInflater inflater;

    // 用来控制CheckBox的选中状况
    private static HashMap<Integer, Boolean> isSelected;

    public UploadFileAdapter(Context context,List<RemoteFile> list,HashMap<Integer,Boolean> isSelected) {
        this.context = context;
        this.list = list;
        UploadFileAdapter.isSelected = isSelected;
        inflater = LayoutInflater.from(context);
        initData();
    }

    // 初始化isSelected的数据
    private void initData() {
        for (int i = 0; i < list.size(); i++) {
            getIsSelected().put(i, false);
        }
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
            convertView = inflater.inflate(R.layout.item_upload_file,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_fileName.setText(list.get(position).getName());
        viewHolder.tv_time.setText(list.get(position).getTime());
        viewHolder.cb_file.setChecked(getIsSelected().get(position));
        viewHolder.cb_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (isSelected.get(position)) {
                        isSelected.put(position, false);
                        setIsSelected(isSelected);
                    } else {
                        isSelected.put(position, true);
                        setIsSelected(isSelected);
                    }
                    notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        UploadFileAdapter.isSelected = isSelected;
    }

    class ViewHolder{
        private ImageView iv_img;
        private TextView tv_fileName,tv_time;
        private CheckBox cb_file;

        public ViewHolder(View view) {
            iv_img = view.findViewById(R.id.iv_img);
            tv_fileName = view.findViewById(R.id.tv_fileName);
            tv_time = view.findViewById(R.id.tv_time);
            cb_file = view.findViewById(R.id.cb_file);
        }
    }
}
