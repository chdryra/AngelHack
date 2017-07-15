package io.angelhack.verd.model;

import java.util.List;
import java.util.UUID;

/**
 * Represents a unique user in the system.
 * Created by sameenislam on 15/07/2017.
 */

public class User {

    /**
     * Creates and returns a new User.
     * @return
     */
    public static User generate() {
        return new User(UUID.randomUUID());
    }

    private UUID id;


    private User(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
