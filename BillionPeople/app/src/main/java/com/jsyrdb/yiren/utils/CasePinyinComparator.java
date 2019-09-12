package com.jsyrdb.yiren.utils;

import com.jsyrdb.yiren.model.Case;

import java.util.Comparator;

public class CasePinyinComparator implements Comparator<Case.ResultBean> {
    @Override
    public int compare(Case.ResultBean o1, Case.ResultBean o2) {
        if (o2.getSortLetters().equals("#")) {
            return -1;
        } else if (o1.getSortLetters().equals("#")) {
            return 1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }
}
