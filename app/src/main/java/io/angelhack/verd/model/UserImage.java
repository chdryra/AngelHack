package io.angelhack.verd.model;

import android.graphics.Bitmap;

import java.net.URI;

/**
 * Created by sameenislam on 15/07/2017.
 */

public class UserImage {

    private User user;
    private Bitmap photo;
    private URI photoURI;

    public UserImage() {
    }

    public UserImage(User user, Bitmap photo) {
        this.user = user;
        this.photo = photo;

    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public URI getPhotoURI() {
        return photoURI;
    }
}
