package com.jsyrdb.yiren.model;

public class UserUpload {

    /**
     * sucess : 上传成功
     * info : {"userid":"cffd116b073e4","username":"admin","type":"message","mimetype":"image/gif","_id":"file_r__c0d9211426c43","fileid":"file__c8eef280d9dd4","creat_datetime":"20190716173853","creat_date":"20190716","creat_timestamp":"1563269933010","filename":"5-1206010T004-51.gif","original":{"fieldname":"upfile","originalname":"5-1206010T004-51.gif","encoding":"7bit","mimetype":"image/gif","size":3130},"MD5":"feed4f978544f5e3c581ce9ecfe7429c"}
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
         * type : message
         * mimetype : image/gif
         * _id : file_r__c0d9211426c43
         * fileid : file__c8eef280d9dd4
         * creat_datetime : 20190716173853
         * creat_date : 20190716
         * creat_timestamp : 1563269933010
         * filename : 5-1206010T004-51.gif
         * original : {"fieldname":"upfile","originalname":"5-1206010T004-51.gif","encoding":"7bit","mimetype":"image/gif","size":3130}
         * MD5 : feed4f978544f5e3c581ce9ecfe7429c
         */

        private String userid;
        private String username;
        private String type;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
             * originalname : 5-1206010T004-51.gif
             * encoding : 7bit
             * mimetype : image/gif
             * size : 3130
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
