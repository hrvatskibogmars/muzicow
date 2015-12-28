package hr.mars.muzicow.Activities.Fragments.User;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import hr.mars.muzicow.R;

/**
 * Created by Emil on 27.12.2015..
 */
public class EventInfo extends AppCompatActivity {
    Context context;

    TextView EventGenre;
    TextView EventName;
    TextView EventLatitude;
    TextView EventLongitude;

    String Id;
    String Genre;
    String Name;
    String Latitude;
    String Longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.event_show_data);
        context = getApplicationContext();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        EventLatitude= (TextView) findViewById(R.id.LatitudeInfo);
        EventLongitude= (TextView)findViewById(R.id.LongitudeInfo);
        EventGenre= (TextView)findViewById(R.id.GenreInfo);
        EventName = (TextView)findViewById(R.id.NameInfo);

        if (bundle != null) {
            Id= bundle.getString("EventId");
            Genre= bundle.getString("EventGenre");
            Name= bundle.getString("EventName");
            Latitude= bundle.getString("EventLatitude");
            Longitude= bundle.getString("EventLongitude");

        }

        showData();

    }

    public void showData(){
        EventName.setText(Name);
        EventGenre.setText(Genre);
        EventLatitude.setText(Latitude);
        EventLongitude.setText(Longitude);
    }
}
