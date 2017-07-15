package io.angelhack.verd.firebase;

/**
 * Created by sameenislam on 15/07/2017.
 */

public class FBProfile {

    private String id;
    private String name;
    private String photoURI;

    public FBProfile() {

    }

    public FBProfile(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPhotoURI() {
        return photoURI;
    }

    public void setPhotoURI(String photoURI) {
        this.photoURI = photoURI;
    }

    public String getId() {
        return id;
    }
}
