package com.jsyrdb.yiren.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.add.CustomerSetupActivity;
import com.jsyrdb.yiren.activity.cases.SelectLinkMainActivity;
import com.jsyrdb.yiren.glide.GlideUtils;
import com.jsyrdb.yiren.model.Customer;

import java.util.List;

public class CustomerAdapter extends BaseAdapter {
    private List<Customer.ResultBean> list;
    private Context context;
    private LayoutInflater inflater;

    public CustomerAdapter(Context context,List<Customer.ResultBean> list) {
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
            convertView = inflater.inflate(R.layout.item_customer,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_customer.setText(list.get(position).getName());
        if (!TextUtils.isEmpty(list.get(position).getLink_man())) {
            viewHolder.tv_link_man.setText("联系人:  "+list.get(position).getLink_man());
        }

        viewHolder.cb_custom.setChecked(list.get(position).isChecked());

        viewHolder.ll_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0;i < list.size();i++) {
                    list.get(i).setChecked(false);
                }
                list.get(position).setChecked(true);

                notifyDataSetChanged();

            }
        });

        viewHolder.iv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SelectLinkMainActivity.class);
                intent.putExtra("customerid",list.get(position).get_id());
                intent.putExtra("index",position);
                ((Activity)context).startActivityForResult(intent,2);
            }
        });

        return convertView;
    }

    class ViewHolder{
        private TextView tv_customer;
        private TextView tv_link_man;
        private CheckBox cb_custom;
        private ImageView iv_select;
        private LinearLayout ll_parent;

        public ViewHolder(View view) {
            tv_customer = view.findViewById(R.id.tv_customer);
            tv_link_man = view.findViewById(R.id.tv_link_man);
            cb_custom = view.findViewById(R.id.cb_custom);
            iv_select = view.findViewById(R.id.iv_select);
            ll_parent = view.findViewById(R.id.ll_parent);
        }
    }
}
