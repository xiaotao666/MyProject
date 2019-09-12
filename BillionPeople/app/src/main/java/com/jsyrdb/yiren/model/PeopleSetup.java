package com.jsyrdb.yiren.model;

import java.util.List;

public class PeopleSetup {

    /**
     * error : null
     * result : [{"_id":"cffd116b073e4","name":"admin","describe":"","username":"admin","mobile":"","email":"","creat_date":"20190505"},{"_id":"_c0f18f878ca6d","name":"特朗普","describe":"美国现任总统","username":"tlp","mobile":"119","email":"11923","creat_date":"20190715"}]
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
         * _id : cffd116b073e4
         * name : admin
         * describe :
         * username : admin
         * mobile :
         * email :
         * creat_date : 20190505
         */

        private String _id;
        private String name;
        private String describe;
        private String username;
        private String mobile;
        private String email;
        private String creat_date;
        private String sortLetters;

        public String getSortLetters() {
            return sortLetters;
        }

        public void setSortLetters(String sortLetters) {
            this.sortLetters = sortLetters;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCreat_date() {
            return creat_date;
        }

        public void setCreat_date(String creat_date) {
            this.creat_date = creat_date;
        }
    }
}
