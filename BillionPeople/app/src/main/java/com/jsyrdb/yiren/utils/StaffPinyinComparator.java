package com.jsyrdb.yiren.utils;

import com.jsyrdb.yiren.model.PeopleSetup;

import java.util.Comparator;

public class StaffPinyinComparator implements Comparator<PeopleSetup.ResultBean> {
    @Override
    public int compare(PeopleSetup.ResultBean o1, PeopleSetup.ResultBean o2) {
        if (o2.getSortLetters().equals("#")) {
            return -1;
        } else if (o1.getSortLetters().equals("#")) {
            return 1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }
}
