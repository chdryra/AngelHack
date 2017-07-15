package io.angelhack.verd.firebase;

import java.net.URI;

/**
 * Created by sameenislam on 15/07/2017.
 */

public class Profile {
    private String name;
    private URI photoURI;

    public Profile() {
    }

    public String getName() {
        return name;
    }

    public URI getPhotoURI() {
        return photoURI;
    }
}
