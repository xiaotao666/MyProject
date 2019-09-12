package com.jsyrdb.yiren.model;

import java.io.Serializable;
import java.util.List;

public class Labels implements Serializable{

    /**
     * error : err
     * result : [{"type":"apply","name":"申请流程","key":"apply_process","describe":"申请流程","icon_type":"message"},{"type":"case","name":"评审流程","key":"case_process","describe":"评审流程","icon_type":"eye"},{"type":"lending","name":"放款流程","key":"lending_process","describe":"放款流程","icon_type":"pay-circle"},{"type":"regulatory","name":"保后流程","key":"regulatory_process","describe":"申请流程","icon_type":"insurance"}]
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

    public static class ResultBean implements Serializable {
        /**
         * type : apply
         * name : 申请流程
         * key : apply_process
         * describe : 申请流程
         * icon_type : message
         */

        private String type;
        private String name;
        private String key;
        private String describe;
        private String icon_type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getIcon_type() {
            return icon_type;
        }

        public void setIcon_type(String icon_type) {
            this.icon_type = icon_type;
        }
    }
}
