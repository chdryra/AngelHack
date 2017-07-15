package io.angelhack.verd.test;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import io.angelhack.verd.model.Review;

public class DummyReviewHarness {

    final static int REVIEWS = 5;

    FileReader reader=new FileReader("dummyreviews.properties");

    public DummyReviewHarness() throws IOException {
    }
//
//    public static void main() throws IOException {
//        FileWriter writer=new FileWriter("dummyreviews.properties");
//        Properties p = new Properties();
//        for (int i = 0; i < REVIEWS; i++) {
//            p.setProperty("uuid"+i, ""+i);
//            p.setProperty("rating"+i, ""+new Random().nextInt(5));
//            int numTags = new Random().nextInt(4);
//            String tags = "";
//            for (int j = 0; j < numTags; j++) {
//                tags =  "tag"+j + (j < numTags-1 ? ",":"");
//            }
//            numTags !=0 ? p.setProperty("tags"+i, tags);
//            if(new Random().nextBoolean()) p.setProperty("comment"+i, "Sample Comment");
//        }
//        p.store(writer, "Dummy Reviews");
//    }

    public List<Review> getData() throws IOException {
        Properties p = new Properties();
        List<Review> reviews = new ArrayList<>();
        p.load(reader);
        for (int i = 0; i < REVIEWS; i++) {

            String fp = "img" + p.getProperty("image" + i) + ".jpeg";
            Bitmap bitmap = BitmapFactory.decodeFile(fp);
//            reviews.add(new Review(UUID.fromString(p.getProperty("uuid" + i)), Integer.parseInt(p.getProperty("rating" + i)), Arrays.asList(p.getProperty("tags" + i).split("\\s*,\\s*")), null, p.getProperty("comment" + i)));
        }
        for (Review review :
                reviews) {
            System.out.println(review);
        }
        return reviews;
    }
}
