package hr.mars.muzicow.fragments.Login;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import hr.mars.muzicow.Adapters.FragmentAdapter;
import hr.mars.muzicow.Interface.Sesija;
import hr.mars.muzicow.Interface.SocialnetworkManager;
import hr.mars.muzicow.R;


/**
 * Created by Emil on 26.11.2015..
 */
public class Login extends AppCompatActivity  {
/*

    private TwitterLoginButton loginButton;
    String role;

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
            public void success(Result<TwitterSession> result) {
                String text = Twitter.getSessionManager().getActiveSession().toString();
                Intent myIntent = new Intent(Login.this, FragmentAdapter.class);
                myIntent.putExtra("userRole", "Korisnik");
                Sesija.getInstance().equals(text);
                myIntent.putExtra("sesija", text);
                Login.this.startActivity(myIntent);
            }

            @Override
            public void failure(TwitterException exception) {
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
