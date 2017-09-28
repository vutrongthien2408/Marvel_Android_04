package com.framgia.movie.screen.movie;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.movie.R;
import com.framgia.movie.data.model.Movie;
import com.framgia.movie.databinding.ItemMovieBinding;
import com.framgia.movie.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TrongThien on 9/20/2017.
 */

public class MovieAdapter extends BaseRecyclerViewAdapter<MovieAdapter.ItemViewHolder> {

    private List<Movie> mMovies = new ArrayList<>();
    private ItemMovieBinding mItemMovieBinding;

    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Movie> mItemClickListener;

    public MovieAdapter(@NonNull Context context) {
        super(context);
    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Movie> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void updateMovie(List<Movie> movies) {
        if (movies == null) {
            return;
        }
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        mItemMovieBinding = DataBindingUtil.inflate(inflater, R.layout.item_movie, parent, false);
        return new ItemViewHolder(mItemMovieBinding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return (mMovies == null) ? 0 : mMovies.size();
    }

    /**
     * Item holder
     */

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieBinding mBinding;
        private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Movie> mItemClickListener;

        public ItemViewHolder(ItemMovieBinding movieBinding,
                BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Movie> listener) {
            super(movieBinding.getRoot());
            mBinding = movieBinding;
            mItemClickListener = listener;
        }

        public void bind(Movie movie) {
            mBinding.setViewModel(new MovieItemViewModel(movie, mItemClickListener));
            mBinding.setMovie(movie);
            mBinding.executePendingBindings();
        }
    }
}
