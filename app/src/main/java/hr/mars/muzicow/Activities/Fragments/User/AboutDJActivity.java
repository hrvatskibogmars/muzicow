package hr.mars.muzicow.Activities.Fragments.User;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import hr.mars.muzicow.APIs.UserAPI;
import hr.mars.muzicow.Models.DJ;
import hr.mars.muzicow.R;
import hr.mars.muzicow.Services.ImageListener;
import hr.mars.muzicow.Services.ServiceGenerator;
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
    private ImageListener listener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_artist);
        context = getApplicationContext();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {

            djId=bundle.getString("EventDjId");

        }

        showData();



        name=(TextView)findViewById(R.id.nameTxt);
        nick=(TextView)findViewById(R.id.nickTxt);
        web=(TextView)findViewById(R.id.websiteTxt);
        location=(TextView)findViewById(R.id.locationTxt);
        twUrl=(TextView)findViewById(R.id.twUrlTxt);
        description=(TextView)findViewById(R.id.descriptionView);
        imageView = (ImageView)findViewById(R.id.imageView);



    }


    public void setListener( ImageListener listener){

        this.listener = listener;
    }


    public void showData(){
        String query = "djs?filter=%7B%22where%22%3A%7B%22_ID%22%3A%22" + djId + "%22%7D%7D";

        UserAPI djInfoRetrofit = ServiceGenerator.createService(UserAPI.class);
        djInfoRetrofit.getDJ(query, new Callback<List<DJ>>() {
            @Override
            public void success(List<DJ> djs, Response response) {

                name.setText(djs.get(0).getName());
                nick.setText(djs.get(0).getNickname());
                web.setText(djs.get(0).getWebsite());
                location.setText(djs.get(0).getLocation());
                twUrl.setText(djs.get(0).getTwitter_url());
                description.setText((djs.get(0)).getDescription());
                path = djs.get(0).getProfile_url();
                Log.d("DJID", path);

                Glide.with(AboutDJActivity.this).load(path).into(imageView);






            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("DJID", error.getMessage());
            }
        });
    }


}
