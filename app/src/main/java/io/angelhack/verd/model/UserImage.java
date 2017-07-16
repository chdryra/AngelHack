package io.angelhack.verd.model;

import android.net.Uri;

/**
 * Created by sameenislam on 15/07/2017.
 */

public class UserImage {

    private User user;
    private Uri photoURI;

    public UserImage() {
    }

    public UserImage(User user, Uri uri) {
        this.user = user;
        this.photoURI = uri;

    }

    public User getUser() {
        return user;
    }

    public Uri getPhotoURI() {
        return photoURI;
    }
}
