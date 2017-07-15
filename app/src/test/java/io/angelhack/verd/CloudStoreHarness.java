package io.angelhack.verd;

import android.content.Context;

import com.google.firebase.FirebaseApp;

import org.junit.Test;

import io.angelhack.verd.model.Profile;
import io.angelhack.verd.model.User;
import io.angelhack.verd.model.UserImage;
import io.angelhack.verd.persistence.CloudStore;

/**
 * Created by sameenislam on 15/07/2017.
 */

public class CloudStoreHarness {

    @Test
    public void addProfileTest() {
        Context context = InstrumentationRegistry.getContext();
        FirebaseApp.initializeApp(this.getInstrumentation().getTargetContext().getApplicationContext());
        User fakeUser = User.generate();
        Profile fakeProfile = new Profile(fakeUser, "Joe Blogg");
        UserImage profilePic = new UserImage(fakeUser, null);
        CloudStore cloud = new CloudStore();
        cloud.addProfile(fakeProfile, profilePic);
    }

}
