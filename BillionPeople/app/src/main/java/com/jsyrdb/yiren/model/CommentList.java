package com.jsyrdb.yiren.model;

import java.util.List;

public class CommentList {

    /**
     * error : null
     * result : [{"_id":"comment_resource_c1d00bc8b5572","commentid":"commenttag_c8fa327f7cd2d","commentname":"测试评论","value":"888888","userid":"cffd116b073e4","username":"admin","creat_date":"20190715","creat_timestamp":"1563174073583"},{"_id":"comment_resource_ca02f7df22a76","commentid":"commenttag_c8fa327f7cd2d","commentname":"测试评论","value":"kkkk","userid":"cffd116b073e4","username":"admin","creat_date":"20190715","creat_timestamp":"1563174077529"},{"_id":"comment_resource_c3f09e8aa970b","commentid":"commenttag_c8fa327f7cd2d","commentname":"测试评论","value":"ccccccccccccccccccccccccccccccc","userid":"cffd116b073e4","username":"admin","creat_date":"20190717","creat_timestamp":"1563348810786"},{"_id":"comment_resource_c718f9c062ff7","commentid":"commenttag_c8fa327f7cd2d","commentname":"测试评论","value":"dddddddddddd","userid":"cffd116b073e4","username":"admin","creat_date":"20190718","creat_timestamp":"1563416514326"},{"_id":"comment_resource_ce6d07c19f378","commentid":"commenttag_c8fa327f7cd2d","commentname":"测试评论","value":"点点滴滴","userid":"cffd116b073e4","username":"admin","creat_date":"20190718","creat_timestamp":"1563427444814"},{"_id":"comment_resource_cfaefee924450","commentid":"commenttag_c8fa327f7cd2d","commentname":"测试评论","value":"是十四岁","userid":"cffd116b073e4","username":"admin","creat_date":"20190719","creat_timestamp":"1563521835693"}]
     */

    private Object error;
    private List<ResultBean> result;

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * _id : comment_resource_c1d00bc8b5572
         * commentid : commenttag_c8fa327f7cd2d
         * commentname : 测试评论
         * value : 888888
         * userid : cffd116b073e4
         * username : admin
         * creat_date : 20190715
         * creat_timestamp : 1563174073583
         */

        private String _id;
        private String commentid;
        private String commentname;
        private String value;
        private String userid;
        private String username;
        private String creat_date;
        private String creat_timestamp;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCommentid() {
            return commentid;
        }

        public void setCommentid(String commentid) {
            this.commentid = commentid;
        }

        public String getCommentname() {
            return commentname;
        }

        public void setCommentname(String commentname) {
            this.commentname = commentname;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCreat_date() {
            return creat_date;
        }

        public void setCreat_date(String creat_date) {
            this.creat_date = creat_date;
        }

        public String getCreat_timestamp() {
            return creat_timestamp;
        }

        public void setCreat_timestamp(String creat_timestamp) {
            this.creat_timestamp = creat_timestamp;
        }
    }
}
