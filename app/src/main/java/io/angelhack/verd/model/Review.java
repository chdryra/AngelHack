package io.angelhack.verd.model;

import android.media.Image;

import java.util.List;
import java.util.UUID;

public class Review {

    @Deprecated
    String subject;

    UUID reviewID;
    int rating;

    List<String> tags; // optional
    Image image; // optional
    String comment; // optional

    public Review() {
    }

    @Deprecated
    public Review(String subject, int rating, List<String> tags, Image image, String comment) {
        this.subject = subject;
        this.rating = rating;
        this.tags = tags;
        this.image = image;
        this.comment = comment;
    }

    @Deprecated
    public Review(String subject, int rating, List<String> tags) {
        this.subject = subject;
        this.rating = rating;
        this.tags = tags;
    }

    public Review(UUID reviewID, int rating, List<String> tags, Image image, String comment) {
        this.reviewID=reviewID;
        this.rating = rating;
        this.tags = tags;
        this.image = image;
        this.comment = comment;
    }


}
