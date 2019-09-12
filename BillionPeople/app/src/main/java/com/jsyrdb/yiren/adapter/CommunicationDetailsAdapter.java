package com.jsyrdb.yiren.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.cases.PhotoDetailActivity;
import com.jsyrdb.yiren.activity.cases.PreviewFileActivity;
import com.jsyrdb.yiren.model.CommunicationDetails;
import com.jsyrdb.yiren.model.LoginResult;
import com.jsyrdb.yiren.utils.CommonUtils;

import java.util.List;

public class CommunicationDetailsAdapter extends BaseAdapter {
    public Context context;
    public List<CommunicationDetails.ResultBean> list;
    public LayoutInflater inflater;
    private String user_id = "";
    private String user_name = "";

    public CommunicationDetailsAdapter(Context context,List<CommunicationDetails.ResultBean> list) {
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
        CommunicationDetails.ResultBean resultBean = list.get(position);
        LoginResult.ResultBean.UserBean userBean = CommonUtils.getUserBean(context);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_left_msg,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (userBean != null) {
            user_id = userBean.get_id();
            user_name = userBean.getUsername();
        }
        if (resultBean.getFrom_user_id() != null) {
            if (resultBean.getFrom_user_id().equals(user_id)) {
                viewHolder.rl_myself.setVisibility(View.VISIBLE);
                viewHolder.rl_other.setVisibility(View.GONE);
                if (list.get(position).getContent_type().equals("text")) {
                    viewHolder.tv_my_text.setText(String.valueOf(list.get(position).getContent_info()));
                    viewHolder.tv_my_text.setVisibility(View.VISIBLE);
                    viewHolder.ll_my_file.setVisibility(View.GONE);
                } else if (list.get(position).getContent_type().equals("file")){
                    JSONObject jsonObject = (JSONObject) list.get(position).getContent_info();
                    viewHolder.tv_my_fileName.setText(jsonObject.getString("filename"));
                    viewHolder.tv_my_text.setVisibility(View.GONE);
                    viewHolder.ll_my_file.setVisibility(View.VISIBLE);
                }
            } else {
                viewHolder.rl_myself.setVisibility(View.GONE);
                viewHolder.rl_other.setVisibility(View.VISIBLE);
                viewHolder.tv_name.setText(list.get(position).getFrom_user_name());
                if (list.get(position).getContent_type().equals("text")) {
                    viewHolder.tv_text.setText(String.valueOf(list.get(position).getContent_info()));
                    viewHolder.tv_text.setVisibility(View.VISIBLE);
                    viewHolder.ll_file.setVisibility(View.GONE);
                } else if (list.get(position).getContent_type().equals("file")){
                    JSONObject jsonObject = (JSONObject) list.get(position).getContent_info();
                    viewHolder.tv_fileName.setText(jsonObject.getString("filename"));
                    viewHolder.tv_text.setVisibility(View.GONE);
                    viewHolder.ll_file.setVisibility(View.VISIBLE);
                }
            }

        } else {
            viewHolder.rl_myself.setVisibility(View.GONE);
            viewHolder.rl_other.setVisibility(View.GONE);
        }


       viewHolder.ll_file.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               JSONObject jsonObject = (JSONObject) list.get(position).getContent_info();
               String file_name = jsonObject.getString("filename");
               String file_id = jsonObject.getString("_id");
               String format = file_name.substring(file_name.lastIndexOf("."));
               if (format.equalsIgnoreCase(".jpg") || format.equalsIgnoreCase(".png")
                       || format.equalsIgnoreCase(".jpeg") || format.equalsIgnoreCase(".gif")) {

                   Intent intent = new Intent(context, PhotoDetailActivity.class);
                   intent.putExtra("file_id",file_id);
                   intent.putExtra("file_name",file_name);
                   intent.putExtra("fromWhich",2);
                   context.startActivity(intent);
               } else {
                   Intent intent = new Intent(context, PreviewFileActivity.class);
                   intent.putExtra("file_id",file_id);
                   intent.putExtra("file_name",file_name);
                   intent.putExtra("fromWhich",2);
                   context.startActivity(intent);
               }

           }
       });

        viewHolder.ll_my_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = (JSONObject) list.get(position).getContent_info();
                String file_name = jsonObject.getString("filename");
                String file_id = jsonObject.getString("_id");
                String format = file_name.substring(file_name.lastIndexOf("."));
                Log.e("format",format);
                if (format.equalsIgnoreCase(".jpg") || format.equalsIgnoreCase(".png")
                        || format.equalsIgnoreCase(".jpeg") || format.equalsIgnoreCase(".gif")) {

                    Intent intent = new Intent(context, PhotoDetailActivity.class);
                    intent.putExtra("file_id",file_id);
                    intent.putExtra("file_name",file_name);
                    intent.putExtra("fromWhich",2);
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, PreviewFileActivity.class);
                    intent.putExtra("file_id",jsonObject.getString("_id"));
                    intent.putExtra("file_name",jsonObject.getString("filename"));
                    intent.putExtra("fromWhich",2);
                    context.startActivity(intent);
                }

            }
        });

        return convertView;
    }

    class ViewHolder{
        private ImageView iv_head;
        private TextView tv_name,tv_text,tv_my_text,tv_fileName,tv_my_fileName;
        private RelativeLayout rl_other,rl_myself;
        private LinearLayout ll_file,ll_my_file;

        public ViewHolder(View view) {
            iv_head = view.findViewById(R.id.iv_head);

            tv_name = view.findViewById(R.id.tv_name);

            tv_text = view.findViewById(R.id.tv_text);
            tv_my_text = view.findViewById(R.id.tv_my_text);
            tv_fileName = view.findViewById(R.id.tv_fileName);
            tv_my_fileName = view.findViewById(R.id.tv_my_fileName);
            ll_file = view.findViewById(R.id.ll_file);
            ll_my_file = view.findViewById(R.id.ll_my_file);
            rl_other = view.findViewById(R.id.rl_other);
            rl_myself = view.findViewById(R.id.rl_myself);
        }
    }
}
