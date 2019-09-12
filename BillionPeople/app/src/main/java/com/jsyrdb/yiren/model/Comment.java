package com.jsyrdb.yiren.model;

import java.util.List;

public class Comment {

    /**
     * error : null
     * result : [{"_id":"commenttag_c8fa327f7cd2d","name":"测试评论","key":"","plugin_type":"commenttag","level_type":"case","describe":"测试评论","creat_date":"20190711","creat_timestamp":"1562848463428","tag_id":"_cc562bf2f2654","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c66266fba076f","casename":"客户1_test_20190711","processid":"process_c22988bcc7565","processname":"测试申请流程","jobid":"job_c25d8a84780ae","jobname":"测试任务1","tagidx":0}]
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
         * _id : commenttag_c8fa327f7cd2d
         * name : 测试评论
         * key :
         * plugin_type : commenttag
         * level_type : case
         * describe : 测试评论
         * creat_date : 20190711
         * creat_timestamp : 1562848463428
         * tag_id : _cc562bf2f2654
         * customerid : customer_ce6e96397f913
         * customername : 客户1
         * caseid : case_c66266fba076f
         * casename : 客户1_test_20190711
         * processid : process_c22988bcc7565
         * processname : 测试申请流程
         * jobid : job_c25d8a84780ae
         * jobname : 测试任务1
         * tagidx : 0
         */

        private String _id;
        private String name;
        private String key;
        private String plugin_type;
        private String level_type;
        private String describe;
        private String creat_date;
        private String creat_timestamp;
        private String tag_id;
        private String customerid;
        private String customername;
        private String caseid;
        private String casename;
        private String processid;
        private String processname;
        private String jobid;
        private String jobname;
        private int tagidx;

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

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getPlugin_type() {
            return plugin_type;
        }

        public void setPlugin_type(String plugin_type) {
            this.plugin_type = plugin_type;
        }

        public String getLevel_type() {
            return level_type;
        }

        public void setLevel_type(String level_type) {
            this.level_type = level_type;
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

        public String getTag_id() {
            return tag_id;
        }

        public void setTag_id(String tag_id) {
            this.tag_id = tag_id;
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

        public String getCaseid() {
            return caseid;
        }

        public void setCaseid(String caseid) {
            this.caseid = caseid;
        }

        public String getCasename() {
            return casename;
        }

        public void setCasename(String casename) {
            this.casename = casename;
        }

        public String getProcessid() {
            return processid;
        }

        public void setProcessid(String processid) {
            this.processid = processid;
        }

        public String getProcessname() {
            return processname;
        }

        public void setProcessname(String processname) {
            this.processname = processname;
        }

        public String getJobid() {
            return jobid;
        }

        public void setJobid(String jobid) {
            this.jobid = jobid;
        }

        public String getJobname() {
            return jobname;
        }

        public void setJobname(String jobname) {
            this.jobname = jobname;
        }

        public int getTagidx() {
            return tagidx;
        }

        public void setTagidx(int tagidx) {
            this.tagidx = tagidx;
        }
    }
}
