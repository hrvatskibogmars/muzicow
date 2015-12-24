package hr.mars.muzicow.fragments.DJ;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.CharArrayReader;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import hr.mars.muzicow.RESTful.model.DJ;
import hr.mars.muzicow.RESTful.api.API;
import hr.mars.muzicow.R;
import hr.mars.muzicow.RESTful.ServiceGenerator;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by mars on 14/11/15.
 */
public class EditProfileFragment extends Fragment implements View.OnClickListener {
    List<DJ>DJlist;
    Button updateProfileButton;
    TextView id;
    TextView city;
    TextView country;
    TextView name;
    TextView nickname;
    TextView website;
    TextView email;


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        updateProfileButton = (Button) view.findViewById(R.id.updateProfileButton);
        updateProfileButton.setOnClickListener(this);
        id = (TextView)view.findViewById(R.id.IDFd);
        city = (TextView)view.findViewById(R.id.CityFd);
        country = (TextView)view.findViewById(R.id.CountryFd);
        name = (TextView)view.findViewById(R.id.NameFd);
        nickname = (TextView)view.findViewById(R.id.NicknameFd);
        website = (TextView)view.findViewById(R.id.WebsiteFd);
        email =(TextView)view.findViewById(R.id.email);
        loadData();

        return view;

    }

    String emaill = "/api/djs?filter=%7B%22where%22%3A%7B%22email%22%3A%22string%22%7D%7D";

    public void loadData(){
        API eventRetrofit = ServiceGenerator.createService(API.class);
        eventRetrofit.getDJ("embalint@mail.com",new Callback<List<DJ>>() {
            @Override
            public void success(List<DJ> djs, Response response) {

                    id.setText(djs.get(0).get_ID());
                    name.setText(djs.get(0).getName());
                    website.setText(djs.get(0).getWebsite());
                    country.setText(djs.get(0).getCountry());
                    city.setText(djs.get(0).getCity());
                    email.setText(djs.get(0).getEmail());
                    nickname.setText(djs.get(0).getNickname());





            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("prikaz", "neuspijeh");
            }
        });
        //Map obj=new LinkedHashMap();
        //obj.put("email","hrorejas@foi.hr");
        //String jsonText = JSONValue.toJSONString(obj);
        /*
        List<DJ> call = eventRetrofit.getDJ(emaill);
        call.enqueue(new Callback<List<DJ>>() {
            @Override
            public void onResponse(Response<List<DJ>> response, Retrofit retrofit) {
                if (response.isSuccess()) {

                    Log.d("Error", response.body().toString());
                    DJlist = response.body();

                    Log.d("Error", DJlist.get(1).get_ID());
                    Log.d("Error", DJlist.get(1).getCity());
                    Log.d("Error", DJlist.get(1).getCountry());
                    Log.d("Error", DJlist.get(1).getName());
                    Log.d("Error", DJlist.get(1).getNickname());
                    Log.d("Error", DJlist.get(1).getWebsite());


                    for (int i=0;i<DJlist.size();i++){
                        try {
                            Log.d("Error", DJlist.get(i).get_ID());
                            Log.d("Error", DJlist.get(i).getCity());
                            Log.d("Error", DJlist.get(i).getCountry());
                            Log.d("Error", DJlist.get(i).getName());
                            Log.d("Error", DJlist.get(i).getNickname());
                            Log.d("Error", DJlist.get(i).getWebsite());

                            id.setText(DJlist.get(i).get_ID());
                            city.setText(DJlist.get(i).getCity());
                            country.setText(DJlist.get(i).getCountry());
                            name.setText(DJlist.get(i).getName());
                            nickname.setText(DJlist.get(i).getNickname());
                            website.setText(DJlist.get(i).getWebsite());
                            email.setText(DJlist.get(i).getEmail());

                        }


                        catch (Exception e){
                            e.getMessage();
                        }

                    }


                } else {
                    Log.d("API response", response.raw().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
        */
    }

    @Override
    public void onClick(View view) {
        API eventRetrofit = ServiceGenerator.createService(API.class);


        eventRetrofit.updateDJ(id.getText().toString(),
                name.getText().toString(), website.getText().toString(),
                country.getText().toString(), city.getText().toString(),
                email.getText().toString(), nickname.getText().toString(),
                new Callback<List<DJ>>() {
                    @Override
                    public void success(List<DJ> djs, Response response) {
                        Log.d("prikaz","uspjeh");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("prikaz",error.getMessage());

                    }
                });

        /*
        String url = "/api/djs/update?where=%7B%22_ID%22%3A%223%22%7D";

        API eventRetrofit = ServiceGenerator.createService(API.class);
        Call<List<DJ>> call = eventRetrofit.updateDJ(url, id.getText().toString(), city.getText().toString(), country.getText().toString(),
                name.getText().toString(), nickname.getText().toString(), website.getText().toString(), email.getText().toString());
        call.enqueue(new Callback<List<DJ>>() {
            @Override
            public void onResponse(Response<List<DJ>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Log.d("Error Sucess", "uspijeh");
                    DJlist = response.body();

                    }
                else {
                    Log.d("Response error", response.raw().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailureError", t.toString());
            }
        });

        */
    }



}