package hr.mars.muzicow.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import hr.mars.muzicow.adapter.FragmentAdapterChooser;
import hr.mars.muzicow.models.DJ;
import hr.mars.muzicow.models.Login;

/**
 * Created by mars on 27/01/16.
 */
public class FacebookAuth implements SocialAuth<Login, Context, CallbackManager> {
    Context ctx;
    LoginButton loginButton;
    TextView info;
    String Provider;
    String FACEBOOK_KEY;
    String FACEBOOK_SECRET;
    String role;
    CallbackManager callbackManager;

    DJ djObject;

    @Override
    public Context getContext() {
        return this.ctx;
    }

    @Override
    public void setContext(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void setSocialObject(CallbackManager callbackManager) {
        this.callbackManager = callbackManager;
    }

    @Override
    public CallbackManager getsocialObject() {
        return this.callbackManager;
    }

    public void setup() {
        FacebookSdk.sdkInitialize(ctx);
        this.setSocialObject(CallbackManager.Factory.create());
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
        return FACEBOOK_KEY;
    }

    @Override
    public void setKey(String key) {
        this.FACEBOOK_KEY = key;
    }

    @Override
    public String getSecret() {
        return FACEBOOK_SECRET;
    }

    @Override
    public void setSecret(String key) {
        this.FACEBOOK_SECRET = key;
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
    public void logout() {
        LoginManager.getInstance().logOut();
    }

    @Override
    public void signup(CallbackManager callbackManager) {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {

                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        GraphRequest request = GraphRequest.newMeRequest(
                                AccessToken.getCurrentAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(
                                            JSONObject object,
                                            GraphResponse response) {

                                        djObject = new DJ();
                                        try {
                                            djObject.set_ID(object.getString("id"));
                                            djObject.setName(object.getString("name"));
                                            //djObject.setDescription(object.getString("about"));
                                            //djObject.setLocation(object.getString("location"));
                                            djObject.setProfile_url(object.getString("link"));
                                            //djObject.setNickname(object.getString("middle_name"));
                                            //djObject.setWebsite(object.getString("website"));
                                            Log.d("fb object", object.toString());
                                        } catch (Exception e) {
                                            Log.d("error", e.getMessage());
                                        }
                                        showUserData(djObject);
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,link,email,middle_name,website");
                        request.setParameters(parameters);
                        request.executeAsync();
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
