package io.angelhack.verd;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import io.angelhack.verd.firebase.SampleFirebaseSDK;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        SampleFirebaseSDK test = new SampleFirebaseSDK();
        test.sampleUpdate();
        test.readSampleUpdate();

        final BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if (item.getItemId() == R.id.feed_button) {
                            Snackbar.make(bottomNavigationView, "feed pressed", Snackbar.LENGTH_SHORT)
                                    .setAction("Action", null).show();
                        }

                        else if (item.getItemId() == R.id.search_button) {
                            Snackbar.make(bottomNavigationView, "search pressed", Snackbar.LENGTH_SHORT)
                                    .setAction("Action", null).show();
                        }

                        else if (item.getItemId() == R.id.my_profile_button) {
                            Snackbar.make(bottomNavigationView, "profile pressed", Snackbar.LENGTH_SHORT)
                                    .setAction("Action", null).show();
                        }
                    return true;

                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
