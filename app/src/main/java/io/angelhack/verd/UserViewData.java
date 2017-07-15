package io.angelhack.verd;

import android.media.Image;

import java.util.UUID;

/**
 * Created by: Rizwan Choudrey
 * On: 15/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class UserViewData {
    private final UUID mId;
    private final String mName;
    private final Image mBitmap;

    public UserViewData(UUID id, String name, Image bitmap) {
        mId = id;
        mName = name;
        mBitmap = bitmap;
    }

    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public Image getBitmap() {
        return mBitmap;
    }
}
