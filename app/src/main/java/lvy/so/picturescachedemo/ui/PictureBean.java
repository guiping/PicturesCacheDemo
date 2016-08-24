package lvy.so.picturescachedemo.ui;

import java.util.List;

/**
 * @author gping  email: gping.vip@gmail.com
 * @date Created by 2016/8/24.19:30
 * @filename PictureBean.class
 * @description
 * @TODO
 */
public class PictureBean {


    /**
     * error : false
     * results : [{"_id":"57bc5238421aa9125fa3ed70","createdAt":"2016-08-23T21:40:08.159Z","desc":"8.24","publishedAt":"2016-08-24T11:38:48.733Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1f740f701gqj20u011hgo9.jpg","used":true,"who":"daimajia"},{"_id":"57bb039e421aa9125fa3ed5e","createdAt":"2016-08-22T21:52:30.572Z","desc":"8-22","publishedAt":"2016-08-23T11:29:45.813Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f72v5ra09fj20u011hdit.jpg","used":true,"who":"代码家"},{"_id":"57b93f9e421aa950cf8050ff","createdAt":"2016-08-21T13:43:58.241Z","desc":"8-22","publishedAt":"2016-08-22T11:29:37.164Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1f71bezmt3tj20u00k0757.jpg","used":true,"who":"代码家"}]
     */

    private boolean error;
    /**
     * _id : 57bc5238421aa9125fa3ed70
     * createdAt : 2016-08-23T21:40:08.159Z
     * desc : 8.24
     * publishedAt : 2016-08-24T11:38:48.733Z
     * source : chrome
     * type : 福利
     * url : http://ww3.sinaimg.cn/large/610dc034jw1f740f701gqj20u011hgo9.jpg
     * used : true
     * who : daimajia
     */

    private List<ResultsEntity> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public static class ResultsEntity {
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
