package com.jsyrdb.yiren.model;

import java.util.List;

public class CommunicationDetails {

    /**
     * error : null
     * result : [{"_id":"msg_cfb6f895d87d5","content_info":"测试测试","content_type":"text","group_id":"case_c66266fba076f","group_name":"客户1_test_20190711","group_type":"case","creat_timestamp":"1565849096355","from_user_id":"cffd116b073e4","from_user_name":"admin","status":"read","to_user_id":"cffd116b073e4","to_user_name":"admin"},{"_id":"msg_c77bfda12f477","content_info":"哟哟切克闹","content_type":"text","group_id":"case_c66266fba076f","group_name":"客户1_test_20190711","group_type":"case","creat_timestamp":"1565849315908","from_user_id":"cffd116b073e4","from_user_name":"admin","status":"read","to_user_id":"cffd116b073e4","to_user_name":"admin"},{"_id":"msg_c64d7b77458bf","content_info":"测试过","content_type":"text","group_id":"case_c66266fba076f","group_name":"客户1_test_20190711","group_type":"case","to_user_id":"cffd116b073e4","to_user_name":"admin","creat_timestamp":"1565850287333","from_user_id":"cffd116b073e4","from_user_name":"admin","status":"read"},{"_id":"msg_c7fd19b501e65","content_info":"我爱中国","content_type":"text","group_id":"case_c66266fba076f","group_name":"客户1_test_20190711","group_type":"case","creat_timestamp":"1565854263630","from_user_id":"_c0f18f878ca6d","from_user_name":"特朗普","status":"unread","to_user_id":"cffd116b073e4","to_user_name":"admin"}]
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
         * _id : msg_cfb6f895d87d5
         * content_info : 测试测试
         * content_type : text
         * group_id : case_c66266fba076f
         * group_name : 客户1_test_20190711
         * group_type : case
         * creat_timestamp : 1565849096355
         * from_user_id : cffd116b073e4
         * from_user_name : admin
         * status : read
         * to_user_id : cffd116b073e4
         * to_user_name : admin
         */

        private String _id;
        private Object content_info;
        private String content_type;
        private String group_id;
        private String group_name;
        private String group_type;
        private String creat_timestamp;
        private String from_user_id;
        private String from_user_name;
        private String status;
        private String to_user_id;
        private String to_user_name;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public Object getContent_info() {
            return content_info;
        }

        public void setContent_info(Object content_info) {
            this.content_info = content_info;
        }

        public String getContent_type() {
            return content_type;
        }

        public void setContent_type(String content_type) {
            this.content_type = content_type;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getGroup_type() {
            return group_type;
        }

        public void setGroup_type(String group_type) {
            this.group_type = group_type;
        }

        public String getCreat_timestamp() {
            return creat_timestamp;
        }

        public void setCreat_timestamp(String creat_timestamp) {
            this.creat_timestamp = creat_timestamp;
        }

        public String getFrom_user_id() {
            return from_user_id;
        }

        public void setFrom_user_id(String from_user_id) {
            this.from_user_id = from_user_id;
        }

        public String getFrom_user_name() {
            return from_user_name;
        }

        public void setFrom_user_name(String from_user_name) {
            this.from_user_name = from_user_name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTo_user_id() {
            return to_user_id;
        }

        public void setTo_user_id(String to_user_id) {
            this.to_user_id = to_user_id;
        }

        public String getTo_user_name() {
            return to_user_name;
        }

        public void setTo_user_name(String to_user_name) {
            this.to_user_name = to_user_name;
        }

        public static class ContentInfoBean {
            /**
             * _id : file__c1c2e4fa311d7
             * filename : IMG_0003.JPG
             */

            private String _id;
            private String filename;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }
        }
    }
}
