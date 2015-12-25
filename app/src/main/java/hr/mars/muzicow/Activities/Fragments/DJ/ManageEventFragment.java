package hr.mars.muzicow.Activities.Fragments.DJ;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;


import java.util.List;

import hr.mars.muzicow.R;
import hr.mars.muzicow.Services.ServiceGenerator;
import hr.mars.muzicow.APIs.EventAPI;
import hr.mars.muzicow.Models.Event;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by mars on 15/11/15.
 */
public class ManageEventFragment extends Fragment implements View.OnClickListener,GoogleApiClient.ConnectionCallbacks{
    EditText eventID;
    EditText djID;
    EditText latitude;
    EditText longitude;
    EditText genre;
    EditText status;
    EditText eventName;
    Button createEvent;

    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    protected String mLatitudeLabel;
    protected String mLongitudeLabel;
    protected static final String TAG = "MainActivity";

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

        buildGoogleApiClient();

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        return view;
    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
    }


    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
    @Override
    public void onConnected(Bundle connectionHint) {
        // Provides a simple way of getting a device's location and is well suited for
        // applications that do not require a fine-grained location and that do not need location
        // updates. Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);


        if (mLastLocation != null) {
            latitude.setText(String.format("%s: %f", mLatitudeLabel,
                    mLastLocation.getLatitude()));
            longitude.setText(String.format("%s: %f", mLongitudeLabel,
                    mLastLocation.getLongitude()));
        } else {
            Toast.makeText(getActivity(), R.string.no_location_detected, Toast.LENGTH_LONG).show();
        }
    }


    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }


    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    public void active_event(){
        //{"where": {"and" : [ {"dj_ID":"6"},{"status":"status"}]}}
        String eventUrl = "events?filter=%7B%22where%22%3A%20%7B%22and%22%20%3A%20%5B%20%7B%22dj_ID%22%3A%226%22%7D%2C%7B%22status%22%3A%22status%22%7D%5D%7D%7D";
        EventAPI eventRetrofit = ServiceGenerator.createService(EventAPI.class);
        eventRetrofit.getActiveEvent(eventUrl, new Callback<List<Event>>(){
            @Override
            public void success(List<Event> events, Response response) {
                Log.d("Event update ok", "Success Update");
                eventID.setText(events.get(0).getDj_ID());
                djID.setText(events.get(0).getDj_ID());
                //latitude.setText(events.get(0).getLatitude());
                //longitude.setText(events.get(0).getLongitude());
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
        EventAPI eventRetrofit = ServiceGenerator.createService(EventAPI.class);
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
