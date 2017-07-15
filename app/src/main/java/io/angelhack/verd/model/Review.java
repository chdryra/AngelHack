package io.angelhack.verd.model;

import android.net.Uri;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Review {
    private User userId;
    private UUID reviewID;
    private int rating;
    private Uri imageUri; // optional
    private String comment; // optional
    private Date timestamp;

    public Review(User userId, UUID reviewID, int rating,
                  String comment, Date timestamp, Uri imageUri) {
        this.reviewID=reviewID;
        this.rating = rating;
        this.comment = comment;
        this.userId = userId;
        this.timestamp = timestamp;
        this.imageUri = imageUri;
    }

    public User getUserId() {
        return userId;
    }

    public UUID getReviewID() {
        return reviewID;
    }

    public int getRating() {
        return rating;
    }

    public Uri getImageUri() {
        return imageUri;
    }


    public String getComment() {
        return comment;
    }

    public Date getTimestamp() {
        return timestamp;
    }

}
