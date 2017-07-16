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
import io.angelhack.verd.model.Review;
import io.angelhack.verd.model.UserImage;

/**
 * Firebase cloud realtime database.
 * Created by sameenislam on 15/07/2017.
 */

public class CloudStore implements PersistenceIFace {

    public static CloudStore instance = null;

    public static CloudStore getInstance() {
        if(instance == null)
            instance = new CloudStore();
        return instance;
    }

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
        if (userImage != null) {
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
                    @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
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
    public void addReview(final FBReview review) {
        if(review.getImageUri() != null) {
            writeImageThenReview(review);
        } else {
            writeReview(review);
        }
    }

    private void writeImageThenReview(final FBReview review) {
        try {
            StorageReference imgRef = this.storageReference.child(review.getReviewId())
                    .child("reviewPic.jpeg");
            InputStream stream = new FileInputStream(new File(review.getImageUri().toString()));
            UploadTask uploadTask = imgRef.putStream(stream);

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    writeReview(review);
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests")  Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    review.setImageUri(downloadUrl.getPath());
                    writeReview(review);
                }
            });

        } catch (FileNotFoundException e) {
            Log.e("VERD", "Unable to locate FBProfile picture URI!");
        }
    }

    private void writeReview(FBReview review) {
        this.dbRef.child(review.getReviewId()).child("review").setValue(review);
    }

}
