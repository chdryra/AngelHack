package io.angelhack.verd.persistence;

import io.angelhack.verd.firebase.FBProfile;
import io.angelhack.verd.model.Profile;
import io.angelhack.verd.model.Review;
import io.angelhack.verd.model.UserImage;

/**
 * Created by sameenislam on 15/07/2017.
 */

interface PersistenceIFace {

    void addProfile(FBProfile profile, UserImage userImage);
    void addReview(Review review);


}
