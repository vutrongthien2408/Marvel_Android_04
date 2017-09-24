package com.framgia.movie.screen.search_movie;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.framgia.movie.R;
import com.framgia.movie.databinding.ActivitySearchBinding;
import com.framgia.movie.screen.BaseActivity;

/**
 * Search_movie Screen.
 */
public class SearchMovieActivity extends BaseActivity {

    private SearchMovieContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new SearchMovieViewModel();

        SearchMovieContract.Presenter presenter = new SearchMoviePresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivitySearchBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.setViewModel((SearchMovieViewModel) mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
