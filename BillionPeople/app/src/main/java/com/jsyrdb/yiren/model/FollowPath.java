package com.jsyrdb.yiren.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

public class FollowPath implements Serializable{

    /**
     * error : null
     * result : [{"_id":"_c6687f6e62dd0","name":"测试申请流程","describe":"测试申请流程","nodes":[{"type":"process_node","plan_cycle":0,"elements":[{"name":"测试任务1","type":"job","info":{"_id":"_c2861aa55e8e0","name":"测试任务1","describe":"测试任务1","attributes":[{"_id":"_c755f9f3300d9","name":"勾选标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"勾选标签","input_type":"check","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562848058047"},{"_id":"_c88b03bea4b0c","name":"选择属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"选择属性标签","input_type":"select","required":true,"select_options":[{"value":"一级选项1","label":"一级选项1","children":[{"value":"二级选项1","label":"二级选项1"},{"value":"二级选项2","label":"二级选项2"}]},{"value":"一级选项2","label":"一级选项2","children":[]}],"unit":"","creat_date":"20190711","creat_timestamp":"1562848035917"},{"_id":"_c614d1cac283c","name":"文本属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"文本属性标签","input_type":"text","required":false,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847952086"},{"_id":"_c46a449db7bfa","name":"日期范围","key":"","plugin_type":"attributetag","level_type":"case","describe":"日期范围","input_type":"date_range","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847930890"},{"_id":"_c0b7dd26cad21","name":"日期属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"日期属性","input_type":"date","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847911387"},{"_id":"_c2375f5a964a5","name":"数字属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"数字属性","input_type":"number","required":true,"select_options":[],"unit":"元","creat_date":"20190711","creat_timestamp":"1562847892585"}],"files":[{"_id":"_c76cb8920b63b","name":"测试文件","key":"","plugin_type":"filetag","level_type":"case","describe":"测试文件","creat_date":"20190711","creat_timestamp":"1562847843802"}],"comments":[{"_id":"_cc562bf2f2654","name":"测试评论","key":"","plugin_type":"commenttag","level_type":"case","describe":"测试评论","creat_date":"20190711","creat_timestamp":"1562847860743"}],"received":[],"messages":[],"status":"","creat_date":"20190711","creat_timestamp":"1562848073922","time":{"_id":"_ce1760a5a4ecd","name":"时间插件","type":"time_flow","plugin_type":"time","describe":"时间插件","time_length":10,"time_span":0,"alert_span":0,"start_type":"auto","start_delay_span":0,"creat_date":"20190711","creat_timestamp":"1562847824205"}}}]},{"type":"process_node","plan_cycle":0,"elements":[{"name":"测试任务2","type":"job","info":{"_id":"_c2ed2493102aa","name":"测试任务2","describe":"测试任务2","attributes":[],"files":[],"comments":[],"received":[],"messages":[],"status":"","creat_date":"20190711","creat_timestamp":"1562848133524","prejobs":[{"_id":"_c2861aa55e8e0","name":"测试任务1"}]}}]}],"startNode":{"type":"process_start_node","job_drawer_visible":false,"job_drawer_width":949.5,"element":{"_id":"process_start_node","name":"流程开始任务模板","describe":"流程开始时执行的一些动作的任务。","type":"job","jobInfo":{"_id":"process_start_node","name":"流程开始任务","describe":"流程开始时执行的一些动作的任务。","plugins":[],"attributes":[{"_id":"_c614d1cac283c","name":"文本属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"文本属性标签","input_type":"text","required":false,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847952086"},{"_id":"_c2375f5a964a5","name":"数字属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"数字属性","input_type":"number","required":true,"select_options":[],"unit":"元","creat_date":"20190711","creat_timestamp":"1562847892585"}],"comments":[]},"job_drawer_width":949.5}},"stopNode":{"type":"process_stop_node","job_drawer_visible":false,"job_drawer_width":"","element":{"_id":"process_stop_node","name":"流程结束任务模板","describe":"流程结束时执行的一些动作的任务。","type":"job","info":{"_id":"process_stop_node_job","name":"流程结束任务","describe":"流程开始时执行的一些动作的任务。","plugins":[],"attributes":[],"comments":[]}}},"type":"process","next_process_list":[],"process_type":"apply_process","process_type_name":"申请流程","creat_date":"20190711","creat_timestamp":"1562848230576"},{"_id":"_cf5f420fde731","name":"审核案件","describe":"","nodes":[],"startNode":{"type":"process_start_node","job_drawer_visible":false,"job_drawer_width":640,"element":{"_id":"process_start_node","name":"流程开始任务模板","describe":"流程开始时执行的一些动作的任务。","type":"job","jobInfo":{"_id":"process_start_node","name":"流程开始任务","describe":"流程开始时执行的一些动作的任务。","plugins":[],"attributes":[{"_id":"_c755f9f3300d9","name":"勾选标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"勾选标签","input_type":"check","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562848058047"}],"comments":[]},"job_drawer_width":640}},"stopNode":{"type":"process_stop_node","job_drawer_visible":false,"job_drawer_width":"","element":{"_id":"process_stop_node","name":"流程结束任务模板","describe":"流程结束时执行的一些动作的任务。","type":"job","info":{"_id":"process_stop_node_job","name":"流程结束任务","describe":"流程开始时执行的一些动作的任务。","plugins":[],"attributes":[],"comments":[]}}},"type":"process","next_process_list":[],"process_type":"apply_process","process_type_name":"申请流程","creat_date":"20190715","creat_timestamp":"1563179896212"},{"_id":"_c4c7f18dc0af4","name":"模版名称0111","describe":"模版描述0111","nodes":[],"startNode":{"type":"process_start_node","job_drawer_visible":false,"job_drawer_width":640,"element":{"_id":"process_start_node","name":"流程开始任务模板","describe":"流程开始时执行的一些动作的任务。","type":"job","jobInfo":{"_id":"process_start_node","name":"流程开始任务","describe":"流程开始时执行的一些动作的任务。","plugins":[],"attributes":[{"_id":"_c755f9f3300d9","name":"勾选标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"勾选标签","input_type":"check","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562848058047"},{"_id":"_c2375f5a964a5","name":"数字属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"数字属性","input_type":"number","required":true,"select_options":[],"unit":"元","creat_date":"20190711","creat_timestamp":"1562847892585"}],"comments":[]},"job_drawer_width":640}},"stopNode":{"type":"process_stop_node","job_drawer_visible":false,"job_drawer_width":"","element":{"_id":"process_stop_node","name":"流程结束任务模板","describe":"流程结束时执行的一些动作的任务。","type":"job","info":{"_id":"process_stop_node_job","name":"流程结束任务","describe":"流程开始时执行的一些动作的任务。","plugins":[],"attributes":[],"comments":[]}}},"type":"process","next_process_list":[{"_id":"_cf5f420fde731","name":"审核案件"},{"_id":"_c6687f6e62dd0","name":"测试申请流程"}],"process_type":"apply_process","process_type_name":"申请流程","creat_date":"20190717","creat_timestamp":"1563341458155"},{"_id":"_c16915da2fae7","name":"流程模版2","describe":"新的流程模版","nodes":[{"type":"process_node","plan_cycle":0,"elements":[]},{"type":"process_node","plan_cycle":0,"elements":[]}],"startNode":{"type":"process_start_node","job_drawer_visible":false,"job_drawer_width":640,"element":{"_id":"process_start_node","name":"流程开始任务模板","describe":"流程开始时执行的一些动作的任务。","type":"job","jobInfo":{"_id":"process_start_node","name":"流程开始任务","describe":"流程开始时执行的一些动作的任务。","plugins":[],"attributes":[{"_id":"_c755f9f3300d9","name":"勾选标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"勾选标签","input_type":"check","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562848058047"},{"_id":"_c2375f5a964a5","name":"数字属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"数字属性","input_type":"number","required":true,"select_options":[],"unit":"元","creat_date":"20190711","creat_timestamp":"1562847892585"}],"comments":[]},"job_drawer_width":640}},"stopNode":{"type":"process_stop_node","job_drawer_visible":false,"job_drawer_width":"","element":{"_id":"process_stop_node","name":"流程结束任务模板","describe":"流程结束时执行的一些动作的任务。","type":"job","info":{"_id":"process_stop_node_job","name":"流程结束任务","describe":"流程开始时执行的一些动作的任务。","plugins":[],"attributes":[],"comments":[]}}},"type":"process","next_process_list":[],"process_type":"apply_process","process_type_name":"申请流程","creat_date":"20190718","creat_timestamp":"1563427308896"},{"_id":"_c9b581d65ba4a","name":"流程模版345","describe":"流程模块","nodes":[{"type":"process_node","plan_cycle":0,"elements":[]},{"type":"process_node","plan_cycle":0,"elements":[{"name":"测试任务2","type":"job","info":{"_id":"_c2ed2493102aa","name":"测试任务2","describe":"测试任务2","attributes":[],"files":[{"_id":"_c76cb8920b63b","name":"测试文件","key":"","plugin_type":"filetag","level_type":"case","describe":"测试文件","creat_date":"20190711","creat_timestamp":"1562847843802"}],"comments":[],"received":[],"messages":[],"status":"","creat_date":"20190711","creat_timestamp":"1562848133524"}}]}],"startNode":{"type":"process_start_node","job_drawer_visible":false,"job_drawer_width":640,"element":{"_id":"process_start_node","name":"流程开始任务模板","describe":"流程开始时执行的一些动作的任务。","type":"job","jobInfo":{"_id":"process_start_node","name":"流程开始任务","describe":"流程描数","plugins":[],"attributes":[{"_id":"_c46a449db7bfa","name":"日期范围","key":"","plugin_type":"attributetag","level_type":"case","describe":"日期范围","input_type":"date_range","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847930890"},{"_id":"_c2375f5a964a5","name":"数字属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"数字属性","input_type":"number","required":true,"select_options":[],"unit":"元","creat_date":"20190711","creat_timestamp":"1562847892585"}],"comments":[]},"job_drawer_width":640}},"stopNode":{"type":"process_stop_node","job_drawer_visible":false,"job_drawer_width":640,"element":{"_id":"process_stop_node","name":"流程结束任务模板","describe":"流程结束时执行的一些动作的任务。","type":"job","info":{"_id":"process_stop_node_job","name":"流程结束任务","describe":"流程开始时执行的一些动作的任务。","plugins":[],"attributes":[],"comments":[]}}},"type":"process","next_process_list":[],"process_type":"apply_process","process_type_name":"申请流程","creat_date":"20190718","creat_timestamp":"1563427542828"}]
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
         * _id : _c6687f6e62dd0
         * name : 测试申请流程
         * describe : 测试申请流程
         * nodes : [{"type":"process_node","plan_cycle":0,"elements":[{"name":"测试任务1","type":"job","info":{"_id":"_c2861aa55e8e0","name":"测试任务1","describe":"测试任务1","attributes":[{"_id":"_c755f9f3300d9","name":"勾选标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"勾选标签","input_type":"check","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562848058047"},{"_id":"_c88b03bea4b0c","name":"选择属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"选择属性标签","input_type":"select","required":true,"select_options":[{"value":"一级选项1","label":"一级选项1","children":[{"value":"二级选项1","label":"二级选项1"},{"value":"二级选项2","label":"二级选项2"}]},{"value":"一级选项2","label":"一级选项2","children":[]}],"unit":"","creat_date":"20190711","creat_timestamp":"1562848035917"},{"_id":"_c614d1cac283c","name":"文本属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"文本属性标签","input_type":"text","required":false,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847952086"},{"_id":"_c46a449db7bfa","name":"日期范围","key":"","plugin_type":"attributetag","level_type":"case","describe":"日期范围","input_type":"date_range","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847930890"},{"_id":"_c0b7dd26cad21","name":"日期属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"日期属性","input_type":"date","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847911387"},{"_id":"_c2375f5a964a5","name":"数字属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"数字属性","input_type":"number","required":true,"select_options":[],"unit":"元","creat_date":"20190711","creat_timestamp":"1562847892585"}],"files":[{"_id":"_c76cb8920b63b","name":"测试文件","key":"","plugin_type":"filetag","level_type":"case","describe":"测试文件","creat_date":"20190711","creat_timestamp":"1562847843802"}],"comments":[{"_id":"_cc562bf2f2654","name":"测试评论","key":"","plugin_type":"commenttag","level_type":"case","describe":"测试评论","creat_date":"20190711","creat_timestamp":"1562847860743"}],"received":[],"messages":[],"status":"","creat_date":"20190711","creat_timestamp":"1562848073922","time":{"_id":"_ce1760a5a4ecd","name":"时间插件","type":"time_flow","plugin_type":"time","describe":"时间插件","time_length":10,"time_span":0,"alert_span":0,"start_type":"auto","start_delay_span":0,"creat_date":"20190711","creat_timestamp":"1562847824205"}}}]},{"type":"process_node","plan_cycle":0,"elements":[{"name":"测试任务2","type":"job","info":{"_id":"_c2ed2493102aa","name":"测试任务2","describe":"测试任务2","attributes":[],"files":[],"comments":[],"received":[],"messages":[],"status":"","creat_date":"20190711","creat_timestamp":"1562848133524","prejobs":[{"_id":"_c2861aa55e8e0","name":"测试任务1"}]}}]}]
         * startNode : {"type":"process_start_node","job_drawer_visible":false,"job_drawer_width":949.5,"element":{"_id":"process_start_node","name":"流程开始任务模板","describe":"流程开始时执行的一些动作的任务。","type":"job","jobInfo":{"_id":"process_start_node","name":"流程开始任务","describe":"流程开始时执行的一些动作的任务。","plugins":[],"attributes":[{"_id":"_c614d1cac283c","name":"文本属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"文本属性标签","input_type":"text","required":false,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847952086"},{"_id":"_c2375f5a964a5","name":"数字属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"数字属性","input_type":"number","required":true,"select_options":[],"unit":"元","creat_date":"20190711","creat_timestamp":"1562847892585"}],"comments":[]},"job_drawer_width":949.5}}
         * stopNode : {"type":"process_stop_node","job_drawer_visible":false,"job_drawer_width":"","element":{"_id":"process_stop_node","name":"流程结束任务模板","describe":"流程结束时执行的一些动作的任务。","type":"job","info":{"_id":"process_stop_node_job","name":"流程结束任务","describe":"流程开始时执行的一些动作的任务。","plugins":[],"attributes":[],"comments":[]}}}
         * type : process
         * next_process_list : []
         * process_type : apply_process
         * process_type_name : 申请流程
         * creat_date : 20190711
         * creat_timestamp : 1562848230576
         */

