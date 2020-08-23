package com.interview.inshorts;

import android.app.Application;

import com.interview.inshorts.di.ApplicationComponent;
import com.interview.inshorts.di.ApplicationModule;
import com.interview.inshorts.di.DaggerApplicationComponent;
import com.interview.inshorts.di.RoomModule;

public class AppController extends Application {
    private static AppController INSTANCE;
    ApplicationComponent appComponent;

    public static AppController getInstance() {
        return INSTANCE;
    }

    public ApplicationComponent getApplicationComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .roomModule(new RoomModule(this))
                .build();
    }
}
