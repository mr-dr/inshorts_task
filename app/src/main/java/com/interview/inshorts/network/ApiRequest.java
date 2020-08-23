package com.interview.inshorts.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import android.util.Log;

import static com.interview.inshorts.network.TMDBService.BASE_URL;

public abstract class ApiRequest<T> implements Callback<T> {

    @Nullable
    private ApiListener<T> mListener;

    @Nullable
    private Disposable disposable;

    public ApiRequest(@Nullable ApiListener<T> listener) {
        mListener = listener;
    }

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        TMDBService service = retrofit.create(TMDBService.class);

        Observable<T> observable = getObservable(service);
        disposable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .map(result -> result.ticker)
                .subscribe(response -> {
                    Log.d("ApiResponse", new Gson().toJson(response));
                    if (mListener != null) mListener.onResponse(response);
                }, throwable -> {
                    Log.d("ApiResponse", throwable.getMessage());
                    if (mListener != null) mListener.onError(throwable);
                });

    }

    public void cancel() {
        if (disposable == null) return;
        disposable.dispose();
        disposable = null;
    }

    protected abstract Observable<T> getObservable(TMDBService service);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        // do nothing
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        // do nothing
    }
}