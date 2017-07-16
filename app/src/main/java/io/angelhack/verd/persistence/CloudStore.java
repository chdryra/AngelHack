package io.angelhack.verd.persistence;

import android.content.Context;
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

    private static CloudStore instance = null;
    private Context context;
    private FirebaseDatabase db; // db instance
    private FirebaseStorage store;
    private DatabaseReference dbRef; // db reference
    private StorageReference storageReference; // for storing imgs
    /**
     * Creates a new instance of FirebaseDatabase.
     */
    public CloudStore(Context context) {
        this.db = FirebaseDatabase.getInstance();
        this.dbRef = this.db.getReference();
        this.store = FirebaseStorage.getInstance();
        this.storageReference = this.store.getReference();
        this.context = context;
    }

    public CloudStore(FirebaseDatabase database) {
        this.db = database;
        this.dbRef = this.db.getReference();

    }

    public static CloudStore getInstance(Context context) {
        if (instance == null)
            instance = new CloudStore(context);
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
    public void addReview(final Review review) {
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

    private void writeImageThenReview(final Review review) {
        Uri imageUri = review.getImageUri();
        final FBReview fbReview = new FBReview(review.getUserId().getId().toString(),
                review.getReviewID().toString(), review.getRating(), review.getComment(), review.getTimestamp().getTime());
        if (imageUri != null) {
            try {
                StorageReference imgRef = this.storageReference.child(fbReview.getReviewId())
                        .child("image.jpeg");
                InputStream stream = context.getContentResolver().openInputStream(review.getImageUri());
                UploadTask uploadTask = imgRef.putStream(stream);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        writeReview(fbReview);
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot
                                .getDownloadUrl();
                        if (downloadUrl != null) {
                            fbReview.setImageUri(downloadUrl.getPath());
                        }
                        writeReview(fbReview);
                    }
                });

            } catch (FileNotFoundException e) {
                Log.e("VERD", "Unable to locate FBProfile picture URI!");
            }
        } else {
            writeReview(fbReview);
        }
    }

    private void writeReview(FBReview review) {
        this.dbRef.child(review.getUserId()).child("reviews").child(review.getReviewId())
                .setValue(review);
    }

}
