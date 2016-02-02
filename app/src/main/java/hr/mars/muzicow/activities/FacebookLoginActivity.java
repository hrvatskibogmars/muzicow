package hr.mars.muzicow.activities;

import android.app.Fragment;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import hr.mars.muzicow.R;
import hr.mars.muzicow.utils.FacebookAuth;
import hr.mars.muzicow.utils.SocialAuth;

/**
 * Created by Emil on 2.2.2016..
 */
public class FacebookLoginActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    SocialAuth authManager = new FacebookAuth();
    private LoginButton loginButton1;
    String role;
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

        authManager.setContext(getBaseContext());
        authManager.setProvider("Facebook");
        authManager.setup();
        authManager.signup(authManager.getsocialObject());
        setContentView(R.layout.facebook_login);
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
        loginButton1 = (LoginButton) findViewById(R.id.login_buttonFB);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager = (CallbackManager)authManager.getsocialObject();
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
