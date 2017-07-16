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
    private static final int TOP = R.id.top_button;
    private static final int BOTTOM = R.id.bottom_button;
    public static final String RIZ = "ff4a3c26-f1c3-4475-a65e-4e78e0e64eef";
    public static final String RIZ_NAME = "Riz";
    public static final String FARAZ = "ff4a3c26-f1c3-4475-a65e-4e78e0e65eef";
    public static final String FARAZ_NAME = "Faraz";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        Button riz = (Button) findViewById(TOP);
        Button faraz = (Button) findViewById(BOTTOM);

        riz.setText(RIZ_NAME);
        faraz.setText(FARAZ_NAME);
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
