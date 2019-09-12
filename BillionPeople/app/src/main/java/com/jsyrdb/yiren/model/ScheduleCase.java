package com.jsyrdb.yiren.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class ScheduleCase {

    /**
     * error : null
     * result : [{"_id":"2019-08-02","count":7,"date":"2019-08-02","jobList":[{"_id":"job_c4bcf91f81574","creat_date":"20190731","creat_timestamp":"1564565704669","describe":"测试任务2","id":"_c2ed2493102aa","name":"测试任务2","status":"running","templateid":null,"customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_cacf79174993d","casename":"南京科技公司_777_20190731","processid":"process_c27622ba9002d","processname":"申请模版730","nodeidx":0,"nodename":"node","jobidx":0,"creat_userid":"cffd116b073e4","creat_username":"admin","job_manager":{"_id":"cffd116b073e4","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_cbe9e419305b1","name":"notime","type":"time_noneed","plugin_type":"time","describe":"notime","time_length":0,"time_span":0,"alert_span":0,"start_type":"auto","start_delay_span":0,"status":"running","tag_id":"_c243370728e0b","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_cacf79174993d","casename":"南京科技公司_777_20190731","processid":"process_c27622ba9002d","processname":"申请模版730","jobid":"job_c4bcf91f81574","jobname":"测试任务2","creat_date":"20190731","creat_timestamp":"1564565704911","tagidx":0,"actual_start":"1564733290235"},"groupDateStamp":"1564733290235","dateM":"2019-08","dateDay":"2019-08-02"},{"_id":"job_c5bd7ee9e9a51","creat_date":"20190731","creat_timestamp":"1564566002665","describe":"测试任务","id":"_c2861aa55e8e0","name":"测试任务","status":"running","templateid":null,"customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c392c93a02ea0","casename":"客户1_2525_20190731","processid":"process_c0d28e818fc01","processname":"测试模版","nodeidx":1,"nodename":"node","jobidx":0,"creat_userid":"cffd116b073e4","creat_username":"admin","job_manager":{"_id":"cffd116b073e4","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c13c4cea5ae8b","name":"notime","type":"time_noneed","plugin_type":"time","describe":"notime","time_length":0,"time_span":0,"alert_span":0,"start_type":"auto","start_delay_span":0,"status":"running","tag_id":"_c243370728e0b","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c392c93a02ea0","casename":"客户1_2525_20190731","processid":"process_c0d28e818fc01","processname":"测试模版","jobid":"job_c5bd7ee9e9a51","jobname":"测试任务","creat_date":"20190731","creat_timestamp":"1564566003035","tagidx":0,"actual_start":"1564731528695"},"groupDateStamp":"1564731528695","dateM":"2019-08","dateDay":"2019-08-02"},{"_id":"job_c4fd18e9507c2","name":"测试任务2","describe":"测试任务2","status":"running","creat_date":"20190802","creat_timestamp":"1564733349924","templateid":"_c2ed2493102aa","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c392c93a02ea0","casename":"客户1_2525_20190731","processid":"process_c1f335510b58b","processname":"测试模版","nodeidx":0,"nodename":"node","jobidx":0,"creat_userid":"cffd116b073e4","creat_username":"admin","job_manager":{"_id":"cffd116b073e4","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c10e03ff67f02","name":"notime","type":"time_noneed","plugin_type":"time","describe":"notime","time_length":0,"time_span":0,"alert_span":0,"start_type":"auto","start_delay_span":0,"status":"running","tag_id":"_c243370728e0b","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c392c93a02ea0","casename":"客户1_2525_20190731","processid":"process_c1f335510b58b","processname":"测试模版","jobid":"job_c4fd18e9507c2","jobname":"测试任务2","creat_date":"20190802","creat_timestamp":"1564733350181","tagidx":0,"actual_start":"1564733361798"},"groupDateStamp":"1564733361798","dateM":"2019-08","dateDay":"2019-08-02"},{"_id":"startjob_c551897161395","describe":"流程开始时执行的一些动作的任务。","name":"流程开始任务","templateid":"process_start_node","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_cb117a3c7b956","casename":"客户1_0802_20190802","processid":"process_cb9af7309ce0e","processname":"流程模版004","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190802","creat_timestamp":"1564737612133","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","describe":"事实上事实哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c345bca4cc35c","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1564737612133","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1564993212133","stop_alert":"1564986012133","tag_id":"startnode_time","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_cb117a3c7b956","casename":"客户1_0802_20190802","processid":"process_cb9af7309ce0e","processname":"流程模版004","jobid":"startjob_c551897161395","jobname":"流程开始任务","creat_date":"20190802","creat_timestamp":"1564737612246","tagidx":0},"groupDateStamp":"1564737612133","dateM":"2019-08","dateDay":"2019-08-02"},{"_id":"startjob_ce5189802e216","describe":"流程开始时执行的一些动作的任务。","id":"process_start_node","name":"流程开始任务","plugins":[],"templateid":"process_start_node","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_c450b129318f5","casename":"南京科技公司_我的我的_20190802","processid":"process_c3e922f0f431c","processname":"流程模版004","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190802","creat_timestamp":"1564739861124","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","describe":"事实上事实哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c743fb328ad47","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1564739861124","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1564993800000","stop_alert":"1564986600000","tag_id":"startnode_time","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_c450b129318f5","casename":"南京科技公司_我的我的_20190802","processid":"process_c3e922f0f431c","processname":"流程模版004","jobid":"startjob_ce5189802e216","jobname":"流程开始任务","creat_date":"20190802","creat_timestamp":"1564739861235","tagidx":0},"groupDateStamp":"1564739861124","dateM":"2019-08","dateDay":"2019-08-02"},{"_id":"startjob_c4492c73fc897","describe":"流程开始时执行的一些动作的任务。","id":"process_start_node","name":"流程开始任务","plugins":[],"templateid":"process_start_node","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_c2a28021cd326","casename":"南京科技公司_阿拉伯_20190802","processid":"process_ccb81a526da05","processname":"流程模版004","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190802","creat_timestamp":"1564742275109","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","describe":"事实上事实哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_cc8eb3b35725a","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1564742275109","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1564993800000","stop_alert":"1564986600000","tag_id":"startnode_time","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_c2a28021cd326","casename":"南京科技公司_阿拉伯_20190802","processid":"process_ccb81a526da05","processname":"流程模版004","jobid":"startjob_c4492c73fc897","jobname":"流程开始任务","creat_date":"20190802","creat_timestamp":"1564742275215","tagidx":0},"groupDateStamp":"1564742275109","dateM":"2019-08","dateDay":"2019-08-02"},{"_id":"startjob_c6cf04aae2e5d","describe":"流程开始时执行的一些动作的任务。","id":"process_start_node","name":"流程开始任务","plugins":[],"templateid":"process_start_node","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_c1ed9b7737c31","casename":"南京科技公司_4446655_20190802","processid":"process_c27ec7bda2342","processname":"流程模版004","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190802","creat_timestamp":"1564742794970","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","describe":"事实上事实哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c9f61ffe85593","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1564742794970","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1564993800000","stop_alert":"1564986600000","tag_id":"startnode_time","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_c1ed9b7737c31","casename":"南京科技公司_4446655_20190802","processid":"process_c27ec7bda2342","processname":"流程模版004","jobid":"startjob_c6cf04aae2e5d","jobname":"流程开始任务","creat_date":"20190802","creat_timestamp":"1564742795077","tagidx":0},"groupDateStamp":"1564742794970","dateM":"2019-08","dateDay":"2019-08-02"}]},{"_id":"2019-08-05","count":4,"date":"2019-08-05","jobList":[{"_id":"startjob_cd7cdfc7bfd3b","describe":"流程开始时执行的一些动作的任务。","name":"流程开始任务","templateid":"process_start_node","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c10ffeb0676f1","casename":"客户1_101010_20190805","processid":"process_cc7a536617761","processname":"流程模版004","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190805","creat_timestamp":"1564977187260","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","describe":"事实上事实哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_cb941388aadc2","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1564977187260","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1565059987260","stop_alert":"1565052787260","tag_id":"startnode_time","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c10ffeb0676f1","casename":"客户1_101010_20190805","processid":"process_cc7a536617761","processname":"流程模版004","jobid":"startjob_cd7cdfc7bfd3b","jobname":"流程开始任务","creat_date":"20190805","creat_timestamp":"1564977187371","tagidx":0},"groupDateStamp":"1564977187260","dateM":"2019-08","dateDay":"2019-08-05"},{"_id":"startjob_ce9b9e0348a4f","describe":"流程开始时执行的一些动作的任务。","name":"流程开始任务","templateid":"process_start_node","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_cc0297b6838f4","casename":"客户1_6666_20190805","processid":"process_cfc9cbeee0cb2","processname":"流程模版004","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190805","creat_timestamp":"1564977382807","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","describe":"事实上事实哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c983ec5cd2bfd","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1564977382807","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1565060182807","stop_alert":"1565052982807","tag_id":"startnode_time","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_cc0297b6838f4","casename":"客户1_6666_20190805","processid":"process_cfc9cbeee0cb2","processname":"流程模版004","jobid":"startjob_ce9b9e0348a4f","jobname":"流程开始任务","creat_date":"20190805","creat_timestamp":"1564977382916","tagidx":0},"groupDateStamp":"1564977382807","dateM":"2019-08","dateDay":"2019-08-05"},{"_id":"startjob_cf8dce607e3a8","describe":"流程开始时执行的一些动作的任务。","id":"process_start_node","name":"流程开始任务","plugins":[],"templateid":"process_start_node","customerid":"customer_c4520cb701105","customername":"上海安西信息科技有限分公司","caseid":"case_c0c512240fb34","casename":"上海安西信息科技有限分公司_666_20190805","processid":"process_c653a4bce9623","processname":"流程模版004","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190805","creat_timestamp":"1564986288145","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","describe":"事实上事实哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_cf240154afc0e","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1564986288145","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1565317488145","stop_alert":"1565061888145","tag_id":"startnode_time","customerid":"customer_c4520cb701105","customername":"上海安西信息科技有限分公司","caseid":"case_c0c512240fb34","casename":"上海安西信息科技有限分公司_666_20190805","processid":"process_c653a4bce9623","processname":"流程模版004","jobid":"startjob_cf8dce607e3a8","jobname":"流程开始任务","creat_date":"20190805","creat_timestamp":"1564986288257","tagidx":0,"end_delay_span":24},"groupDateStamp":"1564986288145","dateM":"2019-08","dateDay":"2019-08-05"},{"_id":"job_c10536790b603","creat_date":"20190805","creat_timestamp":"1564986288145","describe":"测试任务3","id":"_cf32ecaba2f14","name":"测试任务3","status":"running","templateid":null,"customerid":"customer_c4520cb701105","customername":"上海安西信息科技有限分公司","caseid":"case_c0c512240fb34","casename":"上海安西信息科技有限分公司_666_20190805","processid":"process_c653a4bce9623","processname":"流程模版004","nodeidx":0,"nodename":"node","jobidx":0,"creat_userid":"cffd116b073e4","creat_username":"admin","job_manager":{"_id":"cffd116b073e4","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c1dd63e08e8c5","alert_span":10,"creat_date":"20190805","creat_timestamp":"1564986288376","describe":"插件2","id":"_cc156b61a09fc","name":"时间插件2","plugin_type":"time","start_delay_span":0,"start_type":"auto","time_length":10,"time_span":0,"type":"time_flow","status":"running","tag_id":null,"customerid":"customer_c4520cb701105","customername":"上海安西信息科技有限分公司","caseid":"case_c0c512240fb34","casename":"上海安西信息科技有限分公司_666_20190805","processid":"process_c653a4bce9623","processname":"流程模版004","jobid":"job_c10536790b603","jobname":"测试任务3","tagidx":0,"actual_start":"1565014412806","should_stop":"1565170200000","stop_alert":"1565051400000","end_delay_span":8},"groupDateStamp":"1565014412806","dateM":"2019-08","dateDay":"2019-08-05"}]},{"_id":"2019-08-06","count":7,"date":"2019-08-06","jobList":[{"_id":"startjob_c97de117ae90a","describe":"流程开始时执行的一些动作的任务。","id":"process_start_node","name":"流程开始任务","plugins":[],"templateid":"process_start_node","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_c7fa13d84c676","casename":"南京科技公司_xiao_20190806","processid":"process_cfd1a8018415e","processname":"申请模版730","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190806","creat_timestamp":"1565058833168","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","describe":"事实上事实哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_cc0f7a935572f","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1565058833168","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1565141633168","stop_alert":"1565080433168","tag_id":"startnode_time","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_c7fa13d84c676","casename":"南京科技公司_xiao_20190806","processid":"process_cfd1a8018415e","processname":"申请模版730","jobid":"startjob_c97de117ae90a","jobname":"流程开始任务","creat_date":"20190806","creat_timestamp":"1565058833313","tagidx":0},"groupDateStamp":"1565058833168","dateM":"2019-08","dateDay":"2019-08-06"},{"_id":"startjob_c526e3d6a4f5c","describe":"流程开始时执行的一些动作的任务。","id":"process_start_node","name":"流程开始任务","plugins":[],"templateid":"process_start_node","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c2d86ebf8135a","casename":"客户1_dada_20190806","processid":"process_c7801146b3d4f","processname":"测试模版","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190806","creat_timestamp":"1565060094132","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","describe":"事实上事实哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_cf8598e552d74","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1565060094132","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1565142894132","stop_alert":"1565081694132","tag_id":"startnode_time","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c2d86ebf8135a","casename":"客户1_dada_20190806","processid":"process_c7801146b3d4f","processname":"测试模版","jobid":"startjob_c526e3d6a4f5c","jobname":"流程开始任务","creat_date":"20190806","creat_timestamp":"1565060094267","tagidx":0},"groupDateStamp":"1565060094132","dateM":"2019-08","dateDay":"2019-08-06"},{"_id":"startjob_cc9552aa38954","plugins":[],"name":"流程开始任务","describe":"流程开始时执行的一些动作的任务。","templateid":"process_start_node","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c2d86ebf8135a","casename":"客户1_dada_20190806","processid":"process_c419c0cb1c000","processname":"流程模版004","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190806","creat_timestamp":"1565070783360","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","describe":"事实上事实哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c9b0662366940","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1565070783360","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1565153583360","stop_alert":"1565146383360","tag_id":"startnode_time","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c2d86ebf8135a","casename":"客户1_dada_20190806","processid":"process_c419c0cb1c000","processname":"流程模版004","jobid":"startjob_cc9552aa38954","jobname":"流程开始任务","creat_date":"20190806","creat_timestamp":"1565070783425","tagidx":0},"groupDateStamp":"1565070783360","dateM":"2019-08","dateDay":"2019-08-06"},{"_id":"startjob_c92aaf804b9e9","plugins":[],"name":"流程开始任务","describe":"流程开始时执行的一些动作的任务。","templateid":"process_start_node","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_c7fa13d84c676","casename":"南京科技公司_xiao_20190806","processid":"process_cdcfdd654ded6","processname":"流程模版004","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190806","creat_timestamp":"1565070793829","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","describe":"事实上事实哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_cc54ca38b011e","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1565070793829","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1565153593829","stop_alert":"1565146393829","tag_id":"startnode_time","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_c7fa13d84c676","casename":"南京科技公司_xiao_20190806","processid":"process_cdcfdd654ded6","processname":"流程模版004","jobid":"startjob_c92aaf804b9e9","jobname":"流程开始任务","creat_date":"20190806","creat_timestamp":"1565070793893","tagidx":0},"groupDateStamp":"1565070793829","dateM":"2019-08","dateDay":"2019-08-06"},{"_id":"startjob_ca00c40db4a83","plugins":[],"name":"流程开始任务","describe":"流程开始时执行的一些动作的任务。","templateid":"process_start_node","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c2d86ebf8135a","casename":"客户1_dada_20190806","processid":"process_c5e3fea302ea5","processname":"申请模版730","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190806","creat_timestamp":"1565074193092","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","describe":"事实上事实哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c418207490892","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1565074193092","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1565156993092","stop_alert":"1565149793092","tag_id":"startnode_time","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c2d86ebf8135a","casename":"客户1_dada_20190806","processid":"process_c5e3fea302ea5","processname":"申请模版730","jobid":"startjob_ca00c40db4a83","jobname":"流程开始任务","creat_date":"20190806","creat_timestamp":"1565074193183","tagidx":0},"groupDateStamp":"1565074193092","dateM":"2019-08","dateDay":"2019-08-06"},{"_id":"startjob_cf5a7579cfeff","plugins":[],"name":"流程开始任务","describe":"流程开始时执行的一些动作的任务。","templateid":"process_start_node","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c2810ae5b1d9b","casename":"客户1_20190726","processid":"process_cbe615032f20f","processname":"流程模版004","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190806","creat_timestamp":"1565080810181","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","describe":"事实上事实哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c0338325aa177","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1565080810181","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1565163610181","stop_alert":"1565156410181","tag_id":"startnode_time","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c2810ae5b1d9b","casename":"客户1_20190726","processid":"process_cbe615032f20f","processname":"流程模版004","jobid":"startjob_cf5a7579cfeff","jobname":"流程开始任务","creat_date":"20190806","creat_timestamp":"1565080810246","tagidx":0},"groupDateStamp":"1565080810181","dateM":"2019-08","dateDay":"2019-08-06"},{"_id":"startjob_c7b32a03fb693","plugins":[],"name":"流程开始任务","describe":"流程开始时执行的一些动作的任务。","templateid":"process_start_node","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_cc61b85c4b2ed","casename":"客户1_开始测试_20190724","processid":"process_c64dc382bde38","processname":"测试模版","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190806","creat_timestamp":"1565084927701","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","online":true,"describe":"事实上事实哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_ce136fa33742b","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1565084927701","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1565166600000","stop_alert":"1565159400000","tag_id":"startnode_time","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_cc61b85c4b2ed","casename":"客户1_开始测试_20190724","processid":"process_c64dc382bde38","processname":"测试模版","jobid":"startjob_c7b32a03fb693","jobname":"流程开始任务","creat_date":"20190806","creat_timestamp":"1565084927788","tagidx":0},"groupDateStamp":"1565084927701","dateM":"2019-08","dateDay":"2019-08-06"}]},{"_id":"2019-08-07","count":3,"date":"2019-08-07","jobList":[{"_id":"startjob_cee0f0dd6f34f","plugins":[],"name":"流程开始任务","describe":"流程开始时执行的一些动作的任务。","templateid":"process_start_node","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c66266fba076f","casename":"客户1_test_20190711","processid":"process_c6784ea98df1c","processname":"流程模版004","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190807","creat_timestamp":"1565155448430","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","online":false,"describe":"事实上事实哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c1c7a9300a1a0","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1565155448430","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1565238248430","stop_alert":"1565231048430","tag_id":"startnode_time","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c66266fba076f","casename":"客户1_test_20190711","processid":"process_c6784ea98df1c","processname":"流程模版004","jobid":"startjob_cee0f0dd6f34f","jobname":"流程开始任务","creat_date":"20190807","creat_timestamp":"1565155448498","tagidx":0},"groupDateStamp":"1565155448430","dateM":"2019-08","dateDay":"2019-08-07"},{"_id":"startjob_cdc96e8b212d9","plugins":[],"name":"流程开始任务","describe":"流程开始时执行的一些动作的任务。","templateid":"process_start_node","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_c7fa13d84c676","casename":"南京科技公司_xiao_20190806","processid":"process_c403819b6a86b","processname":"流程模版004","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190807","creat_timestamp":"1565156337265","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","describe":"事实上事实哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c877a225cedcb","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1565156337265","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1565239137265","stop_alert":"1565231937265","tag_id":"startnode_time","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_c7fa13d84c676","casename":"南京科技公司_xiao_20190806","processid":"process_c403819b6a86b","processname":"流程模版004","jobid":"startjob_cdc96e8b212d9","jobname":"流程开始任务","creat_date":"20190807","creat_timestamp":"1565156337326","tagidx":0},"groupDateStamp":"1565156337265","dateM":"2019-08","dateDay":"2019-08-07"},{"_id":"startjob_cb608aebb4250","plugins":[],"name":"流程开始任务","describe":"流程开始时执行的一些动作的任务。","templateid":"process_start_node","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c66266fba076f","casename":"客户1_test_20190711","processid":"process_c6f1a8e51aec7","processname":"流程模版004","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190807","creat_timestamp":"1565166115097","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","online":true,"describe":"事实上事实哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c83a5083506ab","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1565166115097","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1565248915097","stop_alert":"1565241715097","tag_id":"startnode_time","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c66266fba076f","casename":"客户1_test_20190711","processid":"process_c6f1a8e51aec7","processname":"流程模版004","jobid":"startjob_cb608aebb4250","jobname":"流程开始任务","creat_date":"20190807","creat_timestamp":"1565166115163","tagidx":0},"groupDateStamp":"1565166115097","dateM":"2019-08","dateDay":"2019-08-07"}]},{"_id":"2019-08-20","count":2,"date":"2019-08-20","jobList":[{"_id":"startjob_c1d64b0839854","describe":"流程开始时执行的一些动作的任务。","name":"流程开始任务","templateid":"process_start_node","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_cda6e3f48b331","casename":"客户1_567_20190820","processid":"process_c29c483722b18","processname":"流程模版004","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190820","creat_timestamp":"1566267843358","creat_userid":"_c0f18f878ca6d","creat_username":"特朗普","status":"running","job_manager":{"_id":"cffd116b073e4","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_cb293f12e2649","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1566267843358","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1566350643358","stop_alert":"1566289443358","tag_id":"startnode_time","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_cda6e3f48b331","casename":"客户1_567_20190820","processid":"process_c29c483722b18","processname":"流程模版004","jobid":"startjob_c1d64b0839854","jobname":"流程开始任务","creat_date":"20190820","creat_timestamp":"1566267843468","tagidx":0},"groupDateStamp":"1566267843358","dateM":"2019-08","dateDay":"2019-08-20"},{"_id":"startjob_cf04d4a83bb26","describe":"流程开始时执行的一些动作的任务。","name":"流程开始任务","plugins":[],"templateid":"process_start_node","customerid":"customer_c4520cb701105","customername":"上海安西信息科技有限分公司","caseid":"case_c8ba02255ed34","casename":"上海安西信息科技有限分公司_20190820","processid":"process_c49a5db7469e0","processname":"测试模版","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190820","creat_timestamp":"1566316080785","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","online":false,"describe":"事实上事实哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c9b5a3f3c76cb","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1566316080785","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1566376200000","stop_alert":"1566369000000","tag_id":"startnode_time","customerid":"customer_c4520cb701105","customername":"上海安西信息科技有限分公司","caseid":"case_c8ba02255ed34","casename":"上海安西信息科技有限分公司_20190820","processid":"process_c49a5db7469e0","processname":"测试模版","jobid":"startjob_cf04d4a83bb26","jobname":"流程开始任务","creat_date":"20190820","creat_timestamp":"1566316080912","tagidx":0},"groupDateStamp":"1566316080785","dateM":"2019-08","dateDay":"2019-08-20"}]},{"_id":"2019-08-22","count":1,"date":"2019-08-22","jobList":[{"_id":"startjob_c052d8b206cd9","plugins":[],"name":"流程开始任务","describe":"流程开始时执行的一些动作的任务。","templateid":"process_start_node","customerid":"customer_c4520cb701105","customername":"上海安西信息科技有限分公司","caseid":"case_c8ba02255ed34","casename":"上海安西信息科技有限分公司_20190820","processid":"process_c82ab4756941a","processname":"测试模版","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190822","creat_timestamp":"1566457449237","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","online":true,"describe":"事实上事实哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c2f42d1c1ea7f","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1566457449237","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1566540249237","stop_alert":"1566533049237","tag_id":"startnode_time","customerid":"customer_c4520cb701105","customername":"上海安西信息科技有限分公司","caseid":"case_c8ba02255ed34","casename":"上海安西信息科技有限分公司_20190820","processid":"process_c82ab4756941a","processname":"测试模版","jobid":"startjob_c052d8b206cd9","jobname":"流程开始任务","creat_date":"20190822","creat_timestamp":"1566457449350","tagidx":0},"groupDateStamp":"1566457449237","dateM":"2019-08","dateDay":"2019-08-22"}]},{"_id":"2019-08-23","count":2,"date":"2019-08-23","jobList":[{"_id":"startjob_cc27beecded51","describe":"流程开始时执行的一些动作的任务。","name":"流程开始任务","plugins":[],"templateid":"process_start_node","customerid":"customer_c4520cb701105","customername":"上海安西信息科技有限分公司","caseid":"case_cfb71d78fe40d","casename":"上海安西信息科技有限分公司_20190823","processid":"process_c105c306007b9","processname":"申请模版730","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190823","creat_timestamp":"1566534946868","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c03ec2316b327","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1566534946868","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1566790546868","stop_alert":"1566783346868","tag_id":"startnode_time","customerid":"customer_c4520cb701105","customername":"上海安西信息科技有限分公司","caseid":"case_cfb71d78fe40d","casename":"上海安西信息科技有限分公司_20190823","processid":"process_c105c306007b9","processname":"申请模版730","jobid":"startjob_cc27beecded51","jobname":"流程开始任务","creat_date":"20190823","creat_timestamp":"1566534946999","tagidx":0},"groupDateStamp":"1566534946868","dateM":"2019-08","dateDay":"2019-08-23"},{"_id":"startjob_c05a71f6de9eb","describe":"流程开始时执行的一些动作的任务。","name":"流程开始任务","plugins":[],"templateid":"process_start_node","customerid":"customer_c4520cb701105","customername":"上海安西信息科技有限分公司","caseid":"case_cfbb9d5633e01","casename":"上海安西信息科技有限分公司_20190823","processid":"process_c633bfd25d963","processname":"申请模版730","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190823","creat_timestamp":"1566534947591","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c4cdef0ad9489","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1566534947591","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1566790547591","stop_alert":"1566783347591","tag_id":"startnode_time","customerid":"customer_c4520cb701105","customername":"上海安西信息科技有限分公司","caseid":"case_cfbb9d5633e01","casename":"上海安西信息科技有限分公司_20190823","processid":"process_c633bfd25d963","processname":"申请模版730","jobid":"startjob_c05a71f6de9eb","jobname":"流程开始任务","creat_date":"20190823","creat_timestamp":"1566534947719","tagidx":0},"groupDateStamp":"1566534947591","dateM":"2019-08","dateDay":"2019-08-23"}]}]
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
         * _id : 2019-08-02
         * count : 7
         * date : 2019-08-02
         * jobList : [{"_id":"job_c4bcf91f81574","creat_date":"20190731","creat_timestamp":"1564565704669","describe":"测试任务2","id":"_c2ed2493102aa","name":"测试任务2","status":"running","templateid":null,"customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_cacf79174993d","casename":"南京科技公司_777_20190731","processid":"process_c27622ba9002d","processname":"申请模版730","nodeidx":0,"nodename":"node","jobidx":0,"creat_userid":"cffd116b073e4","creat_username":"admin","job_manager":{"_id":"cffd116b073e4","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_cbe9e419305b1","name":"notime","type":"time_noneed","plugin_type":"time","describe":"notime","time_length":0,"time_span":0,"alert_span":0,"start_type":"auto","start_delay_span":0,"status":"running","tag_id":"_c243370728e0b","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_cacf79174993d","casename":"南京科技公司_777_20190731","processid":"process_c27622ba9002d","processname":"申请模版730","jobid":"job_c4bcf91f81574","jobname":"测试任务2","creat_date":"20190731","creat_timestamp":"1564565704911","tagidx":0,"actual_start":"1564733290235"},"groupDateStamp":"1564733290235","dateM":"2019-08","dateDay":"2019-08-02"},{"_id":"job_c5bd7ee9e9a51","creat_date":"20190731","creat_timestamp":"1564566002665","describe":"测试任务","id":"_c2861aa55e8e0","name":"测试任务","status":"running","templateid":null,"customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c392c93a02ea0","casename":"客户1_2525_20190731","processid":"process_c0d28e818fc01","processname":"测试模版","nodeidx":1,"nodename":"node","jobidx":0,"creat_userid":"cffd116b073e4","creat_username":"admin","job_manager":{"_id":"cffd116b073e4","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c13c4cea5ae8b","name":"notime","type":"time_noneed","plugin_type":"time","describe":"notime","time_length":0,"time_span":0,"alert_span":0,"start_type":"auto","start_delay_span":0,"status":"running","tag_id":"_c243370728e0b","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c392c93a02ea0","casename":"客户1_2525_20190731","processid":"process_c0d28e818fc01","processname":"测试模版","jobid":"job_c5bd7ee9e9a51","jobname":"测试任务","creat_date":"20190731","creat_timestamp":"1564566003035","tagidx":0,"actual_start":"1564731528695"},"groupDateStamp":"1564731528695","dateM":"2019-08","dateDay":"2019-08-02"},{"_id":"job_c4fd18e9507c2","name":"测试任务2","describe":"测试任务2","status":"running","creat_date":"20190802","creat_timestamp":"1564733349924","templateid":"_c2ed2493102aa","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c392c93a02ea0","casename":"客户1_2525_20190731","processid":"process_c1f335510b58b","processname":"测试模版","nodeidx":0,"nodename":"node","jobidx":0,"creat_userid":"cffd116b073e4","creat_username":"admin","job_manager":{"_id":"cffd116b073e4","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c10e03ff67f02","name":"notime","type":"time_noneed","plugin_type":"time","describe":"notime","time_length":0,"time_span":0,"alert_span":0,"start_type":"auto","start_delay_span":0,"status":"running","tag_id":"_c243370728e0b","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c392c93a02ea0","casename":"客户1_2525_20190731","processid":"process_c1f335510b58b","processname":"测试模版","jobid":"job_c4fd18e9507c2","jobname":"测试任务2","creat_date":"20190802","creat_timestamp":"1564733350181","tagidx":0,"actual_start":"1564733361798"},"groupDateStamp":"1564733361798","dateM":"2019-08","dateDay":"2019-08-02"},{"_id":"startjob_c551897161395","describe":"流程开始时执行的一些动作的任务。","name":"流程开始任务","templateid":"process_start_node","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_cb117a3c7b956","casename":"客户1_0802_20190802","processid":"process_cb9af7309ce0e","processname":"流程模版004","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190802","creat_timestamp":"1564737612133","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","describe":"事实上事实哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c345bca4cc35c","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1564737612133","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1564993212133","stop_alert":"1564986012133","tag_id":"startnode_time","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_cb117a3c7b956","casename":"客户1_0802_20190802","processid":"process_cb9af7309ce0e","processname":"流程模版004","jobid":"startjob_c551897161395","jobname":"流程开始任务","creat_date":"20190802","creat_timestamp":"1564737612246","tagidx":0},"groupDateStamp":"1564737612133","dateM":"2019-08","dateDay":"2019-08-02"},{"_id":"startjob_ce5189802e216","describe":"流程开始时执行的一些动作的任务。","id":"process_start_node","name":"流程开始任务","plugins":[],"templateid":"process_start_node","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_c450b129318f5","casename":"南京科技公司_我的我的_20190802","processid":"process_c3e922f0f431c","processname":"流程模版004","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190802","creat_timestamp":"1564739861124","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","describe":"事实上事实哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c743fb328ad47","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1564739861124","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1564993800000","stop_alert":"1564986600000","tag_id":"startnode_time","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_c450b129318f5","casename":"南京科技公司_我的我的_20190802","processid":"process_c3e922f0f431c","processname":"流程模版004","jobid":"startjob_ce5189802e216","jobname":"流程开始任务","creat_date":"20190802","creat_timestamp":"1564739861235","tagidx":0},"groupDateStamp":"1564739861124","dateM":"2019-08","dateDay":"2019-08-02"},{"_id":"startjob_c4492c73fc897","describe":"流程开始时执行的一些动作的任务。","id":"process_start_node","name":"流程开始任务","plugins":[],"templateid":"process_start_node","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_c2a28021cd326","casename":"南京科技公司_阿拉伯_20190802","processid":"process_ccb81a526da05","processname":"流程模版004","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190802","creat_timestamp":"1564742275109","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","describe":"事实上事实哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_cc8eb3b35725a","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1564742275109","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1564993800000","stop_alert":"1564986600000","tag_id":"startnode_time","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_c2a28021cd326","casename":"南京科技公司_阿拉伯_20190802","processid":"process_ccb81a526da05","processname":"流程模版004","jobid":"startjob_c4492c73fc897","jobname":"流程开始任务","creat_date":"20190802","creat_timestamp":"1564742275215","tagidx":0},"groupDateStamp":"1564742275109","dateM":"2019-08","dateDay":"2019-08-02"},{"_id":"startjob_c6cf04aae2e5d","describe":"流程开始时执行的一些动作的任务。","id":"process_start_node","name":"流程开始任务","plugins":[],"templateid":"process_start_node","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_c1ed9b7737c31","casename":"南京科技公司_4446655_20190802","processid":"process_c27ec7bda2342","processname":"流程模版004","nodeidx":0,"nodename":"startnode","jobidx":0,"creat_date":"20190802","creat_timestamp":"1564742794970","creat_userid":"cffd116b073e4","creat_username":"admin","status":"running","job_manager":{"_id":"cffd116b073e4","mobile":"15056975386","describe":"事实上事实哈哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈","creat_date":"20190505","username":"admin","email":"792194598","name":"admin"},"job_manager_id":"cffd116b073e4","job_manager_name":"admin","timedata":{"_id":"time_c9f61ffe85593","name":"startnode_time","type":"time_flow","plugin_type":"time","describe":"startnode_time","time_length":8,"time_span":0,"alert_span":2,"actual_start":"1564742794970","start_type":"auto","start_delay_span":0,"status":"running","should_stop":"1564993800000","stop_alert":"1564986600000","tag_id":"startnode_time","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_c1ed9b7737c31","casename":"南京科技公司_4446655_20190802","processid":"process_c27ec7bda2342","processname":"流程模版004","jobid":"startjob_c6cf04aae2e5d","jobname":"流程开始任务","creat_date":"20190802","creat_timestamp":"1564742795077","tagidx":0},"groupDateStamp":"1564742794970","dateM":"2019-08","dateDay":"2019-08-02"}]
         */

        private String _id;
        private int count;
        private String date;
        private List<JobListBean> jobList;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<JobListBean> getJobList() {
            return jobList;
        }

        public void setJobList(List<JobListBean> jobList) {
            this.jobList = jobList;
        }

        public static class JobListBean {
            /**
             * _id : job_c4bcf91f81574
             * creat_date : 20190731
             * creat_timestamp : 1564565704669
             * describe : 测试任务2
             * id : _c2ed2493102aa
             * name : 测试任务2
             * status : running
             * templateid : null
             * customerid : customer_c65e5e8ad45a8
             * customername : 南京科技公司
             * caseid : case_cacf79174993d
             * casename : 南京科技公司_777_20190731
             * processid : process_c27622ba9002d
             * processname : 申请模版730
             * nodeidx : 0
             * nodename : node
             * jobidx : 0
             * creat_userid : cffd116b073e4
             * creat_username : admin
             * job_manager : {"_id":"cffd116b073e4","name":"admin"}
             * job_manager_id : cffd116b073e4
             * job_manager_name : admin
             * timedata : {"_id":"time_cbe9e419305b1","name":"notime","type":"time_noneed","plugin_type":"time","describe":"notime","time_length":0,"time_span":0,"alert_span":0,"start_type":"auto","start_delay_span":0,"status":"running","tag_id":"_c243370728e0b","customerid":"customer_c65e5e8ad45a8","customername":"南京科技公司","caseid":"case_cacf79174993d","casename":"南京科技公司_777_20190731","processid":"process_c27622ba9002d","processname":"申请模版730","jobid":"job_c4bcf91f81574","jobname":"测试任务2","creat_date":"20190731","creat_timestamp":"1564565704911","tagidx":0,"actual_start":"1564733290235"}
             * groupDateStamp : 1564733290235
             * dateM : 2019-08
             * dateDay : 2019-08-02
             * plugins : []
             */

            private String _id;
            private String creat_date;
            private String creat_timestamp;
            private String describe;
            private String id;
            private String name;
            private String status;
            private Object templateid;
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
            private TimedataBean timedata;
            private String groupDateStamp;
            private String dateM;
            private String dateDay;
            private List<?> plugins;

            @JSONField(name="_id")
            public String get_id() {
                return _id;
            }

            @JSONField(name="_id")
            public void set_id(String _id) {
                this._id = _id;
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

            public String getDescribe() {
                return describe;
            }

            public void setDescribe(String describe) {
                this.describe = describe;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getTemplateid() {
                return templateid;
            }

            public void setTemplateid(Object templateid) {
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

            public TimedataBean getTimedata() {
                return timedata;
            }

            public void setTimedata(TimedataBean timedata) {
                this.timedata = timedata;
            }

            public String getGroupDateStamp() {
                return groupDateStamp;
            }

            public void setGroupDateStamp(String groupDateStamp) {
                this.groupDateStamp = groupDateStamp;
            }

            public String getDateM() {
                return dateM;
            }

            public void setDateM(String dateM) {
                this.dateM = dateM;
            }

            public String getDateDay() {
                return dateDay;
            }

            public void setDateDay(String dateDay) {
                this.dateDay = dateDay;
            }

            public List<?> getPlugins() {
                return plugins;
            }

            public void setPlugins(List<?> plugins) {
                this.plugins = plugins;
            }

            public static class JobManagerBean {
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

            public static class TimedataBean {
                /**
                 * _id : time_cbe9e419305b1
                 * name : notime
                 * type : time_noneed
                 * plugin_type : time
                 * describe : notime
                 * time_length : 0
                 * time_span : 0
                 * alert_span : 0
                 * start_type : auto
                 * start_delay_span : 0
                 * status : running
                 * tag_id : _c243370728e0b
                 * customerid : customer_c65e5e8ad45a8
                 * customername : 南京科技公司
                 * caseid : case_cacf79174993d
                 * casename : 南京科技公司_777_20190731
                 * processid : process_c27622ba9002d
                 * processname : 申请模版730
                 * jobid : job_c4bcf91f81574
                 * jobname : 测试任务2
                 * creat_date : 20190731
                 * creat_timestamp : 1564565704911
                 * tagidx : 0
                 * actual_start : 1564733290235
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
                private String status;
                private String tag_id;
                private String customerid;
                private String customername;
                private String caseid;
                private String casename;
                private String processid;
                private String processname;
                private String jobid;
                private String jobname;
                private String creat_date;
                private String creat_timestamp;
                private int tagidx;
                private String actual_start;

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

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
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

                public int getTagidx() {
                    return tagidx;
                }

                public void setTagidx(int tagidx) {
                    this.tagidx = tagidx;
                }

                public String getActual_start() {
                    return actual_start;
                }

                public void setActual_start(String actual_start) {
                    this.actual_start = actual_start;
                }
            }
        }
    }
}
