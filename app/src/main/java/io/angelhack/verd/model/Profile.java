package io.angelhack.verd.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores human-readable information for a given user.
 * Created by sameenislam on 15/07/2017.
 */

public class Profile {
    private User user;
    private String name;
    private List<User> following;

    public Profile(User user, String name) {
        this.user = user;
        this.name = name;
        this.following = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public void addFollowing(User user) {
        following.add(user);
    }

    public List<User> getFollowing() {
        return following;
    }
}
