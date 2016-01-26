/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hr.mars.muzicow.Fragments.DJ;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import hr.mars.muzicow.APICalls.SongAPI;
import hr.mars.muzicow.Models.Song;
import hr.mars.muzicow.R;
import hr.mars.muzicow.Services.ServiceGenerator;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SongDetailActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "song_name";
    Context context;
    Button accept,decline;
    String status,songID;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_song_detail);
        context = getApplicationContext();
        accept=(Button)findViewById(R.id.acceptB);
        decline=(Button)findViewById(R.id.declineB);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String cheeseName = bundle.getString("SongName");
        status = bundle.getString("SongStatus");
        songID = bundle.getString("SongID");
        if (status.equals("1")||status.equals("2")){
            accept.setVisibility(View.INVISIBLE);
            decline.setVisibility(View.INVISIBLE);
        }


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(cheeseName);


    }

    public void buttonClickFunction(View v) {
        final SongAPI eventRetrofit = ServiceGenerator.createService(SongAPI.class);
        String songsUrl = "update?where=%7B%22_ID%22%3A%20%22"+songID+"%22%7D";
        switch (v.getId()) {
            case R.id.acceptB:
                Toast.makeText(SongDetailActivity.this, "You have successfully accepted song", Toast.LENGTH_LONG).show();
                accept.setVisibility(View.INVISIBLE);
                decline.setVisibility(View.INVISIBLE);
                    eventRetrofit.updateSong(songsUrl,"1", new Callback<List<Song>>() {

                            @Override
                            public void success (List < Song > songs, Response response){
                            Toast.makeText(SongDetailActivity.this, "You have successfully accepted song", Toast.LENGTH_LONG).show();
                        }

                            @Override
                            public void failure (RetrofitError error){
                            Log.d("error", error.getResponse().toString());
                        }

                    });

                break;
            case R.id.declineB:
                Toast.makeText(SongDetailActivity.this, "You have declined song", Toast.LENGTH_LONG).show();
                accept.setVisibility(View.INVISIBLE);
                decline.setVisibility(View.INVISIBLE);
                eventRetrofit.updateSong(songsUrl, "2", new Callback<List<Song>>() {
                    @Override
                    public void success(List<Song> songs, Response response) {
                        Toast.makeText(SongDetailActivity.this, "You have declined song", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
                break;

        }
    }
    /*
    public void buttonClickFunction(View v) {
        Toast toast = Toast.makeText(context, "You have successfully upvoted song", Toast.LENGTH_LONG);
        toast.show();
    }
    */
}
