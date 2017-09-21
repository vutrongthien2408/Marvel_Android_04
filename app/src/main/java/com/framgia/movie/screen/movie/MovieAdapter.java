package com.framgia.movie.screen.movie;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by TrongThien on 9/20/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ItemViewHolder> {

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    /**
     * Item holder
     */

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
