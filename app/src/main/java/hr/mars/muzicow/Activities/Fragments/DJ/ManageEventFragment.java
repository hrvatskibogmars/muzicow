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


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import hr.mars.muzicow.Activities.adapters.FragmentAdapter;
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
    String eventID;
    EditText djID;
    EditText latitude;
    EditText longitude;
    EditText genre;
    EditText status;
    EditText eventName;
    Button createEvent;
    Button updateEvent;
    Button finishEvent;
    FragmentAdapter faObject = new FragmentAdapter();


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
        updateEvent = (Button) view.findViewById(R.id.updateEvent);
        finishEvent = (Button) view.findViewById(R.id.finishEvent);
        updateEvent.setOnClickListener(this);
        createEvent.setOnClickListener(this);
        finishEvent.setOnClickListener(this);


        latitude= (EditText)view.findViewById(R.id.latitude);
        longitude= (EditText)view.findViewById(R.id.longitude);
        genre= (EditText)view.findViewById(R.id.genre);
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
            latitude.setText(String.valueOf(mLastLocation.getLatitude()));
            longitude.setText(String.valueOf(mLastLocation.getLongitude()));
        } else {
            //Toast.makeText(getActivity(), R.string.no_location_detected, Toast.LENGTH_LONG).show();
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
        String eventUrl = "events?filter=%7B%22where%22%3A%20%7B%22and%22%20%3A%20%5B%20%7B%22dj_ID%22%3A%22"+faObject.getDjObject().get_ID()+"%22%7D%2C%7B%22status%22%3A%22"+ "1"+"%22%7D%5D%7D%7D";
        EventAPI eventRetrofit = ServiceGenerator.createService(EventAPI.class);
        eventRetrofit.getActiveEvent(eventUrl, new Callback<List<Event>>() {
            @Override
            public void success(List<Event> events, Response response) {
                if (events.isEmpty()) {
                    //Toast.makeText(getActivity(), "Create new event", Toast.LENGTH_LONG).show();
                    createEvent.setEnabled(true);
                    updateEvent.setEnabled(false);
                    finishEvent.setEnabled(false);
                } else {
                    //Log.d("Event update ok", "Success Update");
                    eventID = events.get(0).get_ID();
                    //djID.setText(events.get(0).getDj_ID());
                    latitude.setText(events.get(0).getLatitude());
                    longitude.setText(events.get(0).getLongitude());
                    genre.setText(events.get(0).getGenre());
                    //status.setText(events.get(0).getStatus());
                    eventName.setText(events.get(0).getName());
                    createEvent.setEnabled(false);
                    Toast.makeText(getActivity(), eventID, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Active event error", error.getMessage());
            }

        });
    }


    @Override
    public void onClick(View view) {
        final EventAPI eventRetrofit = ServiceGenerator.createService(EventAPI.class);
        String eventUrl = "update?where=%7B%22_ID%22%3A%20%22"+eventID+"%22%7D";
        switch (view.getId()) {

            case R.id.createEvent:
                eventRetrofit.createEvent(faObject.getDjObject().get_ID(), latitude.getText().toString(),
                        longitude.getText().toString(), genre.getText().toString(), "1", eventName.getText().toString(),
                        new Callback<Response>() {
                            @Override
                            public void success(Response events, Response req) {
                                Log.d("Event Create ok", "Success Update");
                                String json = events.getBody().toString();
                                //Toast.makeText(getActivity(), json, Toast.LENGTH_LONG).show();
                                Log.d("Event Create ok", json);
                                updateEvent.setEnabled(true);
                                createEvent.setEnabled(false);
                                finishEvent.setEnabled(true);
                                active_event();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.d("Event Create error", error.getMessage());


                            }
                        });

                break;

            case R.id.updateEvent:
                eventRetrofit.updateEvent(eventUrl, faObject.getDjObject().get_ID(), latitude.getText().toString(),
                        longitude.getText().toString(), genre.getText().toString(), "1", eventName.getText().toString(),
                        new Callback<Response>() {
                            @Override
                            public void success(Response resp, Response req) {
                                String json = resp.getBody().toString();
                                //Log.d("Event update ok", json);

                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.d("Event update error", error.getMessage());
                            }
                        });
                break;
            case R.id.finishEvent:
                eventRetrofit.updateEvent(eventUrl, faObject.getDjObject().get_ID(), latitude.getText().toString(),
                        longitude.getText().toString(), genre.getText().toString(), "0", eventName.getText().toString(),
                        new Callback<Response>() {
                            @Override
                            public void success(Response resp, Response req) {
                                Log.d("Event finished ok", "Success Update");
                                createEvent.setEnabled(true);
                                updateEvent.setEnabled(false);
                                finishEvent.setEnabled(false);
                                latitude.setText("");
                                longitude.setText("");
                                eventName.setText("");
                                genre.setText("");
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.d("Event finished error", error.getMessage());
                            }
                        });

        }

    }

}
