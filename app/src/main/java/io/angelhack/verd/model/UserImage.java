package io.angelhack.verd.model;

import android.graphics.Bitmap;

/**
 * Created by sameenislam on 15/07/2017.
 */

public class UserImage {

    private String userID;
    private Bitmap photo;

    public UserImage() {
    }

    public UserImage(Bitmap photo) {
        this.photo = photo;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
