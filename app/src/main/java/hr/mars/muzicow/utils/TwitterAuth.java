package hr.mars.muzicow.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import hr.mars.muzicow.adapter.FragmentAdapterChooser;
import hr.mars.muzicow.models.DJ;
import hr.mars.muzicow.models.Login;
import io.fabric.sdk.android.Fabric;

/**
 * Created by mars on 27/01/16.
 */
public class TwitterAuth implements SocialAuth<Login, Context, TwitterLoginButton> {
    Context ctx;
    TwitterLoginButton loginButton;
    String Provider;
    String TWITTER_KEY;
    String TWITTER_SECRET;
    String role;
    TwitterSession session;
    DJ djObject;
    TwitterRetData twitterReadData = new TwitterRetData();

    @Override
    public Context getContext() {
        return this.ctx;
    }

    @Override
    public void setContext(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void setSocialObject(TwitterLoginButton loginButton) {
        this.loginButton = loginButton;
    }

    @Override
    public TwitterLoginButton getsocialObject() {
        return this.loginButton;
    }

    public void setup() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(this.getKey(), this.getSecret());
        Fabric.with(this.ctx, new Twitter(authConfig));
    }

    @Override
    public String getProvider() {
        return Provider;
    }

    @Override
    public void setProvider(String provider) {
        this.Provider = provider;
    }

    @Override
    public String getKey() {
        return TWITTER_KEY;
    }

    @Override
    public void setKey(String key) {
        this.TWITTER_KEY = key;
    }

    @Override
    public String getSecret() {
        return TWITTER_SECRET;
    }

    @Override
    public void setSecret(String key) {
        this.TWITTER_SECRET = key;
    }

    @Override
    public boolean save(Login object_login) {
        return false;
    }

    @Override
    public Login load() {
        return null;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public void signup(TwitterLoginButton loginButton) {
        /*
            Signup implementation for Twitter login.
        */
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                ((Login) Registry.getInstance().get("login.atr")).setSession(Twitter.getInstance().core.getSessionManager().getActiveSession());

                twitterReadData.retSNetData();
                twitterReadData.setListener(new TwitterLoginListener() {
                    @Override
                    public void success(Result<User> userOb) {
                        djObject = new DJ();
                        djObject.set_ID(userOb.data.idStr);
                        djObject.setName(userOb.data.name);
                        djObject.setDescription(userOb.data.description);
                        djObject.setLocation(userOb.data.location);
                        djObject.setProfile_url(userOb.data.profileImageUrl);
                        djObject.setNickname(userOb.data.screenName);
                        djObject.setWebsite(userOb.data.url);
                        showUserData(djObject);
                        Log.d("Name", djObject.getName());
                    }

                    @Override
                    public void failure(TwitterException e) {
                        Log.d("Tw - data get fail", e.getMessage());
                    }
                });
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });
    }

    public void showUserData(DJ djObject) {
        Intent myIntent = new Intent(this.ctx, FragmentAdapterChooser.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (role.equals("Participant")) {
            myIntent.putExtra("userRole", "Participant");
            myIntent.putExtra("Session", role);
            myIntent.putExtra("Twitter object", djObject);
            this.ctx.startActivity(myIntent);
        } else {
            myIntent.putExtra("userRole", "Artist");
            myIntent.putExtra("Session", role);
            myIntent.putExtra("Twitter object", djObject);
            this.ctx.startActivity(myIntent);
        }
    }
}