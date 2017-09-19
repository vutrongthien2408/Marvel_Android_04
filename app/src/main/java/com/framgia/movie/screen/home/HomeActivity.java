package com.framgia.movie.screen.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.framgia.movie.R;
import com.framgia.movie.databinding.ActivityHomeBinding;
import com.framgia.movie.screen.BaseActivity;

/**
 * Home Screen.
 */
public class HomeActivity extends BaseActivity {

    private HomeContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new HomeViewModel();

        HomeContract.Presenter presenter =
                new HomePresenter(mViewModel);
        mViewModel.setPresenter(presenter);

        ActivityHomeBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setViewModel((HomeViewModel) mViewModel);
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
