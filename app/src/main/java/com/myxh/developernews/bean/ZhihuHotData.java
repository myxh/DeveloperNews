package com.myxh.developernews.bean;

import java.util.List;

/**
 * Created by asus on 2016/9/19.
 */
public class ZhihuHotData {

    /**
     * news_id : 8800327
     * url : http://news-at.zhihu.com/api/2/news/8800327
     * thumbnail : http://pic3.zhimg.com/034c1f1ac36b46502f45a9e568d8548a.jpg
     * title : 瞎扯 · 如何正确地吐槽
     */

    private List<RecentBean> recent;

    public List<RecentBean> getRecent() {
        return recent;
    }

    public void setRecent(List<RecentBean> recent) {
        this.recent = recent;
    }

    public static class RecentBean {
        private int news_id;
        private String url;
        private String thumbnail;
        private String title;

        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
