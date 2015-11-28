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

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                //instanciram singleton fabric da mogu provjerit dal postoji sesija
                TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
                Fabric.with(this, new Twitter(authConfig));
                setContentView(R.layout.proba);
                context = getApplicationContext();

        }

        public void buttonClickFunction(View v) {

            Spinner mySpinner=(Spinner) findViewById(R.id.spinner);
            String uloga = mySpinner.getSelectedItem().toString();
            Intent myIntent = new Intent(MainActivity.this, FragmentAdapter.class);
            myIntent.putExtra("userRole",uloga);
            myIntent.putExtra("sesija","");
            MainActivity.this.startActivity(myIntent);



        }


}