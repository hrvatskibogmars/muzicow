package hr.mars.muzicow.Activities.Fragments.User;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

import hr.mars.muzicow.APIs.GetEventAPI;
import hr.mars.muzicow.Models.Event;
import hr.mars.muzicow.R;
import hr.mars.muzicow.Services.ServiceGenerator;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by mars on 27/10/15.
 */
public class EventFragment extends Fragment  {
    ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dj, container, false);
        lv = (ListView) view.findViewById(R.id.listView);


        loadData();
        return view;

    }

    String status = "events?filter=%7B%22where%22%3A%7B%22status%22%3A%22" + "1" + "%22%7D%7D";

    public void loadData(){
        GetEventAPI eventRetrofit = ServiceGenerator.createService(GetEventAPI.class);
        eventRetrofit.getEvent(status, new Callback<List<Event>>() {
            @Override
            public void success(final List<Event> events, Response response) {
                Log.d("Event",events.get(0).getName());
                CustomEvenetListAdapter adapter = new CustomEvenetListAdapter(getContext(),events);
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        int position = lv.getPositionForView(view);
                        Log.d("Position", String.valueOf(position));

                        Intent intent = new Intent(getContext(), EventInfo.class);
                        intent.putExtra("EventId", events.get(position).getEvent_ID());
                        intent.putExtra("EventGenre", events.get(position).getGenre());
                        intent.putExtra("EventName", events.get(position).getName());
                        intent.putExtra("EventLatitude", events.get(position).getLatitude());
                        intent.putExtra("EventLongitude", events.get(position).getLongitude());
                        startActivity(intent);
                    }
                });



            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Event",error.getMessage());
            }
        });
    }



}
