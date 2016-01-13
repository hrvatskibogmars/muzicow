package hr.mars.muzicow.Activities.Fragments.User;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hr.mars.muzicow.Models.Event;
import hr.mars.muzicow.R;
import hr.mars.muzicow.Registry.Registry;

/**
 * Created by Emil on 27.12.2015..
 */
public class EventInfo extends AppCompatActivity implements View.OnClickListener{
    Context context;

    TextView EventGenre;
    TextView EventName;
    TextView EventLatitude;
    TextView EventLongitude;

    String id;
    String genre;
    String name;
    String latitude;
    String longitude;
    String djId;
    Button djButton;
    Button reqSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.event_show_data);
        context = getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        final ActionBar ab = getSupportActionBar();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        EventLatitude= (TextView) findViewById(R.id.LatitudeInfo);
        EventLongitude= (TextView)findViewById(R.id.LongitudeInfo);
        EventGenre= (TextView)findViewById(R.id.GenreInfo);
        EventName = (TextView)findViewById(R.id.NameInfo);

        djButton= (Button)findViewById(R.id.djShowbtn);
        djButton.setOnClickListener(this);

        reqSong=(Button)findViewById(R.id.requestBtn);
        reqSong.setOnClickListener(this);

        if (bundle != null) {
            Event eve = new Event();
            Registry.getInstance().set("Event", eve);
            ((Event)Registry.getInstance().get("Event")).set_ID(bundle.getString("EventId"));
            ((Event)Registry.getInstance().get("Event")).setName(bundle.getString("EventName"));
            ((Event)Registry.getInstance().get("Event")).setGenre(bundle.getString("EventGenre"));
            ((Event)Registry.getInstance().get("Event")).setLatitude(bundle.getString("EventLatitude"));
            ((Event)Registry.getInstance().get("Event")).setLongitude(bundle.getString("EventLongitude"));
            ((Event)Registry.getInstance().get("Event")).setDj_ID(bundle.getString("EventDjId"));

        }

        showData();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.djShowbtn:
                Intent intent = new Intent(EventInfo.this, AboutDJActivity.class);
                intent.putExtra("EventDjId", djId);
                startActivity(intent);
                break;
            case R.id.requestBtn:
                intent = new Intent(EventInfo.this, RequestSong.class);
                //intent.putExtra("EventDjId", djId);
                startActivity(intent);
                break;

        }
    }

    public void showData(){
        EventName.setText(((Event)Registry.getInstance().get("Event")).getName());
        EventGenre.setText(((Event)Registry.getInstance().get("Event")).getGenre());
        EventLatitude.setText(((Event)Registry.getInstance().get("Event")).getLatitude());
        EventLongitude.setText(((Event)Registry.getInstance().get("Event")).getLongitude());
        Log.d("event",((Event)Registry.getInstance().get("Event")).getName());
    }
}
