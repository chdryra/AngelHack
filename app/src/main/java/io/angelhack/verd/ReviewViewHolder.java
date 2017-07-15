package io.angelhack.verd;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by: Rizwan Choudrey
 * On: 15/07/2017
 * Email: rizwan.choudrey@gmail.com
 */
public class ReviewViewHolder extends RecyclerView.ViewHolder {
    private static final int USER_PHOTO = R.id.user_photo;
    private static final int USER_NAME = R.id.user_name;
    private static final int REVIEW_PHOTO = R.id.review_photo;
    private static final int REVIEW_COMMENT = R.id.review_comment;

    private final ImageView mUserPhoto;
    private final TextView mUserName;
    private final ImageView mReviewPhoto;
    private final TextView mReviewComment;

    public ReviewViewHolder(View v) {
        super(v);
        mUserPhoto = (ImageView)v.findViewById(USER_PHOTO);
        mUserName = (TextView)v.findViewById(USER_NAME);
        mReviewPhoto = (ImageView)v.findViewById(REVIEW_PHOTO);
        mReviewComment = (TextView)v.findViewById(REVIEW_COMMENT);
    }

    public void setReviewData(ReviewViewData review) {
        mReviewPhoto.setImageBitmap(review.getBitmap());
        mReviewComment.setText(review.getComment());
    }
}
