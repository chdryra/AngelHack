package io.angelhack.verd;

import android.graphics.Bitmap;

import java.util.Date;
import java.util.UUID;

/**
 * Created by: Rizwan Choudrey
 * On: 15/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class ReviewViewData {
    private final UUID mId;
    private final UUID mAuthorId;
    private final Date mDate;
    private final Bitmap mBitmap;
    private final int mRating;
    private final String mComment;

    public ReviewViewData(UUID id, UUID authorId, Date date, Bitmap bitmap, int rating, String
            comment) {
        mId = id;
        mAuthorId = authorId;
        mDate = date;
        mBitmap = bitmap;
        mRating = rating;
        mComment = comment;
    }

    public UUID getId() {
        return mId;
    }

    public UUID getAuthorId() {
        return mAuthorId;
    }

    public Date getDate() {
        return mDate;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public int getRating() {
        return mRating;
    }

    public String getComment() {
        return mComment;
    }
}
