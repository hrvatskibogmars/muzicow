package hr.mars.muzicow.fragments.DJ;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import hr.mars.muzicow.R;
import hr.mars.muzicow.RESTful.ServiceGenerator;
import hr.mars.muzicow.RESTful.api.API;
import hr.mars.muzicow.RESTful.model.Event;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by mars on 15/11/15.
 */
public class CreateEventFragment extends Fragment implements View.OnClickListener {
    EditText eventID;
    EditText djID;
    EditText latitude;
    EditText longitude;
    EditText genre;
    EditText status;
    EditText eventName;
    Button createEvent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_event, container, false);
        createEvent = (Button) view.findViewById(R.id.createEvent);
        createEvent.setOnClickListener(this);

        eventID  = (EditText)view.findViewById(R.id.eventID);
        djID = (EditText)view.findViewById(R.id.djID);
        latitude= (EditText)view.findViewById(R.id.latitude);
        longitude= (EditText)view.findViewById(R.id.longitude);
        genre= (EditText)view.findViewById(R.id.genre);
        status = (EditText)view.findViewById(R.id.status);
        eventName = (EditText)view.findViewById(R.id.eventName);
        active_event();

        return view;




    }

    public void active_event(){
        //{"where": {"and" : [ {"dj_ID":"6"},{"status":"status"}]}}
        String eventUrl = "findOne?filter=%7B%22where%22%3A%20%7B%22and%22%20%3A%20%5B%20%7B%22dj_ID%22%3A%226%22%7D%2C%7B%22status%22%3A%22status%22%7D%5D%7D%7D";
        API eventRetrofit = ServiceGenerator.createService(API.class);
        eventRetrofit.getActiveEvent(eventUrl, new Callback<List<Event>>(){
            @Override
            public void success(List<Event> events, Response response) {
                Log.d("Event update ok", "Success Update");
                eventID.setText(events.get(0).getDj_ID());
                djID.setText(events.get(0).getDj_ID());
                latitude.setText(events.get(0).getLatitude());
                longitude.setText(events.get(0).getLongitude());
                genre.setText(events.get(0).getGenre());
                status.setText(events.get(0).getStatus());
                eventName.setText(events.get(0).getName());
                createEvent.setText("Update Event");

            }
            @Override
            public void failure(RetrofitError error) {
                Log.d("Event update error", error.getMessage());
            }

        });
    }

    @Override
    public void onClick(View view) {
        API eventRetrofit = ServiceGenerator.createService(API.class);
        eventRetrofit.createEvent(djID.getText().toString(), latitude.getText().toString(),
                longitude.getText().toString(), genre.getText().toString(),
                status.getText().toString(), eventName.getText().toString(),
                new Callback<List<Event>>() {
                    @Override
                    public void success(List<Event> djs, Response response) {
                        Log.d("Event update ok", "Success Update");

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("Event update error", error.getMessage());
                    }
                });
    }

}
