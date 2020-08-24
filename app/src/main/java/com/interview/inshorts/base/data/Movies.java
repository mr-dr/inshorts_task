package com.interview.inshorts.base.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

public abstract class Movies implements Parcelable {

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

    public Movies(int id, String title, String posterPath, String description, String rating, boolean isAdult) {
        this.id = id;
        this.isAdult = isAdult;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.posterPath = posterPath;
    }

    protected Movies(Parcel in) {
        id = in.readInt();
        isAdult = in.readByte() != 0;
        title = in.readString();
        description = in.readString();
        rating = in.readString();
        posterPath = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeByte((byte) (isAdult ? 1 : 0));
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(rating);
        dest.writeString(posterPath);
    }
}
