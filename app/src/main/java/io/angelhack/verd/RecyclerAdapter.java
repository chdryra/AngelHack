package io.angelhack.verd;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Rizwan Choudrey
 * On: 15/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class RecyclerAdapter extends RecyclerView.Adapter<ReviewViewHolder>{
    private static final int RECYCLER_VIEW_ITEM = R.layout.recycler_view_item;
    private List<ReviewViewData> mReviews;
    private UsersRepo mUsers;

    public RecyclerAdapter(UsersRepo users) {
        mReviews = new ArrayList<>();
        mUsers = users;
    }

    public void addReview(ReviewViewData review) {
        mReviews.add(review);
        notifyDataSetChanged();
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(RECYCLER_VIEW_ITEM, parent, false);
        ReviewViewHolder vh = new ReviewViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.setReviewData(mReviews.get(position), mUsers);
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }
}
