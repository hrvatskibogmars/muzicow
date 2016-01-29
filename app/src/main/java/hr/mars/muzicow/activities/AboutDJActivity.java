package hr.mars.muzicow.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterCore;

import java.util.List;

import hr.mars.muzicow.R;
import hr.mars.muzicow.models.DJ;
import hr.mars.muzicow.models.Event;
import hr.mars.muzicow.requests.UserAPI;
import hr.mars.muzicow.services.ServiceGenerator;
import hr.mars.muzicow.utils.Registry;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by mars on 27/10/15.
 */
public class AboutDJActivity extends AppCompatActivity {
    Context context;
    String djId;
    TextView name;
    TextView nick;
    TextView web;
    TextView location;
    TextView twUrl;
    TextView description;
    String path;
    ImageView imageView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_artist);
        context = getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /**
         * Listener for click in menu items
         * @param item clicked item
         */
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                TwitterCore.getInstance().logOut();
                LoginManager.getInstance().logOut();
                logout();
                return true;
            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        showData();
        name = (TextView) findViewById(R.id.nameTxt);
        nick = (TextView) findViewById(R.id.nickTxt);
        web = (TextView) findViewById(R.id.websiteTxt);
        location = (TextView) findViewById(R.id.locationTxt);
        twUrl = (TextView) findViewById(R.id.twUrlTxt);
        description = (TextView) findViewById(R.id.descriptionView);
        imageView = (ImageView) findViewById(R.id.imageView);
    }
    /**
     * Method for logout,
     * it calls MainActivity
     */
    public void logout() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    /**
     * Method for show participant data for chosed event
     */
    public void showData() {
        String query = "djs?filter=%7B%22where%22%3A%7B%22_ID%22%3A%22" + ((Event) Registry.getInstance().get("Event")).getDj_ID() + "%22%7D%7D";
        try {
            UserAPI djInfoRetrofit = ServiceGenerator.createService(UserAPI.class);
            djInfoRetrofit.getDJ(query, new Callback<List<DJ>>() {
                /**
                 * Method for retriving data from API
                 * based on query
                 * @param djs DJ object data returned from API
                 * @param response Response object which has head, body messages
                 */
                @Override
                public void success(List<DJ> djs, Response response) {

                    name.setText(djs.get(0).getName());
                    nick.setText(djs.get(0).getNickname());
                    web.setText(djs.get(0).getWebsite());
                    location.setText(djs.get(0).getLocation());
                    twUrl.setText(djs.get(0).getTwitter_url());
                    description.setText((djs.get(0)).getDescription());
                    path = djs.get(0).getProfile_url();

                    Glide.with(AboutDJActivity.this).load(path).into(imageView);
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.d("Get DJ ID error", error.getMessage());
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(),
                    Toast.LENGTH_LONG).show();

        }
    }
}
