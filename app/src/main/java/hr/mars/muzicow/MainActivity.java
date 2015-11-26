package hr.mars.muzicow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import hr.mars.muzicow.Adapters.FragmentAdapter;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;

/**
 * Created by mars on 14/11/15.
 */
public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "f6FNdst2ZaoQWZYvYOu2a5QCy";
    private static final String TWITTER_SECRET = "deHaJ2nBf5Lj5luPg2Avu7w0JOxbb61GUNZavlb4SELDyK0WUV ";

        Context context;
        private TwitterLoginButton loginButton;






        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
                Fabric.with(this, new Twitter(authConfig));
                setContentView(R.layout.proba);
                context = getApplicationContext();
/*
            loginButton = (TwitterLoginButton) findViewById(R.id.twitter);
            loginButton.setCallback(new Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> result) {
                    text=result.data.getUserName();
                    Intent myIntent = new Intent(MainActivity.this, FragmentAdapter.class);
                    myIntent.putExtra("userRole",text.toString());
                    MainActivity.this.startActivity(myIntent);
                }

                @Override
                public void failure(TwitterException exception) {
                    Toast toast = Toast.makeText(MainActivity.this, "ne radi", Toast.LENGTH_LONG);
                    toast.show();
                }
            });
            */



        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }




        public void buttonClickFunction(View v) {

            Spinner mySpinner=(Spinner) findViewById(R.id.spinner);
            String uloga = mySpinner.getSelectedItem().toString();
            Intent myIntent = new Intent(MainActivity.this, FragmentAdapter.class);
            myIntent.putExtra("userRole",uloga);
            MainActivity.this.startActivity(myIntent);



        }


}