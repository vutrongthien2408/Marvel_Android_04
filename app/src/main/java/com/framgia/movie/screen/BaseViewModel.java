package com.framgia.movie.screen;

/**
 * Created by TrongThien on 9/19/2017.
 */

public interface BaseViewModel<T> {
    void onStart();

    void onStop();

    void setPresenter(T presenter);
}
