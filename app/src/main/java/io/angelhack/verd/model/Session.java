package io.angelhack.verd.model;

/**
 * Created by: Rizwan Choudrey
 * On: 16/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class Session {
    private static Session instance = null;
    private User mUser;

    private Session(User user) {
        mUser = user;
    }

    public static void newSession(User user) {
        instance = new Session(user);
    }

    public static Session getInstance() {
        return instance;
    }

    public User getUser() {
        return mUser;
    }
}
