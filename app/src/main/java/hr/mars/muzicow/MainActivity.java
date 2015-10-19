package hr.mars.muzicow;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<DummyData> dummyDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"I am not working yet",Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        });
        //Generate Dummy data
        initializeData();

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);


        // specify an adapter (see also next example)
        mAdapter = new RVAdapter(dummyDataList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void initializeData() {
        dummyDataList = new ArrayList<>();
        //String songName, String authorName, String youtubeUrl, String genre, String upVote, String downVote
        dummyDataList.add(new DummyData("Right Here Right Now", "Fatboy Slim", "https://www.youtube.com/watch?v=F7jSp2xmmEE", "Techno", "5", "6"));
        dummyDataList.add(new DummyData("LEK ZA SPAVANJE", "SEKA ALEKSIC", "https://www.youtube.com/watch?v=UOjuwdonmn4", "The Caja", "1", "12"));
        dummyDataList.add(new DummyData("Gogol Bordello", "Start Wearing Purple", "https://www.youtube.com/watch?v=SkkIwO_X4i4", "Gypsy Punk", "10", "6"));
        dummyDataList.add(new DummyData("Gogol Bordello", "My Companjera", "https://www.youtube.com/watch?v=yXU5zUHA1Ak", "Gypsy Punk", "11", "3"));
        dummyDataList.add(new DummyData("Pink Floyd", "The Gunner's Dream", "https://www.youtube.com/watch?v=g9mhU1mm5P0", "English Rock", "3", "2"));
        dummyDataList.add(new DummyData("Scorpions", "Wind of change", "https://www.youtube.com/watch?v=n4RjJKxsamQ", "Classic Rock", "10", "1"));
        dummyDataList.add(new DummyData("Dara Bubamara", "�ena zma", "https://www.youtube.com/watch?v=rrX4UHMpjQk", "The Caja", "1", "5"));
        dummyDataList.add(new DummyData("Bob Marley", "Buffalo soldier", "https://www.youtube.com/watch?v=S5FCdx7Dn0o", "Regge", "420", "0"));
        dummyDataList.add(new DummyData("Pink Floyd", "Dark Side Of The Moon", "https://www.youtube.com/watch?v=DLOth-BuCNY", "English rock", "7", "3"));
        dummyDataList.add(new DummyData("The Doors", "Riders on the Storm", "https://www.youtube.com/watch?v=BLBV6ZwLKDU", "Classic rock", "10", "6"));
        dummyDataList.add(new DummyData("The Doors", "L.A Woman", "https://www.youtube.com/watch?v=JskztPPSJwY", "Classic rock", "14", "1"));

        dummyDataList.add(new DummyData("Iggy Pop", "The Passenger", "https://www.youtube.com/watch?v=hLhN__oEHaw", "Progressive Rock", "1", "1"));
        dummyDataList.add(new DummyData("Stealers Wheel", "Stuck In The Middle With You", "https://www.youtube.com/watch?v=DohRa9lsx0Q", "Rock", "5", "6"));
        dummyDataList.add(new DummyData("Talking Heads", "Psycho Killer", "https://www.youtube.com/watch?v=O52jAYa4Pm8", "Progressive Rock", "2", "3"));
        dummyDataList.add(new DummyData("ZZ Top", "La Grange", "https://www.youtube.com/watch?v=Vppbdf-qtGU", "Hard Rock", "5", "5"));
        dummyDataList.add(new DummyData("Let 3", "Dijete u vremenu", "https://www.youtube.com/watch?v=XNSKKOaHVFY", "Rock", "8", "6"));
        dummyDataList.add(new DummyData("Psihomodo pop", "Natrag u garazu", "", "Rock", "8", "3"));
        dummyDataList.add(new DummyData("Eminem", "Without me", "", "rap", "1", "4"));
        dummyDataList.add(new DummyData("50 Cent ", "In Da Club", "https://www.youtube.com/watch?v=5qm8PH4xAss", "Hip Hop", "1", "0"));
        dummyDataList.add(new DummyData("The Prodigy", "Breathe", "https://www.youtube.com/watch?v=rmHDhAohJlQ", "EDM", "3", "6"));
        dummyDataList.add(new DummyData("Mungo Jerry", "In the summertime", "https://www.youtube.com/watch?v=yG0oBPtyNb0", "Classic rock", "11", "6"));
        dummyDataList.add(new DummyData("Run The Jewels", "Get It", "https://www.youtube.com/watch?v=aES4SbFTH2Y", "hip hop", "5", "6"));
        dummyDataList.add(new DummyData("Dark Oscilators", "Stereophobia", "https://www.youtube.com/watch?v=7V9Ove3vKxQ", "Hardstyle", "1", "6"));
        dummyDataList.add(new DummyData("Dark Oscilators", "Superstar DJ", "https://www.youtube.com/watch?v=kh7YpPTwWP0", "Hardstyle", "3", "6"));
        dummyDataList.add(new DummyData("EKV", "Krug", "https://www.youtube.com/watch?v=5bJz2Wsot7w", "Ex-Yu Rock", "7", "2"));
        dummyDataList.add(new DummyData("Leb i Sol ", "Uci Me Majko, Karaj Me", "https://www.youtube.com/watch?v=b9TpnK-WvFE", "Ex-Yu Rock", "5", "6"));
        dummyDataList.add(new DummyData("?avoli", "Pri?aj mi o ljubavi", "https://www.youtube.com/watch?v=OlUMwGGThvs", "Ex-Yu Rock", "5", "8"));
        dummyDataList.add(new DummyData("Film", "Srce na cesti", "", "Ex-Yu Rock", "10", "3"));
        dummyDataList.add(new DummyData("XS Project", "?????,????,????????", "https://www.youtube.com/watch?v=BxOObDYl7Pw", "Hard Bass", "1337", "0"));



        //dummyDataList.add(new Person("Lavery Maiss", "25 years old", R.drawable.lavery));
        //dummyDataList.add(new Person("Lillie Watts", "35 years old", R.drawable.lillie));
    }
}

