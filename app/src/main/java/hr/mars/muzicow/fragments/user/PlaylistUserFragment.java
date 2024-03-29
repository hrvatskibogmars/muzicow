package hr.mars.muzicow.fragments.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import hr.mars.muzicow.requests.SongAPI;
import hr.mars.muzicow.activities.SongInfoActivity;
import hr.mars.muzicow.adapter.SongAdapter;
import hr.mars.muzicow.models.Event;
import hr.mars.muzicow.models.Song;
import hr.mars.muzicow.R;
import hr.mars.muzicow.utils.Registry;
import hr.mars.muzicow.services.ServiceGenerator;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Emil on 21.1.2016..
 */
public class PlaylistUserFragment extends Fragment {
    ListView lv;
    Handler mHandler = new Handler();
    Event eventObj;
    final static SongAPI eventRetrofit = ServiceGenerator.createService(SongAPI.class);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        lv = (ListView) view.findViewById(R.id.listView);
        eventObj = (Event) Registry.getInstance().get("Event");

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                loadData();
                            }
                        });
                    } catch (Exception e) {
                    }
                }
            }
        }).start();
        return view;
    }


    public void loadData() {
        try {
            String songs = "songs?filter=%7B%22where%22%3A%7B%22event_id%22%3A%22" + eventObj.get_ID() + "%22%7D%7D";
            //Log.d("songs", "id eventa " + eventObj.get_ID());
            eventRetrofit.getSongs(songs, new Callback<List<Song>>() {
                @Override
                public void success(final List<Song> songs, Response response) {
                    //isAdded because of async call
                    if (isAdded()) {
                        // for comparing list
                        Collections.sort(songs, new Comparator<Song>() {

                            public int compare(Song o1, Song o2) {
                                return o2.getUpvoted().compareTo(o1.getUpvoted());
                            }
                        });
                        SongAdapter adapter = new SongAdapter(getContext(), songs);
                        lv.setAdapter(adapter);

                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                int position = lv.getPositionForView(view);

                                Log.d("Position", String.valueOf(position));

                                Intent intent = new Intent(getContext(), SongInfoActivity.class);
                                intent.putExtra("SongsName", songs.get(position).getName());
                                intent.putExtra("SongArtist", songs.get(position).getArtist());
                                intent.putExtra("SongDescription", songs.get(position).getDescription());
                                intent.putExtra("SongId", songs.get(position).get_ID());
                                intent.putExtra("SongUpvoite", songs.get(position).getUpvoted());
                                startActivity(intent);
                            }
                        });
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                }
            });
        } catch (Exception e) {
            Toast.makeText(getActivity(), "No available events", Toast.LENGTH_LONG).show();
        }
    }
}

