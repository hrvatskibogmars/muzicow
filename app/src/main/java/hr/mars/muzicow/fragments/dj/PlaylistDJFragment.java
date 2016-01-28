package hr.mars.muzicow.fragments.dj;

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
import hr.mars.muzicow.adapter.SongAdapter;
import hr.mars.muzicow.models.DJ;
import hr.mars.muzicow.models.Event;
import hr.mars.muzicow.models.Song;
import hr.mars.muzicow.R;
import hr.mars.muzicow.utils.Registry;
import hr.mars.muzicow.services.ServiceGenerator;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class PlaylistDJFragment extends Fragment {
    ListView lv;
    Handler mHandler = new Handler();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        lv = (ListView) view.findViewById(R.id.listView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    try {
                        Thread.sleep(2000);
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                loadData();
                            }
                        });
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();


        return view;

    }

    final String event_id = "events?filter=%7B%22where%22%3A%7B%22dj_ID%22%3A%22" + ((DJ) Registry.getInstance().get("djObject")).get_ID() + "%22%7D%7D";

    public void loadData(){
        final SongAPI eventRetrofit = ServiceGenerator.createService(SongAPI.class);
        eventRetrofit.getEvent(event_id, new Callback<List<Event>>() {
            @Override
            public void success(final List<Event> events, Response response) {
                try {
                    String eventID= events.get(0).get_ID();
                    String songs ="songs?filter=%7B%22where%22%3A%7B%22event_id%22%3A%22"+eventID+"%22%7D%7D";
                    //Log.d("songs", "id eventa " + eventID);
                    eventRetrofit.getSongs(songs, new Callback<List<Song>>() {
                        @Override
                        public void success(final List<Song> songs, Response response) {
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

                                    //Log.d("Position", String.valueOf(position));

                                    Intent intent = new Intent(getContext(), SongDetailActivity.class);
                                    intent.putExtra("SongName", songs.get(position).getName());
                                    intent.putExtra("SongStatus", songs.get(position).getStatus());
                                    intent.putExtra("SongID", songs.get(position).get_ID());

                                    startActivity(intent);

                                }
                            });
                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });



                }
                catch (Exception e){
                    //Toast.makeText(getActivity(), "No available events", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void failure(RetrofitError error) {
                Log.d("Event",error.getMessage());
            }
        });
    }
}

