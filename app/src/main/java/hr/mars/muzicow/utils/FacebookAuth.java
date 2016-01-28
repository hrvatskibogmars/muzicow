package hr.mars.muzicow.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
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
import hr.mars.muzicow.adapter.FragmentAdapterChooser;
import hr.mars.muzicow.models.DJ;
import hr.mars.muzicow.models.Login;
import hr.mars.muzicow.services.SocialAuth;
import hr.mars.muzicow.services.TwitterLoginListener;
import io.fabric.sdk.android.Fabric;

/**
 * Created by mars on 27/01/16.
 */
public class FacebookAuth implements SocialAuth<Login, Context, TwitterLoginButton> {
    Context ctx;
    TwitterLoginButton loginButton;
    TextView info;
    String Provider;
    String TWITTER_KEY;
    String TWITTER_SECRET;
    String role;
    CallbackManager callbackManager;

    TwitterSession session;
    DJ djObject;

    TwitterRetData tw = new TwitterRetData();
    SNetworkChooser ch = new SNetworkChooser();

    @Override
    public void setContext(Context ctx) { this.ctx = ctx; }

    @Override
    public Context getContext() { return this.ctx; }

    @Override
    public void setLoginButton(TwitterLoginButton loginButton) { this.loginButton = loginButton; }

    @Override
    public TwitterLoginButton getLoginButton() { return this.loginButton; }

    public void setup() {
        FacebookSdk.sdkInitialize(ctx);
        callbackManager = CallbackManager.Factory.create();
    }

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
    public boolean save(Login object_login) {
        return false;
    }

    @Override
    public Login load() { return null; }

    @Override
    public void setRole(String role) { this.role = role; }

    @Override
    public String getRole() { return role; }

    @Override
    public boolean login() { return false; }

    @Override
    public boolean logout() { return false; }

    @Override
    public void signup(TwitterLoginButton loginButton){
        //info = (TextView)findViewById(R.id.info);

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        /*
                        info.setText(
                                "User ID: "
                                        + loginResult.getAccessToken().getUserId()
                                        + "\n" +
                                        "Auth Token: "
                                        + loginResult.getAccessToken().getToken()
                        );
                        */
                    }

                    @Override
                    public void onCancel() {
                        //info.setText("Login attempt canceled.");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        //info.setText("Login attempt failed.");
                    }
                });
    }
}
