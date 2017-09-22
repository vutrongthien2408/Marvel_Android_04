package com.framgia.movie.screen.movie;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.movie.R;
import com.framgia.movie.data.model.Movie;
import com.framgia.movie.databinding.ItemMovieBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TrongThien on 9/20/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ItemViewHolder> {

    private List<Movie> mMovies = new ArrayList<>();
    private ItemMovieBinding mItemMovieBinding;

    public MovieAdapter() {
    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(List<Movie> movies) {
        mMovies.addAll(movies);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        mItemMovieBinding = DataBindingUtil.inflate(inflater, R.layout.item_movie, parent, false);
        return new ItemViewHolder(mItemMovieBinding);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    /**
     * Item holder
     */

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieBinding mBinding;

        public ItemViewHolder(ItemMovieBinding movieBinding) {
            super(movieBinding.getRoot());
            mBinding = movieBinding;
        }

        public void bind(Movie movie) {
            mBinding.setMovie(movie);
            mBinding.executePendingBindings();
        }
    }
}
