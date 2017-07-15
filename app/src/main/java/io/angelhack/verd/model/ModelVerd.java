package io.angelhack.verd.model;

import java.util.UUID;

/**
 * Created by sameenislam on 15/07/2017.
 */

public class ModelVerd implements ModelVerdIFace {

    private static ModelVerd instance = null;

    protected ModelVerd() {

    }

    public static ModelVerdIFace getInstance() {
        if(instance == null)
            instance = new ModelVerd();
        return instance;
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
        throw new UnsupportedOperationException();
    }
}
