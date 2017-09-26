package com.framgia.movie.screen.movie_detail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.movie.R;
import com.framgia.movie.data.model.Movie;
import com.framgia.movie.databinding.ItemTheSameMovieBinding;
import com.framgia.movie.screen.BaseRecyclerViewAdapter;
import com.framgia.movie.screen.movie.MovieItemViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TrongThien on 9/26/2017.
 */

public class TheSameMovieAdapter
        extends BaseRecyclerViewAdapter<TheSameMovieAdapter.ItemViewHolder> {

    private List<Movie> mMovies = new ArrayList<>();
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Movie> mItemClickListener;

    protected TheSameMovieAdapter(@NonNull Context context) {
        super(context);
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Movie> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTheSameMovieBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_the_same_movie, parent, false);
        return new ItemViewHolder(binding, mItemClickListener);
    }

    public void updateMovie(List<Movie> movies) {
        if (movies == null) {
            return;
        }
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return ((mMovies == null) ? 0 : mMovies.size());
    }

    /**
     * Item holder
     */
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemTheSameMovieBinding mBinding;
        private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Movie> mItemClickListener;

        public ItemViewHolder(ItemTheSameMovieBinding binding,
                BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Movie> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
        }

        public void bind(Movie movie) {
            if (movie == null) {
                return;
            }
            mBinding.setViewModel(new MovieItemViewModel(movie, mItemClickListener));
            mBinding.setMovie(movie);
            mBinding.executePendingBindings();
        }
    }
}
