package com.jwen.gallerylayout;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.jwen.gallerylayout.uitl.ImageUtil;
import com.jwen.gallerylayout.viewpager.DepthTransformation;
import com.jwen.gallerylayout.viewpager.MyTransformation;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private int pagerWidth;
    private List<ImageView> imageViewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        initImageDataList();

        final ViewPager viewPager= (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);
        pagerWidth= (int) (getResources().getDisplayMetrics().widthPixels*3.0f/5.0f);
        ViewGroup.LayoutParams lp=viewPager.getLayoutParams();
        if (lp==null){
            lp=new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        }else {
            lp.width=pagerWidth;
        }
        viewPager.setLayoutParams(lp);
        viewPager.setPageMargin(-50);
        findViewById(R.id.activity_main).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return viewPager.dispatchTouchEvent(motionEvent);
            }
        });
        viewPager.setPageTransformer(true,new MyTransformation());
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageViewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(imageViewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView=imageViewList.get(position);
                container.addView(imageView,position);
                return imageView;
            }
        });

    }


    private void initImageDataList() {
        imageViewList=new ArrayList<>();
        ImageView first=new ImageView(ViewPagerActivity.this);
        ImageView second=new ImageView(ViewPagerActivity.this);
        ImageView third=new ImageView(ViewPagerActivity.this);
        ImageView fourth=new ImageView(ViewPagerActivity.this);
        ImageView fifth=new ImageView(ViewPagerActivity.this);
        ImageView sixth=new ImageView(ViewPagerActivity.this);

        first.setImageResource(R.mipmap.icon1);
        second.setImageResource(R.mipmap.icon2);
        third.setImageResource(R.mipmap.icon3);
        fourth.setImageResource(R.mipmap.icon4);
        fifth.setImageResource(R.mipmap.icon5);
        sixth.setImageResource(R.mipmap.icon6);

//        first.setImageBitmap(ImageUtil.getReverseBitmapById(ViewPagerActivity.this,R.mipmap.icon1));
//        second.setImageBitmap(ImageUtil.getReverseBitmapById(ViewPagerActivity.this,R.mipmap.icon2));
//        third.setImageBitmap(ImageUtil.getReverseBitmapById(ViewPagerActivity.this,R.mipmap.icon3));
//        fourth.setImageBitmap(ImageUtil.getReverseBitmapById(ViewPagerActivity.this,R.mipmap.icon4));
//        fifth.setImageBitmap(ImageUtil.getReverseBitmapById(ViewPagerActivity.this,R.mipmap.icon5));
//        sixth.setImageBitmap(ImageUtil.getReverseBitmapById(ViewPagerActivity.this,R.mipmap.icon6));

        imageViewList.add(first);
        imageViewList.add(second);
        imageViewList.add(third);
        imageViewList.add(fourth);
        imageViewList.add(fifth);
        imageViewList.add(sixth);
    }
}
