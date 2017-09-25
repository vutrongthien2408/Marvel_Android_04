package com.framgia.movie.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TrongThien on 9/25/2017.
 */

public class CharactorRespone {
    @SerializedName("cast")
    @Expose
    private List<Charactor> mCharactors = new ArrayList<>();

    public List<Charactor> getCharactors() {
        return mCharactors;
    }
}
