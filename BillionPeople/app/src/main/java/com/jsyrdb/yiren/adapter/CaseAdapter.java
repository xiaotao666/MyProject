package com.jsyrdb.yiren.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jsyrdb.yiren.activity.cases.SetupPeopleActivity;
import com.jsyrdb.yiren.utils.DateUtils;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.cases.BusinessActivity;
import com.jsyrdb.yiren.model.Case;

import java.util.List;

public class CaseAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Case.ResultBean> list;

    public CaseAdapter(Context context,List<Case.ResultBean> list) {
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
            convertView = inflater.inflate(R.layout.item_case,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ViewHolder finalViewHolder = viewHolder;// listView的itemView

        String creteDate = DateUtils.getDate2String(Long.valueOf(list.get(position).getCreat_timestamp()),"yyyy-MM-dd HH:mm");

        String creteDate1 = DateUtils.getDate2String(Long.valueOf(list.get(position).getCreat_timestamp()),"yyyy-MM-dd HH:mm:sss");

        viewHolder.tv_company.setText(list.get(position).getName());
        viewHolder.tv_num.setText("已进行" + DateUtils.dateDiff(creteDate1));
        viewHolder.tv_state.setText(list.get(position).getCurrent_process_type_name());
        viewHolder.tv_time.setText(creteDate);
        //viewHolder.tv_money.setText(list.get(position).getMoney() + "");

        viewHolder.btn_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalViewHolder.swipeLayout.quickClose();// 关闭侧滑菜单：需要将itemView强转，然后调用quickClose()方法
                Intent intent = new Intent(context, SetupPeopleActivity.class);
                intent.putExtra("step",1);
                intent.putExtra("_id",list.get(position).get_id());
                context.startActivity(intent);
            }
        });

        viewHolder.ll_contentItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BusinessActivity.class);
                intent.putExtra("caseid",list.get(position).get_id());
                intent.putExtra("name",list.get(position).getName());
                intent.putExtra("status",list.get(position).getStatus());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder{
        private TextView tv_company,tv_num,tv_state,tv_time,tv_money;
        private LinearLayout ll_contentItem;
        private Button btn_setup;
        private SwipeMenuLayout swipeLayout;
//      private Button btn_sign,btn_toTop,btn_delete;


        public ViewHolder(View view) {
            tv_company = view.findViewById(R.id.tv_company);
            tv_num = view.findViewById(R.id.tv_num);
            tv_state = view.findViewById(R.id.tv_state);
            tv_time = view.findViewById(R.id.tv_time);
            tv_money = view.findViewById(R.id.tv_money);
            ll_contentItem = view.findViewById(R.id.ll_contentItem);
            btn_setup = view.findViewById(R.id.btn_setup);
            swipeLayout = view.findViewById(R.id.swipeLayout);
//            btn_toTop = view.findViewById(R.id.btn_toTop);
//            btn_delete = view.findViewById(R.id.btn_delete);

        }
    }
}
