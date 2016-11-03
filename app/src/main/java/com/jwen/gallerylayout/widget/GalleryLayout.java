package com.jwen.gallerylayout.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * author: Jwen
 * date:2016-11-02.
 */

public class GalleryLayout extends LinearLayout {
    public GalleryLayout(Context context) {
        this(context,null);
    }

    public GalleryLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GalleryLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
