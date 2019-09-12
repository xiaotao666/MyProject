package com.jsyrdb.yiren.model;

import java.util.List;

public class SelectParent {
    private String title;
    private List<SelectChild> childList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SelectChild> getChildList() {
        return childList;
    }

    public void setChildList(List<SelectChild> childList) {
        this.childList = childList;
    }
}
