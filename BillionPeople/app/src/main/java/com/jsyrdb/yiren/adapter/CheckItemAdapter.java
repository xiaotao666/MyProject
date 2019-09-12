package com.jsyrdb.yiren.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.model.Attribute;
import com.jsyrdb.yiren.model.CheckItem;

import java.util.HashMap;
import java.util.List;

public class CheckItemAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Attribute.ResultBean> list;
    private boolean isAllItemEnable = false;

    // 用来控制CheckBox的选中状况
    private static HashMap<Integer, Boolean> isSelected;

    public CheckItemAdapter(Context context,List<Attribute.ResultBean> list,HashMap<Integer,Boolean> isSelected) {
        this.context = context;
        this.list = list;
        CheckItemAdapter.isSelected = isSelected;
        inflater = LayoutInflater.from(context);
        //initData();
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
            convertView = inflater.inflate(R.layout.item_check,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_check.setText(list.get(position).getName());
        if (getIsSelected().get(position) != null) {
            viewHolder.cb_check.setChecked(getIsSelected().get(position));
        }
        //viewHolder.cb_check.setEnabled(false);
        // 控制是否置灰
        if (!isAllItemEnable) {
            viewHolder.cb_check.setEnabled(false);
        } else {
            viewHolder.cb_check.setEnabled(true);
        }
        viewHolder.cb_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected.get(position) != null) {
                    if (isSelected.get(position)) {
                        isSelected.put(position, false);
                        setIsSelected(isSelected);
                    } else {
                        isSelected.put(position, true);
                        setIsSelected(isSelected);
                    }
                    notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        return isAllItemEnable;
    }

    //设置listview每项都不可点击
    public void disableAllItemChoser() {
        isAllItemEnable = false;
        notifyDataSetChanged();
    }
    public void enableItemChoser() {
        isAllItemEnable = true;
        notifyDataSetChanged();
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        CheckItemAdapter.isSelected = isSelected;
    }

    class ViewHolder{
        private LinearLayout ll_check;
        private CheckBox cb_check;
        private TextView tv_check;

        public ViewHolder(View view) {
            ll_check = view.findViewById(R.id.ll_check);
            cb_check = view.findViewById(R.id.cb_check);
            tv_check = view.findViewById(R.id.tv_check);
        }
    }
}
