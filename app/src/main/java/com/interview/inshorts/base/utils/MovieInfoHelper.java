package com.interview.inshorts.base.utils;

import com.interview.inshorts.network.Constants;

public class MovieInfoHelper {
    public static String getImageUrl(String posterPath) {
        return Constants.POSTER_BASE_URL + posterPath;
    }
}
