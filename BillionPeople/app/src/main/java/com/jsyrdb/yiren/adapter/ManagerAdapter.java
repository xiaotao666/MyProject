package com.jsyrdb.yiren.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.glide.GlideUtils;
import com.jsyrdb.yiren.model.Manager;

import java.util.HashMap;
import java.util.List;

public class ManagerAdapter extends BaseAdapter {
    private Context context;
    private List<Manager> list;
    private LayoutInflater inflater;

    // 用来控制CheckBox的选中状况
    private static HashMap<Integer, Boolean> isSelected;

    public ManagerAdapter(Context context,List<Manager> list,HashMap<Integer,Boolean> isSelected) {
        this.context = context;
        this.list = list;
        ManagerAdapter.isSelected = isSelected;
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
            convertView = inflater.inflate(R.layout.item_manager,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        GlideUtils.loadPic(context,"",viewHolder.iv_head,R.drawable.logo,R.drawable.logo);
        viewHolder.tv_name.setText(list.get(position).getName());
        viewHolder.tv_phone.setText(list.get(position).getPhone());
        viewHolder.cb_manager.setChecked(getIsSelected().get(position));

        viewHolder.rl_choose.setOnClickListener(new View.OnClickListener() {
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
        ManagerAdapter.isSelected = isSelected;
    }


    class ViewHolder{
        private RelativeLayout rl_choose;
        private ImageView iv_head;
        private TextView tv_name,tv_phone;
        private CheckBox cb_manager;

        public ViewHolder(View view) {
            rl_choose = view.findViewById(R.id.rl_choose);
            iv_head = view.findViewById(R.id.iv_head);
            tv_name = view.findViewById(R.id.tv_name);
            tv_phone = view.findViewById(R.id.tv_phone);
            cb_manager = view.findViewById(R.id.cb_manager);
        }
    }
}
