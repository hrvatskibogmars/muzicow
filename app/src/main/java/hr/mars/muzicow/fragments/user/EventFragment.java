package hr.mars.muzicow.fragments.user;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import hr.mars.muzicow.R;
import hr.mars.muzicow.activities.EventContainActivity;
import hr.mars.muzicow.adapter.CustomEvenetListAdapter;
import hr.mars.muzicow.models.Event;
import hr.mars.muzicow.requests.UserAPI;
import hr.mars.muzicow.services.ServiceGenerator;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by mars on 27/10/15.
 */
public class EventFragment extends Fragment {
    ListView lv;
    String status = "events?filter=%7B%22where%22%3A%7B%22status%22%3A%22" + "1" + "%22%7D%7D";
    final static UserAPI eventRetrofit = ServiceGenerator.createService(UserAPI.class);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dj, container, false);
        lv = (ListView) view.findViewById(R.id.listView);
        loadData();
        return view;

    }
    /**
     * Method for load data from API based
     * on query
     */

    public void loadData() {
        eventRetrofit.getEvent(status, new Callback<List<Event>>() {
            @Override
            public void success(final List<Event> events, Response response) {
                try {
                    /**
                     * Method for setting list to adapter
                     * @param events list of events getted from API
                     */
                    CustomEvenetListAdapter adapter = new CustomEvenetListAdapter(getContext(), events);
                    lv.setAdapter(adapter);
                    /**
                     * Method listening onClick event in list
                     * @param AdapterView.OnItemClickListener()   list view adapter
                     */
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            int position = lv.getPositionForView(view);
                            Log.d("Position", String.valueOf(position));

                            Intent intent = new Intent(getContext(), EventContainActivity.class);
                            intent.putExtra("EventId", events.get(position).get_ID());
                            intent.putExtra("EventGenre", events.get(position).getGenre());
                            intent.putExtra("EventName", events.get(position).getName());
                            intent.putExtra("EventLatitude", events.get(position).getLatitude());
                            intent.putExtra("EventLongitude", events.get(position).getLongitude());
                            intent.putExtra("EventDjId", events.get(position).getDj_ID());
                            startActivity(intent);
                        }
                    });

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "No available events", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Event", error.getMessage());
            }
        });
    }

}

