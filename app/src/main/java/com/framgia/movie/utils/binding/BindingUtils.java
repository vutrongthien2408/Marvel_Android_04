package com.framgia.movie.utils.binding;

import android.databinding.BindingAdapter;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by TrongThien on 9/20/2017.
 */

public final class BindingUtils {

    @BindingAdapter({ "recyclerAdapter" })
    public static void setAdapterForRecyclerView(RecyclerView recyclerView,
            RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({ "setLayoutManager" })
    public static void setLayoutManager(RecyclerView recyclerView,
            LinearLayoutManager linearLayoutManager) {
        linearLayoutManager =
                new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL,
                        false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @BindingAdapter({ "pagerAdapter" })
    public static void setAdapterForPager(ViewPager pager, FragmentPagerAdapter adapter) {
        pager.setAdapter(adapter);
    }

    @BindingAdapter({ "setImage" })
    public static void setImageToImageView(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @BindingAdapter({ "openNavigation" })
    public static void openNavigation(DrawerLayout drawerLayout, int gravity) {
        if (gravity == Gravity.START) {
            drawerLayout.openDrawer(gravity);
        } else {
            drawerLayout.closeDrawers();
        }
    }

    @BindingAdapter({ "playYoutubeTrailer" })
    public static void playYoutubeTrailer(YouTubePlayerView playerView,
            YouTubePlayer.OnInitializedListener listener) {
        if (listener == null) {
            return;
        }
        playerView.initialize(com.framgia.movie.BuildConfig.YOUTUBE_API_KEY, listener);
    }
}
