package com.bodhixu.teademo.base;

import android.app.Application;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

/**
 * Created by bodhixu on 2016/10/10.
 * 全局的Application
 */
public class BaseApplication extends Application{

    //执行1次，优先于Activity的onCreate触发
    @Override
    public void onCreate() {
        super.onCreate();
        initPicasso();
    }

    //初始化Picasso，实现单例模式
    private void initPicasso() {
        Picasso picasso = new Picasso.Builder(this)
                //设置内存缓存大小10M
                .memoryCache(new LruCache(10 << 20))
                //设置左上角标记，主要用于测试
                //红色-从网络下载
                //蓝色-从磁盘加载
                //绿色-从内存加载
                .indicatorsEnabled(true)
                .build();
        //设置Picasso单例模式
        Picasso.setSingletonInstance(picasso);

    }

}
