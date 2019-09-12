package com.jsyrdb.yiren.model;

import java.io.Serializable;
import java.util.List;

public class CustomLinkMan implements Serializable{

    /**
     * error : null
     * result : [{"_id":"_c6e2b04fa7b7f","customerid":"customer_c4520cb701105","customername":"上海安西信息科技有限分公司","name":"刘西","remark":"刘西","tag":"","mobile":"123456","phone":"123456","mail":"123456","position":"经理","address":"上海 浦东","creat_date":"20190718","creat_timestamp":"1563427777760"}]
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
         * _id : _c6e2b04fa7b7f
         * customerid : customer_c4520cb701105
         * customername : 上海安西信息科技有限分公司
         * name : 刘西
         * remark : 刘西
         * tag :
         * mobile : 123456
         * phone : 123456
         * mail : 123456
         * position : 经理
         * address : 上海 浦东
         * creat_date : 20190718
         * creat_timestamp : 1563427777760
         */

        private String _id;
        private String customerid;
        private String customername;
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
