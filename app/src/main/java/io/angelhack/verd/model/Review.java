package io.angelhack.verd.model;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Review {

    private User user;
    private UUID reviewID;
    private int rating;
    private List<String> tags; // optional
    private Bitmap image; // optional
    private String comment; // optional
    private Date timestamp;

    public Review() {

    }

    public Review(UUID reviewID, int rating, List<String> tags, Bitmap image, String comment) {
        this.reviewID=reviewID;
        this.rating = rating;
        this.tags = tags;
        this.image = image;
        this.comment = comment;
    }


}
