package com.jwen.gallerylayout.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * author: Jwen
 * date:2016-11-05.
 */

public class GalleryImageView extends View{

    private Bitmap mBitmap;
    private Paint mPaint;
    private int mWidth = 300;
    private int mHeight = 300;

    public GalleryImageView(Context context) {
        this(context,null);
    }
    public GalleryImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public GalleryImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    /**
     * 设置bitmap资源
     * @param bitmapId
     */
    public void setImageResource(int bitmapId){
        mBitmap = BitmapFactory.decodeResource(getContext().getResources(),bitmapId);
        invalidate();
    }

    /**
     * 设置bitmap
     * @param bitmap
     */
    public void setImageBitmap(Bitmap bitmap){
        mBitmap = bitmap;
        invalidate();
    }

    /**
     * 改变宽高
     * @param sale
     */
    public void changeWidthAndHeight(int sale){


        mWidth += sale/4;
        mHeight += sale/4;


        mWidth = mWidth < 300?300:mWidth;
        mHeight = mHeight < 500?500:mHeight;

        invalidate();
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mBitmap != null){
            canvas.drawBitmap(mBitmap, null, new Rect(0, 0,mWidth,mHeight), mPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(350,550);
    }
}
