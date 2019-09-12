package com.jsyrdb.yiren.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.cases.FileListActivity;
import com.jsyrdb.yiren.activity.cases.PhotoDetailActivity;
import com.jsyrdb.yiren.activity.cases.PreviewFileActivity;
import com.jsyrdb.yiren.model.CommentList;
import com.jsyrdb.yiren.model.FileList;
import com.jsyrdb.yiren.utils.ClearEditText;
import com.jsyrdb.yiren.utils.CommonData;
import com.jsyrdb.yiren.utils.CommonUtils;
import com.jsyrdb.yiren.utils.DateUtils;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FileAdapter extends BaseAdapter {
    private Context context;
    private List<FileList.ResultBean> list;
    private LayoutInflater inflater;

    public FileAdapter(Context context,List<FileList.ResultBean> list) {
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
            convertView = inflater.inflate(R.layout.item_file,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ViewHolder finalViewHolder = viewHolder;// listView的itemView

        String creteDate = DateUtils.getDate2String(Long.valueOf(list.get(position).getCreat_timestamp()),"yyyy-MM-dd");
        viewHolder.tv_file.setText(list.get(position).getFilename());
        viewHolder.tv_time.setText(creteDate);
        if (!TextUtils.isEmpty(list.get(position).getDeleted())) {
            if (list.get(position).getDeleted().equals("deleted")) {
                viewHolder.iv_sign.setVisibility(View.VISIBLE);
            } else {
                viewHolder.iv_sign.setVisibility(View.GONE);
            }
        } else {
            viewHolder.iv_sign.setVisibility(View.GONE);
        }

        viewHolder.btn_rename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalViewHolder.swipeLayout.quickClose();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.dialog_rename,null);
                EditText et_name = view.findViewById(R.id.et_name);
                TextView tv_confirm = view.findViewById(R.id.tv_confirm);
                TextView tv_cancel = view.findViewById(R.id.tv_cancel);
                TextView tv_format = view.findViewById(R.id.tv_format);
                final Dialog dialog = builder.create();
                dialog.show();

                dialog.getWindow().setContentView(view);
                dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

                String fileName = list.get(position).getFilename();
                String name = fileName.substring(0,fileName.lastIndexOf("."));
                String format = fileName.substring(fileName.lastIndexOf("."));
                et_name.setText(name);
                tv_format.setText(format);
                et_name.setSelection(et_name.length());


                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                });

                tv_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!TextUtils.isEmpty(et_name.getText().toString())) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            renameFile(position,et_name.getText().toString()+format);
                        } else {
                            Toast.makeText(context,"名称不能为空",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalViewHolder.swipeLayout.quickClose();

                deleteFile(position);
            }
        });

        viewHolder.btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalViewHolder.swipeLayout.quickClose();

                removeFile(position);
            }
        });

        viewHolder.ll_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = list.get(position).getFilename();
                String format = filename.substring(filename.lastIndexOf("."));

                if (format.equalsIgnoreCase(".jpg") || format.equalsIgnoreCase(".png")
                        || format.equalsIgnoreCase(".jpeg") || format.equalsIgnoreCase(".gif")) {

                    Intent intent = new Intent(context, PhotoDetailActivity.class);
                    intent.putExtra("file_id",list.get(position).get_id());
                    intent.putExtra("file_name",list.get(position).getFilename());
                    intent.putExtra("fromWhich",1);
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, PreviewFileActivity.class);
                    intent.putExtra("file_id",list.get(position).get_id());
                    intent.putExtra("file_name",list.get(position).getFilename());
                    intent.putExtra("fromWhich",1);
                    context.startActivity(intent);
                }


            }
        });

        return convertView;
    }

    //改名
    public void renameFile(int position,String newName) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fileid",list.get(position).get_id());
        jsonObject.put("filename",newName);
        jsonObject.put("oldname",list.get(position).getFilename());

        MediaType json = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(context);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_job_file/rename_job_file")
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("rename",result);
                        JSONObject jsonObject1 = JSON.parseObject(result);
                        if (jsonObject1.getString("error") == null) {
                            Toast.makeText(context,"修改成功",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //删除(可恢复)
    public void deleteFile(int position) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fileid",list.get(position).get_id());

        MediaType json = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(context);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_job_file/delete_job_file")
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("delete",result);
                        JSONObject jsonObject1 = JSON.parseObject(result);
                        if (jsonObject1.getString("error") == null) {
                            Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //彻底删除
    public void removeFile(int position) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fileid",list.get(position).get_id());

        MediaType json = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient okHttpClient = CommonUtils.getMyOKHttpClient(context);
        RequestBody requestBody = RequestBody.create(json,String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url(CommonData.server_url + "action_job_file/remove_job_file")
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("remove",result);
                        JSONObject jsonObject1 = JSON.parseObject(result);
                        if (jsonObject1.getString("error") == null) {
                            Toast.makeText(context,"彻底删除成功",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    class ViewHolder{
        private TextView tv_file,tv_time;
        private Button btn_rename,btn_delete,btn_remove;
        private SwipeMenuLayout swipeLayout;
        private ImageView iv_sign;
        private RelativeLayout ll_parent;
        public ViewHolder(View view) {
            tv_file = view.findViewById(R.id.tv_file);
            tv_time = view.findViewById(R.id.tv_time);
            btn_rename = view.findViewById(R.id.btn_rename);
            btn_delete = view.findViewById(R.id.btn_delete);
            btn_remove = view.findViewById(R.id.btn_remove);
            swipeLayout = view.findViewById(R.id.swipeLayout);
            iv_sign = view.findViewById(R.id.iv_sign);
            ll_parent = view.findViewById(R.id.ll_parent);
        }
    }
}
