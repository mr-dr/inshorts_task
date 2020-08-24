package com.interview.inshorts.base;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.Nullable;

public class MovieApiConfig {

    @SerializedName("id")
    private int id;
    @SerializedName("adult")
    private boolean isAdult;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("title")
    private String title;
    @SerializedName("overview")
    private String description1;
    @SerializedName("tagline")
    private String description2;
    @SerializedName("popularity")
    private float popularity;
    @SerializedName("vote_average")
    private float rating;
    @SerializedName("vote_count")
    private int ratingsReceived;
    @SerializedName("poster_path")
    private String poster1;
    @SerializedName("backdrop_path")
    private String poster2;

    // TODO can add genre info as well

    public int getId() {
        return id;
    }

    public boolean isAdult() {
        return isAdult;
    }

    @Nullable
    public String getPosterPath() {
        return poster1 != null ? poster1 : poster2;
    }

    @Nullable
    public String getTitle() {
        return title != null ? title : originalTitle;
    }

    @Nullable
    public String getDescription() {
        return description1 != null ? description1 : description2;
    }

    // TODO move to MovieInfoHelper
    @Nullable
    public String getFormattedRating() { // can be moved out
        if (rating <= 0) return null;
        StringBuilder formattedRating = new StringBuilder(String.valueOf(rating));
        if (ratingsReceived > 0) {
            formattedRating.append(" (" + ratingsReceived + ")");
        }
        return formattedRating.toString();
    }
}
