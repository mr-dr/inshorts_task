package com.interview.inshorts.di;

import android.app.Application;

import com.interview.inshorts.AppController;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Application application;
    public ApplicationModule(AppController application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }
}
