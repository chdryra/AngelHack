package io.angelhack.verd.model;

/**
 * Created by sameenislam on 15/07/2017.
 */

public interface ModelVerdIFace {

    Profile getProfile(User user);
    void addProfile(Profile profile);
    void addReview(Review review);

}
