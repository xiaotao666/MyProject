package com.jsyrdb.yiren.model;

public class UploadResult {


    /**
     * sucess : 上传成功
     * info : {"userid":"cffd116b073e4","username":"admin","jobfileid":"filetag_cae56035fdd75","customerid":"customer_ce6e96397f913","customername":"客户1","caseid":"case_c66266fba076f","casename":"客户1_test_20190711","processid":"process_c22988bcc7565","processname":"测试申请流程","jobid":"job_c25d8a84780ae","jobname":"测试任务1","mimetype":"text/plain","_id":"file_r__c56d5833a2c6b","fileid":"file__c2b4cd213f91f","creat_datetime":"20190723133245","creat_date":"20190723","creat_timestamp":"1563859965169","filename":"sttt.pdf","original":{"fieldname":"upfile","originalname":"sttt.pdf","encoding":"7bit","mimetype":"text/plain","size":8929365},"MD5":"21ebb50203be435fd762c50b98f13665"}
     */

    private String sucess;
    private InfoBean info;

    public String getSucess() {
        return sucess;
    }

    public void setSucess(String sucess) {
        this.sucess = sucess;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * userid : cffd116b073e4
         * username : admin
         * jobfileid : filetag_cae56035fdd75
         * customerid : customer_ce6e96397f913
         * customername : 客户1
         * caseid : case_c66266fba076f
         * casename : 客户1_test_20190711
         * processid : process_c22988bcc7565
         * processname : 测试申请流程
         * jobid : job_c25d8a84780ae
         * jobname : 测试任务1
         * mimetype : text/plain
         * _id : file_r__c56d5833a2c6b
         * fileid : file__c2b4cd213f91f
         * creat_datetime : 20190723133245
         * creat_date : 20190723
         * creat_timestamp : 1563859965169
         * filename : sttt.pdf
         * original : {"fieldname":"upfile","originalname":"sttt.pdf","encoding":"7bit","mimetype":"text/plain","size":8929365}
         * MD5 : 21ebb50203be435fd762c50b98f13665
         */

        private String userid;
        private String username;
        private String jobfileid;
        private String customerid;
        private String customername;
        private String caseid;
        private String casename;
        private String processid;
        private String processname;
        private String jobid;
        private String jobname;
        private String mimetype;
        private String _id;
        private String fileid;
        private String creat_datetime;
        private String creat_date;
        private String creat_timestamp;
        private String filename;
        private OriginalBean original;
        private String MD5;

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

        public String getJobfileid() {
            return jobfileid;
        }

        public void setJobfileid(String jobfileid) {
            this.jobfileid = jobfileid;
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

        public String getMimetype() {
            return mimetype;
        }

        public void setMimetype(String mimetype) {
            this.mimetype = mimetype;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getFileid() {
            return fileid;
        }

        public void setFileid(String fileid) {
            this.fileid = fileid;
        }

        public String getCreat_datetime() {
            return creat_datetime;
        }

        public void setCreat_datetime(String creat_datetime) {
            this.creat_datetime = creat_datetime;
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

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public OriginalBean getOriginal() {
            return original;
        }

        public void setOriginal(OriginalBean original) {
            this.original = original;
        }

        public String getMD5() {
            return MD5;
        }

        public void setMD5(String MD5) {
            this.MD5 = MD5;
        }

        public static class OriginalBean {
            /**
             * fieldname : upfile
             * originalname : sttt.pdf
             * encoding : 7bit
             * mimetype : text/plain
             * size : 8929365
             */

            private String fieldname;
            private String originalname;
            private String encoding;
            private String mimetype;
            private int size;

            public String getFieldname() {
                return fieldname;
            }

            public void setFieldname(String fieldname) {
                this.fieldname = fieldname;
            }

            public String getOriginalname() {
                return originalname;
            }

            public void setOriginalname(String originalname) {
                this.originalname = originalname;
            }

            public String getEncoding() {
                return encoding;
            }

            public void setEncoding(String encoding) {
                this.encoding = encoding;
            }

            public String getMimetype() {
                return mimetype;
            }

            public void setMimetype(String mimetype) {
                this.mimetype = mimetype;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }
        }
    }
}
