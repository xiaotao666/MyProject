package com.jsyrdb.yiren.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

public class Task implements Serializable{

    /**
     * error : null
     * result : [{"_id":"job_c8cc43ad5e1c8","name":"测试任务1","describe":"测试任务1","attributes":[{"_id":"_c755f9f3300d9","name":"勾选标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"勾选标签","input_type":"check","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562848058047"},{"_id":"_c88b03bea4b0c","name":"选择属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"选择属性标签","input_type":"select","required":true,"select_options":[{"value":"一级选项1","label":"一级选项1","children":[{"value":"二级选项1","label":"二级选项1"},{"value":"二级选项2","label":"二级选项2"}]},{"value":"一级选项2","label":"一级选项2","children":[]}],"unit":"","creat_date":"20190711","creat_timestamp":"1562848035917"},{"_id":"_c614d1cac283c","name":"文本属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"文本属性标签","input_type":"text","required":false,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847952086"},{"_id":"_c46a449db7bfa","name":"日期范围","key":"","plugin_type":"attributetag","level_type":"case","describe":"日期范围","input_type":"date_range","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847930890"},{"_id":"_c0b7dd26cad21","name":"日期属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"日期属性","input_type":"date","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847911387"},{"_id":"_c2375f5a964a5","name":"数字属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"数字属性","input_type":"number","required":true,"select_options":[],"unit":"元","creat_date":"20190711","creat_timestamp":"1562847892585"}],"files":[{"_id":"_c76cb8920b63b","name":"测试文件","key":"","plugin_type":"filetag","level_type":"case","describe":"测试文件","creat_date":"20190711","creat_timestamp":"1562847843802"}],"comments":[{"_id":"_cc562bf2f2654","name":"测试评论","key":"","plugin_type":"commenttag","level_type":"case","describe":"测试评论","creat_date":"20190711","creat_timestamp":"1562847860743"}],"received":[],"messages":[],"status":"ending","creat_date":"20190715","creat_timestamp":"1563161593330","time":{"_id":"_ce1760a5a4ecd","name":"时间插件","type":"time_flow","plugin_type":"time","describe":"时间插件","time_length":10,"time_span":0,"alert_span":0,"start_type":"auto","start_delay_span":0,"creat_date":"20190711","creat_timestamp":"1562847824205"},"templateid":"_c2861aa55e8e0","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_cecee3ef9f3f1","casename":"南京科技公司_测试测试_20190715","processid":"process_cffa4bddf412a","processname":"测试申请流程","nodeidx":0,"nodename":"node","jobidx":0,"creat_userid":"cffd116b073e4","creat_username":"admin","job_manager":{"_id":"cffd116b073e4","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","readOnly":true},{"_id":"job_cc75954d9fea2","name":"测试任务2","describe":"测试任务2","attributes":[],"files":[],"comments":[],"received":[],"messages":[],"status":"ending","creat_date":"20190715","creat_timestamp":"1563161593330","prejobs":[{"_id":"_c2861aa55e8e0","name":"测试任务1"}],"templateid":"_c2ed2493102aa","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_cecee3ef9f3f1","casename":"南京科技公司_测试测试_20190715","processid":"process_cffa4bddf412a","processname":"测试申请流程","nodeidx":1,"nodename":"node","jobidx":0,"creat_userid":"cffd116b073e4","creat_username":"admin","job_manager":{"_id":"cffd116b073e4","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","readOnly":true}]
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
         * _id : job_c8cc43ad5e1c8
         * name : 测试任务1
         * describe : 测试任务1
         * attributes : [{"_id":"_c755f9f3300d9","name":"勾选标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"勾选标签","input_type":"check","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562848058047"},{"_id":"_c88b03bea4b0c","name":"选择属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"选择属性标签","input_type":"select","required":true,"select_options":[{"value":"一级选项1","label":"一级选项1","children":[{"value":"二级选项1","label":"二级选项1"},{"value":"二级选项2","label":"二级选项2"}]},{"value":"一级选项2","label":"一级选项2","children":[]}],"unit":"","creat_date":"20190711","creat_timestamp":"1562848035917"},{"_id":"_c614d1cac283c","name":"文本属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"文本属性标签","input_type":"text","required":false,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847952086"},{"_id":"_c46a449db7bfa","name":"日期范围","key":"","plugin_type":"attributetag","level_type":"case","describe":"日期范围","input_type":"date_range","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847930890"},{"_id":"_c0b7dd26cad21","name":"日期属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"日期属性","input_type":"date","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847911387"},{"_id":"_c2375f5a964a5","name":"数字属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"数字属性","input_type":"number","required":true,"select_options":[],"unit":"元","creat_date":"20190711","creat_timestamp":"1562847892585"}]
         * files : [{"_id":"_c76cb8920b63b","name":"测试文件","key":"","plugin_type":"filetag","level_type":"case","describe":"测试文件","creat_date":"20190711","creat_timestamp":"1562847843802"}]
         * comments : [{"_id":"_cc562bf2f2654","name":"测试评论","key":"","plugin_type":"commenttag","level_type":"case","describe":"测试评论","creat_date":"20190711","creat_timestamp":"1562847860743"}]
         * received : []
         * messages : []
         * status : ending
         * creat_date : 20190715
         * creat_timestamp : 1563161593330
         * time : {"_id":"_ce1760a5a4ecd","name":"时间插件","type":"time_flow","plugin_type":"time","describe":"时间插件","time_length":10,"time_span":0,"alert_span":0,"start_type":"auto","start_delay_span":0,"creat_date":"20190711","creat_timestamp":"1562847824205"}
         * templateid : _c2861aa55e8e0
         * customerid : customer_c65e5e8ad45a8
         * customername : 南京科技公司
         * caseid : case_cecee3ef9f3f1
         * casename : 南京科技公司_测试测试_20190715
         * processid : process_cffa4bddf412a
         * processname : 测试申请流程
         * nodeidx : 0
         * nodename : node
         * jobidx : 0
         * creat_userid : cffd116b073e4
         * creat_username : admin
         * job_manager : {"_id":"cffd116b073e4","name":"admin"}
         * job_manager_id : cffd116b073e4
         * job_manager_name : admin
         * readOnly : true
         * prejobs : [{"_id":"_c2861aa55e8e0","name":"测试任务1"}]
         */

