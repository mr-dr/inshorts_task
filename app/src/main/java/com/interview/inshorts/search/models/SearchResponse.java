package com.interview.inshorts.search.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {
    @SerializedName("page") int page;
    @SerializedName("results") List<SearchResults> results;
    @SerializedName("total_pages") int totalPages;
    @SerializedName("total_results") int totalResults;
}
