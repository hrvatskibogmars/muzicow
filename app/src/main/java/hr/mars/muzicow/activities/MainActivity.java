package hr.mars.muzicow.activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.login.widget.LoginButton;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import hr.mars.muzicow.models.DJ;
import hr.mars.muzicow.models.Login;
import hr.mars.muzicow.R;
import hr.mars.muzicow.utils.FacebookAuth;
import hr.mars.muzicow.utils.Registry;
import hr.mars.muzicow.utils.TwitterAuth;
/**
 * Created by mars on 14/11/15.
 */
public class MainActivity extends AppCompatActivity {
    private TwitterLoginButton loginButton;
    private LoginButton loginButton1;
    Login at = new Login();
    TwitterAuth authManager = new TwitterAuth();
    FacebookAuth authManagerFB = new FacebookAuth();
    String role;
    Intent intent;
    Bundle bundle;
    DJ djObject;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Registry.getInstance().set("login.atr", at);
        //authManager.setContext(getBaseContext());
        //authManager.setLoginButton(loginButton);
        //authManager.setProvider("Twitter");
        //authManager.setKey("f6FNdst2ZaoQWZYvYOu2a5QCy");
        //authManager.setSecret("deHaJ2nBf5Lj5luPg2Avu7w0JOxbb61GUNZavlb4SELDyK0WUV");
        //authManager.setup();

        authManagerFB.setContext(getBaseContext());
        authManagerFB.setup();


        setContentView(R.layout.social_network_login);

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

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter);
        authManager.signup(loginButton);


        //info = (TextView)findViewById(R.id.info);
        loginButton1 = (LoginButton)findViewById(R.id.login_button);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
        //callbackManager.onActivityResult(requestCode, resultCode, data);

    }
}
