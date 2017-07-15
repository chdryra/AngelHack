package io.angelhack.verd.persistence;

import io.angelhack.verd.model.Profile;
import io.angelhack.verd.model.Review;

/**
 * Created by sameenislam on 15/07/2017.
 */

interface PersistenceIFace {

    void addProfile(Profile profile);
    void addReview(Review review);


}
