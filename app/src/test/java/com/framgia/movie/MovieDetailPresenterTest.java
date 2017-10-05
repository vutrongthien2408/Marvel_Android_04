package com.framgia.movie;

import android.content.Context;
import com.framgia.movie.data.model.Charactor;
import com.framgia.movie.data.model.Movie;
import com.framgia.movie.data.source.CharactorRepository;
import com.framgia.movie.data.source.MovieRepository;
import com.framgia.movie.screen.movie_detail.CharactorAdapter;
import com.framgia.movie.screen.movie_detail.MovieDetailPresenter;
import com.framgia.movie.screen.movie_detail.MovieDetailViewModel;
import com.framgia.movie.screen.movie_detail.TheSameMovieAdapter;
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
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by TrongThien on 10/5/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieDetailPresenterTest {

    @Mock
    private Context mContext;
    @Spy
    private MovieDetailViewModel mViewModel = new MovieDetailViewModel(mContext);
    @InjectMocks
    private MovieDetailPresenter mPresenter;
    @Mock
    private MovieRepository mMovieRepository;
    @Mock
    private CharactorRepository mCharactorRepository;
    @Mock
    private CharactorAdapter mCharactorAdapter;
    @Mock
    private TheSameMovieAdapter mTheSameMovieAdapter;

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
        mViewModel.setCharactorAdapter(mCharactorAdapter);
        mViewModel.setTheSameMovieAdapter(mTheSameMovieAdapter);
    }

    @Test
    public void getTrailer_shouldReturnTrue_whenGetTrailer() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie());
        List<Charactor> charactors = new ArrayList<>();
        charactors.add(new Charactor());
        when(mMovieRepository.loadTheSame(ArgumentMatchers.anyInt())).thenReturn(
                Observable.just(movies));
        when(mCharactorRepository.loadCharactorByMovie(ArgumentMatchers.anyInt())).thenReturn(
                Observable.just(charactors));
        mViewModel.setPresenter(mPresenter);
        verify(mViewModel).onLoadDetailSuccess(charactors);
        verify(mViewModel).onLoadTheSameMovieSuccess(movies);
    }
}
