package com.jsyrdb.yiren.model;

import java.io.Serializable;
import java.util.List;

public class LoginResult implements Serializable {

    /**
     * error : null
     * result : [{"user":{"_id":"_c15e09dbe16d3","name":"王小亚","describe":"客户经理","username":"wang","mobile":"15895824816","email":"","creat_date":"20190604"},"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7InVzZXIiOnsiX2lkIjoiX2MxNWUwOWRiZTE2ZDMiLCJuYW1lIjoi546L5bCP5LqaIn19LCJpYXQiOjE1NTk3MTcyMDEsImV4cCI6MTU1OTgwMzYwMX0.ZZKZ2ZFXF1KW-VzfG8WdRStrprsj4EgLboyJUKH3PjE"}]
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

    public static class ResultBean implements Serializable{
        /**
         * user : {"_id":"_c15e09dbe16d3","name":"王小亚","describe":"客户经理","username":"wang","mobile":"15895824816","email":"","creat_date":"20190604"}
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7InVzZXIiOnsiX2lkIjoiX2MxNWUwOWRiZTE2ZDMiLCJuYW1lIjoi546L5bCP5LqaIn19LCJpYXQiOjE1NTk3MTcyMDEsImV4cCI6MTU1OTgwMzYwMX0.ZZKZ2ZFXF1KW-VzfG8WdRStrprsj4EgLboyJUKH3PjE
         */

        private UserBean user;
        private String token;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class UserBean implements Serializable{
            /**
             * _id : _c15e09dbe16d3
             * name : 王小亚
             * describe : 客户经理
             * username : wang
             * mobile : 15895824816
             * email :
             * creat_date : 20190604
             */

            private String _id;
            private String name;
            private String describe;
            private String username;
            private String mobile;
            private String email;
            private String creat_date;

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

            public String getDescribe() {
                return describe;
            }

            public void setDescribe(String describe) {
                this.describe = describe;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getCreat_date() {
                return creat_date;
            }

            public void setCreat_date(String creat_date) {
                this.creat_date = creat_date;
            }
        }
    }
}
