package io.angelhack.verd.persistence;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import io.angelhack.verd.firebase.FBProfile;
import io.angelhack.verd.firebase.FBReview;
import io.angelhack.verd.model.Profile;
import io.angelhack.verd.model.Review;
import io.angelhack.verd.model.User;
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

    public interface FeedListener {
        void onReview(Review review);
    }

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

    public StorageReference getImageReference(Review review) {
        return getImageReference(review.getReviewID().toString());
    }

    public void getFeed(Profile profile, final FeedListener callback) {
        sunscribeToFeed(profile.getUser(), callback);
        for(User user : profile.getFollowing()) {
            sunscribeToFeed(user, callback);
        }
    }

    private void sunscribeToFeed(final User user, final FeedListener callback) {
        DatabaseReference ref = this.dbRef.child(user.getId().toString()).child("reviews");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                FBReview review = dataSnapshot.getValue(FBReview.class);
                Uri imageUri = Uri.parse(review.getImageUri());
                Review rev = new Review(user, UUID.fromString(review.getReviewId()), review.getRating(), review.getComment(), new Date(review.getTimestamp()), imageUri);
                callback.onReview(rev);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void writeImageThenUser(final FBProfile profile, UserImage userImage) {
        try {
            InputStream stream = getImageInputStream(userImage);

            StorageReference imgRef = this.storageReference.child(profile.getId())
                    .child("profilePic.jpeg");
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

        } catch (Exception e) {
            Log.e("VERD", "Unable to locate FBProfile picture URI!");
        }
    }

    @NonNull
    private InputStream getImageInputStream(UserImage userImage) {
        Bitmap bmp = BitmapFactory.decodeFile(userImage.getPhotoURI().getPath());
        Bitmap scaled = Bitmap.createScaledBitmap(bmp, 300, 300, false);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        scaled.compress(Bitmap.CompressFormat.JPEG, 50, bos);
        return new ByteArrayInputStream(bos.toByteArray());
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
                String reviewId = fbReview.getReviewId();
                StorageReference imgRef = getImageReference(reviewId);
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

    @NonNull
    private StorageReference getImageReference(String reviewId) {
        return this.storageReference.child(reviewId)
                            .child("image.jpeg");
    }

    private void writeReview(FBReview review) {
        this.dbRef.child(review.getUserId()).child("reviews").child(review.getReviewId())
                .setValue(review);
    }

}
