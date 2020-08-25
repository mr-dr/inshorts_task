package com.interview.inshorts.base.listeners;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public abstract class TypeAheadListener implements TextWatcher {
    private final long WAIT_TIME_IN_MILLIS;
    private long lastUpdatedAt = 0;
    private Subject<CharSequence> searchTextObservable;

    public TypeAheadListener(long waitTime) {
        WAIT_TIME_IN_MILLIS = waitTime;
        searchTextObservable = PublishSubject.create();
        Disposable disposable = searchTextObservable.debounce(WAIT_TIME_IN_MILLIS, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    submitText(response.toString());
                });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // do nothing
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        lastUpdatedAt = System.currentTimeMillis();
        searchTextObservable.onNext(s);
    }

    @Override
    public void afterTextChanged(Editable s) {
        // do nothing
    }

    public abstract void submitText(String text);
}
