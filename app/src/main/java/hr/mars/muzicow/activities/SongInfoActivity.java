package hr.mars.muzicow.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hr.mars.muzicow.requests.SongAPI;
import hr.mars.muzicow.models.Song;
import hr.mars.muzicow.R;
import hr.mars.muzicow.services.ServiceGenerator;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Emil on 21.1.2016..
 */
public class SongInfoActivity extends AppCompatActivity {

    Context context;
    TextView artist,description,upvoited;
    final SongAPI eventRetrofit = ServiceGenerator.createService(SongAPI.class);
    String songID,songUpvoite,songDescription,songArtist,songName,songsUrl;
    int voite=0;
    ImageView upVoiteBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_fragmet_playlist);
        context = getApplicationContext();
        artist=(TextView)findViewById(R.id.artistPlTxt);
        upVoiteBtn=(ImageView)findViewById(R.id.upVoite);
        description=(TextView)findViewById(R.id.descriptionPlTxt);
        upvoited=(TextView)findViewById(R.id.upvoitedNmb);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle!= null) {
            songName = bundle.getString("SongsName");
            songArtist = bundle.getString("SongArtist");
            songDescription = bundle.getString("SongDescription");
            songID = bundle.getString("SongId");
            songUpvoite = bundle.getString("SongUpvoite");

        }


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(songName);
        artist.setText(songArtist);
        description.setText(songDescription);
        Log.d("test", songID);
        songsUrl ="songs?filter=%7B%22where%22%3A%7B%22_ID%22%3A%22"+songID+"%22%7D%7D";
        eventRetrofit.getSongs(songsUrl, new Callback<List<Song>>() {
            @Override
            public void success(List<Song> songs, Response response) {
                if(!songs.isEmpty()) {
                    upvoited.setText(songs.get(0).getUpvoited());

                }
                else {
                    Log.d("test","empty list");
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


    }



    public void buttonClickFunction(View v) {

            voite = Integer.parseInt(songUpvoite) + 1;

            final String updateVoite = "update?where=%7B%22_ID%22%3A%20%22"+songID+"%22%7D";
            eventRetrofit.updateUpvoite(updateVoite, Integer.toString(voite), new Callback<Song>() {
                        @Override
                        public void success(Song songs, Response response) {
                            if (!songs.equals(null)) {
                                Toast toast = Toast.makeText(context, "You have successfully upvoted song", Toast.LENGTH_LONG);
                                toast.show();
                                upVoiteBtn.setVisibility(View.INVISIBLE);
                                eventRetrofit.getSongs(songsUrl, new Callback<List<Song>>() {
                                    @Override
                                    public void success(List<Song> songs, Response response) {
                                        upvoited.setText(songs.get(0).getUpvoited());
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {

                                    }
                                });
                            } else {
                                Log.d("test", "empty list");
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Toast toast = Toast.makeText(context, "You have didnt upvoted song", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }

            );



        }


    }
