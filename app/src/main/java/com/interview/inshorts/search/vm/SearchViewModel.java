package com.interview.inshorts.search.vm;

import android.util.Log;

import com.interview.inshorts.AppController;
import com.interview.inshorts.base.MovieApiConfig;
import com.interview.inshorts.base.data.Movies;
import com.interview.inshorts.search.data.SearchRepo;
import com.interview.inshorts.search.models.SearchMovies;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchViewModel extends ViewModel {

    @Inject SearchRepo searchRepo;
    private MutableLiveData<List<Movies>> resultsLiveData;
    private int totalItems = 0;
    private int totalPages = 0;
    private int populatedItemCount = 0;
    private int nextPage = 1;
    private String searchKeyword;
    private boolean isLoading;

    public SearchViewModel() {
        AppController.getInstance().getApplicationComponent().inject(this);
        resultsLiveData = new MutableLiveData<>();
        Disposable disposable = searchRepo.getResultsObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    Log.d("app", "vm search call succeeded");
                    isLoading = false;
                    List<SearchMovies> results = convertApiConfigToSearchConfig(response.results);
                    if(results == null) return;
                    List<Movies> newValues;
                    List<Movies> oldValues = resultsLiveData.getValue();
                    if(oldValues != null) {
                        newValues = new ArrayList<>(oldValues);
                    } else {
                        newValues = new ArrayList<>();
                    }
                    newValues.addAll(results);
                    resultsLiveData.setValue(newValues);
                    totalItems = response.totalResults;
                    totalPages = response.totalPages;
                    populatedItemCount = newValues.size();
                }, throwable -> {
                    Log.d("app", "vm search call failed");
                    isLoading = false;
                });
    }

    public LiveData<List<Movies>> getResultsLiveData() {
        return resultsLiveData;
    }

    public void fetchFirstPageResults(String searchText) {
        searchKeyword = searchText;
        nextPage = 1;
        resultsLiveData.setValue(new ArrayList<>());
        fetchResults();
    }

    public void fetchNextPageResults() {
        fetchResults();
    }

    private void fetchResults() {
        isLoading = true;
        searchRepo.loadResults(searchKeyword, nextPage);
        nextPage++;
    }

    public int getNextPageCount() {
        return nextPage;
    }

    public int getTotalItemCount() {
        return totalItems;
    }

    public int getTotalPageCount() {
        return totalPages;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public int getPopulatedItemCount() {
        return populatedItemCount;
    }

    private List<SearchMovies> convertApiConfigToSearchConfig(@Nullable List<MovieApiConfig> apiResults) {
        List<SearchMovies> list = new ArrayList<>();
        if (apiResults == null) return list;
        for(MovieApiConfig apiConfig : apiResults) {
            list.add(new SearchMovies(apiConfig.getId(), apiConfig.getTitle(), apiConfig.getPosterPath(),
                    apiConfig.getDescription(), apiConfig.getFormattedRating(), apiConfig.isAdult()));
        }
        return list;
    }
}
