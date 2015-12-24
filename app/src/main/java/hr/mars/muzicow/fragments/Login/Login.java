package hr.mars.muzicow.fragments.Login;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import hr.mars.muzicow.Adapters.FragmentAdapter;
import hr.mars.muzicow.R;
import io.fabric.sdk.android.Fabric;



/**
 * Created by Emil on 26.11.2015..
 */

public class Login extends AppCompatActivity  {


    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "f6FNdst2ZaoQWZYvYOu2a5QCy";
    private static final String TWITTER_SECRET = "deHaJ2nBf5Lj5luPg2Avu7w0JOxbb61GUNZavlb4SELDyK0WUV ";

    private TwitterLoginButton loginButton;
    //private LoginButton loginButton2;
    //CallbackManager callbackManager;
    String role;
    String userName;
    Long userID;
    String sess;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
          TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
                Fabric.with(this, new Twitter(authConfig));
       // FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            role = bundle.getString("userRole");


        }
        /*
        callbackManager = CallbackManager.Factory.create();

        loginButton2 = (LoginButton) findViewById(R.id.facebook);
        loginButton2.setReadPermissions();
        loginButton2.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Log.i("TAG",loginResult.getAccessToken().getSource().name());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

                Log.i("TAG",error.toString());
            }
        });

*/

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter);
        loginButton.setCallback(new Callback<TwitterSession>() {

            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                userName = session.getUserName();
                userID = session.getUserId();
                sess = Twitter.getInstance().core.getSessionManager().getActiveSession().toString();
                Intent myIntent = new Intent(Login.this, FragmentAdapter.class);
                if(role.equals("Korisnik")) {
                    myIntent.putExtra("userRole", "Korisnik");
                    myIntent.putExtra("sesija", sess);
                    Login.this.startActivity(myIntent);
                }
                else{
                    myIntent.putExtra("userRole", "DJ");
                    myIntent.putExtra("sesija", sess);
                    Login.this.startActivity(myIntent);

                }

            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        //callbackManager.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);

    }
/*
    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

*/
}
