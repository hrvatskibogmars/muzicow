package hr.mars.muzicow.fragments.Login;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import hr.mars.muzicow.Adapters.FragmentAdapter;
import hr.mars.muzicow.Interface.Sesija;
import hr.mars.muzicow.Interface.SocialnetworkManager;
import hr.mars.muzicow.R;
import io.fabric.sdk.android.Fabric;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by Emil on 26.11.2015..
 */

public class Login extends AppCompatActivity  {


    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "f6FNdst2ZaoQWZYvYOu2a5QCy";
    private static final String TWITTER_SECRET = "deHaJ2nBf5Lj5luPg2Avu7w0JOxbb61GUNZavlb4SELDyK0WUV ";

    private TwitterLoginButton loginButton;
    String role;

    /*
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
          TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
                Fabric.with(this, new Twitter(authConfig));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            role = bundle.getString("userRole");


        }



        loginButton = (TwitterLoginButton) findViewById(R.id.twitter);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void onResponse(Response<TwitterSession> response) {
                String text = Twitter.getSessionManager().getActiveSession().toString();
                Intent myIntent = new Intent(Login.this, FragmentAdapter.class);
                myIntent.putExtra("userRole", "Korisnik");
                Sesija.getInstance().equals(text);
                myIntent.putExtra("sesija", text);
                Login.this.startActivity(myIntent);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast toast = Toast.makeText(Login.this, "ne radi", Toast.LENGTH_LONG);
                toast.show();
            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);

    }

*/
}
