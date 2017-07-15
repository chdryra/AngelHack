package io.angelhack.verd;

import java.util.UUID;

import io.angelhack.verd.model.ModelVerdIFace;
import io.angelhack.verd.model.Profile;

/**
 * Created by: Rizwan Choudrey
 * On: 16/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class UsersRepo {
    private ModelVerdIFace mModel;

    public UsersRepo(ModelVerdIFace model) {
        mModel = model;
    }

    public UserViewData getProfile(UUID userId) {
        Profile profile = mModel.getProfile(userId);
        return new UserViewData(userId, profile.getName(), null);
    }
}
