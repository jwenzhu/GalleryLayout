package com.jwen.gallerylayout.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.jwen.gallerylayout.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Jwen
 * date:2016-11-02.
 */

public class GalleryLayout extends LinearLayout {

    private LinearLayout.LayoutParams params;
    private List<GalleryImageView> imageViewList;
    private int firstPosition = 0;
    private int imageWidth = 400;

    public GalleryLayout(Context context) {
        this(context,null);
    }

    public GalleryLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GalleryLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOrientation(LinearLayout.HORIZONTAL);
        this.setGravity(Gravity.CENTER_VERTICAL);
        this.setBackgroundColor( context.getResources().getColor(R.color.colorTransparent));

        imageViewList = new ArrayList<>();
    }

    /**
     * 添加图片
     * @param imageViews
     */
    public void addImageData(List<GalleryImageView> imageViews) {
        imageViewList = imageViews;
        if(params == null)
            params = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        int size = imageViewList.size();
        for (int i = 0;i < size;i++) {
            GalleryImageView imageView = imageViewList.get(i);
            params.setMargins(20,20,20,20);
            this.addView(imageView);
        }
        invalidate();
        changeImagePosition(imageWidth);
    }


    int startX;
    int lastX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean onTouch = super.onTouchEvent(event);

        int disX = 0;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = startX = (int) event.getX();
               return true;
            case MotionEvent.ACTION_MOVE:
                if(disX > 0)
                    firstPosition ++;
                if(disX < 0)
                    firstPosition --;
                disX = (int) (startX - event.getX());
                scrollTo(disX,0);
                changeImagePosition(Math.abs(disX));
                break;
            case MotionEvent.ACTION_UP:
                lastX = (int) event.getX();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;

        }
        return onTouch;
    }

    private void changeImagePosition(int scale) {
        int size = imageViewList.size();
        for (int i = 0;i < size;i++) {
            GalleryImageView imageView = imageViewList.get(i);
            if(firstPosition == i){
                imageView.changeWidthAndHeight(scale);
            }else{
                imageView.changeWidthAndHeight(-scale);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;
        int count = getChildCount();
        for (int i=0;i<count;i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int childWidth = child.getMeasuredWidth();
            width += childWidth;
            int childHeight = child.getMeasuredHeight();
            height = Math.max(childHeight, height);
        }
        setMeasuredDimension((measureWidthMode == MeasureSpec.EXACTLY) ? measureWidth: width, (measureHeightMode == MeasureSpec.EXACTLY) ? measureHeight: height);

    }
}
