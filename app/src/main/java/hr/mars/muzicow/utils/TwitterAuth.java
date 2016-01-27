package hr.mars.muzicow.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.facebook.login.widget.LoginButton;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import hr.mars.muzicow.R;
import hr.mars.muzicow.activities.MainActivity;
import hr.mars.muzicow.adapter.FragmentAdapterChoser;
import hr.mars.muzicow.models.DJ;
import hr.mars.muzicow.models.Login;
import hr.mars.muzicow.services.SocialAuth;
import hr.mars.muzicow.services.SocialnetworkManager;
import hr.mars.muzicow.services.TwitterLoginListener;
import io.fabric.sdk.android.Fabric;

/**
 * Created by mars on 27/01/16.
 */
public class TwitterAuth implements SocialAuth {
    String Provider;
    String TWITTER_KEY;
    String TWITTER_SECRET;
    String role;
    TwitterSession session;
    DJ djObject;
    private TwitterLoginButton loginButton;

    TwitterRetData tw = new TwitterRetData();
    SNetworkChooser ch = new SNetworkChooser();

    @Override
    public void setProvider(String provider) { this.Provider = provider; }

    @Override
    public String getProvider() {return Provider; }

    @Override
    public void setKey(String key) { this.TWITTER_KEY = key; }

    @Override
    public String getKey() {return TWITTER_KEY; }

    @Override
    public void setSecret(String key) { this.TWITTER_SECRET = key; }

    @Override
    public String getSecret() { return TWITTER_SECRET; }

    @Override
    public boolean save(Object object_login) {
        return false;
    }

    @Override
    public Object load() { return null; }

    @Override
    public void setRole(String role) { this.role = role; }

    @Override
    public String getRole() { return role; }

    @Override
    public boolean login() { return false; }

    @Override
    public boolean logout() { return false; }

    @Override
    public void signup(TwitterLoginButton loginButton) {
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                ((Login) Registry.getInstance().get("login.atr")).setSession(Twitter.getInstance().core.getSessionManager().getActiveSession());
                ch.setSNetwork(tw);
                ch.loginChoice();

                tw.setListener(new TwitterLoginListener() {
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
                        //showUserData(djObject);
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

}
