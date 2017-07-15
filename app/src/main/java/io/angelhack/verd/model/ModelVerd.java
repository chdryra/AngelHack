package io.angelhack.verd.model;

import java.util.UUID;

/**
 * Created by sameenislam on 15/07/2017.
 */

public class ModelVerd implements ModelVerdIFace {

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
}
