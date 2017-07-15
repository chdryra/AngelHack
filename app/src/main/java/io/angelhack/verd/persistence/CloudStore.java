package io.angelhack.verd.persistence;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

import io.angelhack.verd.firebase.FBProfile;
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
    private FirebaseStorage store;
    private DatabaseReference dbRef; // db reference
    private StorageReference storageReference; // for storing imgs

    /**
     * Creates a new instance of FirebaseDatabase.
     */
    public CloudStore() {
        this.db = FirebaseDatabase.getInstance();
        this.dbRef = this.db.getReference();
        this.store = FirebaseStorage.getInstance();
        this.storageReference = this.store.getReference();
    }

    public CloudStore(FirebaseDatabase database) {
        this.db = database;
        this.dbRef = this.db.getReference();

    }

    @Override
    public void addProfile(final FBProfile profile, @Nullable UserImage userImage) {
        if(userImage != null) {
            writeImageThenUser(profile, userImage);
        } else {
            writeUser(profile);
        }

    }

    private void writeImageThenUser(final FBProfile profile, @Nullable UserImage userImage) {
        try {
            StorageReference imgRef = this.storageReference.child(profile.getId())
                    .child("profilePic.jpeg");
            InputStream stream = new FileInputStream(new File(userImage.getPhotoURI().toString()));
            UploadTask uploadTask = imgRef.putStream(stream);

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    writeUser(profile);
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    profile.setPhotoURI(downloadUrl.getPath());
                    writeUser(profile);
                }
            });

        } catch (FileNotFoundException e) {
            Log.e("VERD", "Unable to locate FBProfile picture URI!");
        }
    }


    private void writeUser(FBProfile profile) {
        this.dbRef.child(profile.getId()).child("profile").setValue(profile);
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
