package com.interview.inshorts.home.models;

import com.google.gson.annotations.SerializedName;
import com.interview.inshorts.base.MovieApiConfig;

import java.util.List;

public class TrendingResponse {
    @SerializedName("results") private List<MovieApiConfig> results;
    // never used
    @SerializedName("page") private int page;
    @SerializedName("total_pages") private int totalPages;
    @SerializedName("total_results") private int totalResults;

    public List<MovieApiConfig> getResults() {
        return results;
    }
}
