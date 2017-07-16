package io.angelhack.verd;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.UUID;

import io.angelhack.verd.model.Session;
import io.angelhack.verd.model.User;

/**
 * Created by: Rizwan Choudrey
 * On: 16/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class LoginActivity extends AppCompatActivity {
    private static final int LAYOUT = R.layout.activity_login;
    private static final int RIZ_B = R.id.riz_button;
    private static final int FARAZ_B = R.id.faraz_button;
    private static final int SAM_B = R.id.sam_button;
    private static final int GAUTHAM_B = R.id.gautham_button;
    private static final int JAY_B = R.id.jay_button;
    public static final String RIZ = "ff4a3c26-f1c3-4475-a65e-4e78e0e64eef";
    public static final String RIZ_NAME = "Riz";
    public static final String FARAZ = "ff4a3c26-f1c3-4475-a65e-4e78e0e65eef";
    public static final String FARAZ_NAME = "Faraz";
    public static final String SAM = "ff4a3c26-f1c3-4475-a65e-4e78e0e66eef";
    public static final String SAM_NAME = "Sam";
    public static final String GAUTHAM = "ff4a3c26-f1c3-4475-a65e-4e78e0e67eef";
    public static final String GAUTHAM_NAME = "Gautham";
    public static final String JAY = "ff4a3c26-f1c3-4475-a65e-4e78e0e6eef";
    public static final String JAY_NAME = "Jay";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        Button riz = (Button) findViewById(RIZ_B);
        Button faraz = (Button) findViewById(FARAZ_B);
        Button sam = (Button) findViewById(SAM_B);
        Button gautham = (Button) findViewById(GAUTHAM_B);
        Button jay = (Button) findViewById(JAY_B);

        riz.setText(RIZ_NAME);
        faraz.setText(FARAZ_NAME);
        sam.setText(SAM_NAME);
        gautham.setText(GAUTHAM_NAME);
        jay.setText(JAY_NAME);

        riz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSession(RIZ);
            }
        });

        faraz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSession(FARAZ);
            }
        });

        sam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSession(SAM);
            }
        });

        gautham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSession(GAUTHAM);
            }
        });

        jay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSession(JAY);
            }
        });
    }

    private void setSession(String id) {
        User user = User.generate();
        user.setId(UUID.fromString(id));
        Session.newSession(user);

        Intent intent = new Intent(getBaseContext(), FeedActivity.class);
        startActivity(intent);
        finish();
    }
}
