package com.framgia.movie.utils.binding;

import android.databinding.BindingAdapter;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import com.framgia.movie.screen.movie.MovieAdapter;

/**
 * Created by TrongThien on 9/20/2017.
 */

public final class BindingUtils {

    @BindingAdapter({ "recyclerAdapter" })
    public static void setAdapterForRecyclerView(RecyclerView recyclerView, MovieAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({ "pagerAdapter" })
    public static void setAdapterForPager(ViewPager pager, FragmentPagerAdapter adapter) {
        pager.setAdapter(adapter);
    }
}
