package com.bodhixu.teademo.constant;

/**
 * Created by bodhixu on 2016/10/11.
 */
public class URLConstants {

    //广告地址
    public static final String AD_URL = "http://sns.maimaicha.com/api? apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getSlideshow";

    //头条
    public static final String TT_URL = "http://sns.maimaicha.com/api? apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getHeadlines&page=%d&rows=15";

    //百科/资讯/数据/经营  列表数据
    public static final String OTHRER_URL = "http://sns.maimaicha.com/api? apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getListByType&page=%d&rows=15&type=%d";

    //详情画面
    public static final String DETAIL_URL = "http://sns.maimaicha.com/api? apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getNewsContent&id=%s";
}
