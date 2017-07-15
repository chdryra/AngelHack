package io.angelhack.verd.model;

import android.media.Image;

import java.util.List;

public class Review {

    String subject;
    int rating;
    List<String> tags;
    Image image;
    String comment;

    public Review(String subject, int rating, List<String> tags, Image image, String comment) {
        this.subject = subject;
        this.rating = rating;
        this.tags = tags;
        this.image = image;
        this.comment = comment;
    }

    public Review(String subject, int rating, List<String> tags) {
        this.subject = subject;
        this.rating = rating;
        this.tags = tags;
    }
}
