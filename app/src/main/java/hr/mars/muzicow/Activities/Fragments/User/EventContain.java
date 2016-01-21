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
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hr.mars.muzicow.Activities.Fragments.DJ.EditProfileFragment;
import hr.mars.muzicow.Activities.Fragments.DJ.ManageEventFragment;
import hr.mars.muzicow.Activities.Fragments.FragmentAdapter;
import hr.mars.muzicow.Models.Event;
import hr.mars.muzicow.R;
import hr.mars.muzicow.Registry.Registry;

/**
 * Created by Emil on 27.12.2015..
 */
public class EventContain extends AppCompatActivity {
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

    Event eventObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);
            context = getApplicationContext();
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);


            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            Event eve = new Event();
            Registry.getInstance().set("Event", eve);

            eventObj = (Event) Registry.getInstance().get("Event");
        /*
        EventLatitude= (TextView) findViewById(R.id.LatitudeInfo);
        EventLongitude= (TextView)findViewById(R.id.LongitudeInfo);
        EventGenre= (TextView)findViewById(R.id.GenreInfo);
        EventName = (TextView)findViewById(R.id.NameInfo);

        djButton= (Button)findViewById(R.id.djShowbtn);
        djButton.setOnClickListener(this);

        reqSong=(Button)findViewById(R.id.requestBtn);
        reqSong.setOnClickListener(this);
        */
            if (bundle != null) {


                eventObj.set_ID(bundle.getString("EventId"));
                eventObj.setName(bundle.getString("EventName"));
                eventObj.setGenre(bundle.getString("EventGenre"));
                eventObj.setLatitude(bundle.getString("EventLatitude"));
                eventObj.setLongitude(bundle.getString("EventLongitude"));
                eventObj.setDj_ID(bundle.getString("EventDjId"));

            }

            //showData();

            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            if (viewPager != null) {
                setupViewPager(viewPager);
            }

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
        }
        catch (Exception e){

        }
    }
/*
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.djShowbtn:
                Intent intent = new Intent(EventContain.this, AboutDJActivity.class);
                //intent.putExtra("EventDjId", ((Event)Registry.getInstance().get("Event")).getDj_ID());
                startActivity(intent);
                break;
            case R.id.requestBtn:
                intent = new Intent(EventContain.this, RequestSong.class);
                //intent.putExtra("EventDjId", djId);
                startActivity(intent);
                break;

        }
    }
*/
    /*
    public void showData(){
        EventName.setText(eventObj.getName());
        EventGenre.setText(eventObj.getGenre());
        EventLatitude.setText(eventObj.getLatitude());
        EventLongitude.setText(eventObj.getLongitude());
        Log.d("event",eventObj.getName());
    }
*/
    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new EventInfo(), "Event info");
        //adapter.addFragment(new PlaylistFragment(), "Review Playlist");
        adapter.addFragment(new EditProfileFragment(), "Profile");
        viewPager.setAdapter(adapter);

    }

}
