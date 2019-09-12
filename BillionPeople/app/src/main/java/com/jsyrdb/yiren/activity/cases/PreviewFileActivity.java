package com.jsyrdb.yiren.activity.cases;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.jsyrdb.yiren.R;
import com.jsyrdb.yiren.base.BaseActivity;
import com.jsyrdb.yiren.utils.DownLoadUtils;
import com.jsyrdb.yiren.utils.StatusBarUtils;
import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;

public class PreviewFileActivity extends Activity {

    private static final String TAG = "PreviewFileActivity";
    private TbsReaderView tbsReaderView;
    private String file_id;
    private String file_name;

    private int fromWhich;//判断是从哪跳过来的(1.案件详情里的文件 2.交流详情的文件)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_preview_file);

        StatusBarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        if (!StatusBarUtils.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtils.setStatusBarColor(this, 0x55000000);
        }

        tbsReaderView = new TbsReaderView(this, new TbsReaderView.ReaderCallback() {
            @Override
            public void onCallBackAction(Integer integer, Object o, Object o1) {
                Log.d(TAG, "onCallBackAction: " + integer);
            }
        });

        file_id = getIntent().getStringExtra("file_id");
        file_name = getIntent().getStringExtra("file_name");
        fromWhich = getIntent().getIntExtra("fromWhich",0);

        String path = Environment.getExternalStorageDirectory() + "/yiren/file/"+file_id+"_"+file_name;
        File file = new File(path);
        if (file.exists()) {
            previewFile(path);
        } else {
            if (fromWhich == 1) {
                downloadFile("http://api.jsyrdb.com:84/file/download/"+file_id);
            } else if (fromWhich == 2) {
                downloadFile("http://api.jsyrdb.com:84/file_user/download/"+file_id);
            }

        }
    }

    public void previewFile(String filePath) {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        viewGroup.addView(tbsReaderView);
        if (!TextUtils.isEmpty(filePath)) {
            String tbsReaderTemp = Environment.getExternalStorageDirectory() + "/" + "TbsReaderTemp";
            File file = new File(tbsReaderTemp);
            if (!file.exists()) {
                Log.d(TAG, "准备创建/storage/emulated/0/TbsReaderTemp！！");
                boolean mkdir = file.mkdir();
                if (!mkdir) {
                    Log.e(TAG, "创建/storage/emulated/0/TbsReaderTemp失败！！！！！");
                }
            }
            boolean bool = tbsReaderView.preOpen(getFileType(filePath),false);
            if (bool) {
                Bundle bundle = new Bundle();
                bundle.putString("filePath", filePath);
                bundle.putString("tempPath", Environment.getExternalStorageDirectory() + "/" + "TbsReaderTemp");
                tbsReaderView.openFile(bundle);
            }
        }
    }

    public void downloadFile(String url) {
        ProgressDialog progressDialog = new ProgressDialog(PreviewFileActivity.this);
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
                        previewFile(file.toString());
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
                        Toast.makeText(PreviewFileActivity.this,"文件下载异常,请确保网络连接正常",Toast.LENGTH_SHORT).show();
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                });

            }
        });
    }

    /***
     * 获取文件类型
     *
     * @param paramString
     * @return
     */
    private String getFileType(String paramString) {
        String str = "";

        if (TextUtils.isEmpty(paramString)) {
            Log.d(TAG, "paramString---->null");
            return str;
        }
        Log.d(TAG, "paramString:" + paramString);
        int i = paramString.lastIndexOf('.');
        if (i <= -1) {
            Log.d(TAG, "i <= -1");
            return str;
        }


        str = paramString.substring(i + 1);
        Log.d(TAG, "paramString.substring(i + 1)------>" + str);
        return str;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在停止展示时记得要调用onStop方法，否则再次打开文件会显示一直加载中
        if (tbsReaderView != null) {
            tbsReaderView.onStop();
        }
    }
}
