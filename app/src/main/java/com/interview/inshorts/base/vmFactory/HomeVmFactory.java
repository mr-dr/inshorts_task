package com.interview.inshorts.base.vmFactory;

import com.interview.inshorts.AppController;
import com.interview.inshorts.home.data.HomeViewLocalDataSource;
import com.interview.inshorts.home.data.HomeViewRemoteDataSource;
import com.interview.inshorts.home.vm.HomeViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class HomeVmFactory implements ViewModelProvider.Factory {
    @Inject HomeViewLocalDataSource mLocalDataSource;
    @Inject HomeViewRemoteDataSource mRemoteDataSource;

    public HomeVmFactory() {
        AppController.getInstance().getApplicationComponent().inject(this);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HomeViewModel();
    }
}
