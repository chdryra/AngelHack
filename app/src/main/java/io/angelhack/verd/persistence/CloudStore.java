package io.angelhack.verd.persistence;

import com.google.firebase.database.FirebaseDatabase;

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

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public void setDatabase(FirebaseDatabase database) {
        this.database = database;
    }




}
