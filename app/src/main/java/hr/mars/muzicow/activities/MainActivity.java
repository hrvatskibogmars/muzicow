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

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import hr.mars.muzicow.models.Login;
import hr.mars.muzicow.R;
import hr.mars.muzicow.utils.FacebookAuth;
import hr.mars.muzicow.utils.Registry;
import hr.mars.muzicow.utils.TwitterAuth;
/**
 * Created by mars on 14/11/15.
 */
public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    private TwitterLoginButton loginButton;
    private LoginButton loginButton1;

    Login at = new Login();

    TwitterAuth authManager = new TwitterAuth();
    FacebookAuth authManagerFB = new FacebookAuth();

    String role;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /**
         *
         * Use this to generate Keyhash for facebook
         */
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:",
                        Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch (PackageManager.NameNotFoundException e) {
        }
        catch (NoSuchAlgorithmException e) {
        }


        Registry.getInstance().set("login.atr", at);

        authManagerFB.setContext(getBaseContext());
        authManagerFB.setProvider("Facebook");
        authManagerFB.setup();
        authManagerFB.signup(authManagerFB.getsocialObject());

        authManager.setContext(getBaseContext());
        authManager.setSocialObject(loginButton);
        authManager.setProvider("Twitter");
        authManager.setKey("f6FNdst2ZaoQWZYvYOu2a5QCy");
        authManager.setSecret("deHaJ2nBf5Lj5luPg2Avu7w0JOxbb61GUNZavlb4SELDyK0WUV");
        authManager.setup();

        setContentView(R.layout.social_network_login);

        final Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                role = mySpinner.getSelectedItem().toString();
                authManager.setRole(role);
                authManagerFB.setRole(role);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter);
        authManager.signup(loginButton);

        loginButton1 = (LoginButton)findViewById(R.id.login_buttonFB);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
        callbackManager = authManagerFB.getsocialObject();
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
