package com.framgia.movie;

import android.content.Context;
import com.framgia.movie.data.model.Movie;
import com.framgia.movie.data.source.FavoriteRepository;
import com.framgia.movie.data.source.MovieRepository;
import com.framgia.movie.screen.movie.MovieAdapter;
import com.framgia.movie.screen.search_movie.SearchMoviePresenter;
import com.framgia.movie.screen.search_movie.SearchMovieViewModel;
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
public class SearchMoviePresenterTest {
    @Mock
    private Context mContext;
    @Spy
    private SearchMovieViewModel mViewModel = new SearchMovieViewModel(mContext);
    @InjectMocks
    private SearchMoviePresenter mPresenter;
    @Mock
    private MovieRepository mMovieRepository;
    @Mock
    private FavoriteRepository mFavoriteRepository;
    @Mock
    private MovieAdapter mMovieAdapter;

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
        mPresenter.setFavoriteRepository(mFavoriteRepository);
        mPresenter.setCategory("popular");
        mPresenter.setCharactorId(0);
        mViewModel.setAdapter(mMovieAdapter);
    }

    @Test
    public void searchMovie_shouldReturnTrue_whenSearchMovie() {
        List<Movie> movies = new ArrayList<>();
        when(mMovieRepository.loadMovieByCategory(ArgumentMatchers.anyString())).thenReturn(
                Observable.just(movies));
        when(mMovieRepository.loadMovieByCharactor(ArgumentMatchers.anyInt())).thenReturn(
                Observable.just(movies));
        when(mFavoriteRepository.getMovies()).thenReturn(movies);
        mViewModel.setPresenter(mPresenter);
        verify(mViewModel).onSearchSuccess(movies);
    }
}


