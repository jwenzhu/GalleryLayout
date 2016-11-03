package com.jwen.gallerylayout;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * author: Jwen
 * date:2016-11-02.
 */

public class MainActivity extends Activity{


    public static class MyHandler extends Handler{

        private WeakReference<MainActivity> mActivity;

        public MyHandler(MainActivity mainActivity) {
            mActivity= new WeakReference<MainActivity>(mainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity mainActivity = mActivity.get();
            if(mainActivity != null){

            }
        }
    }

    private final MyHandler myHandler = new MyHandler(this);

    private static Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        System.arraycopy();//数组拷贝

        myHandler.postDelayed(runnable,10*60*60);
        finish();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
