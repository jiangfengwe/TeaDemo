package com.bodhixu.teademo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bodhixu.teademo.R;
import com.bodhixu.teademo.adapter.MyViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 引导画面
 */
public class GuideActivity extends AppCompatActivity {

    //画面图片的下标
    public static final int[] RES_IDS = {R.mipmap.slide1, R.mipmap.slide2, R.mipmap.slide3};


    @BindView(R.id.guide_vp)
    ViewPager guideVp;
    @BindView(R.id.dot_layout)
    LinearLayout dotLayout;

    //引导画面View的集合
    private List<View> guideViews;

    //适配器
    private MyViewAdapter guideAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        initItemViews();
        initView();
        initDot();
    }

    //引导的圆点
    private void initDot() {
        for (int i=0; i<RES_IDS.length; i++) {
            ImageView iv = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            if (i < RES_IDS.length - 1) {
                params.rightMargin = 30;
            }
            if (i == 0) {
                iv.setSelected(true);
            }
            iv.setLayoutParams(params); //设置布局
            iv.setImageResource(R.drawable.dot); //设置图片资源
            iv.setScaleType(ImageView.ScaleType.FIT_XY); //设置填充属性
            dotLayout.addView(iv);
        }

    }

    private void initView() {
        guideAdapter = new MyViewAdapter(guideViews);
        guideVp.setAdapter(guideAdapter);
        //ViewPager滑动监听
        guideVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                int itemCount = dotLayout.getChildCount(); //获得Layout中子View的数量
                for (int i=0; i<itemCount; i++) {
                    View view = dotLayout.getChildAt(i);//提取子View
                    if (i == position) {
                        view.setSelected(true);
                    } else {
                        view.setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

    }

    //初始化ViewPager数据源
    private void initItemViews() {
        guideViews = new ArrayList<>();
        for (int i = 0; i < RES_IDS.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            )); //设置布局
            iv.setImageResource(RES_IDS[i]); //设置图片资源
            iv.setScaleType(ImageView.ScaleType.FIT_XY); //设置填充属性
            guideViews.add(iv);
        }
        guideViews.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转主画面
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
