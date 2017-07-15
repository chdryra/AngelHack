package io.angelhack.verd.persistence;

import com.google.firebase.database.FirebaseDatabase;

import io.angelhack.verd.model.Review;

/**
 * Firebase cloud realtime database.
 * Created by sameenislam on 15/07/2017.
 */

public class CloudStore {

    private FirebaseDatabase database;

    /**
     * Creates a new instance of FirebaseDatabase.
     */
    private CloudStore() {
        this.database = FirebaseDatabase.getInstance();
    }

    public CloudStore(FirebaseDatabase database) {
        this.database = database;
    }

    public void writeReview(Review review) {

    }

    public String readReview() {
        return null;
    }

    public void writeProfile() {

    }






}
