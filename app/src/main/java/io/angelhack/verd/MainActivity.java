package io.angelhack.verd;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import io.angelhack.verd.firebase.SampleFirebaseSDK;

public class MainActivity extends AppCompatActivity {
    CharSequence imageOptions[] = new CharSequence[] {"Take a picture", "Choose from gallery"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final Intent intent = new Intent(getBaseContext(), NewReviewActivity.class);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                builder.setTitle("Add image");
                builder.setItems(imageOptions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int optionChosen) {
                        if(optionChosen==0){
                            intent.putExtra("option", optionChosen);
                            startActivity(intent);
                        }
                        else if(optionChosen==1){
                            intent.putExtra("option", optionChosen);
                            startActivity(intent);
                        }
                    }
                });
                builder.show();
            }
        });

        final BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if (item.getItemId() == R.id.feed_button) {

                        }

                        else if (item.getItemId() == R.id.search_button) {

                        }

                        else if (item.getItemId() == R.id.my_profile_button) {
                            final Intent intent = new Intent(getBaseContext(), MyProfile.class);
                            startActivity(intent);
                        }
                    return true;

                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
