package com.framgia.movie.screen.movie_detail;

import android.databinding.BaseObservable;
import android.view.View;
import com.framgia.movie.data.model.Charactor;
import com.framgia.movie.screen.BaseRecyclerViewAdapter;

/**
 * Created by TrongThien on 9/26/2017.
 */

public class CharactorItemViewModel extends BaseObservable {
    private Charactor mCharactor;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Charactor> mItemClickListener;

    public CharactorItemViewModel(Charactor charactor,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Charactor> itemClickListener) {
        mCharactor = charactor;
        mItemClickListener = itemClickListener;
    }

    public void onItemClicked(View view) {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mCharactor);
    }
}
