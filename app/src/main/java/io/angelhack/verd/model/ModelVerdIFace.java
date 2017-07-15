package io.angelhack.verd.model;

import java.util.UUID;

/**
 * Created by sameenislam on 15/07/2017.
 */

public interface ModelVerdIFace {

    Profile getProfile(UUID userID);
    Review getReview(UUID reviewID);


}
