package com.framgia.movie;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.format.DateUtils;
import com.framgia.movie.screen.search_movie.SearchMovieActivity;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by TrongThien on 10/6/2017.
 */
@RunWith(AndroidJUnit4.class)
public class TestSearchMovie {
    private static final int ITEM_BELOW_THE_FOLD = 2;
    private static final String TYPE_TEXT_SEARCH = "The ring";
    private static final String CATEGORY_NAME = "popular";
    private IdlingResource mIdlingResource;
    public static final int WAITTING_SECOND = 10;
    @Rule
    public ActivityTestRule<SearchMovieActivity> mActivityRule =
            new ActivityTestRule<>(SearchMovieActivity.class);

    @Before
    public void setUp() {
        Intent intent = SearchMovieActivity.getCategoryIntent(
                mActivityRule.getActivity().getApplicationContext(), CATEGORY_NAME);
        mActivityRule.launchActivity(intent);
        long waitingTime = DateUtils.SECOND_IN_MILLIS;
        mIdlingResource = new ElapsedTimeIdlingResource(waitingTime);
        IdlingPolicies.setMasterPolicyTimeout(waitingTime * WAITTING_SECOND, TimeUnit.MILLISECONDS);
        IdlingPolicies.setIdlingResourceTimeout(waitingTime * WAITTING_SECOND,
                TimeUnit.MILLISECONDS);
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void getSearchMOvie_shouldReturnTrue_whenSearchViewClick() {
        onView(withId(R.id.search_view)).perform(typeText(TYPE_TEXT_SEARCH), click());
    }

    @Test
    public void getDetail_shouldReturnTrue_whenItemClicked() {

        onView(withId(R.id.recycler_search_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_search_movie)).perform(
                RecyclerViewActions.actionOnItemAtPosition(ITEM_BELOW_THE_FOLD, click()));
        Espresso.unregisterIdlingResources(mIdlingResource);
    }
}
