package io.angelhack.verd.model;

import java.util.List;
import java.util.UUID;

/**
 * Represents a unique user in the system.
 * Created by sameenislam on 15/07/2017.
 */

public class User {

    private UUID id;
    private List<UUID> following;
    private List<UUID> followers;

    public User() {
    }

    public User(UUID id, List<UUID> following, List<UUID> followers) {
        this.id = id;
        this.following = following;
        this.followers = followers;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
