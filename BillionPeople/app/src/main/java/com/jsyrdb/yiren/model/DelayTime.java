package com.jsyrdb.yiren.model;

public class DelayTime {
    private String time;
    private boolean isCheckd;

    public DelayTime() {

    }

    public DelayTime(String time, boolean isCheckd) {
        this.time = time;
        this.isCheckd = isCheckd;
    }

    public boolean isCheckd() {
        return isCheckd;
    }

    public void setCheckd(boolean checkd) {
        isCheckd = checkd;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
