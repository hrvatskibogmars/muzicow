package hr.mars.muzicow.Activities.Fragments.User;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import hr.mars.muzicow.APIs.SongAPI;
import hr.mars.muzicow.Activities.Fragments.DJ.SongDetailActivity;
import hr.mars.muzicow.Activities.adapters.SongAdapter;
import hr.mars.muzicow.Models.DJ;
import hr.mars.muzicow.Models.Event;
import hr.mars.muzicow.Models.Song;
import hr.mars.muzicow.R;
import hr.mars.muzicow.Registry.Registry;
import hr.mars.muzicow.Services.ServiceGenerator;
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


    public void loadData(){
        final SongAPI eventRetrofit = ServiceGenerator.createService(SongAPI.class);

                try {

                    String songs ="songs?filter=%7B%22where%22%3A%7B%22event_id%22%3A%22"+eventObj.get_ID()+"%22%7D%7D";
                    Log.d("songs", "id eventa " + eventObj.get_ID());
                    eventRetrofit.getSongs(songs, new Callback<List<Song>>() {
                        @Override
                        public void success(final List<Song> songs, Response response) {
                            //isAdded because of async call
                            if(isAdded()){
                                SongAdapter adapter = new SongAdapter(getContext(), songs);
                                lv.setAdapter(adapter);

                                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                        int position = lv.getPositionForView(view);

                                        Log.d("Position", String.valueOf(position));

                                        Intent intent = new Intent(getContext(), SongDetailActivity.class);
                                        intent.putExtra("SongName", songs.get(position).getName());
                                        intent.putExtra("SongStatus", songs.get(position).getStatus());
                                        intent.putExtra("SongID", songs.get(position).get_ID());

                                        startActivity(intent);

                                    }
                                });
                            }

                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });



                }
                catch (Exception e){
                    Toast.makeText(getActivity(), "No available events", Toast.LENGTH_LONG).show();
                }
            }

    }