        private String _id;
        private String id;
        private String name;
        private String describe;
        private String status;
        private String creat_date;
        private String creat_timestamp;
        private TimeBean time;
        private String templateid;
        private String customerid;
        private String customername;
        private String caseid;
        private String casename;
        private String processid;
        private String processname;
        private int nodeidx;
        private String nodename;
        private int jobidx;
        private String creat_userid;
        private String creat_username;
        private JobManagerBean job_manager;
        private String job_manager_id;
        private String job_manager_name;
        private boolean readOnly;
        private List<AttributesBean> attributes;
        private List<FilesBean> files;
        private List<CommentsBean> comments;
        private List<?> received;
        private List<?> messages;
        private List<PrejobsBean> prejobs;

        @JSONField(name="_id")
        public String get_id() {
            return _id;
        }
        @JSONField(name="_id")
        public void set_id(String _id) {
            this._id = _id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public TimeBean getTime() {
            return time;
        }

        public void setTime(TimeBean time) {
            this.time = time;
        }

        public String getTemplateid() {
            return templateid;
        }

        public void setTemplateid(String templateid) {
            this.templateid = templateid;
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

        public int getNodeidx() {
            return nodeidx;
        }

        public void setNodeidx(int nodeidx) {
            this.nodeidx = nodeidx;
        }

        public String getNodename() {
            return nodename;
        }

        public void setNodename(String nodename) {
            this.nodename = nodename;
        }

        public int getJobidx() {
            return jobidx;
        }

        public void setJobidx(int jobidx) {
            this.jobidx = jobidx;
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

        public JobManagerBean getJob_manager() {
            return job_manager;
        }

        public void setJob_manager(JobManagerBean job_manager) {
            this.job_manager = job_manager;
        }

        public String getJob_manager_id() {
            return job_manager_id;
        }

        public void setJob_manager_id(String job_manager_id) {
            this.job_manager_id = job_manager_id;
        }

        public String getJob_manager_name() {
            return job_manager_name;
        }

        public void setJob_manager_name(String job_manager_name) {
            this.job_manager_name = job_manager_name;
        }

        public boolean isReadOnly() {
            return readOnly;
        }

        public void setReadOnly(boolean readOnly) {
            this.readOnly = readOnly;
        }

        public List<AttributesBean> getAttributes() {
            return attributes;
        }

        public void setAttributes(List<AttributesBean> attributes) {
            this.attributes = attributes;
        }

        public List<FilesBean> getFiles() {
            return files;
        }

        public void setFiles(List<FilesBean> files) {
            this.files = files;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public List<?> getReceived() {
            return received;
        }

        public void setReceived(List<?> received) {
            this.received = received;
        }

        public List<?> getMessages() {
            return messages;
        }

        public void setMessages(List<?> messages) {
            this.messages = messages;
        }

        public List<PrejobsBean> getPrejobs() {
            return prejobs;
        }

        public void setPrejobs(List<PrejobsBean> prejobs) {
            this.prejobs = prejobs;
        }

        public static class TimeBean implements Serializable{
            /**
             * _id : _ce1760a5a4ecd
             * name : 时间插件
             * type : time_flow
             * plugin_type : time
             * describe : 时间插件
             * time_length : 10
             * time_span : 0
             * alert_span : 0
             * start_type : auto
             * start_delay_span : 0
             * creat_date : 20190711
             * creat_timestamp : 1562847824205
             */

            private String _id;
            private String name;
            private String type;
            private String plugin_type;
            private String describe;
            private Object time_length;
            private Object time_span;
            private Object alert_span;
            private String start_type;
            private Object start_delay_span;
            private String creat_date;
            private String creat_timestamp;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPlugin_type() {
                return plugin_type;
            }

            public void setPlugin_type(String plugin_type) {
                this.plugin_type = plugin_type;
            }

            public String getDescribe() {
                return describe;
            }

            public void setDescribe(String describe) {
                this.describe = describe;
            }

            public Object getTime_length() {
                return time_length;
            }

            public void setTime_length(Object time_length) {
                this.time_length = time_length;
            }

            public Object getTime_span() {
                return time_span;
            }

            public void setTime_span(Object time_span) {
                this.time_span = time_span;
            }

            public Object getAlert_span() {
                return alert_span;
            }

            public void setAlert_span(Object alert_span) {
                this.alert_span = alert_span;
            }

            public String getStart_type() {
                return start_type;
            }

            public void setStart_type(String start_type) {
                this.start_type = start_type;
            }

            public Object getStart_delay_span() {
                return start_delay_span;
            }

            public void setStart_delay_span(Object start_delay_span) {
                this.start_delay_span = start_delay_span;
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

        public static class JobManagerBean implements Serializable{
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

        public static class AttributesBean implements Serializable{
            /**
             * _id : _c755f9f3300d9
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
             * creat_timestamp : 1562848058047
             */

            private String _id;
            private String name;
            private String key;
            private String plugin_type;
            private String level_type;
            private String describe;
            private String input_type;
            private Object required;
            private String unit;
            private String creat_date;
            private String creat_timestamp;
            private List<?> select_options;

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

            public Object isRequired() {
                return required;
            }

            public void setRequired(Object required) {
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

            public List<?> getSelect_options() {
                return select_options;
            }

            public void setSelect_options(List<?> select_options) {
                this.select_options = select_options;
            }
        }

        public static class FilesBean implements Serializable{
            /**
             * _id : _c76cb8920b63b
             * name : 测试文件
             * key :
             * plugin_type : filetag
             * level_type : case
             * describe : 测试文件
             * creat_date : 20190711
             * creat_timestamp : 1562847843802
             */

            private String _id;
            private String name;
            private String key;
            private String plugin_type;
            private String level_type;
            private String describe;
            private String creat_date;
            private String creat_timestamp;

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
        }

        public static class CommentsBean implements Serializable{
            /**
             * _id : _cc562bf2f2654
             * name : 测试评论
             * key :
             * plugin_type : commenttag
             * level_type : case
             * describe : 测试评论
             * creat_date : 20190711
             * creat_timestamp : 1562847860743
             */

            private String _id;
            private String name;
            private String key;
            private String plugin_type;
            private String level_type;
            private String describe;
            private String creat_date;
            private String creat_timestamp;

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
        }

        public static class PrejobsBean implements Serializable{
            /**
             * _id : _c2861aa55e8e0
             * name : 测试任务1
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
