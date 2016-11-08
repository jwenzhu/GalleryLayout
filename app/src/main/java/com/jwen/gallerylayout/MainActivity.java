package com.jwen.gallerylayout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.jwen.gallerylayout.uitl.AsyncTaskUtil;
import com.jwen.gallerylayout.uitl.ImageUtil;
import com.jwen.gallerylayout.widget.GalleryLayout;
import com.jwen.gallerylayout.widget.GalleryImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.zip.Inflater;

/**
 * author: Jwen
 * date:2016-11-02.
 */

public class MainActivity extends Activity{


    private LruCache<String,Bitmap> mCache;
    private String key = "key";


    private int[] arrays = {
            R.mipmap.icon1,
            R.mipmap.icon3,
            R.mipmap.icon4,
            R.mipmap.icon5,
            R.mipmap.icon2,
            R.mipmap.icon6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<GalleryImageView> bitmapList = new ArrayList<>();
        int maxSize = (int) (Runtime.getRuntime().freeMemory() / 4);
        mCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };

        int length = arrays.length;
        for(int i = 0;i <length;i++){
            GalleryImageView galleryImageView = new GalleryImageView(this);
            Bitmap bm = mCache.get(key);
            if (bm == null) {
                bm =  BitmapFactory.decodeResource(getResources(),  arrays[i]);
//                bm =  ImageUtil.getReverseBitmapById(MainActivity.this, arrays[i]);
            }else {
                mCache.put(key, bm);
            }
            galleryImageView.setImageBitmap(bm);
            bitmapList.add(galleryImageView);
        }

        GalleryLayout galleryLayout = (GalleryLayout) findViewById(R.id.galleryLayout);

        if(bitmapList.size() > 0){
            galleryLayout.addImageData(bitmapList);
        }

//        initAsyncTask();


    }


    private void initAsyncTask() {
        ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.setMax(100);
        mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mDialog.setCancelable(false);

        TextView asyncTask = (TextView) findViewById(R.id.tv_asyncTask);

        Executor exec = new ThreadPoolExecutor(15, 200, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        new AsyncTaskUtil(mDialog,asyncTask).executeOnExecutor(exec);
    }


}
