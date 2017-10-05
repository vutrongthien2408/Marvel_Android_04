package com.framgia.movie;

import android.content.Context;
import com.framgia.movie.data.model.MovieTrailer;
import com.framgia.movie.data.source.MovieTrailerRepository;
import com.framgia.movie.screen.video_trailer.VideoTrailerPresenter;
import com.framgia.movie.screen.video_trailer.VideoTrailerViewModel;
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
 * Created by TrongThien on 10/4/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieTrailerPresenterTest {

    @Mock
    private Context mContext;
    @InjectMocks
    private VideoTrailerPresenter mPresenter = new VideoTrailerPresenter();
    @Spy
    private VideoTrailerViewModel mViewModel = new VideoTrailerViewModel(mContext);
    @Mock
    private MovieTrailerRepository mRepository;

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
    public void getTrailer_shouldReturnTrue_whenGetTrailer() throws Exception {
        List<MovieTrailer> trailers = new ArrayList<>();
        MovieTrailer movieTrailer = new MovieTrailer();
        trailers.add(movieTrailer);
        when(mRepository.loadMovieTrailer(ArgumentMatchers.anyInt())).thenReturn(
                Observable.just(trailers));
        mViewModel.setPresenter(mPresenter);
        verify(mViewModel).onLoadTrailerSuccess(trailers.get(0));
    }
}
