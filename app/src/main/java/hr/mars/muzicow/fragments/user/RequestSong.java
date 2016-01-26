package hr.mars.muzicow.fragments.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hr.mars.muzicow.requests.SongAPI;
import hr.mars.muzicow.models.Event;
import hr.mars.muzicow.R;
import hr.mars.muzicow.utils.Registry;
import hr.mars.muzicow.services.ServiceGenerator;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Emil on 13.1.2016..
 */
public class RequestSong extends AppCompatActivity implements View.OnClickListener{
    Context context;
    EditText song;
    EditText artist;
    EditText description;
    EditText youtube;
    Button send;
    Event eventObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.song_request);
        context = getApplicationContext();
        eventObj= (Event)Registry.getInstance().get("Event");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        send = (Button)findViewById(R.id.sendBtn);
        send.setOnClickListener(this);
        song=(EditText)findViewById(R.id.songTxt);
        artist=(EditText)findViewById(R.id.artistTxt);
        description=(EditText)findViewById(R.id.descriptionTxt);
        youtube=(EditText)findViewById(R.id.ytbTxt);



    }
    public void onClick(View v) {
        final SongAPI eventRetrofit = ServiceGenerator.createService(SongAPI.class);
        switch (v.getId()){
            case R.id.sendBtn:

                eventRetrofit.createSong(artist.getText().toString(), eventObj.get_ID(),
                        description.getText().toString(), "0","0", youtube.getText().toString(),
                        song.getText().toString(),
                        new Callback<Response>() {
                            @Override
                            public void success(Response response, Response response2) {
                                Toast.makeText(RequestSong.this, "You have successfully requested song", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Toast.makeText(RequestSong.this, "Song wasnt succesfully requested", Toast.LENGTH_LONG).show();
                            }
                        });


                        Log.d("button", song.getText() + ((Event) Registry.getInstance().get("Event")).getName());

        }
    }


}
