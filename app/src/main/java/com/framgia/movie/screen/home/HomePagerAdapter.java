package com.framgia.movie.screen.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.framgia.movie.data.model.Genre;
import com.framgia.movie.screen.movie.MovieFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TrongThien on 9/21/2017.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {
    private List<Genre> mGenres = new ArrayList<>();

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genre> genres) {
        mGenres = genres;
    }

    @Override
    public Fragment getItem(int position) {

        return MovieFragment.newInstance(mGenres.get(position).getId());
    }

    @Override
    public int getCount() {
        return mGenres.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mGenres.get(position).getName();
    }
}
