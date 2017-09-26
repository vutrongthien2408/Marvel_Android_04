package com.framgia.movie;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.format.DateUtils;
import com.framgia.movie.screen.home.HomeActivity;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by TrongThien on 10/10/2017.
 */
@RunWith(AndroidJUnit4.class)
public class TestDetailMovie {

    private static final int ITEM_BELOW_THE_FOLD = 10;
    private IdlingResource mIdlingResource;
    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule =
            new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void setUp() {
        long waitingTime = DateUtils.SECOND_IN_MILLIS;

        mIdlingResource = new ElapsedTimeIdlingResource(waitingTime);
        IdlingPolicies.setMasterPolicyTimeout(waitingTime * TestSearchMovie.WAITTING_SECOND,
                TimeUnit.MILLISECONDS);
        IdlingPolicies.setIdlingResourceTimeout(waitingTime * TestSearchMovie.WAITTING_SECOND,
                TimeUnit.MILLISECONDS);
        Espresso.registerIdlingResources(mIdlingResource);
        onView(allOf(withId(R.id.recycler_movie), isCompletelyDisplayed())).check(
                matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_BELOW_THE_FOLD, click()));
    }

    @Test
    public void getTrailer_shouldReturnTrue_whenPosterClicked() {
        onView(withId(R.id.relative_poster)).perform(click());
        Espresso.unregisterIdlingResources(mIdlingResource);
    }

    @Test
    public void getMovieByCharactor_shouldReturnTrue_whenItemCharactorClick() {
        onView(withId(R.id.recycler_charactor)).perform(
                RecyclerViewActions.actionOnItemAtPosition(ITEM_BELOW_THE_FOLD, click()));
        Espresso.unregisterIdlingResources(mIdlingResource);
    }

    @Test
    public void getTheSameMovie_shouldReturnTrue_whenItemTheSameMovieClick() {

        long waitingTime = DateUtils.SECOND_IN_MILLIS;
        mIdlingResource = new ElapsedTimeIdlingResource(waitingTime);
        IdlingPolicies.setMasterPolicyTimeout(waitingTime * TestSearchMovie.WAITTING_SECOND,
                TimeUnit.MILLISECONDS);
        IdlingPolicies.setIdlingResourceTimeout(waitingTime * TestSearchMovie.WAITTING_SECOND,
                TimeUnit.MILLISECONDS);
        Espresso.registerIdlingResources(mIdlingResource);
        onView(withId(R.id.recycler_the_same_movie)).perform(
                RecyclerViewActions.actionOnItemAtPosition(ITEM_BELOW_THE_FOLD, click()));
        Espresso.unregisterIdlingResources(mIdlingResource);
    }
}
