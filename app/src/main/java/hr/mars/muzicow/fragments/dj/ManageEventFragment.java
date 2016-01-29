package hr.mars.muzicow.fragments.dj;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import hr.mars.muzicow.models.DJ;
import hr.mars.muzicow.models.Event;
import hr.mars.muzicow.requests.EventAPI;
import hr.mars.muzicow.services.ServiceGenerator;
import hr.mars.muzicow.utils.Registry;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by mars on 15/11/15.
 */
public class ManageEventFragment extends Fragment implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks {
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;
    protected static final String TAG = "MainActivity";
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    String eventID;
    EditText latitude;
    EditText longitude;
    EditText genre;
    EditText eventName;
    Button createEvent;
    Button updateEvent;
    Button finishEvent;

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


        latitude = (EditText) view.findViewById(R.id.latitude);
        longitude = (EditText) view.findViewById(R.id.longitude);
        genre = (EditText) view.findViewById(R.id.genre);
        eventName = (EditText) view.findViewById(R.id.eventName);
        active_event();

        /**
         * Method for ask user if he will give application acces
         * to  his gps location
         * @param getActivity    current activity
         * @param Manifest.permission.ACCESS_FINE_LOCATION  premission for gps location
         */
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        }

        buildGoogleApiClient();

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mGoogleApiClient.connect();

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
        super.onStop();
        mGoogleApiClient.isConnected();
    }


    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
    /**
     * Method for get user longitude and latitude position
     * @param connectionHint    Bundle object
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            latitude.setText(String.valueOf(mLastLocation.getLatitude()));
            longitude.setText(String.valueOf(mLastLocation.getLongitude()));
        } else {
            Toast.makeText(getActivity(), R.string.no_location_detected, Toast.LENGTH_LONG).show();
        }
    }

    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }
    /**
     * Method for check if event is enabled or not
     * If it is enabled then its sets data to
     * TextViews and create button is set to invisible.
     * If its not enabled it sets create button to enabled,
     * and update and finish button to invisible
     */
    public void active_event() {
        String eventUrl = "events?filter=%7B%22where%22%3A%20%7B%22and%22%20%3A%20%5B%20%7B%22dj_ID%22%3A%22" + ((DJ) Registry.getInstance().get("djObject")).get_ID() + "%22%7D%2C%7B%22status%22%3A%22" + "1" + "%22%7D%5D%7D%7D";
        EventAPI eventRetrofit = ServiceGenerator.createService(EventAPI.class);
        eventRetrofit.getActiveEvent(eventUrl, new Callback<List<Event>>() {
            @Override
            public void success(List<Event> events, Response response) {
                if (events.isEmpty()) {
                    createEvent.setEnabled(true);
                    updateEvent.setEnabled(false);
                    finishEvent.setEnabled(false);
                    updateEvent.setVisibility(View.INVISIBLE);
                    finishEvent.setVisibility(View.INVISIBLE);

                } else {

                    eventID = events.get(0).get_ID();
                    latitude.setText(events.get(0).getLatitude());
                    longitude.setText(events.get(0).getLongitude());
                    genre.setText(events.get(0).getGenre());
                    eventName.setText(events.get(0).getName());
                    createEvent.setEnabled(false);
                    createEvent.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Active event error", error.getMessage());
            }
        });
    }
    /**
     * Method for checking witch button is clicked
     * @param view    View object
     */

    @Override
    public void onClick(View view) {
        final EventAPI eventRetrofit = ServiceGenerator.createService(EventAPI.class);
        String eventUrl = "update?where=%7B%22_ID%22%3A%20%22" + eventID + "%22%7D";
        switch (view.getId()) {
            case R.id.createEvent:
                if (eventName.getText().toString().matches("") || genre.getText().toString().matches("") ||
                        latitude.getText().toString().matches("") || longitude.getText().toString().matches("")) {
                    Toast.makeText(getActivity(), "You must enter all values", Toast.LENGTH_LONG).show();
                } else {
                    /**
                     * Method for create event based.
                     * It insert data to API from this paramaters
                     * @param get_ID()   Artist ID value
                     * @param latitude()   latitude value
                     * @param longitude()   longitude value
                     * @param genre()   genre of songs on event
                     * @param genre()   event name
                     */
                    eventRetrofit.createEvent(((DJ) Registry.getInstance().get("djObject")).get_ID(), latitude.getText().toString(),
                            longitude.getText().toString(), genre.getText().toString(), "1", eventName.getText().toString(),
                            new Callback<Response>() {
                                @Override
                                public void success(Response events, Response req) {
                                    Log.d("Event Create ok", "Success Update");
                                    String json = events.getBody().toString();
                                    Log.d("Event Create ok", json);

                                    updateEvent.setEnabled(true);
                                    createEvent.setEnabled(false);
                                    finishEvent.setEnabled(true);
                                    createEvent.setVisibility(View.INVISIBLE);
                                    updateEvent.setVisibility(View.VISIBLE);
                                    finishEvent.setVisibility(View.VISIBLE);
                                    active_event();
                                    Toast.makeText(getActivity(), "You have successfully created event", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    Log.d("Event Create error", error.getMessage());
                                }
                            });
                }

                break;

            case R.id.updateEvent:
                if (eventName.getText().toString().matches("") || genre.getText().toString().matches("") ||
                        latitude.getText().toString().matches("") || longitude.getText().toString().matches("")) {
                    Toast.makeText(getActivity(), "You must enter all values", Toast.LENGTH_LONG).show();

                } else {
                    /**
                     * Method for update event based on parameters.
                     * It insert data to API from this paramaters
                     * @param get_ID()   Artist ID value
                     * @param latitude()   latitude value
                     * @param longitude()   longitude value
                     * @param genre()   genre of songs on event
                     * @param genre()   event name
                     */
                    eventRetrofit.updateEvent(eventUrl, ((DJ) Registry.getInstance().get("djObject")).get_ID(), latitude.getText().toString(),
                            longitude.getText().toString(), genre.getText().toString(), "1", eventName.getText().toString(),
                            new Callback<Response>() {
                                @Override
                                public void success(Response resp, Response req) {
                                    String json = resp.getBody().toString();
                                    Log.d("Event update ok", json);

                                    Toast.makeText(getActivity(), "You have successfully updated event", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    Log.d("Event update error", error.getMessage());
                                }
                            });
                }
                break;
            case R.id.finishEvent:
                eventRetrofit.updateEvent(eventUrl, ((DJ) Registry.getInstance().get("djObject")).get_ID(), latitude.getText().toString(),
                        longitude.getText().toString(), genre.getText().toString(), "0", eventName.getText().toString(),
                        new Callback<Response>() {
                            /**
                             * Method for finish active event
                             */
                            @Override
                            public void success(Response resp, Response req) {
                                Log.d("Event finished ok", "Success Update");
                                createEvent.setEnabled(true);
                                updateEvent.setEnabled(false);
                                finishEvent.setEnabled(false);
                                latitude.setText(String.valueOf(mLastLocation.getLatitude()));
                                longitude.setText(String.valueOf(mLastLocation.getLongitude()));
                                eventName.setText("");
                                genre.setText("");
                                createEvent.setVisibility(View.VISIBLE);
                                updateEvent.setVisibility(View.INVISIBLE);
                                finishEvent.setVisibility(View.INVISIBLE);
                                Toast.makeText(getActivity(), "You have successfully finished event", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.d("Event finished error", error.getMessage());
                            }
                        });
        }
    }
}
