package com.jsyrdb.yiren.model;

import java.io.Serializable;
import java.util.List;

public class Attribute implements Serializable {

    /**
     * error : null
     * result : [{"_id":"attributetag_c2954c25c570b","name":"勾选标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"勾选标签","input_type":"check","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562848463405","tag_id":"_c755f9f3300d9","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c66266fba076f","casename":"客户1_test_20190711","processid":"process_c22988bcc7565","processname":"测试申请流程","jobid":"job_c25d8a84780ae","jobname":"测试任务1","tagidx":0,"edit_date":"20190718","edit_timestamp":"1563440521222","userid":"cffd116b073e4","username":"admin","value":true},{"_id":"attributetag_c71d73ab7c4a4","name":"选择属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"选择属性标签","input_type":"select","required":true,"select_options":[{"value":"一级选项1","label":"一级选项1","children":[{"value":"二级选项1","label":"二级选项1"},{"value":"二级选项2","label":"二级选项2"}]},{"value":"一级选项2","label":"一级选项2","children":[]}],"unit":"","creat_date":"20190711","creat_timestamp":"1562848463405","tag_id":"_c88b03bea4b0c","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c66266fba076f","casename":"客户1_test_20190711","processid":"process_c22988bcc7565","processname":"测试申请流程","jobid":"job_c25d8a84780ae","jobname":"测试任务1","tagidx":1,"edit_date":"20190718","edit_timestamp":"1563439558838","userid":"cffd116b073e4","username":"admin","value":"一级选项2"},{"_id":"attributetag_c71d9d0e4a6d1","name":"文本属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"文本属性标签","input_type":"text","required":false,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562848463405","tag_id":"_c614d1cac283c","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c66266fba076f","casename":"客户1_test_20190711","processid":"process_c22988bcc7565","processname":"测试申请流程","jobid":"job_c25d8a84780ae","jobname":"测试任务1","tagidx":2,"edit_date":"20190718","edit_timestamp":"1563434464626","userid":"cffd116b073e4","username":"admin","value":"222"},{"_id":"attributetag_c5322987f02f9","name":"日期范围","key":"","plugin_type":"attributetag","level_type":"case","describe":"日期范围","input_type":"date_range","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562848463405","tag_id":"_c46a449db7bfa","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c66266fba076f","casename":"客户1_test_20190711","processid":"process_c22988bcc7565","processname":"测试申请流程","jobid":"job_c25d8a84780ae","jobname":"测试任务1","tagidx":3,"edit_date":"20190717","edit_timestamp":"1563350147047","userid":"cffd116b073e4","username":"admin","value":["2019-07-01","2019-07-01"]},{"_id":"attributetag_c4dbaec2dcc38","name":"日期属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"日期属性","input_type":"date","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562848463405","tag_id":"_c0b7dd26cad21","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c66266fba076f","casename":"客户1_test_20190711","processid":"process_c22988bcc7565","processname":"测试申请流程","jobid":"job_c25d8a84780ae","jobname":"测试任务1","tagidx":4,"edit_date":"20190718","edit_timestamp":"1563434430119","userid":"cffd116b073e4","username":"admin","value":"2019-07-03"},{"_id":"attributetag_ceee17497cda6","name":"数字属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"数字属性","input_type":"number","required":true,"select_options":[],"unit":"元","creat_date":"20190711","creat_timestamp":"1562848463405","tag_id":"_c2375f5a964a5","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c66266fba076f","casename":"客户1_test_20190711","processid":"process_c22988bcc7565","processname":"测试申请流程","jobid":"job_c25d8a84780ae","jobname":"测试任务1","tagidx":5,"edit_date":"20190717","edit_timestamp":"1563351333103","userid":"cffd116b073e4","username":"admin","value":111112}]
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
         * _id : attributetag_c2954c25c570b
         * name : 勾选标签
         * key :
         * plugin_type : attributetag
         * level_type : case
         * describe : 勾选标签
         * input_type : check
         * required : true
         * select_options : []
         * unit :
         * creat_date : 20190711
         * creat_timestamp : 1562848463405
         * tag_id : _c755f9f3300d9
         * customerid : customer_ce6e96397f913
         * customername : 客户1
         * caseid : case_c66266fba076f
         * casename : 客户1_test_20190711
         * processid : process_c22988bcc7565
         * processname : 测试申请流程
         * jobid : job_c25d8a84780ae
         * jobname : 测试任务1
         * tagidx : 0
         * edit_date : 20190718
         * edit_timestamp : 1563440521222
         * userid : cffd116b073e4
         * username : admin
         * value : true
         */

        private String _id;
        private String name;
        private String key;
        private String plugin_type;
        private String level_type;
        private String describe;
        private String input_type;
        private boolean required;
        private String unit;
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
        private String edit_date;
        private String edit_timestamp;
        private String userid;
        private String username;
        private Object value;
        private List<SelectOptionsBean> select_options;

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

        public String getInput_type() {
            return input_type;
        }

        public void setInput_type(String input_type) {
            this.input_type = input_type;
        }

        public boolean isRequired() {
            return required;
        }

        public void setRequired(boolean required) {
            this.required = required;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
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

        public String getEdit_date() {
            return edit_date;
        }

        public void setEdit_date(String edit_date) {
            this.edit_date = edit_date;
        }

        public String getEdit_timestamp() {
            return edit_timestamp;
        }

        public void setEdit_timestamp(String edit_timestamp) {
            this.edit_timestamp = edit_timestamp;
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

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public List<SelectOptionsBean> getSelect_options() {
            return select_options;
        }

        public void setSelect_options(List<SelectOptionsBean> select_options) {
            this.select_options = select_options;
        }

        public static class SelectOptionsBean implements Serializable{
            /**
             * value : 一级选项1
             * label : 一级选项1
             * children : [{"value":"二级选项1","label":"二级选项1"},{"value":"二级选项2","label":"二级选项2"}]
             */

            private String value;
            private String label;
            private List<ChildrenBean> children;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public List<ChildrenBean> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            public static class ChildrenBean implements Serializable{
                /**
                 * value : 二级选项1
                 * label : 二级选项1
                 */

                private String value;
                private String label;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }
            }
        }
    }
}
