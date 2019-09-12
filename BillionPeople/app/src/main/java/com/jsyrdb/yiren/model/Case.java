package com.jsyrdb.yiren.model;

import java.util.List;

public class Case {

    /**
     * error : null
     * result : [{"_id":"case_caf45526566dd","customerid":"_c276b675926f1","customername":"测试公司1","customer_linkmanid":"_c56adfbf753e7","customer_linkmanname":"联系人2","case_source_id":"_ca10ba214ea81","case_source_name":"南京银行","case_source_linkmanid":"_c8b163fdb6dcb","case_source_linkmanname":"张三","creat_date":"20190603","creat_timestamp":"1559573656086","name":"测试公司1_20190603","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","current_process_type":"apply_process","current_process_id":"process_c8de1d64f610c","current_process_type_name":"申请流程","case_manager":{"_id":"cffd116b073e4","name":"admin"},"case_manager_id":"cffd116b073e4","case_manager_name":"admin"}]
     */

    private String error;
    private List<ResultBean> result;

    public String getError() {
        return error;
    }

    public void setError(String error) {
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
         * _id : case_caf45526566dd
         * customerid : _c276b675926f1
         * customername : 测试公司1
         * customer_linkmanid : _c56adfbf753e7
         * customer_linkmanname : 联系人2
         * case_source_id : _ca10ba214ea81
         * case_source_name : 南京银行
         * case_source_linkmanid : _c8b163fdb6dcb
         * case_source_linkmanname : 张三
         * creat_date : 20190603
         * creat_timestamp : 1559573656086
         * name : 测试公司1_20190603
         * creat_userid : cffd116b073e4
         * creat_username : admin
         * status : running
         * current_process_type : apply_process
         * current_process_id : process_c8de1d64f610c
         * current_process_type_name : 申请流程
         * case_manager : {"_id":"cffd116b073e4","name":"admin"}
         * case_manager_id : cffd116b073e4
         * case_manager_name : admin
         */

        private String _id;
        private String describe;
        private String customerid;
        private String customername;
        private String customer_linkmanid;
        private String customer_linkmanname;
        private String case_source_id;
        private String case_source_name;
        private String case_source_linkmanid;
        private String case_source_linkmanname;
        private String creat_date;
        private String creat_timestamp;
        private String name;
        private String creat_userid;
        private String creat_username;
        private String status;
        private String current_process_type;
        private String current_process_id;
        private String current_process_type_name;
        private CaseManagerBean case_manager;
        private String case_manager_id;
        private String case_manager_name;
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

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getCustomerid() {
            return customerid;
        }

        public void setCustomerid(String customerid) {
            this.customerid = customerid;
        }

        public String getCustomername() {
            return customername;
        }

        public void setCustomername(String customername) {
            this.customername = customername;
        }

        public String getCustomer_linkmanid() {
            return customer_linkmanid;
        }

        public void setCustomer_linkmanid(String customer_linkmanid) {
            this.customer_linkmanid = customer_linkmanid;
        }

        public String getCustomer_linkmanname() {
            return customer_linkmanname;
        }

        public void setCustomer_linkmanname(String customer_linkmanname) {
            this.customer_linkmanname = customer_linkmanname;
        }

        public String getCase_source_id() {
            return case_source_id;
        }

        public void setCase_source_id(String case_source_id) {
            this.case_source_id = case_source_id;
        }

        public String getCase_source_name() {
            return case_source_name;
        }

        public void setCase_source_name(String case_source_name) {
            this.case_source_name = case_source_name;
        }

        public String getCase_source_linkmanid() {
            return case_source_linkmanid;
        }

        public void setCase_source_linkmanid(String case_source_linkmanid) {
            this.case_source_linkmanid = case_source_linkmanid;
        }

        public String getCase_source_linkmanname() {
            return case_source_linkmanname;
        }

        public void setCase_source_linkmanname(String case_source_linkmanname) {
            this.case_source_linkmanname = case_source_linkmanname;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreat_userid() {
            return creat_userid;
        }

        public void setCreat_userid(String creat_userid) {
            this.creat_userid = creat_userid;
        }

        public String getCreat_username() {
            return creat_username;
        }

        public void setCreat_username(String creat_username) {
            this.creat_username = creat_username;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCurrent_process_type() {
            return current_process_type;
        }

        public void setCurrent_process_type(String current_process_type) {
            this.current_process_type = current_process_type;
        }

        public String getCurrent_process_id() {
            return current_process_id;
        }

        public void setCurrent_process_id(String current_process_id) {
            this.current_process_id = current_process_id;
        }

        public String getCurrent_process_type_name() {
            return current_process_type_name;
        }

        public void setCurrent_process_type_name(String current_process_type_name) {
            this.current_process_type_name = current_process_type_name;
        }

        public CaseManagerBean getCase_manager() {
            return case_manager;
        }

        public void setCase_manager(CaseManagerBean case_manager) {
            this.case_manager = case_manager;
        }

        public String getCase_manager_id() {
            return case_manager_id;
        }

        public void setCase_manager_id(String case_manager_id) {
            this.case_manager_id = case_manager_id;
        }

        public String getCase_manager_name() {
            return case_manager_name;
        }

        public void setCase_manager_name(String case_manager_name) {
            this.case_manager_name = case_manager_name;
        }

        public static class CaseManagerBean {
            /**
             * _id : cffd116b073e4
             * name : admin
             */

            private String _id;
            private String name;

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
        }
    }
}
