package io.angelhack.verd.model;

import android.util.Log;

import java.util.UUID;

import io.angelhack.verd.firebase.FBReview;
import io.angelhack.verd.persistence.CloudStore;

/**
 * Created by sameenislam on 15/07/2017.
 */

public class ModelVerd implements ModelVerdIFace {

    private static ModelVerd instance = null;

    public static ModelVerd getInstance() {
        if(instance == null) {
            instance = new ModelVerd();
        }
        return instance;
    }

    private CloudStore cloudStorage;

    public CloudStore getCloudStorage() {
        return cloudStorage;
    }

    protected ModelVerd() {
        this.cloudStorage = CloudStore.getInstance();
    }

    @Override
    public Profile getProfile(UUID userID) {
        throw new UnsupportedOperationException();
//        return null;
    }

    @Override
    public Review getReview(UUID reviewID) {
        throw new UnsupportedOperationException();
//        return null;
    }

    @Override
    public void addProfile(Profile profile) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addReview(Review review) {
        FBReview fbReview = new FBReview(review.getUserId().getId().toString(),
                review.getReviewID().toString(), review.getRating(), review.getComment(),
                review.getTimestamp().getTime());

        cloudStorage.addReview(fbReview);
        Log.d("VERD", "Review added to FIREBASE.");
    }
}
