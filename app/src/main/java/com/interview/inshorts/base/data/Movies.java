package com.interview.inshorts.base.data;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

public abstract class Movies {

    @PrimaryKey
    @ColumnInfo(name = "id")
    protected int id;
    @ColumnInfo(name = "adult")
    protected boolean isAdult;
    @ColumnInfo(name = "title")
    protected String title;
    @ColumnInfo(name = "description")
    protected String description;
    @ColumnInfo(name = "rating")
    protected String rating;
    @ColumnInfo(name = "poster_path")
    protected String posterPath;

    @Ignore
    public Movies(int id, String title, String posterPath, String description, String rating, boolean isAdult) {
        this.id = id;
        this.isAdult = isAdult;
        this.title = title;
        this.rating = rating;
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }

    public String getPosterPath() {
        return posterPath;
    }

}
