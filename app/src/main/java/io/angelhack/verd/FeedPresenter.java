package io.angelhack.verd;

import io.angelhack.verd.model.ModelVerdIFace;

/**
 * Created by: Rizwan Choudrey
 * On: 16/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class FeedPresenter {
    private ModelVerdIFace mModel;

    public FeedPresenter(ModelVerdIFace model) {
        mModel = model;
    }

    public UsersRepo getUsersRepo() {
        return new UsersRepo(mModel);
    }
}
