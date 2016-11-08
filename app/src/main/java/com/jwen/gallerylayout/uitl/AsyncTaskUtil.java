package com.jwen.gallerylayout.uitl;

import android.app.ProgressDialog;
import android.content.res.ObbInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;


/**
 * author: Jwen
 * date:2016-11-04.
 */

public class AsyncTaskUtil extends AsyncTask<String, Integer, Object>{

    private static final String TAG = "MainActivity";
    private ProgressDialog mDialog;
    private TextView mTextView;

    public AsyncTaskUtil(ProgressDialog dialog, TextView textView) {
        this.mDialog = dialog;
        this.mTextView = textView;
    }

    @Override
    protected void onPreExecute()
    {
        mDialog.show();
        Log.e(TAG, Thread.currentThread().getName() + " onPreExecute ");
    }

    @Override
    protected Object doInBackground(String... params)
    {
        // 模拟数据的加载,耗时的任务
        for (int i = 0; i < 100; i++)
        {
            try
            {
                Thread.sleep(80);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            publishProgress(i);
        }

        Log.e(TAG, Thread.currentThread().getName() + " doInBackground ");
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        mDialog.setProgress(values[0]);
        Log.e(TAG, Thread.currentThread().getName() + " onProgressUpdate ");
    }

    @Override
    protected void onPostExecute(Object result)
    {
        // 进行数据加载完成后的UI操作
        mDialog.dismiss();
        mTextView.setText("LOAD DATA SUCCESS ");
        Log.e(TAG, Thread.currentThread().getName() + " onPostExecute ");
    }

}
