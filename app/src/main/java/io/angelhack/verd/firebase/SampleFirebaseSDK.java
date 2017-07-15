package io.angelhack.verd.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by sameenislam on 15/07/2017.
 */

public class SampleFirebaseSDK {

    public SampleFirebaseSDK() {

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello Verd");
    }

}
