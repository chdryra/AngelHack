package io.angelhack.verd.model;

import android.graphics.Bitmap;

/**
 * Stores human-readable information for a given user.
 * Created by sameenislam on 15/07/2017.
 */

public class Profile {

    private User user;
    private String name;
    private Bitmap photo;

    public Profile() {
    }

    public Profile(User user, String name, Bitmap photo) {
        this.user = user;
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
