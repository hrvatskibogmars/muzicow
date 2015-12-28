package hr.mars.muzicow.Activities.Fragments.User;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    String id;
    String genre;
    String name;
    String latitude;
    String longitude;
    String djId;
    Button djButton;
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
            id= bundle.getString("EventId");
            genre= bundle.getString("EventGenre");
            name= bundle.getString("EventName");
            latitude= bundle.getString("EventLatitude");
            longitude= bundle.getString("EventLongitude");
            djId=bundle.getString("EventDjId");

        }

        showData();

        djButton= (Button)findViewById(R.id.djShowbtn);
        djButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EventInfo.this, AboutDJActivity.class);
                intent.putExtra("EventDjId", djId);
                Log.d("DJID", djId);
                startActivity(intent);

            }
        });

    }

    public void showData(){
        EventName.setText(name);
        EventGenre.setText(genre);
        EventLatitude.setText(latitude);
        EventLongitude.setText(longitude);
    }
}
