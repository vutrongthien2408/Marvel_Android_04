package com.framgia.movie.screen.movie_detail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.movie.R;
import com.framgia.movie.data.model.Charactor;
import com.framgia.movie.databinding.ItemCharactorBinding;
import com.framgia.movie.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TrongThien on 9/25/2017.
 */

public class CharactorAdapter extends BaseRecyclerViewAdapter<CharactorAdapter.ItemViewHolder> {

    private List<Charactor> mCharactors = new ArrayList<>();
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Charactor> mItemClickListener;

    protected CharactorAdapter(@NonNull Context context) {
        super(context);
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Charactor> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCharactorBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_charactor, parent, false);
        return new ItemViewHolder(binding, mItemClickListener);
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
        private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Charactor>
                mItemClickListener;

        public ItemViewHolder(ItemCharactorBinding binding,
                BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Charactor> listener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = listener;
        }

        public void bind(Charactor charactor) {
            if (charactor == null) {
                return;
            }
            mBinding.setViewModel(new CharactorItemViewModel(charactor, mItemClickListener));
            mBinding.setCharactor(charactor);
            mBinding.executePendingBindings();
        }
    }
}
