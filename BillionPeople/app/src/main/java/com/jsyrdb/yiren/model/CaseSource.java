package com.jsyrdb.yiren.model;

import java.io.Serializable;
import java.util.List;

public class CaseSource implements Serializable {

    /**
     * error : null
     * result : [{"_id":"_ca40ecf7bfeaa","name":"北京工商银行","address":"北京 西城区","phone":"123456","describe":"工商","creat_date":"20190718","creat_timestamp":"1563427863425"},{"_id":"_c84cb442315e3","name":"建行","address":"南京 建邺","phone":"23678","describe":"","creat_date":"20190711","creat_timestamp":"1562848353345"}]
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

    public static class ResultBean implements Serializable{
        /**
         * _id : _ca40ecf7bfeaa
         * name : 北京工商银行
         * address : 北京 西城区
         * phone : 123456
         * describe : 工商
         * creat_date : 20190718
         * creat_timestamp : 1563427863425
         */

        private String _id;
        private String name;
        private String address;
        private String phone;
        private String describe;
        private String creat_date;
        private String creat_timestamp;

        private String link_man;
        private String link_id;
        private boolean isChecked;

        public String getLink_man() {
            return link_man;
        }

        public void setLink_man(String link_man) {
            this.link_man = link_man;
        }

        public String getLink_id() {
            return link_id;
        }

        public void setLink_id(String link_id) {
            this.link_id = link_id;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
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
