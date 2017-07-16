package io.angelhack.verd;

import java.util.UUID;

import io.angelhack.verd.model.ModelVerdIFace;
import io.angelhack.verd.model.Profile;
import io.angelhack.verd.model.User;

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

    public UserViewData getProfile(User userId) {
        UUID id = userId.getId();
        Profile profile = mModel.getProfile(id);
        return new UserViewData(id, profile.getName(), null);
    }
}
