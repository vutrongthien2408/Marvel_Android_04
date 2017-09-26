package com.framgia.movie.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TrongThien on 9/29/2017.
 */

public class MovieTrailer extends BaseObservable {
    @SerializedName("id")
    @Expose
    @Bindable
    private String mId;
    @SerializedName("key")
    @Expose
    @Bindable
    private String mKey;
    @SerializedName("size")
    @Expose
    @Bindable
    private int mSize;
    @SerializedName("name")
    @Expose
    @Bindable
    private String mName;

    public String getId() {
        return mId;
    }

    public String getKey() {
        return mKey;
    }

    public int getSize() {
        return mSize;
    }

    public String getName() {
        return mName;
    }
}
