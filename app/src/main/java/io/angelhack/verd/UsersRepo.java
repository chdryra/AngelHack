package io.angelhack.verd;

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

    public Profile getProfile(User user) {
        return mModel.getProfile(user);
    }
}
