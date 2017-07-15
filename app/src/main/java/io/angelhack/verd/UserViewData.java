package io.angelhack.verd;

import android.net.Uri;

import java.util.UUID;

/**
 * Created by: Rizwan Choudrey
 * On: 15/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class UserViewData {
    private final UUID mId;
    private final String mName;
    private final Uri mImage;

    public UserViewData(UUID id, String name, Uri image) {
        mId = id;
        mName = name;
        mImage = image;
    }

    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public Uri getImage() {
        return mImage;
    }
}
