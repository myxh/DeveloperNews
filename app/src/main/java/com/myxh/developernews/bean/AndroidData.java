package com.myxh.developernews.bean;

import java.util.List;

/**
 * Created by asus on 2016/9/21.
 */

public class AndroidData {

    /**
     * error : false
     * results : [{"_id":"57dfc6a3421aa95bc338983e","createdAt":"2016-09-19T19:06:11.394Z","desc":"App瘦身最佳实践","publishedAt":"2016-09-21T11:37:24.210Z","source":"chrome","type":"Android","url":"http://www.jianshu.com/p/8f14679809b3","used":true,"who":"MVP"},{"_id":"57e08336421aa95bd05015e7","createdAt":"2016-09-20T08:30:46.567Z","desc":"Android 搜索过度效果","publishedAt":"2016-09-21T11:37:24.210Z","source":"chrome","type":"Android","url":"https://github.com/alexstyl/Material-SearchTransition","used":true,"who":"代码家"},{"_id":"57e085e6421aa95bcb130152","createdAt":"2016-09-20T08:42:14.877Z","desc":"目前来看 Android 上最棒的一款富文本编辑器。","publishedAt":"2016-09-21T11:37:24.210Z","source":"chrome","type":"Android","url":"https://github.com/wasabeef/richeditor-android","used":true,"who":"代码家"},{"_id":"57e1c8ad421aa95bcb130162","createdAt":"2016-09-21T07:39:25.282Z","desc":"美团 Android 热更新方案 Robust 详解，Robust 是基于 Instant Run 实现的代码热更新。","publishedAt":"2016-09-21T11:37:24.210Z","source":"chrome","type":"Android","url":"http://tech.meituan.com/android_robust.html","used":true,"who":"代码家的机器人"},{"_id":"57e1d809421aa95bc3389857","createdAt":"2016-09-21T08:44:57.956Z","desc":"Material 风格的介绍页，在做介绍的同时，可以向用户申请权限，做的超漂亮！！","publishedAt":"2016-09-21T11:37:24.210Z","source":"chrome","type":"Android","url":"https://github.com/TangoAgency/material-intro-screen","used":true,"who":"代码家"},{"_id":"57e1d836421aa95bc7f06a7d","createdAt":"2016-09-21T08:45:42.595Z","desc":"仿微信风格的 滑动退回","publishedAt":"2016-09-21T11:37:24.210Z","source":"chrome","type":"Android","url":"https://github.com/XBeats/and_swipeback","used":true,"who":"代码家"},{"_id":"57e1f2c7421aa95bc338985a","createdAt":"2016-09-21T10:39:03.500Z","desc":"Android自定义View系列目录","publishedAt":"2016-09-21T11:37:24.210Z","source":"chrome","type":"Android","url":"http://www.idtkm.com/customview/android%E8%87%AA%E5%AE%9A%E4%B9%89view%E7%B3%BB%E5%88%97%E7%9B%AE%E5%BD%95/","used":true,"who":"LHF"},{"_id":"57da61b2421aa95bc7f06a35","createdAt":"2016-09-15T16:54:10.445Z","desc":"纯用SVG做的Google I/O 2016那个炫酷的时钟","publishedAt":"2016-09-20T11:42:05.477Z","source":"web","type":"Android","url":"https://github.com/lypeer/GoogleClock","used":true,"who":"lypeer"},{"_id":"57de6f61421aa95bc338982b","createdAt":"2016-09-18T18:41:37.959Z","desc":"兼容4.0以上版本的点击扩散动画库，几行代码实现Activity转场动画效果 及新增自定义界面转场动画～","publishedAt":"2016-09-20T11:42:05.477Z","source":"web","type":"Android","url":"https://github.com/Yellow5A5/ActSwitchAnimTool","used":true,"who":null},{"_id":"57df2ad3421aa95bc7f06a53","createdAt":"2016-09-19T08:01:23.117Z","desc":"一个完整的生日管理 App: UPMiss 开源了","publishedAt":"2016-09-20T11:42:05.477Z","source":"chrome","type":"Android","url":"https://github.com/qiujuer/UPMiss","used":true,"who":"代码家"}]
     */

    private boolean error;
    /**
     * _id : 57dfc6a3421aa95bc338983e
     * createdAt : 2016-09-19T19:06:11.394Z
     * desc : App瘦身最佳实践
     * publishedAt : 2016-09-21T11:37:24.210Z
     * source : chrome
     * type : Android
     * url : http://www.jianshu.com/p/8f14679809b3
     * used : true
     * who : MVP
     */

    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
