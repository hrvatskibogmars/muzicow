package hr.mars.muzicow.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.twitter.sdk.android.Twitter;

import hr.mars.muzicow.R;
import hr.mars.muzicow.activities.MainActivity;

/**
 * Created by Emil on 28.1.2016..
 */
public class Logout extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Twitter.logOut();
                logout();
                return true;
            }
        });
    }

    public void logout(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
