package io.angelhack.verd.model;

import java.util.List;
import java.util.UUID;

/**
 * Created by sameenislam on 15/07/2017.
 */

public class UserRelation {

    private User user;
    private List<UUID> following;
    private List<UUID> followers;

    public UserRelation() {
    }

    public UserRelation(User user, List<UUID> following, List<UUID> followers) {
        this.user = user;
        this.following = following;
        this.followers = followers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
