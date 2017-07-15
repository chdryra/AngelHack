package io.angelhack.verd.firebase;

/**
 * Created by sameenislam on 15/07/2017.
 */

public class FBReview {

    private String userId;
    private String reviewId;
    private int rating;
    private String imageUri;
    private String comment;
    private long timestamp;

    public FBReview() {
    }

    public FBReview(String userId, String reviewId, int rating, String comment, long timestamp) {
        this.userId = userId;
        this.reviewId = reviewId;
        this.rating = rating;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public String getReviewId() {
        return reviewId;
    }

    public int getRating() {
        return rating;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getComment() {
        return comment;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
