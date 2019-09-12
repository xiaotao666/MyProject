package com.jsyrdb.yiren.activity.cases;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.activity.main.MainNewsActivity;
import com.jsyrdb.yiren.activity.main.MainSearchActivity;
import com.jsyrdb.yiren.adapter.ManagerAdapter;
import com.jsyrdb.yiren.adapter.UploadFileAdapter;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.model.RemoteFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectRemoteFileActivity extends BaseActivity {

    //标题栏
//    private TextView tv_title;
//    private ImageView iv_message,iv_search;
//    private LinearLayout ll_back;

    private ListView lv_file;
    private List<RemoteFile> list;
    private List<RemoteFile> checkList = new ArrayList<>();
    private UploadFileAdapter adapter;
    private HashMap<Integer, Boolean> isSelected;

    private Button btn_commit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_remote_file);

        setStatusBarColor(getResources().getColor(R.color.green_item));
        setStatusBarDarkTheme(false);

        initView();
        setTitle("远程文件");
        initData();
    }

    public void initView() {
//        tv_title = findViewById(R.id.tv_title);
//        tv_title.setText("远程文件");
//        iv_message = findViewById(R.id.iv_message);
//        iv_search = findViewById(R.id.iv_search);
//        ll_back = findViewById(R.id.ll_back);
        lv_file = findViewById(R.id.lv_file);
        btn_commit = findViewById(R.id.btn_commit);

        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCheckList();
            }
        });
//        ll_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        iv_message.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SelectRemoteFileActivity.this, MainNewsActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        iv_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(SelectRemoteFileActivity.this, MainSearchActivity.class);
//                startActivity(intent1);
//            }
//        });
    }

    public void initData() {
        list = new ArrayList<>();
        for (int i = 0;i < 20;i++) {
            RemoteFile remoteFile = new RemoteFile();
            remoteFile.setName("文件.doc");
            remoteFile.setTime("2019-05-20");
            remoteFile.setUrl("/storage/emulated/0/Tencent/MicroMsg/Download/dfb1df1f6cfd60e93c737228f291fddd.pdf");
            list.add(remoteFile);
        }

        isSelected = new HashMap<>();
        adapter = new UploadFileAdapter(this,list,isSelected);
        lv_file.setAdapter(adapter);

        lv_file.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(SelectRemoteFileActivity.this,PreviewFileActivity.class);
//                intent.putExtra("path",list.get(position).getUrl());
//                startActivity(intent);
            }
        });
    }

    public void saveCheckList() {
        checkList.clear();
        isSelected = UploadFileAdapter.getIsSelected();
        if (isSelected != null && isSelected.size() > 0) {
            for (int i = 0; i < isSelected.size(); i++) {
                if (isSelected.get(i).equals(true)) {
                    checkList.add(list.get(i));
                }
            }
            Log.e("check", checkList.size() + "");
        }
    }
}
