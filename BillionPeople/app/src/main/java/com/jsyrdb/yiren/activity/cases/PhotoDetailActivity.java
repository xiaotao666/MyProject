package com.jsyrdb.yiren.activity.cases;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.glide.GlideUtils;
import com.jsyrdb.yiren.utils.DownLoadUtils;
import com.jsyrdb.yiren.utils.StatusBarUtils;

import java.io.File;

public class PhotoDetailActivity extends Activity {

    private ImageView imageView;

    private String file_id;
    private String file_name;

    private int fromWhich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_photo_detail);

        if (!StatusBarUtils.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtils.setStatusBarColor(this, 0x55000000);
        }

        file_id = getIntent().getStringExtra("file_id");
        file_name = getIntent().getStringExtra("file_name");
        fromWhich = getIntent().getIntExtra("fromWhich",0);

        initView();

        String path = Environment.getExternalStorageDirectory() + "/yiren/file/"+file_id+"_"+file_name;
        File file = new File(path);
        if (file.exists()) {
            GlideUtils.loadLocalFile(this,new File(path),imageView);
        } else {
            if (fromWhich == 1) {
                downloadFile("http://api.jsyrdb.com:84/file/download/"+file_id);
            } else if (fromWhich == 2) {
                downloadFile("http://api.jsyrdb.com:84/file_user/download/"+file_id);
            }
        }
    }

    public void initView() {
        imageView = findViewById(R.id.imageView);

    }

    public void downloadFile(String url) {
        ProgressDialog progressDialog = new ProgressDialog(PhotoDetailActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("正在下载");
        progressDialog.setMessage("请稍后...");
        progressDialog.setProgress(0);
        progressDialog.setMax(100);
        progressDialog.show();
        progressDialog.setCancelable(false);
        DownLoadUtils.get().download(url, Environment.getExternalStorageDirectory() + "/yiren/file/", file_id+"_"+file_name, new DownLoadUtils.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(File file) {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                //下载完成进行相关逻辑操作
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        GlideUtils.loadLocalFile(PhotoDetailActivity.this,new File(file.toString()),imageView);
                        //T(file.toString());
                    }
                });


            }

            @Override
            public void onDownloading(int progress) {
                progressDialog.setProgress(progress);
            }

            @Override
            public void onDownloadFailed(Exception e) {
                //下载异常进行相关提示操作
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PhotoDetailActivity.this,"文件下载异常,请确保网络连接正常",Toast.LENGTH_SHORT).show();
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                });

            }
        });
    }

}
