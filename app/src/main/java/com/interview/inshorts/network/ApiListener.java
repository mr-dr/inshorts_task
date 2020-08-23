package com.interview.inshorts.network;

public interface ApiListener<T> {
    void onResponse(T response);
    void onError(Throwable error);
}
