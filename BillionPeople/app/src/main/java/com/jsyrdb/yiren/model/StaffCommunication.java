package com.jsyrdb.yiren.model;

import java.util.List;

public class StaffCommunication {

    /**
     * error : null
     * result : [{"_id":"_c0f18f878ca6d","unread_count":1,"group_name":"特朗普","group_type":"user"}]
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
         * _id : _c0f18f878ca6d
         * unread_count : 1
         * group_name : 特朗普
         * group_type : user
         */

        private String _id;
        private int unread_count;
        private String group_name;
        private String group_type;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public int getUnread_count() {
            return unread_count;
        }

        public void setUnread_count(int unread_count) {
            this.unread_count = unread_count;
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
    }
}
