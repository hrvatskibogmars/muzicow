package hr.mars.muzicow.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import hr.mars.muzicow.R;
import hr.mars.muzicow.models.Login;
import hr.mars.muzicow.utils.FacebookAuth;
import hr.mars.muzicow.utils.Registry;
import hr.mars.muzicow.utils.SocialAuth;
import hr.mars.muzicow.utils.TwitterAuth;

/**
 * Created by Emil on 3.2.2016..
 */
public class LoginActivity extends AppCompatActivity {
    SocialAuth authManager;
    CallbackManager callbackManager;
    Login at;
    private LoginButton loginButton1;
    private TwitterLoginButton loginButton;
    String neworkCoice,role;
    Object obj;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:",
                        Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            neworkCoice=bundle.getString("networkChoice");
        }
        /**
         * First we initialize classes based on user input(facebook or twitter)
         * authManager is object of SocialAuth interface and classes FacebookAuth
         * and TwitterAuth implements SocialAuth methods
         * @param neworkCoice   variable to check which social network
         *                      is chosen
         *  @param obj  object of class Object, so it can take value of any object,
         *              in this case value of loginButton object ( twitter)
         */
        switch (neworkCoice){
            case "Facebook":
                authManager = new FacebookAuth();
                obj=null;
                break;
            case "Twitter":
                at = new Login();
                Registry.getInstance().set("login.atr", at);
                authManager = new TwitterAuth();
                obj=loginButton;
                break;
        }

        Registry.getInstance().set("authManager", authManager);
        authManager.setContext(getBaseContext());
        authManager.setSocialObject(obj);
        authManager.setup();
        if(neworkCoice.equals("Twitter")){
            setContentView(R.layout.twitter_login);
            loginButton = (TwitterLoginButton) findViewById(R.id.twitter);
            obj=loginButton;
        }
        else{
            obj=authManager.getsocialObject();
        }

        authManager.signup(obj);

        if(neworkCoice.equals("Facebook")){
            setContentView(R.layout.facebook_login);
            loginButton1 = (LoginButton) findViewById(R.id.login_buttonFB);
        }


        final Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                role = mySpinner.getSelectedItem().toString();
                authManager.setRole(role);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (neworkCoice){
            case "Twitter":
                loginButton.onActivityResult(requestCode, resultCode, data);
                break;
            case "Facebook":
                callbackManager = (CallbackManager)authManager.getsocialObject();
                callbackManager.onActivityResult(requestCode, resultCode, data);
                break;
        }

    }
}
