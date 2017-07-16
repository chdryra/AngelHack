package io.angelhack.verd.model;

import android.content.Context;
import android.util.Log;

import java.util.UUID;

import io.angelhack.verd.persistence.CloudStore;

/**
 * Created by sameenislam on 15/07/2017.
 */

public class ModelVerd implements ModelVerdIFace {

    private static ModelVerd instance = null;

    public static ModelVerd getInstance(Context context) {
        if(instance == null) {
            instance = new ModelVerd(context);
        }
        return instance;
    }

    private CloudStore cloudStorage;

    public CloudStore getCloudStorage() {
        return cloudStorage;
    }

    protected ModelVerd(Context context) {
        this.cloudStorage = CloudStore.getInstance(context);
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
        cloudStorage.addReview(review);
        Log.d("VERD", "Review added to FIREBASE.");
    }
}
