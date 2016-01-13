package hr.mars.muzicow.Activities.Fragments.User;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hr.mars.muzicow.Models.Event;
import hr.mars.muzicow.R;
import hr.mars.muzicow.Registry.Registry;

/**
 * Created by Emil on 13.1.2016..
 */
public class RequestSong extends AppCompatActivity implements View.OnClickListener{
    Context context;
    EditText song;
    EditText artist;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.song_request);
        context = getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        send = (Button)findViewById(R.id.sendBtn);
        send.setOnClickListener(this);
        song=(EditText)findViewById(R.id.songTxt);
        artist=(EditText)findViewById(R.id.artistTxt);



    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sendBtn:

                Log.d("button",song.getText()+((Event) Registry.getInstance().get("Event")).getName());

        }
    }


}
