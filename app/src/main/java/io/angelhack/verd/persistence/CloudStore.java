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

import io.angelhack.verd.firebase.FBProfile;
import io.angelhack.verd.firebase.FBReview;
import io.angelhack.verd.model.UserImage;

/**
 * Firebase cloud realtime database.
 * Created by sameenislam on 15/07/2017.
 */

public class CloudStore implements PersistenceIFace {

    private static CloudStore instance = null;
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

    public static CloudStore getInstance() {
        if (instance == null)
            instance = new CloudStore();
        return instance;
    }

    @Override
    public void addProfile(final FBProfile profile, @Nullable UserImage userImage) {
        if (userImage != null) {
            writeImageThenUser(profile, userImage);
        } else {
            writeUser(profile);
        }

    }

    @Override
    public void addReview(final FBReview review) {
        writeImageThenReview(review);
    }

    private void writeImageThenUser(final FBProfile profile, UserImage userImage) {
        try {
            StorageReference imgRef = this.storageReference.child(profile.getId())
                    .child("profilePic.jpeg");
            File file = new File(userImage.getPhotoURI().getPath());
            InputStream stream = new FileInputStream(file.getAbsolutePath());
            UploadTask uploadTask = imgRef.putStream(stream);

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    writeUser(profile);
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot
                            .getDownloadUrl();
                    if (downloadUrl != null) profile.setPhotoURI(downloadUrl.getPath());
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

    private void writeImageThenReview(final FBReview review) {
        String imageUri = review.getImageUri();
        if (imageUri != null) {
            try {
                StorageReference imgRef = this.storageReference.child(review.getReviewId())
                        .child("image.jpeg");
                File file = new File(imageUri);
                InputStream stream = new FileInputStream(file.getAbsolutePath());
                UploadTask uploadTask = imgRef.putStream(stream);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        writeReview(review);
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot
                                .getDownloadUrl();
                        if (downloadUrl != null) {
                            review.setImageUri(downloadUrl.getPath());
                        }
                        writeReview(review);
                    }
                });

            } catch (FileNotFoundException e) {
                Log.e("VERD", "Unable to locate FBProfile picture URI!");
            }
        } else {
            writeReview(review);
        }
    }

    private void writeReview(FBReview review) {
        this.dbRef.child(review.getUserId()).child("reviews").child(review.getReviewId())
                .setValue(review);
    }

}
