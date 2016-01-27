package hr.mars.muzicow.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.facebook.login.widget.LoginButton;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import hr.mars.muzicow.adapter.FragmentAdapterChoser;

import hr.mars.muzicow.models.DJ;
import hr.mars.muzicow.models.Login;
import hr.mars.muzicow.R;
import hr.mars.muzicow.utils.Registry;
import hr.mars.muzicow.services.TwitterLoginListener;
import hr.mars.muzicow.utils.SNetworkChooser;
import hr.mars.muzicow.utils.TwitterAuth;
import hr.mars.muzicow.utils.TwitterRetData;
import io.fabric.sdk.android.Fabric;

/**
 * Created by mars on 14/11/15.
 */
public class MainActivity extends AppCompatActivity {
    private TwitterLoginButton loginButton;

    Login at = new Login();
    TwitterAuth twitter = new TwitterAuth();
    TwitterRetData tw = new TwitterRetData();
    SNetworkChooser ch = new SNetworkChooser();
    String role;
    Intent intent;
    Bundle bundle;
    DJ djObject;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Registry.getInstance().set("login.atr", at);
        twitter.setProvider("Twitter");
        twitter.setKey("f6FNdst2ZaoQWZYvYOu2a5QCy");
        twitter.setSecret("deHaJ2nBf5Lj5luPg2Avu7w0JOxbb61GUNZavlb4SELDyK0WUV");

        //Log.d("getKey", twitter.getKey());
        //Log.d("getSecret", twitter.getSecret());

        TwitterAuthConfig authConfig = new TwitterAuthConfig(twitter.getKey(), twitter.getSecret());
        Fabric.with(this, new Twitter(authConfig));

        super.onCreate(savedInstanceState);

        setContentView(R.layout.social_network_login);
        final Spinner mySpinner = (Spinner) findViewById(R.id.spinner);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                role = mySpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        try {
            intent = getIntent();
            bundle = intent.getExtras();
        } catch (Exception e) {

        }
        if (bundle != null) {

            ((Login) Registry.getInstance().get("login.atr")).setRole(bundle.getString(role));

        }

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter);
        twitter.signup(loginButton);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        loginButton.onActivityResult(requestCode, resultCode, data);

    }


}