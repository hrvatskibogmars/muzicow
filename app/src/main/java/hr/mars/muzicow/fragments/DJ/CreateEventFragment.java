package hr.mars.muzicow.fragments.DJ;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import hr.mars.muzicow.R;

/**
 * Created by mars on 15/11/15.
 */
public class CreateEventFragment extends Fragment implements View.OnClickListener {
    EditText event;
    EditText time;
    EditText genre;
    EditText latitude;
    EditText longitude;
    ArrayList<String> listaEvent;
    Button upButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_event, container, false);
        upButton = (Button) view.findViewById(R.id.button2);
        upButton.setOnClickListener(this);
        event = (EditText)view.findViewById(R.id.event);
        time=(EditText)view.findViewById(R.id.vrijme);
        genre= (EditText)view.findViewById(R.id.genre);
        latitude= (EditText)view.findViewById(R.id.latitude);
        longitude= (EditText)view.findViewById(R.id.longitude);
        listaEvent = new ArrayList<>();
        return view;
    }




    @Override
    public void onClick(View view) {
        /*
        listaEvent.add(event.getText().toString());
        listaEvent.add(time.getText().toString());
        listaEvent.add(genre.getText().toString());
        listaEvent.add(latitude.getText().toString());
        listaEvent.add(longitude.getText().toString());

        editProfileDjAPI eventRetrofit = ServiceGenerator.createService(editProfileDjAPI.class);
        Call<String>call = eventRetrofit.slanjeEventa();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                // primanje odgovora
                Log.e("TAG",response.message());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("TAG",t.getMessage());
            }
        });
    */
    }

}
