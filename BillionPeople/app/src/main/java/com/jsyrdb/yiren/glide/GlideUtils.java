package com.jsyrdb.yiren.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.File;

public class GlideUtils {

    /**
     * Glide加载图片(有errorRes)
     * @param context
     * @param url
     * @param imageView
     * @param placeholderRes
     * @param errorRes
     */
    public static void loadPic(Context context, String url, ImageView imageView, int placeholderRes, int errorRes){
        GlideApp.with(context)
                .load(url)
                .placeholder(placeholderRes)
                .error(errorRes)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);
    }

    //加载本地图片
    public static void loadLocalFile(Context context, File file,ImageView imageView) {
        GlideApp.with(context).load(Uri.fromFile(file)).into(imageView);
    }

    //加载圆形图片
    public static void loadRoundPic(Context context,String url,ImageView imageView) {
        GlideApp.with(context).load(url).apply(RequestOptions.circleCropTransform()).into(imageView);
    }

    //加载圆角图片
    public static void loadRoundCornerPic(Context context,String url,ImageView imageView) {
        //设置图片圆角角度
        RoundedCorners roundedCorners= new RoundedCorners(6);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        GlideApp.with(context).load(url).apply(options).into(imageView);
    }
}
