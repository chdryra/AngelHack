package io.angelhack.verd.persistence;

import android.graphics.Bitmap;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.angelhack.verd.model.Profile;
import io.angelhack.verd.model.Review;
import io.angelhack.verd.model.User;

/**
 * Firebase cloud realtime database.
 * Created by sameenislam on 15/07/2017.
 */

public class CloudStore implements PersistenceIFace {

    private FirebaseDatabase db; // db instance
    private DatabaseReference dbRef; // db reference

    /**
     * Creates a new instance of FirebaseDatabase.
     */
    private CloudStore() {
        this.db = FirebaseDatabase.getInstance();
        this.dbRef = this.db.getReference();
    }

    public CloudStore(FirebaseDatabase database) {
        this.db = database;
        this.dbRef = this.db.getReference();
    }

    @Override
    public void addProfile(Profile profile) {
        User user = profile.getUser();

        // Store the following in Firebase RT DB.
        UUID userId = user.getId();
        String profileName = profile.getName();

        // Images will be stored in Firebase 'Storage'.
//        Bitmap profilePhoto = profile.getPhoto();

    }

    @Override
    public void addReview(Review review) {

    }

    private void writeNewUser(String userId, String name) {
//        this.dbRef.child("users").child(userId).setValue();
    }

    public void addUser(User user) {

        // Check for optionals
//        if(photo != null)
//            writeNewUser(id, name, photo);


    }

    public User getUser(UUID userID) {

        User user = null; // TODO: Fetch user from Firebase by userID.
        /*
         * Convert UUID to String for storage.
         */
        List<String> followerIDs = new ArrayList<>();
        for (UUID follower : user.getFollowers()) {
            followerIDs.add(follower.toString());
        }

        List<String> followingIDs = new ArrayList<>();
        for (UUID following : user.getFollowing()) {
            followingIDs.add(following.toString());
        }
        return null;
    }

    public void writeReview(Review review) {

    }

    public String readReview() {
        return null;
    }

    public void writeProfile() {

    }




}
