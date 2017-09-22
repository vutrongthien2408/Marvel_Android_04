package com.framgia.movie.screen.movie;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.movie.R;
import com.framgia.movie.databinding.MovieFragmentBinding;
import com.framgia.movie.screen.BaseFragment;

/**
 * Movie Screen.
 */
public class MovieFragment extends BaseFragment {
    public static final String BUNDLE_GENRE_ID = "BUNDLE_GENRE_ID";
    private MovieContract.ViewModel mViewModel;

    public static MovieFragment newInstance(int genreId) {
        MovieFragment movieFragment = new MovieFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_GENRE_ID, genreId);
        movieFragment.setArguments(bundle);
        return movieFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        savedInstanceState = getArguments();
        int genreId = savedInstanceState.getInt(BUNDLE_GENRE_ID);

        mViewModel = new MovieViewModel();
        MovieContract.Presenter presenter = new MoviePresenter(mViewModel, genreId);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        MovieFragmentBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.movie_fragment, container, false);
        binding.setViewModel((MovieViewModel) mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
