package io.angelhack.verd.model;

/**
 * Stores human-readable information for a given user.
 * Created by sameenislam on 15/07/2017.
 */

public class Profile {
    private User user;
    private String name;

    public Profile() {
    }

    public Profile(User user, String name) {
        this.user = user;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
