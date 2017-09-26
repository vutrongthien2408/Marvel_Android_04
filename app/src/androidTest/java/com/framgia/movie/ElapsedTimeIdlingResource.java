package com.framgia.movie;

import android.support.test.espresso.IdlingResource;

/**
 * Created by TrongThien on 10/9/2017.
 */

public class ElapsedTimeIdlingResource implements IdlingResource {
    private final long mStartTime;
    private final long mWaitingTime;
    private ResourceCallback mResourceCallback;

    public ElapsedTimeIdlingResource(long waitingTime) {
        mStartTime = System.currentTimeMillis();
        mWaitingTime = waitingTime;
    }

    @Override
    public String getName() {
        return ElapsedTimeIdlingResource.class.getName() + ":" + mWaitingTime;
    }

    @Override
    public boolean isIdleNow() {
        long elapsed = System.currentTimeMillis() - mStartTime;
        boolean idle = (elapsed >= mWaitingTime);
        if (idle) {
            mResourceCallback.onTransitionToIdle();
        }
        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        mResourceCallback = resourceCallback;
    }
}
