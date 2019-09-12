package com.jsyrdb.yiren.model;

import java.util.List;

public class DelayNews {

    /**
     * error : null
     * result : [{"_id":"apply_c841428e0fdfc","action_id":"set_job_delay","action_model_name":"action_job","action_name":"延长任务期限","action_type":"auth","describe":"","location_id":"case_c66266fba076f","location_level":"case","location_level_name":"案件","location_name":"客户1_test_20190711","name":"申请任务延期","target_id":"startjob_c533b82cba94d","target_level":"job","target_level_name":"任务","target_name":"流程开始任务","to_user_id":"cffd116b073e4","to_user_name":"admin","creat_date":"20190801","creat_timestamp":"1564641011822","status":"unreplied","from_user_id":"cffd116b073e4","from_user_name":"admin"},{"_id":"apply_c7bfe378f750d","action_id":"set_job_delay","action_model_name":"action_job","action_name":"延长任务期限","action_type":"auth","describe":"测试测试","location_id":"case_c66266fba076f","location_level":"case","location_level_name":"案件","location_name":"客户1_test_20190711","name":"申请任务延期","target_id":"job_c25d8a84780ae","target_level":"job","target_level_name":"任务","target_name":"测试任务1","to_user_id":"cffd116b073e4","to_user_name":"admin","creat_date":"20190801","creat_timestamp":"1564640581036","status":"unreplied","from_user_id":"cffd116b073e4","from_user_name":"admin"},{"_id":"apply_c59bba7173729","to_user_id":"cffd116b073e4","action_name":"延长任务期限","location_level_name":"案件","describe":"ggggg","action_model_name":"action_job","location_name":"客户1_2525_20190731","target_level":"job","location_id":"case_c392c93a02ea0","action_id":"set_job_delay","to_user_name":"admin","target_id":"startjob_cab2279e44881","target_name":"流程开始任务","location_level":"case","action_type":"execute","name":"申请任务延期","target_level_name":"任务","creat_date":"20190801","creat_timestamp":"1564639513689","status":"unreplied","from_user_id":"cffd116b073e4","from_user_name":"admin"}]
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
         * _id : apply_c841428e0fdfc
         * action_id : set_job_delay
         * action_model_name : action_job
         * action_name : 延长任务期限
         * action_type : auth
         * describe :
         * location_id : case_c66266fba076f
         * location_level : case
         * location_level_name : 案件
         * location_name : 客户1_test_20190711
         * name : 申请任务延期
         * target_id : startjob_c533b82cba94d
         * target_level : job
         * target_level_name : 任务
         * target_name : 流程开始任务
         * to_user_id : cffd116b073e4
         * to_user_name : admin
         * creat_date : 20190801
         * creat_timestamp : 1564641011822
         * status : unreplied
         * from_user_id : cffd116b073e4
         * from_user_name : admin
         */

        private String _id;
        private String action_id;
        private String action_model_name;
        private String action_name;
        private String action_type;
        private String describe;
        private String location_id;
        private String location_level;
        private String location_level_name;
        private String location_name;
        private String name;
        private String target_id;
        private String target_level;
        private String target_level_name;
        private String target_name;
        private String to_user_id;
        private String to_user_name;
        private String creat_date;
        private String creat_timestamp;
        private String status;
        private String from_user_id;
        private String from_user_name;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getAction_id() {
            return action_id;
        }

        public void setAction_id(String action_id) {
            this.action_id = action_id;
        }

        public String getAction_model_name() {
            return action_model_name;
        }

        public void setAction_model_name(String action_model_name) {
            this.action_model_name = action_model_name;
        }

        public String getAction_name() {
            return action_name;
        }

        public void setAction_name(String action_name) {
            this.action_name = action_name;
        }

        public String getAction_type() {
            return action_type;
        }

        public void setAction_type(String action_type) {
            this.action_type = action_type;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getLocation_id() {
            return location_id;
        }

        public void setLocation_id(String location_id) {
            this.location_id = location_id;
        }

        public String getLocation_level() {
            return location_level;
        }

        public void setLocation_level(String location_level) {
            this.location_level = location_level;
        }

        public String getLocation_level_name() {
            return location_level_name;
        }

        public void setLocation_level_name(String location_level_name) {
            this.location_level_name = location_level_name;
        }

        public String getLocation_name() {
            return location_name;
        }

        public void setLocation_name(String location_name) {
            this.location_name = location_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTarget_id() {
            return target_id;
        }

        public void setTarget_id(String target_id) {
            this.target_id = target_id;
        }

        public String getTarget_level() {
            return target_level;
        }

        public void setTarget_level(String target_level) {
            this.target_level = target_level;
        }

        public String getTarget_level_name() {
            return target_level_name;
        }

        public void setTarget_level_name(String target_level_name) {
            this.target_level_name = target_level_name;
        }

        public String getTarget_name() {
            return target_name;
        }

        public void setTarget_name(String target_name) {
            this.target_name = target_name;
        }

        public String getTo_user_id() {
            return to_user_id;
        }

        public void setTo_user_id(String to_user_id) {
            this.to_user_id = to_user_id;
        }

        public String getTo_user_name() {
            return to_user_name;
        }

        public void setTo_user_name(String to_user_name) {
            this.to_user_name = to_user_name;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getFrom_user_id() {
            return from_user_id;
        }

        public void setFrom_user_id(String from_user_id) {
            this.from_user_id = from_user_id;
        }

        public String getFrom_user_name() {
            return from_user_name;
        }

        public void setFrom_user_name(String from_user_name) {
            this.from_user_name = from_user_name;
        }
    }
}
