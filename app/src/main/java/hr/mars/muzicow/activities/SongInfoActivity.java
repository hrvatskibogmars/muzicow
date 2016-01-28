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

import java.util.ArrayList;
import java.util.List;

import hr.mars.muzicow.models.DJ;
import hr.mars.muzicow.requests.SongAPI;
import hr.mars.muzicow.models.Song;
import hr.mars.muzicow.R;
import hr.mars.muzicow.services.ServiceGenerator;
import hr.mars.muzicow.utils.Registry;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Emil on 21.1.2016..
 */
public class SongInfoActivity extends AppCompatActivity {

    Context context;
    TextView artist,description,upvoted;
    final SongAPI eventRetrofit = ServiceGenerator.createService(SongAPI.class);
    String songID,songUpvote,songDescription,songArtist,songName,songsUrl;
    int vote=0;
    ImageView upVoteBtn;
    DJ djObject;
    ArrayList<String>voitedIDs;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_fragmet_playlist);
        context = getApplicationContext();
        artist = (TextView) findViewById(R.id.artistPlTxt);
        upVoteBtn = (ImageView) findViewById(R.id.upVoite);
        description = (TextView) findViewById(R.id.descriptionPlTxt);
        upvoted = (TextView) findViewById(R.id.upvoitedNmb);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            songName = bundle.getString("SongsName");
            songArtist = bundle.getString("SongArtist");
            songDescription = bundle.getString("SongDescription");
            songID = bundle.getString("SongId");
            songUpvote = bundle.getString("SongUpvoite");

        }
        djObject = (DJ) Registry.getInstance().get("djObject");


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(songName);
        artist.setText(songArtist);
        description.setText(songDescription);
        Log.d("test", songID);
        songsUrl = "songs?filter=%7B%22where%22%3A%7B%22_ID%22%3A%22" + songID + "%22%7D%7D";
        eventRetrofit.getSongs(songsUrl, new Callback<List<Song>>() {
            @Override
            public void success(List<Song> songs, Response response) {
                if (!songs.isEmpty()) {
                    upvoted.setText(songs.get(0).getUpvoted());
                    voitedIDs=songs.get(0).getVoted();
                    for (int i = 0; i < songs.get(0).getVoted().size(); i++) {
                        if (songs.get(0).getVoted().get(i).equals(djObject.get_ID())) {
                            upVoteBtn.setVisibility(View.INVISIBLE);

                        } else {
                            Log.d("test", "empty list");
                        }
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }



    public void buttonClickFunction(View v) {

        vote = Integer.parseInt(songUpvote) + 1;
        voitedIDs.add(djObject.get_ID());
        final String updateVoite = "update?where=%7B%22_ID%22%3A%20%22"+songID+"%22%7D";
        eventRetrofit.updateUpvoite(updateVoite, Integer.toString(vote),voitedIDs, new Callback<Song>() {
                    @Override
                    public void success(Song songs, Response response) {
                        if (!songs.equals(null)) {
                            Toast toast = Toast.makeText(context, "You have successfully upvoted song", Toast.LENGTH_LONG);
                            toast.show();
                            upVoteBtn.setVisibility(View.INVISIBLE);
                            eventRetrofit.getSongs(songsUrl, new Callback<List<Song>>() {
                                @Override
                                public void success(List<Song> songs, Response response) {
                                    upvoted.setText(songs.get(0).getUpvoted());
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