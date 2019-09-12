package com.jsyrdb.yiren.model;

public enum Action {

    LOGIN("login", 1, null),
    HEARTBEAT("heartbeat", 1, null),//心跳
    SYNC("sync", 1, null);//同步数据

    private String action;
    private int reqEvent;
    private Class respClazz;

    Action(String action, int reqEvent, Class respClazz) {
        this.action = action;
        this.reqEvent = reqEvent;
        this.respClazz = respClazz;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getReqEvent() {
        return reqEvent;
    }

    public void setReqEvent(int reqEvent) {
        this.reqEvent = reqEvent;
    }

    public Class getRespClazz() {
        return respClazz;
    }

    public void setRespClazz(Class respClazz) {
        this.respClazz = respClazz;
    }
}
