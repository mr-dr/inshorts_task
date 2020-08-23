package com.interview.inshorts.base.vmFactory;

import com.interview.inshorts.home.data.HomeViewLocalDataSource;
import com.interview.inshorts.home.data.HomeViewRemoteDataSource;
import com.interview.inshorts.home.vm.HomeViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class HomeVmFactory implements ViewModelProvider.Factory {
    private HomeViewLocalDataSource mLocalDataSource;
    private HomeViewRemoteDataSource mRemoteDataSource;

    public HomeVmFactory(HomeViewLocalDataSource localDataSource, HomeViewRemoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HomeViewModel(mLocalDataSource, mRemoteDataSource);
    }
}
