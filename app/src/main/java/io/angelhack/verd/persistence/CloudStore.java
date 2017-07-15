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
import io.angelhack.verd.model.UserImage;

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
    public CloudStore() {
        this.db = FirebaseDatabase.getInstance();
        this.dbRef = this.db.getReference();
    }

    public CloudStore(FirebaseDatabase database) {
        this.db = database;
        this.dbRef = this.db.getReference();
    }

    @Override
    public void addProfile(Profile profile, UserImage userImage) {
        User user = profile.getUser();

        // Store the following in Firebase RT DB.
        String userIdStr = user.getId().toString();
        String profileName = profile.getName();

        writeUser(userIdStr, profile);

        // Images will be stored in Firebase 'Storage'.



    }

    private void writeUser(String userIdStr, Profile profile) {
        this.dbRef.child(userIdStr).child("profile").setValue(profile);
    }

    @Override
    public void addReview(Review review) {

    }

    public User getUser(UUID userID) {

        User user = null; // TODO: Fetch user from Firebase by userID.

        /*
         * Convert UUID to String for storage.
         */
//        List<String> followerIDs = new ArrayList<>();
//        for (UUID follower : user.getFollowers()) {
//            followerIDs.add(follower.toString());
//        }

//        List<String> followingIDs = new ArrayList<>();
//        for (UUID following : user.getFollowing()) {
//            followingIDs.add(following.toString());
//        }
        return null;
    }

}
