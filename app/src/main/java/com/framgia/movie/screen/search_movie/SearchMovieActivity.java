package com.framgia.movie.screen.search_movie;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.framgia.movie.R;
import com.framgia.movie.databinding.ActivitySearchBinding;
import com.framgia.movie.screen.BaseActivity;

/**
 * Search_movie Screen.
 */
public class SearchMovieActivity extends BaseActivity {

    public static final String EXTRA_CHARACTOR_ID = "EXTRA_PEOPLE_ID";
    public static final int CHARACTOR_ID_DEFAULT = 0;
    private SearchMovieContract.ViewModel mViewModel;

    public static Intent getMovieIntent(Context context, int charactorId) {
        Intent intent = new Intent(context, SearchMovieActivity.class);
        intent.putExtra(EXTRA_CHARACTOR_ID, charactorId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int charactorId;
        if (getIntent() != null) {
            charactorId = getIntent().getIntExtra(EXTRA_CHARACTOR_ID, CHARACTOR_ID_DEFAULT);
        } else {
            charactorId = CHARACTOR_ID_DEFAULT;
        }

        mViewModel = new SearchMovieViewModel(this);

        SearchMovieContract.Presenter presenter = new SearchMoviePresenter(mViewModel, charactorId);
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
