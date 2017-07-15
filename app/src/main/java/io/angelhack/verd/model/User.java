package io.angelhack.verd.model;

import android.graphics.Bitmap;

import java.util.List;
import java.util.UUID;

/**
 * Created by sameenislam on 15/07/2017.
 */

public class User {

    private UUID id;
    private String name;
    private Bitmap photo;
    private List<UUID> following;
    private List<UUID> followers;

    public User() {
    }

    public User(UUID id, String name1, Bitmap photo, List<UUID> following, List<UUID> followers) {
        this.id = id;
        this.name = name1;
        this.photo = photo;
        this.following = following;
        this.followers = followers;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public List<UUID> getFollowing() {
        return following;
    }

    public void setFollowing(List<UUID> following) {
        this.following = following;
    }

    public List<UUID> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UUID> followers) {
        this.followers = followers;
    }
}
