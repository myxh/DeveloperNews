package com.myxh.developernews.bean;

import java.util.List;

/**
 * Created by asus on 2016/8/6.
 */
public class GirlData {

    /**
     * category : ["App","休息视频","Android","iOS","福利"]
     * error : false
     * results : {"Android":[{"_id":"57a04b9e421aa90d36e960ef","createdAt":"2016-08-02T15:28:30.746Z","desc":"图片滤镜，便捷的编辑图片并获取位图","publishedAt":"2016-08-05T11:31:58.293Z","source":"web","type":"Android","url":"https://github.com/Zomato/AndroidPhotoFilters?utm_campaign=explore-email&utm_medium=email&utm_source=newsletter&utm_term=weekly","used":true,"who":"littleMark"},{"_id":"57a202f6421aa90d36e96108","createdAt":"2016-08-03T22:43:02.627Z","desc":"Android 复杂的多类型列表视图新写法：drakeet/MultiType ","publishedAt":"2016-08-05T11:31:58.293Z","source":"web","type":"Android","url":"https://github.com/drakeet/MultiType","used":true,"who":"drakeet"},{"_id":"57a3489d421aa91e26064787","createdAt":"2016-08-04T21:52:29.704Z","desc":"MD登录风格","publishedAt":"2016-08-05T11:31:58.293Z","source":"chrome","type":"Android","url":"https://github.com/fanrunqi/MaterialLogin","used":true,"who":"Jason"},{"_id":"57a36da4421aa90d36e9611f","createdAt":"2016-08-05T00:30:28.173Z","desc":"一步一步教你上传自己的 Library 到 JCenter","publishedAt":"2016-08-05T11:31:58.293Z","source":"web","type":"Android","url":"http://blog.lufficc.com/2016/08/05/%E4%B8%80%E6%AD%A5%E4%B8%80%E6%AD%A5%E6%95%99%E4%BD%A0%E4%B8%8A%E4%BC%A0%E8%87%AA%E5%B7%B1%E7%9A%84-Library-%E5%88%B0-JCenter/","used":true,"who":null},{"_id":"57a3fb34421aa90d36e96124","createdAt":"2016-08-05T10:34:28.780Z","desc":"点赞还可以这么玩","publishedAt":"2016-08-05T11:31:58.293Z","source":"chrome","type":"Android","url":"https://github.com/ldoublem/ThumbUp","used":true,"who":"ldoublem"}],"App":[{"_id":"579ad085421aa91e26064733","createdAt":"2016-07-29T11:41:57.688Z","desc":"一个用键盘来调整窗口摆放位置的app","publishedAt":"2016-08-05T11:31:58.293Z","source":"web","type":"App","url":"https://github.com/eczarny/spectacle","used":true,"who":"color"}],"iOS":[{"_id":"57a3d8ed421aa90d39e709ee","createdAt":"2016-08-05T08:08:13.693Z","desc":"Yahoo开源的在线obj-c    ->   swift工具(Yahoo，好唏嘘)","publishedAt":"2016-08-05T11:31:58.293Z","source":"chrome","type":"iOS","url":"https://github.com/yahoojapan/objc2swift","used":true,"who":"Dear宅学长"},{"_id":"57a3f9fe421aa90d39e709f0","createdAt":"2016-08-05T10:29:18.45Z","desc":"匹配图标颜色的多彩选项卡切换演示（Yalantis 出品）","publishedAt":"2016-08-05T11:31:58.293Z","source":"chrome","type":"iOS","url":"https://github.com/Yalantis/ColorMatchTabs","used":true,"who":"Dear宅学长"},{"_id":"57a401fb421aa90d39e709f2","createdAt":"2016-08-05T11:03:23.24Z","desc":"iOS 简单画板工具库","publishedAt":"2016-08-05T11:31:58.293Z","source":"chrome","type":"iOS","url":"https://github.com/Nicejinux/NXDrawKit","used":true,"who":"代码家"}],"休息视频":[{"_id":"579c197c421aa90d36e960bf","createdAt":"2016-07-30T11:05:32.987Z","desc":"有个好莱坞做特效的爸爸是一种什么样的体验？ 卧槽简直吊炸天，最后一个我下巴都跟着掉了！","publishedAt":"2016-08-05T11:31:58.293Z","source":"chrome","type":"休息视频","url":"http://weibo.com/p/230444058a763da0c7ddff016823859a9b38ed","used":true,"who":"lxxself"}],"福利":[{"_id":"57a4056c421aa91e2606478d","createdAt":"2016-08-05T11:18:04.807Z","desc":"8.5","publishedAt":"2016-08-05T11:31:58.293Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f6ipaai7wgj20dw0kugp4.jpg","used":true,"who":"代码家"}]}
     */

