package io.angelhack.verd;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

import io.angelhack.verd.model.ModelVerdIFace;
import io.angelhack.verd.model.Review;

import static android.R.attr.content;
import static android.R.attr.contextUri;
import static android.R.attr.x;

public class NewReviewActivity extends AppCompatActivity {

    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_review_activity);
        int optionChosen = getIntent().getIntExtra("option",1);

        if (optionChosen == 1) {
            getGalleryImage();
        } else if (optionChosen == 0) {
            dispatchTakePictureIntent();
        }
    }

    final int REQUEST_IMAGE_CAPTURE = 0;
    final int PICK_IMAGE_REQUEST = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void getGalleryImage() {
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        // Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);


                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                try {
                    int orientation = getOrientation(getApplicationContext(),uri);
                    Matrix matrix = new Matrix();
                    if (orientation == 90) {
                        matrix.postRotate(90);
                    }
                    else if (orientation == 180) {
                        matrix.postRotate(180);
                    }
                    else if (orientation == 270) {
                        matrix.postRotate(270);
                    }
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true); // rotating bitmap
                }
                catch (Exception e) {

                }
                ImageView img = (ImageView) findViewById(R.id.imageView);
                img.setImageBitmap(bitmap);

                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int getOrientation(Context context, Uri photoUri) {
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[]{MediaStore.Images.ImageColumns.ORIENTATION}, null, null, null);

        if (cursor.getCount() != 1) {
            cursor.close();
            return -1;
        }

        cursor.moveToFirst();
        int orientation = cursor.getInt(0);
        cursor.close();
        cursor = null;
        return orientation;
    }

    int emojiRating = 0;
    public void onRadioButtonClicked(View view){
        if(view.getId()== R.id.verd_radio_button) {
            emojiRating = 3;
        }
        if(view.getId()== R.id.good_radio_button) {
            emojiRating = 2;
        }
        if(view.getId()== R.id.average_radio_button) {
            emojiRating = 1;
        }
        if(view.getId()== R.id.bad_radio_button) {
            emojiRating = 0;
        }

    }
    public Review shareReviewOnClick(){


        return null;
    }

}
