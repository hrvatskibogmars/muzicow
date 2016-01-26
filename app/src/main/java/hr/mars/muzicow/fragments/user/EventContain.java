package hr.mars.muzicow.fragments.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import hr.mars.muzicow.adapter.FragmentAdapter;
import hr.mars.muzicow.models.Event;
import hr.mars.muzicow.R;
import hr.mars.muzicow.utils.Registry;

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

            if (bundle != null) {
                eventObj.set_ID(bundle.getString("EventId"));
                eventObj.setName(bundle.getString("EventName"));
                eventObj.setGenre(bundle.getString("EventGenre"));
                eventObj.setLatitude(bundle.getString("EventLatitude"));
                eventObj.setLongitude(bundle.getString("EventLongitude"));
                eventObj.setDj_ID(bundle.getString("EventDjId"));
            }


            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            if (viewPager != null) {
                setupViewPager(viewPager);
            }

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new EventInfo(), "Event info");
        adapter.addFragment(new PlaylistUserFragment(), "Review Playlist");
        //adapter.addFragment(new EditProfileFragment(), "Profile");
        viewPager.setAdapter(adapter);

    }
/*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, FragmentAdapterChoser.class);
            intent.putExtra("userRole", "Participant");
            intent.putExtra("Session", "Session");
            startActivity(intent);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
*/
}
