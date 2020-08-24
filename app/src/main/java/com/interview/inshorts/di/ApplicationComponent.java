package com.interview.inshorts.di;

import android.app.Application;

import com.interview.inshorts.bookmarks.BookmarksActivity;
import com.interview.inshorts.details.vm.MovieDetailsViewModel;
import com.interview.inshorts.base.views.MovieListView;
import com.interview.inshorts.base.vmFactory.HomeVmFactory;
import com.interview.inshorts.bookmarks.BookmarksRepo;
import com.interview.inshorts.bookmarks.BookmarksViewModel;
import com.interview.inshorts.details.vm.MovieDetailsRepo;
import com.interview.inshorts.home.activity.HomeActivity;
import com.interview.inshorts.home.data.HomeViewLocalDataSource;
import com.interview.inshorts.home.data.HomeViewRemoteDataSource;
import com.interview.inshorts.home.data.HomeViewRepo;
import com.interview.inshorts.home.vm.HomeViewModel;
import com.interview.inshorts.search.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, RoomModule.class, UiModule.class})
public interface ApplicationComponent {
    void inject(HomeActivity homeActivity);
    void inject(HomeViewLocalDataSource localDataSource);
    void inject(HomeViewRemoteDataSource remoteDataSource);
    void inject(HomeViewRepo homeViewRepo);
    void inject(HomeViewModel homeViewModel);
    void inject(HomeVmFactory homeVmFactory);
    void inject(MovieListView movieListView);

    void inject(SearchActivity searchActivity);

//    void inject(BookmarksActivity bookmarksActivity);
    void inject(BookmarksRepo bookmarksRepo);
    void inject(BookmarksViewModel bookmarksViewModel);

    void inject(MovieDetailsViewModel movieDetailsViewModel);
    void inject(MovieDetailsRepo movieDetailsRepo);
//    void inject();

    Application application();
}
