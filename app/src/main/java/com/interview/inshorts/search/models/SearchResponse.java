package com.interview.inshorts.search.models;

import com.google.gson.annotations.SerializedName;
import com.interview.inshorts.base.MovieApiConfig;
import com.interview.inshorts.base.data.Movies;

import java.util.List;

public class SearchResponse {
    @SerializedName("page") public int page;
    @SerializedName("results") public List<MovieApiConfig> results;
    @SerializedName("total_pages") public int totalPages;
    @SerializedName("total_results") public int totalResults;
}