    private boolean error;
    private ResultsBean results;
    private List<String> category;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public static class ResultsBean {
        /**
         * _id : 57a04b9e421aa90d36e960ef
         * createdAt : 2016-08-02T15:28:30.746Z
         * desc : 图片滤镜，便捷的编辑图片并获取位图
         * publishedAt : 2016-08-05T11:31:58.293Z
         * source : web
         * type : Android
         * url : https://github.com/Zomato/AndroidPhotoFilters?utm_campaign=explore-email&utm_medium=email&utm_source=newsletter&utm_term=weekly
         * used : true
         * who : littleMark
         */

        private List<AndroidBean> Android;
        /**
         * _id : 579ad085421aa91e26064733
         * createdAt : 2016-07-29T11:41:57.688Z
         * desc : 一个用键盘来调整窗口摆放位置的app
         * publishedAt : 2016-08-05T11:31:58.293Z
         * source : web
         * type : App
         * url : https://github.com/eczarny/spectacle
         * used : true
         * who : color
         */

        private List<AppBean> App;
        /**
         * _id : 57a3d8ed421aa90d39e709ee
         * createdAt : 2016-08-05T08:08:13.693Z
         * desc : Yahoo开源的在线obj-c    ->   swift工具(Yahoo，好唏嘘)
         * publishedAt : 2016-08-05T11:31:58.293Z
         * source : chrome
         * type : iOS
         * url : https://github.com/yahoojapan/objc2swift
         * used : true
         * who : Dear宅学长
         */

        private List<IOSBean> iOS;
        /**
         * _id : 579c197c421aa90d36e960bf
         * createdAt : 2016-07-30T11:05:32.987Z
         * desc : 有个好莱坞做特效的爸爸是一种什么样的体验？ 卧槽简直吊炸天，最后一个我下巴都跟着掉了！
         * publishedAt : 2016-08-05T11:31:58.293Z
         * source : chrome
         * type : 休息视频
         * url : http://weibo.com/p/230444058a763da0c7ddff016823859a9b38ed
         * used : true
         * who : lxxself
         */

        private List<休息视频Bean> 休息视频;
        /**
         * _id : 57a4056c421aa91e2606478d
         * createdAt : 2016-08-05T11:18:04.807Z
         * desc : 8.5
         * publishedAt : 2016-08-05T11:31:58.293Z
         * source : chrome
         * type : 福利
         * url : http://ww4.sinaimg.cn/large/610dc034jw1f6ipaai7wgj20dw0kugp4.jpg
         * used : true
         * who : 代码家
         */

        private List<福利Bean> 福利;

        public List<AndroidBean> getAndroid() {
            return Android;
        }

        public void setAndroid(List<AndroidBean> Android) {
            this.Android = Android;
        }

        public List<AppBean> getApp() {
            return App;
        }

        public void setApp(List<AppBean> App) {
            this.App = App;
        }

        public List<IOSBean> getIOS() {
            return iOS;
        }

        public void setIOS(List<IOSBean> iOS) {
            this.iOS = iOS;
        }

        public List<休息视频Bean> get休息视频() {
            return 休息视频;
        }

        public void set休息视频(List<休息视频Bean> 休息视频) {
            this.休息视频 = 休息视频;
        }

        public List<福利Bean> get福利() {
            return 福利;
        }

        public void set福利(List<福利Bean> 福利) {
            this.福利 = 福利;
        }

        public static class AndroidBean {
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

        public static class AppBean {
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

        public static class IOSBean {
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

        public static class 休息视频Bean {
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

        public static class 福利Bean {
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
}
