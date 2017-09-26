package com.framgia.movie.utils.binding;

import android.databinding.BindingAdapter;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

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
}
