package com.framgia.movie;

import android.content.Context;
import com.framgia.movie.data.model.Movie;
import com.framgia.movie.data.source.MovieRepository;
import com.framgia.movie.screen.movie.MoviePresenter;
import com.framgia.movie.screen.movie.MovieViewModel;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by TrongThien on 10/5/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class MoviePresenterTest {

    @Mock
    private Context mContext;
    @Spy
    private MovieViewModel mViewModel = new MovieViewModel(mContext);
    @InjectMocks
    private MoviePresenter mPresenter;
    @Mock
    private MovieRepository mMovieRepository;

    @Before
    public void setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                new Function<Callable<Scheduler>, Scheduler>() {

                    @Override
                    public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                        return Schedulers.trampoline();
                    }
                });
        mPresenter.setViewModel(mViewModel);
    }

    @Test
    public void getListMovie_shouldReturnTrue_whenGetListMovie() {
        List<Movie> movies = new ArrayList<>();
        when(mMovieRepository.loadMovieByGenre(anyInt())).thenReturn(Observable.just(movies));
        mViewModel.setPresenter(mPresenter);
        verify(mViewModel).onGetMovieSuccess(movies);
    }
}