        private String _id;
        private String name;
        private String describe;
        private StartNodeBean startNode;
        private StopNodeBean stopNode;
        private String type;
        private String process_type;
        private String process_type_name;
        private String creat_date;
        private String creat_timestamp;
        private List<NodesBean> nodes;
        private List<NextProcessListBean> next_process_list;

        @JSONField(name="_id")
        public String get_id() {
            return _id;
        }

        @JSONField(name="_id")
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

        public StartNodeBean getStartNode() {
            return startNode;
        }

        public void setStartNode(StartNodeBean startNode) {
            this.startNode = startNode;
        }

        public StopNodeBean getStopNode() {
            return stopNode;
        }

        public void setStopNode(StopNodeBean stopNode) {
            this.stopNode = stopNode;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getProcess_type() {
            return process_type;
        }

        public void setProcess_type(String process_type) {
            this.process_type = process_type;
        }

        public String getProcess_type_name() {
            return process_type_name;
        }

        public void setProcess_type_name(String process_type_name) {
            this.process_type_name = process_type_name;
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

        public List<NodesBean> getNodes() {
            return nodes;
        }

        public void setNodes(List<NodesBean> nodes) {
            this.nodes = nodes;
        }

        public List<NextProcessListBean> getNext_process_list() {
            return next_process_list;
        }

        public void setNext_process_list(List<NextProcessListBean> next_process_list) {
            this.next_process_list = next_process_list;
        }

        public static class StartNodeBean implements Serializable{
            /**
             * type : process_start_node
             * job_drawer_visible : false
             * job_drawer_width : 949.5
             * element : {"_id":"process_start_node","name":"流程开始任务模板","describe":"流程开始时执行的一些动作的任务。","type":"job","jobInfo":{"_id":"process_start_node","name":"流程开始任务","describe":"流程开始时执行的一些动作的任务。","plugins":[],"attributes":[{"_id":"_c614d1cac283c","name":"文本属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"文本属性标签","input_type":"text","required":false,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847952086"},{"_id":"_c2375f5a964a5","name":"数字属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"数字属性","input_type":"number","required":true,"select_options":[],"unit":"元","creat_date":"20190711","creat_timestamp":"1562847892585"}],"comments":[]},"job_drawer_width":949.5}
             */

            private String type;
            private boolean job_drawer_visible;
            private double job_drawer_width;
            private ElementBean element;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public boolean isJob_drawer_visible() {
                return job_drawer_visible;
            }

            public void setJob_drawer_visible(boolean job_drawer_visible) {
                this.job_drawer_visible = job_drawer_visible;
            }

            public double getJob_drawer_width() {
                return job_drawer_width;
            }

            public void setJob_drawer_width(double job_drawer_width) {
                this.job_drawer_width = job_drawer_width;
            }

            public ElementBean getElement() {
                return element;
            }

            public void setElement(ElementBean element) {
                this.element = element;
            }

            public static class ElementBean implements Serializable{
                /**
                 * _id : process_start_node
                 * name : 流程开始任务模板
                 * describe : 流程开始时执行的一些动作的任务。
                 * type : job
                 * jobInfo : {"_id":"process_start_node","name":"流程开始任务","describe":"流程开始时执行的一些动作的任务。","plugins":[],"attributes":[{"_id":"_c614d1cac283c","name":"文本属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"文本属性标签","input_type":"text","required":false,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847952086"},{"_id":"_c2375f5a964a5","name":"数字属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"数字属性","input_type":"number","required":true,"select_options":[],"unit":"元","creat_date":"20190711","creat_timestamp":"1562847892585"}],"comments":[]}
                 * job_drawer_width : 949.5
                 */

                private String _id;
                private String name;
                private String describe;
                private String type;
                private JobInfoBean jobInfo;
                private double job_drawer_width;

                @JSONField(name="_id")
                public String get_id() {
                    return _id;
                }

                @JSONField(name="_id")
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

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public JobInfoBean getJobInfo() {
                    return jobInfo;
                }

                public void setJobInfo(JobInfoBean jobInfo) {
                    this.jobInfo = jobInfo;
                }

                public double getJob_drawer_width() {
                    return job_drawer_width;
                }

                public void setJob_drawer_width(double job_drawer_width) {
                    this.job_drawer_width = job_drawer_width;
                }

                public static class JobInfoBean implements Serializable{
                    /**
                     * _id : process_start_node
                     * name : 流程开始任务
                     * describe : 流程开始时执行的一些动作的任务。
                     * plugins : []
                     * attributes : [{"_id":"_c614d1cac283c","name":"文本属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"文本属性标签","input_type":"text","required":false,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847952086"},{"_id":"_c2375f5a964a5","name":"数字属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"数字属性","input_type":"number","required":true,"select_options":[],"unit":"元","creat_date":"20190711","creat_timestamp":"1562847892585"}]
                     * comments : []
                     */

                    private String _id;
                    private String name;
                    private String describe;
                    private List<?> plugins;
                    private List<AttributesBean> attributes;
                    private List<?> comments;

                    @JSONField(name="_id")
                    public String get_id() {
                        return _id;
                    }

                    @JSONField(name="_id")
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

                    public List<?> getPlugins() {
                        return plugins;
                    }

                    public void setPlugins(List<?> plugins) {
                        this.plugins = plugins;
                    }

                    public List<AttributesBean> getAttributes() {
                        return attributes;
                    }

                    public void setAttributes(List<AttributesBean> attributes) {
                        this.attributes = attributes;
                    }

                    public List<?> getComments() {
                        return comments;
                    }

                    public void setComments(List<?> comments) {
                        this.comments = comments;
                    }

                    public static class AttributesBean implements Serializable{
                        /**
                         * _id : _c614d1cac283c
                         * name : 文本属性标签
                         * key :
                         * plugin_type : attributetag
                         * level_type : case
                         * describe : 文本属性标签
                         * input_type : text
                         * required : false
                         * select_options : []
                         * unit :
                         * creat_date : 20190711
                         * creat_timestamp : 1562847952086
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
                        private List<?> select_options;

                        @JSONField(name="_id")
                        public String get_id() {
                            return _id;
                        }

                        @JSONField(name="_id")
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

                        public List<?> getSelect_options() {
                            return select_options;
                        }

                        public void setSelect_options(List<?> select_options) {
                            this.select_options = select_options;
                        }
                    }
                }
            }
        }

        public static class StopNodeBean implements Serializable{
            /**
             * type : process_stop_node
             * job_drawer_visible : false
             * job_drawer_width :
             * element : {"_id":"process_stop_node","name":"流程结束任务模板","describe":"流程结束时执行的一些动作的任务。","type":"job","info":{"_id":"process_stop_node_job","name":"流程结束任务","describe":"流程开始时执行的一些动作的任务。","plugins":[],"attributes":[],"comments":[]}}
             */

            private String type;
            private boolean job_drawer_visible;
            private String job_drawer_width;
            private ElementBeanX element;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public boolean isJob_drawer_visible() {
                return job_drawer_visible;
            }

            public void setJob_drawer_visible(boolean job_drawer_visible) {
                this.job_drawer_visible = job_drawer_visible;
            }

            public String getJob_drawer_width() {
                return job_drawer_width;
            }

            public void setJob_drawer_width(String job_drawer_width) {
                this.job_drawer_width = job_drawer_width;
            }

            public ElementBeanX getElement() {
                return element;
            }

            public void setElement(ElementBeanX element) {
                this.element = element;
            }

            public static class ElementBeanX implements Serializable{
                /**
                 * _id : process_stop_node
                 * name : 流程结束任务模板
                 * describe : 流程结束时执行的一些动作的任务。
                 * type : job
                 * info : {"_id":"process_stop_node_job","name":"流程结束任务","describe":"流程开始时执行的一些动作的任务。","plugins":[],"attributes":[],"comments":[]}
                 */

                private String _id;
                private String name;
                private String describe;
                private String type;
                private InfoBean info;

                @JSONField(name="_id")
                public String get_id() {
                    return _id;
                }

                @JSONField(name="_id")
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

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public InfoBean getInfo() {
                    return info;
                }

                public void setInfo(InfoBean info) {
                    this.info = info;
                }

                public static class InfoBean implements Serializable{
                    /**
                     * _id : process_stop_node_job
                     * name : 流程结束任务
                     * describe : 流程开始时执行的一些动作的任务。
                     * plugins : []
                     * attributes : []
                     * comments : []
                     */

                    private String _id;
                    private String name;
                    private String describe;
                    private List<?> plugins;
                    private List<?> attributes;
                    private List<?> comments;

                    @JSONField(name="_id")
                    public String get_id() {
                        return _id;
                    }

                    @JSONField(name="_id")
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

                    public List<?> getPlugins() {
                        return plugins;
                    }

                    public void setPlugins(List<?> plugins) {
                        this.plugins = plugins;
                    }

                    public List<?> getAttributes() {
                        return attributes;
                    }

                    public void setAttributes(List<?> attributes) {
                        this.attributes = attributes;
                    }

                    public List<?> getComments() {
                        return comments;
                    }

                    public void setComments(List<?> comments) {
                        this.comments = comments;
                    }
                }
            }
        }

        public static class NodesBean implements Serializable{
            /**
             * type : process_node
             * plan_cycle : 0
             * elements : [{"name":"测试任务1","type":"job","info":{"_id":"_c2861aa55e8e0","name":"测试任务1","describe":"测试任务1","attributes":[{"_id":"_c755f9f3300d9","name":"勾选标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"勾选标签","input_type":"check","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562848058047"},{"_id":"_c88b03bea4b0c","name":"选择属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"选择属性标签","input_type":"select","required":true,"select_options":[{"value":"一级选项1","label":"一级选项1","children":[{"value":"二级选项1","label":"二级选项1"},{"value":"二级选项2","label":"二级选项2"}]},{"value":"一级选项2","label":"一级选项2","children":[]}],"unit":"","creat_date":"20190711","creat_timestamp":"1562848035917"},{"_id":"_c614d1cac283c","name":"文本属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"文本属性标签","input_type":"text","required":false,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847952086"},{"_id":"_c46a449db7bfa","name":"日期范围","key":"","plugin_type":"attributetag","level_type":"case","describe":"日期范围","input_type":"date_range","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847930890"},{"_id":"_c0b7dd26cad21","name":"日期属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"日期属性","input_type":"date","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847911387"},{"_id":"_c2375f5a964a5","name":"数字属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"数字属性","input_type":"number","required":true,"select_options":[],"unit":"元","creat_date":"20190711","creat_timestamp":"1562847892585"}],"files":[{"_id":"_c76cb8920b63b","name":"测试文件","key":"","plugin_type":"filetag","level_type":"case","describe":"测试文件","creat_date":"20190711","creat_timestamp":"1562847843802"}],"comments":[{"_id":"_cc562bf2f2654","name":"测试评论","key":"","plugin_type":"commenttag","level_type":"case","describe":"测试评论","creat_date":"20190711","creat_timestamp":"1562847860743"}],"received":[],"messages":[],"status":"","creat_date":"20190711","creat_timestamp":"1562848073922","time":{"_id":"_ce1760a5a4ecd","name":"时间插件","type":"time_flow","plugin_type":"time","describe":"时间插件","time_length":10,"time_span":0,"alert_span":0,"start_type":"auto","start_delay_span":0,"creat_date":"20190711","creat_timestamp":"1562847824205"}}}]
             */

            private String type;
            private int plan_cycle;
            private List<ElementsBean> elements;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getPlan_cycle() {
                return plan_cycle;
            }

            public void setPlan_cycle(int plan_cycle) {
                this.plan_cycle = plan_cycle;
            }

            public List<ElementsBean> getElements() {
                return elements;
            }

            public void setElements(List<ElementsBean> elements) {
                this.elements = elements;
            }

            public static class ElementsBean implements Serializable{
                /**
                 * name : 测试任务1
                 * type : job
                 * info : {"_id":"_c2861aa55e8e0","name":"测试任务1","describe":"测试任务1","attributes":[{"_id":"_c755f9f3300d9","name":"勾选标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"勾选标签","input_type":"check","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562848058047"},{"_id":"_c88b03bea4b0c","name":"选择属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"选择属性标签","input_type":"select","required":true,"select_options":[{"value":"一级选项1","label":"一级选项1","children":[{"value":"二级选项1","label":"二级选项1"},{"value":"二级选项2","label":"二级选项2"}]},{"value":"一级选项2","label":"一级选项2","children":[]}],"unit":"","creat_date":"20190711","creat_timestamp":"1562848035917"},{"_id":"_c614d1cac283c","name":"文本属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"文本属性标签","input_type":"text","required":false,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847952086"},{"_id":"_c46a449db7bfa","name":"日期范围","key":"","plugin_type":"attributetag","level_type":"case","describe":"日期范围","input_type":"date_range","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847930890"},{"_id":"_c0b7dd26cad21","name":"日期属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"日期属性","input_type":"date","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847911387"},{"_id":"_c2375f5a964a5","name":"数字属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"数字属性","input_type":"number","required":true,"select_options":[],"unit":"元","creat_date":"20190711","creat_timestamp":"1562847892585"}],"files":[{"_id":"_c76cb8920b63b","name":"测试文件","key":"","plugin_type":"filetag","level_type":"case","describe":"测试文件","creat_date":"20190711","creat_timestamp":"1562847843802"}],"comments":[{"_id":"_cc562bf2f2654","name":"测试评论","key":"","plugin_type":"commenttag","level_type":"case","describe":"测试评论","creat_date":"20190711","creat_timestamp":"1562847860743"}],"received":[],"messages":[],"status":"","creat_date":"20190711","creat_timestamp":"1562848073922","time":{"_id":"_ce1760a5a4ecd","name":"时间插件","type":"time_flow","plugin_type":"time","describe":"时间插件","time_length":10,"time_span":0,"alert_span":0,"start_type":"auto","start_delay_span":0,"creat_date":"20190711","creat_timestamp":"1562847824205"}}
                 */

                private String name;
                private String type;
                private InfoBeanX info;

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

                public InfoBeanX getInfo() {
                    return info;
                }

                public void setInfo(InfoBeanX info) {
                    this.info = info;
                }

                public static class InfoBeanX implements Serializable{
                    /**
                     * _id : _c2861aa55e8e0
                     * name : 测试任务1
                     * describe : 测试任务1
                     * attributes : [{"_id":"_c755f9f3300d9","name":"勾选标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"勾选标签","input_type":"check","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562848058047"},{"_id":"_c88b03bea4b0c","name":"选择属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"选择属性标签","input_type":"select","required":true,"select_options":[{"value":"一级选项1","label":"一级选项1","children":[{"value":"二级选项1","label":"二级选项1"},{"value":"二级选项2","label":"二级选项2"}]},{"value":"一级选项2","label":"一级选项2","children":[]}],"unit":"","creat_date":"20190711","creat_timestamp":"1562848035917"},{"_id":"_c614d1cac283c","name":"文本属性标签","key":"","plugin_type":"attributetag","level_type":"case","describe":"文本属性标签","input_type":"text","required":false,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847952086"},{"_id":"_c46a449db7bfa","name":"日期范围","key":"","plugin_type":"attributetag","level_type":"case","describe":"日期范围","input_type":"date_range","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847930890"},{"_id":"_c0b7dd26cad21","name":"日期属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"日期属性","input_type":"date","required":true,"select_options":[],"unit":"","creat_date":"20190711","creat_timestamp":"1562847911387"},{"_id":"_c2375f5a964a5","name":"数字属性","key":"","plugin_type":"attributetag","level_type":"case","describe":"数字属性","input_type":"number","required":true,"select_options":[],"unit":"元","creat_date":"20190711","creat_timestamp":"1562847892585"}]
                     * files : [{"_id":"_c76cb8920b63b","name":"测试文件","key":"","plugin_type":"filetag","level_type":"case","describe":"测试文件","creat_date":"20190711","creat_timestamp":"1562847843802"}]
                     * comments : [{"_id":"_cc562bf2f2654","name":"测试评论","key":"","plugin_type":"commenttag","level_type":"case","describe":"测试评论","creat_date":"20190711","creat_timestamp":"1562847860743"}]
                     * received : []
                     * messages : []
                     * status :
                     * creat_date : 20190711
                     * creat_timestamp : 1562848073922
                     * time : {"_id":"_ce1760a5a4ecd","name":"时间插件","type":"time_flow","plugin_type":"time","describe":"时间插件","time_length":10,"time_span":0,"alert_span":0,"start_type":"auto","start_delay_span":0,"creat_date":"20190711","creat_timestamp":"1562847824205"}
                     */

                    private String _id;
                    private String name;
                    private String describe;
                    private String status;
                    private String creat_date;
                    private String creat_timestamp;
                    private TimeBean time;
                    private List<AttributesBeanX> attributes;
                    private List<FilesBean> files;
                    private List<CommentsBean> comments;
                    private List<?> received;
                    private List<?> messages;

                    @JSONField(name="_id")
                    public String get_id() {
                        return _id;
                    }

                    @JSONField(name="_id")
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

                    public List<AttributesBeanX> getAttributes() {
                        return attributes;
                    }

                    public void setAttributes(List<AttributesBeanX> attributes) {
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
                        private int time_length;
                        private int time_span;
                        private int alert_span;
                        private String start_type;
                        private int start_delay_span;
                        private String creat_date;
                        private String creat_timestamp;

                        @JSONField(name="_id")
                        public String get_id() {
                            return _id;
                        }

                        @JSONField(name="_id")
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

                        public int getTime_length() {
                            return time_length;
                        }

                        public void setTime_length(int time_length) {
                            this.time_length = time_length;
                        }

                        public int getTime_span() {
                            return time_span;
                        }

                        public void setTime_span(int time_span) {
                            this.time_span = time_span;
                        }

                        public int getAlert_span() {
                            return alert_span;
                        }

                        public void setAlert_span(int alert_span) {
                            this.alert_span = alert_span;
                        }

                        public String getStart_type() {
                            return start_type;
                        }

                        public void setStart_type(String start_type) {
                            this.start_type = start_type;
                        }

                        public int getStart_delay_span() {
                            return start_delay_span;
                        }

                        public void setStart_delay_span(int start_delay_span) {
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

                    public static class AttributesBeanX implements Serializable{
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
                        private boolean required;
                        private String unit;
                        private String creat_date;
                        private String creat_timestamp;
                        private List<?> select_options;

                        @JSONField(name="_id")
                        public String get_id() {
                            return _id;
                        }

                        @JSONField(name="_id")
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

                        @JSONField(name="_id")
                        public String get_id() {
                            return _id;
                        }

                        @JSONField(name="_id")
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

                        @JSONField(name="_id")
                        public String get_id() {
                            return _id;
                        }

                        @JSONField(name="_id")
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
                }
            }
        }
        public static class NextProcessListBean implements Serializable{
            /**
             * _id : _c16915da2fae7
             * name : 流程模版2
             */

            private String _id;
            private String name;

            @JSONField(name="_id")
            public String get_id() {
                return _id;
            }

            @JSONField(name="_id")
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
