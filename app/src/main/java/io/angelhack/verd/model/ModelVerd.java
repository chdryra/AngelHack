package io.angelhack.verd.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.UUID;

import io.angelhack.verd.LoginActivity;
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
    public Profile getProfile(User user) {
        Profile profile;
        if(user.getId().toString().equals(LoginActivity.RIZ)) {
            profile = getRiz();
            profile.addFollowing(getFaraz().getUser());
        } else if(user.getId().toString().equals(LoginActivity.FARAZ)){
            profile = getFaraz();
            profile.addFollowing(getRiz().getUser());
        } else if(user.getId().toString().equals(LoginActivity.SAM)){
            profile = getSam();
        } else if(user.getId().toString().equals(LoginActivity.GAUTHAM)){
            profile = getGautham();
        } else {
            profile = getJay();
        }

        return profile;
    }

    @NonNull
    private Profile getRiz() {
        User f = User.generate();
        f.setId(UUID.fromString(LoginActivity.RIZ));
        return new Profile(f, LoginActivity.RIZ_NAME);
    }

    @NonNull
    private Profile getFaraz() {
        User f = User.generate();
        f.setId(UUID.fromString(LoginActivity.FARAZ));
        return new Profile(f, LoginActivity.FARAZ_NAME);
    }

    @NonNull
    private Profile getSam() {
        User f = User.generate();
        f.setId(UUID.fromString(LoginActivity.SAM));
        return new Profile(f, LoginActivity.SAM_NAME);
    }

    @NonNull
    private Profile getGautham() {
        User f = User.generate();
        f.setId(UUID.fromString(LoginActivity.GAUTHAM));
        return new Profile(f, LoginActivity.GAUTHAM_NAME);
    }

    @NonNull
    private Profile getJay() {
        User f = User.generate();
        f.setId(UUID.fromString(LoginActivity.JAY));
        return new Profile(f, LoginActivity.JAY_NAME);
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
