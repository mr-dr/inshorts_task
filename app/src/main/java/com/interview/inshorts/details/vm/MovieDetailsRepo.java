package com.interview.inshorts.details.vm;

import com.interview.inshorts.AppController;
import com.interview.inshorts.bookmarks.BookmarksDao;
import com.interview.inshorts.bookmarks.data.BookmarkedMovies;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;

public class MovieDetailsRepo {
    @Inject BookmarksDao mBookmarksDao;

    public MovieDetailsRepo() {
        AppController.getInstance().getApplicationComponent().inject(this);
    }

    public Observable<List<BookmarkedMovies>> getBookmarksByIds(int[] ids) {
        return mBookmarksDao.getMovieByIds(ids).toObservable();
    }

    public void deleteBookmarksById(int[] ids) {
        mBookmarksDao.deleteMoviesById(ids);
    }

    public void insertBookmarks(List<BookmarkedMovies> movies) {
        mBookmarksDao.insertMovies(movies);
    }
}
