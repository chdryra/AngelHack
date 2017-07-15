package io.angelhack.verd.model;

import android.media.Image;

import java.util.List;
import java.util.UUID;

public class Review {

    private UUID reviewID;
    private int rating;

    private List<String> tags; // optional
    private Image image; // optional
    private String comment; // optional

    public Review() {

    }

    public Review(UUID reviewID, int rating, List<String> tags, Image image, String comment) {
        this.reviewID=reviewID;
        this.rating = rating;
        this.tags = tags;
        this.image = image;
        this.comment = comment;
    }


}
