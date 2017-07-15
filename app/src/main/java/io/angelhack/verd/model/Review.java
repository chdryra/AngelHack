package io.angelhack.verd.model;

import android.net.Uri;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Review {

    private User userId;
    private UUID reviewID;
    private int rating;
    private List<String> tags; // optional
    private Uri imageUri; // optional
    private String comment; // optional
    private Date timestamp;

    public Review() {

    }

    public Review(User userId, UUID reviewID, int rating, List<String> tags,
                  String comment, Date timestamp) {
        this.reviewID=reviewID;
        this.rating = rating;
        this.tags = tags;
        this.comment = comment;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public UUID getReviewID() {
        return reviewID;
    }

    public void setReviewID(UUID reviewID) {
        this.reviewID = reviewID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
