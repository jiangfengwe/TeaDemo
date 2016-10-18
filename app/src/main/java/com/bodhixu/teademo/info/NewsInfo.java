package com.bodhixu.teademo.info;

import java.util.List;

/**
 * Created by bodhixu on 2016/10/11.
 */
public class NewsInfo {


    /**
     * data : [{"id":"8207","title":"\u201c日照绿茶\u201d品牌价值8.85亿 居山东茶类之首","source":"转载",
     * "description":"","wap_thumb":"http://s1.sns.maimaicha
     * .com/images/2016/01/04/20160104104521_47289_suolue3.jpg","create_time":"01月04日10:47",
     * "nickname":"bubu123"},{"id":"8166","title":"买买茶从手游到茶叶--用互联网思维设计商业模式","source":"百度",
     * "description":"买买茶的转变历程","wap_thumb":"http://s1.sns.maimaicha
     * .com/images/2015/12/21/20151221031250268_suolue3.jpg","create_time":"12月21日14:59",
     * "nickname":"bubu123"},{"id":"7257","title":"跳出茶叶做大茶业 买买茶助力茶企互联网化","source":"买买茶",
     * "description":"","wap_thumb":"","create_time":"03月31日11:04","nickname":"杯中茗"},
     * {"id":"7207","title":"星巴克纽约开设首家中国茶馆","source":"买买茶","description":"","wap_thumb":"",
     * "create_time":"02月15日17:00","nickname":"回忆的沙漏"},{"id":"7206",
     * "title":"2013茶界十大事件及2014茶行业十大趋势","source":"买买茶","description":"","wap_thumb":"http://s1
     * .sns.maimaicha.com/images/2014/02/15/20140215162633_50606_suolue3.jpg",
     * "create_time":"02月15日16:30","nickname":"凡人"},{"id":"7191","title":"2014年普洱茶行业的8大趋势",
     * "source":"买买茶","description":"","wap_thumb":"http://s1.sns.maimaicha
     * .com/images/2014/02/10/20140210153356_79343_suolue3.jpg","create_time":"02月10日15:36",
     * "nickname":"阿依玛"},{"id":"7183","title":"网络游戏，竟然是为了卖茶叶？茶叶电商的产业垂直新模式","source":"买买茶",
     * "description":"","wap_thumb":"http://s1.sns.maimaicha
     * .com/images/2014/01/15/20140115133901_47467_suolue3.jpg","create_time":"01月15日13:48",
     * "nickname":"杯中茗"},{"id":"7127","title":"路的尽头","source":"买买茶","description":"",
     * "wap_thumb":"","create_time":"12月10日15:35","nickname":"杯中茗"},{"id":"7091",
     * "title":"飘香诱人馋，茶文化专家教你买茶品茶","source":"茶香一味","description":"飘香诱人馋，茶文化专家教你买茶品茶",
     * "wap_thumb":"","create_time":"11月06日16:16","nickname":"茶香一味"},{"id":"6303",
     * "title":"西湖龙井茶叶遭受十年不遇旱情","source":"买买茶",
     * "description":"2013年7月以来，杭州市已连续20余天出现高温天气，同时未迎来有效降水，导致西湖龙井核心保护区种植龙井43号的茶园部分",
     * "wap_thumb":"http://s1.sns.maimaicha.com/images/2013/07/24/20130724142750_81020_suolue3
     * .jpg","create_time":"07月24日14:28","nickname":"茶语人生"},{"id":"6299","title":"紫娟茶，神秘典雅的紫茶极品",
     * "source":"买买茶",
     * "description":"1985年，云南省农业科学院茶叶研究所科技人员首次发现，紫娟茶含有较高的花青素，茶树芽、叶、茎均呈紫色，并且所制茶品和茶",
     * "wap_thumb":"http://s1.sns.maimaicha.com/images/2013/07/24/20130724141523_76542_suolue3
     * .jpg","create_time":"07月24日14:16","nickname":"叶子"},{"id":"6298","title":"茶企抢滩月饼市场",
     * "source":"买买茶","description":"记者在福州市场上看到，尽管离中秋节到来还有2
     * 个月的时间，但是福州多家茶企已经将月饼产品摆放在专卖店内最显眼的位置展示，并","wap_thumb":"http://s1.sns.maimaicha
     * .com/images/2013/07/24/20130724140859_95193_suolue3.jpg","create_time":"07月24日14:12",
     * "nickname":"春之茶"},{"id":"6275","title":"外国游客来京最爱买茶叶","source":"买买茶",
     * "description":"昨日，北京市旅游委正式发布《北京旅游(000802,股吧)绿皮书2013》。绿皮书中，关于北京市2012年入境旅游者购物",
     * "wap_thumb":"http://s1.sns.maimaicha.com/images/2013/07/19/20130719141647_26325_suolue3
     * .jpg","create_time":"07月19日14:17","nickname":"茶的杯具"},{"id":"6260",
     * "title":"第四届中国日照绿茶节今日开幕","source":"买买茶",
     * "description":"2013年第四届中国日照绿茶节开幕式今日启动，本届绿茶节以\u201c生态、优质、健康\u201d为主题，旨在通过搭建一个茶文化展示平台和茶",
     * "wap_thumb":"http://s1.sns.maimaicha.com/images/2013/07/12/20130712162014_22802_suolue3
     * .jpg","create_time":"07月12日16:21","nickname":"非茶勿扰"},{"id":"6246","title":"十余家茶企已入围候选品牌",
     * "source":"买买茶","description":"由齐鲁晚报、山东省茶文化协会等单位主办的\u201c首届山东人最喜爱的茶叶品牌评选\u201d活动,
     * 正式启动十天时间就有近30家茶企茶商报名","wap_thumb":"http://s1.sns.maimaicha
     * .com/images/2013/07/10/20130710152352_34821_suolue3.jpg","create_time":"07月10日15:25",
     * "nickname":"堂吉诃德与茶"}]
     * errorMessage : success
     */

    private String errorMessage;
    /**
     * id : 8207
     * title : “日照绿茶”品牌价值8.85亿 居山东茶类之首
     * source : 转载
     * description :
     * wap_thumb : http://s1.sns.maimaicha.com/images/2016/01/04/20160104104521_47289_suolue3.jpg
     * create_time : 01月04日10:47
     * nickname : bubu123
     */

    private List<DataBean> data;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String title;
        private String source;
        private String description;
        private String wap_thumb;
        private String create_time;
        private String nickname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getWap_thumb() {
            return wap_thumb;
        }

        public void setWap_thumb(String wap_thumb) {
            this.wap_thumb = wap_thumb;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
