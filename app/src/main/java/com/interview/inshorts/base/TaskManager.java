package com.interview.inshorts.base;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TaskManager {
    private static TaskManager INSTACE;
    public static TaskManager getInstance() {
        if (INSTACE == null) {
            synchronized (TaskManager.class) {
                if(INSTACE == null) {
                    INSTACE = new TaskManager();
                }
            }
        }
        return INSTACE;
    }

    private TaskManager() {}

    public void doInBackground(@Nullable Runnable onBg, @Nullable Runnable onMain) {
        if (onBg == null) return;
        Observable.fromCallable(() -> {
            onBg.run();
            return true;
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).subscribe((result) -> {
            if (onMain == null) return;
            onMain.run();
        });
    }
}
