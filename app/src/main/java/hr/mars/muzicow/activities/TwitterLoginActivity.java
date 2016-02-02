package hr.mars.muzicow.activities;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import hr.mars.muzicow.R;
import hr.mars.muzicow.models.Login;
import hr.mars.muzicow.utils.Registry;
import hr.mars.muzicow.utils.SocialAuth;
import hr.mars.muzicow.utils.TwitterAuth;

/**
 * Created by Emil on 2.2.2016..
 */
public class TwitterLoginActivity extends AppCompatActivity {
    Login at = new Login();
    SocialAuth authManager = new TwitterAuth();
    private TwitterLoginButton loginButton;
    String role;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Registry.getInstance().set("login.atr", at);

        authManager.setContext(getBaseContext());
        authManager.setSocialObject(loginButton);
        authManager.setProvider("Twitter");
        authManager.setKey("f6FNdst2ZaoQWZYvYOu2a5QCy");
        authManager.setSecret("deHaJ2nBf5Lj5luPg2Avu7w0JOxbb61GUNZavlb4SELDyK0WUV");
        authManager.setup();

        setContentView(R.layout.twitter_login); // mjenjat
        loginButton = (TwitterLoginButton) findViewById(R.id.twitter);
        authManager.signup(loginButton);


        final Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                role = mySpinner.getSelectedItem().toString();
                authManager.setRole(role);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);

    }
}
