package com.bodhixu.teademo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bodhixu.teademo.R;
import com.bodhixu.teademo.adapter.MyViewAdapter;
import com.bodhixu.teademo.adapter.TeaAdapter;
import com.bodhixu.teademo.base.BaseActivity;
import com.bodhixu.teademo.constant.MyConstants;
import com.bodhixu.teademo.constant.URLConstants;
import com.bodhixu.teademo.fragment.TeaListFragment;
import com.bodhixu.teademo.info.ADInfo;
import com.bodhixu.teademo.util.OkHttpUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends BaseActivity {

    //标题常量
    public static final String[] TITLES = {"头条","百科","咨询","经营","数据"};
    public static final int[] TYPES = { 0,52,16,54,53 }; //数据类型，用于网络请求

    //广告条
    @BindView(R.id.ad_vp)
    ViewPager adVp;
    @BindView(R.id.ad_tv)
    TextView adTv;
    @BindView(R.id.ad_dot_layout)
    LinearLayout adDotLayout;

    @BindView(R.id.main_tab)
    TabLayout mainTab;
    @BindView(R.id.tea_vp)
    ViewPager teaVp;


    //广告View的集合
    private List<View> adViews;
    private MyViewAdapter adAdapter;
    private Handler adHandler = new Handler();
    private ADRunnable adRunnable;

    //广告的数据
    private ADInfo adData;

    //主画面
    private TeaAdapter teaAdapter;
    private List<Fragment> teaFragments;

    //返回键点击事件
    private long backStartTime, backEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initAD();
        initADData();
        initMain();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            backEndTime = System.currentTimeMillis();
            if (backEndTime - backStartTime > 2000) {
                Toast.makeText(this, "连续按两次退出程序", Toast.LENGTH_SHORT).show();
                backStartTime = backEndTime;
                return true;
            }
//            else {
//                finish();
//            }
        }
        return super.onKeyDown(keyCode, event);
    }

    //加载广告数据
    private void initADData() {
        OkHttpUtils.doGETRequest(URLConstants.AD_URL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) { }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = OkHttpUtils.getStringFromResponse(response);
                Gson gson = new Gson();
                adData = gson.fromJson(result, ADInfo.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initADText(0);
                        initADImage();
                    }
                });
            }
        });
    }

    //加载广告图片
    private void initADImage() {
        for (int i=0; i<adViews.size(); i++) {
            //获得广告的ImageView
            ImageView adIv = (ImageView) adViews.get(i);
            //TODO 使用Picasso加载资
            Picasso.with(this)
                    .load(adData.getData().get(i).getImage())
                    .fit()
                    .into(adIv);
        }
    }


    //初始化广告的文言
    private void initADText(int current) {
        if (adData != null) {
            //获得当前ViewPager位置
            adTv.setText(adData.getData().get(current).getName());
        }
    }

    //初始化主画面
    private void initMain() {
        initMainData();
        teaAdapter = new TeaAdapter(getSupportFragmentManager(), teaFragments, TITLES);
        teaVp.setAdapter(teaAdapter);

        //关联TabLayout
        mainTab.setupWithViewPager(teaVp);
    }

    //初始化主的Fragment
    private void initMainData() {
        teaFragments = new ArrayList<>();
        for (int i =0; i<TITLES.length; i++) {
            TeaListFragment teaListFragment = new TeaListFragment();
            Bundle bundle = new Bundle();
            if (i == 0) {
                bundle.putString(MyConstants.KEY_FRAGMENT_URL_TEY, URLConstants.TT_URL);
            } else {
                bundle.putString(MyConstants.KEY_FRAGMENT_URL_TEY, URLConstants.OTHRER_URL);
            }
            bundle.putInt(MyConstants.KEY_FRAGMENT_TEY, TYPES[i]);
            teaListFragment.setArguments(bundle);
            teaFragments.add(teaListFragment);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //结束广告轮播
        adHandler.removeCallbacks(adRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //开启轮播
        adHandler.postDelayed(adRunnable, 2000);
    }

    //初始化广告
    private void initAD() {
        //初始化AD的子View
        initADItemView();
        //初始化广告圆点
        initDot();
        //初始化AD的ViewPager
        initADViewPager();
        //轮播
        adRunnable = new ADRunnable();
    }

    //初始化AD的ViewPager
    private void initADViewPager() {
        adAdapter = new MyViewAdapter(adViews);
        adVp.setAdapter(adAdapter);
        //ViewPager滑动监听
        adVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

            @Override
            public void onPageSelected(int position) {
                //处理圆点
                int itemCount = adDotLayout.getChildCount(); //获得Layout中子View的数量
                for (int i = 0; i < itemCount; i++) {
                    View view = adDotLayout.getChildAt(i);//提取子View
                    if (i == position) {
                        view.setSelected(true);
                    } else {
                        view.setSelected(false);
                    }
                }
                //处理文字
                initADText(position);
            }
        });
        //触摸监听，按下的时候取消handler回调，松手的时候，重新开启
        adVp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction(); //获得动作
                switch (action) {
                    case MotionEvent.ACTION_DOWN: //按下
                        adHandler.removeCallbacks(adRunnable);
                        break;
                    case MotionEvent.ACTION_UP: //提前
                        adHandler.postDelayed(adRunnable, 2000);
                        break;
                }
                return false;
            }
        });
    }

    //初始化AD的子View
    private void initADItemView() {
        adViews = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            )); //设置布局
            iv.setImageResource(R.mipmap.ic_logo); //设置图片资源
            adViews.add(iv);
        }
    }

    //引导的圆点
    private void initDot() {
        for (int i = 0; i < 3; i++) {
            ImageView iv = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            if (i < 3 - 1) {
                params.rightMargin = 30;
            }
            if (i == 0) {
                iv.setSelected(true);
            }
            iv.setLayoutParams(params); //设置布局
            iv.setImageResource(R.drawable.dot); //设置图片资源
            iv.setScaleType(ImageView.ScaleType.FIT_XY); //设置填充属性
            adDotLayout.addView(iv);
        }
    }

    //广告轮播的任务
    class ADRunnable implements Runnable {
        @Override
        public void run() {
            int currentPosition = adVp.getCurrentItem(); //获得当前的位置
            currentPosition++;
            if (currentPosition > 2) {
                currentPosition = 0;
            }
            adVp.setCurrentItem(currentPosition);//重新设置位置
            //            adRunnable = new ADRunnable();
            adHandler.postDelayed(adRunnable, 2000);
        }
    }
}
