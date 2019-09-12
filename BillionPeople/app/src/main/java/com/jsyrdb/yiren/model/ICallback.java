package com.jsyrdb.yiren.model;

public interface ICallback<T> {
    void onSuccess(T t);

    void onFail(String msg);
}
