package hr.mars.muzicow.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import hr.mars.muzicow.Activities.adapters.FragmentAdapter;
import hr.mars.muzicow.Models.LoginAtributes;
import hr.mars.muzicow.Services.TwitterLoginListener;
import hr.mars.muzicow.Utils.SNetworkChooser;
import hr.mars.muzicow.Utils.TwitterRetData;
import hr.mars.muzicow.Models.DJ;
import hr.mars.muzicow.R;
import io.fabric.sdk.android.Fabric;


/**
 * Created by Emil on 26.11.2015..
 */

public class LoginActivity extends AppCompatActivity {

    private static final String TWITTER_KEY = "f6FNdst2ZaoQWZYvYOu2a5QCy";
    private static final String TWITTER_SECRET = "deHaJ2nBf5Lj5luPg2Avu7w0JOxbb61GUNZavlb4SELDyK0WUV ";
    private TwitterLoginButton loginButton;
    DJ djObject;
    public static LoginAtributes at = new LoginAtributes();
    TwitterRetData tw = new TwitterRetData();
    SNetworkChooser ch = new SNetworkChooser();

    public static LoginAtributes getAt() {
        return at;
    }
    public static void setAt(LoginAtributes at) {
        LoginActivity.at = at;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_network_login);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            at.setRole(bundle.getString("userRole"));
        }

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter);

        loginButton.setCallback(new Callback<TwitterSession>() {

            @Override
            public void success(Result<TwitterSession> result) {
                at.setSession(Twitter.getInstance().core.getSessionManager().getActiveSession());
                ch.setSNetwork(tw);
                ch.loginChoice();

                tw.setListener(new TwitterLoginListener() {
                    @Override
                    public void success(Result<User> userOb) {
                        djObject=new DJ();
                        Log.d("pokazi","ovo je id prosljeden"+userOb.data.idStr);
                        djObject.set_ID(userOb.data.idStr);
                        djObject.setName(userOb.data.name);
                        djObject.setDescription(userOb.data.description);
                        djObject.setLocation(userOb.data.location);
                        djObject.setProfile_url(userOb.data.profileImageUrl);
                        djObject.setNickname(userOb.data.screenName);
                        djObject.setWebsite(userOb.data.url);
                        showUserData(djObject);
                    }

                    @Override
                    public void failure(TwitterException e) {
                        Log.d("pokazi",e.getMessage());

                    }
                });

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

        loginButton.onActivityResult(requestCode, resultCode, data);

    }
    public void showUserData(DJ djObject){
        Intent myIntent = new Intent(LoginActivity.this, FragmentAdapter.class);
        if(at.getRole().equals("Korisnik")) {
            myIntent.putExtra("userRole", "Korisnik");
            myIntent.putExtra("sesija", at.getSession().toString());
            myIntent.putExtra("hr.mars.muzicow.RESTful.model.DJ",  djObject);
            LoginActivity.this.startActivity(myIntent);
        }
        else{
            myIntent.putExtra("userRole", "DJ");

            myIntent.putExtra("sesija", at.getSession().toString());
            myIntent.putExtra("hr.mars.muzicow.RESTful.model.DJ",  djObject);
            LoginActivity.this.startActivity(myIntent);
        }
    }


}
