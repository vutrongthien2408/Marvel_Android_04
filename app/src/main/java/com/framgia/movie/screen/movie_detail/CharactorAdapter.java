package com.framgia.movie.screen.movie_detail;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.movie.R;
import com.framgia.movie.data.model.Charactor;
import com.framgia.movie.databinding.ItemCharactorBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TrongThien on 9/25/2017.
 */

public class CharactorAdapter extends RecyclerView.Adapter<CharactorAdapter.ItemViewHolder> {
    private List<Charactor> mCharactors = new ArrayList<>();

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCharactorBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_charactor, parent, false);
        return new ItemViewHolder(binding);
    }

    public void setCharactors(List<Charactor> charactors) {
        if (charactors == null) {
            return;
        }
        mCharactors.addAll(charactors);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mCharactors.get(position));
    }

    @Override
    public int getItemCount() {
        if (mCharactors == null) {
            return 0;
        }
        return mCharactors.size();
    }

    /**
     * Item holder
     */
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemCharactorBinding mBinding;

        public ItemViewHolder(ItemCharactorBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(Charactor charactor) {
            if (charactor == null) {
                return;
            }
            mBinding.setCharactor(charactor);
            mBinding.executePendingBindings();
        }
    }
}
