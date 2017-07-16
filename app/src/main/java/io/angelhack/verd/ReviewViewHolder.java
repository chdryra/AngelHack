package io.angelhack.verd;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import io.angelhack.verd.model.Profile;
import io.angelhack.verd.model.Review;
import io.angelhack.verd.persistence.CloudStore;

/**
 * Created by: Rizwan Choudrey
 * On: 15/07/2017
 * Email: rizwan.choudrey@gmail.com
 */
public class ReviewViewHolder extends RecyclerView.ViewHolder {
    private static final int USER_PHOTO = R.id.user_photo;
    private static final int USER_NAME = R.id.user_name;
    private static final int REVIEW_PHOTO = R.id.review_photo;
    private static final int REVIEW_EMOJI = R.id.review_emoji;
    private static final int REVIEW_COMMENT = R.id.review_comment;
    private static final int REVIEW_DATE = R.id.review_date;
    private static final int LIKE_BUTTON = R.id.like_button;
    private static final int COMMENT_BUTTON = R.id.comment_button;
    private static final int SHARE_BUTTON = R.id.share_button;

    private final ImageView mUserPhoto;
    private final TextView mUserName;
    private final ImageView mReviewPhoto;
    private final ImageView mReviewEmoji;
    private final TextView mReviewComment;
    private final TextView mReviewDate;
    private final ImageButton mShareButton;
    private final ImageButton mLikeButton;
    private final ImageButton mCommentButton;

    public ReviewViewHolder(View v) {
        super(v);
        mUserPhoto = (ImageView)v.findViewById(USER_PHOTO);
        mUserName = (TextView)v.findViewById(USER_NAME);
        mReviewPhoto = (ImageView)v.findViewById(REVIEW_PHOTO);
        mReviewEmoji = (ImageView)v.findViewById(REVIEW_EMOJI);
        mReviewComment = (TextView)v.findViewById(REVIEW_COMMENT);
        mReviewDate = (TextView) v.findViewById(REVIEW_DATE);
        mShareButton = (ImageButton) v.findViewById(SHARE_BUTTON);
        mLikeButton = (ImageButton) v.findViewById(LIKE_BUTTON);
        mCommentButton = (ImageButton) v.findViewById(COMMENT_BUTTON);
    }

    public void setReviewData(Review review, UsersRepo repo, final Context context) {
        StorageReference ref = CloudStore.getInstance(context).getImageReference(review);
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context).load(uri).into(mReviewPhoto);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.d("Viewholder", exception.getMessage());
            }
        });

        mReviewComment.setText(review.getComment());
        Profile profile = repo.getProfile(review.getUserId());
        mUserPhoto.setImageBitmap(null);
        mUserName.setText(profile.getName());
        mUserPhoto.setImageResource(R.mipmap.profile_icon);
        int emoji;
        if(review.getRating() == 0) {
            emoji = R.mipmap.bad_emoji;
        } else {
            emoji = R.mipmap.verd_emoji;
        }
        mReviewEmoji.setImageResource(emoji);

        DateFormat df = SimpleDateFormat.getDateInstance();
        mReviewDate.setText(df.format(review.getTimestamp()));
    }
}
