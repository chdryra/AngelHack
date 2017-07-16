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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import io.angelhack.verd.model.ModelVerd;
import io.angelhack.verd.model.Profile;
import io.angelhack.verd.model.Review;
import io.angelhack.verd.model.Session;
import io.angelhack.verd.model.User;
import io.angelhack.verd.persistence.CloudStore;

public class FeedActivity extends AppCompatActivity implements CloudStore.FeedListener{

    private FeedPresenter mPresenter;
    private CharSequence imageOptions[] = new CharSequence[] {"Take a picture", "Choose from gallery"};
    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setActionButton();
        setBottomNavigation();
        mPresenter = new FeedPresenter(ModelVerd.getInstance(this));
        setRecyclerView();
        getFeed();
    }

    private void setBottomNavigation() {
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
                            Snackbar.make(bottomNavigationView, "profile pressed", Snackbar.LENGTH_SHORT)
                                    .setAction("Action", null).show();
                        }
                    return true;

                    }
                });
    }

    private void setActionButton() {
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
    }

    private void getFeed() {
        CloudStore cs = CloudStore.getInstance(this);
        User user = Session.getInstance().getUser();
        Profile profile = ModelVerd.getInstance(this).getProfile(user);
        cs.getFeed(profile, this);
    }

    @Override
    public void onReview(Review review) {
        mAdapter.addReview(review);
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerAdapter(mPresenter.getUsersRepo());
        recyclerView.setAdapter(mAdapter);
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
