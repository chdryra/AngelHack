package io.angelhack.verd;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.angelhack.verd.model.Review;

/**
 * Created by: Rizwan Choudrey
 * On: 15/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class RecyclerAdapter extends RecyclerView.Adapter<ReviewViewHolder>{
    private static final int RECYCLER_VIEW_ITEM = R.layout.recycler_view_item;
    private List<Review> mReviews;
    private UsersRepo mUsers;
    private Context mContext;

    public RecyclerAdapter(UsersRepo users) {
        mReviews = new ArrayList<>();
        mUsers = users;
    }

    public void addReview(Review review) {
        mReviews.add(review);
        Collections.sort(mReviews, new Comparator<Review>() {
            public int compare(Review lhs, Review rhs) {
                return rhs.getTimestamp().compareTo(lhs.getTimestamp());
            }
        });
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mContext = recyclerView.getContext();
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(RECYCLER_VIEW_ITEM, parent, false);
        ReviewViewHolder vh = new ReviewViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.setReviewData(mReviews.get(position), mUsers, mContext);
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }
}
