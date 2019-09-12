package com.jsyrdb.yiren.model;

import java.io.Serializable;
import java.util.List;

public class CaseSourceLinkMan implements Serializable{

    /**
     * error : null
     * result : [{"_id":"_c3fa8b43a69f3","case_sourceid":"_c84cb442315e3","case_sourcename":"建行","name":"信贷员1","remark":"sss","tag":"sss","mobile":"123123456","phone":"123123","mail":"123@157.com","position":"信贷员","address":"xuanwu","creat_date":"20190711","creat_timestamp":"1562848398827"}]
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

    public static class ResultBean implements Serializable {
        /**
         * _id : _c3fa8b43a69f3
         * case_sourceid : _c84cb442315e3
         * case_sourcename : 建行
         * name : 信贷员1
         * remark : sss
         * tag : sss
         * mobile : 123123456
         * phone : 123123
         * mail : 123@157.com
         * position : 信贷员
         * address : xuanwu
         * creat_date : 20190711
         * creat_timestamp : 1562848398827
         */

        private String _id;
        private String case_sourceid;
        private String case_sourcename;
        private String name;
        private String remark;
        private String tag;
        private String mobile;
        private String phone;
        private String mail;
        private String position;
        private String address;
        private String creat_date;
        private String creat_timestamp;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCase_sourceid() {
            return case_sourceid;
        }

        public void setCase_sourceid(String case_sourceid) {
            this.case_sourceid = case_sourceid;
        }

        public String getCase_sourcename() {
            return case_sourcename;
        }

        public void setCase_sourcename(String case_sourcename) {
            this.case_sourcename = case_sourcename;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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
