package io.angelhack.verd.model;

import android.media.Image;

import java.util.List;

public class Review {

    @Deprecated
    String subject;

    int rating;
    List<String> tags;
    Image image;
    String comment;

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

    public Review(int rating, List<String> tags, Image image, String comment) {
        this.rating = rating;
        this.tags = tags;
        this.image = image;
        this.comment = comment;
    }
}
