package com.interview.inshorts.di;

import android.app.Application;

import com.interview.inshorts.base.vmFactory.HomeVmFactory;
import com.interview.inshorts.bookmarks.BookmarksActivity;
import com.interview.inshorts.home.activity.HomeActivity;
import com.interview.inshorts.home.data.HomeViewLocalDataSource;
import com.interview.inshorts.home.data.HomeViewRemoteDataSource;
import com.interview.inshorts.home.data.HomeViewRepo;
import com.interview.inshorts.home.vm.HomeViewModel;
import com.interview.inshorts.search.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, RoomModule.class})
public interface ApplicationComponent {
    void inject(HomeActivity homeActivity);
    void inject(HomeViewLocalDataSource localDataSource);
    void inject(HomeViewRemoteDataSource remoteDataSource);
    void inject(HomeViewRepo homeViewRepo);
    void inject(HomeViewModel homeViewModel);
    void inject(HomeVmFactory homeVmFactory);
    void inject(SearchActivity searchActivity);
    void inject(BookmarksActivity bookmarksActivity);
//    void inject(DetailsActivity detailsActivity);

    Application application();
}
