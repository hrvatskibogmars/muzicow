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

import java.util.List;

import hr.mars.muzicow.DJAtributes;
import hr.mars.muzicow.Interface.editProfileDjAPI;
import hr.mars.muzicow.R;
import hr.mars.muzicow.ServiceGenerator;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by mars on 14/11/15.
 */
public class EditProfileFragment extends Fragment implements View.OnClickListener {
    List<DJAtributes>DJlist;
    Button buttonCL;
    TextView id;
    TextView city;
    TextView country;
    TextView name;
    TextView nickname;
    TextView website;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        buttonCL = (Button) view.findViewById(R.id.editProfileButt);
        buttonCL.setOnClickListener(this);
        id = (TextView)view.findViewById(R.id.IDFd);
        city = (TextView)view.findViewById(R.id.CityFd);
        country = (TextView)view.findViewById(R.id.CountryFd);
        name = (TextView)view.findViewById(R.id.NameFd);
        nickname = (TextView)view.findViewById(R.id.NicknameFd);
        website = (TextView)view.findViewById(R.id.WebsiteFd);

        return view;

    }


    @Override
    public void onClick(final View view) {
        editProfileDjAPI eventRetrofit = ServiceGenerator.createService(editProfileDjAPI.class);
        Call<List<DJAtributes>> call = eventRetrofit.getDJ();
        call.enqueue(new Callback<List<DJAtributes>>() {
            @Override
            public void onResponse(Response<List<DJAtributes>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Log.d("Error", "uspijeh");
                    DJlist = response.body();
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
                        }
                        catch (Exception e){
                            e.getMessage();
                        }

                    }
                } else {
                    Log.d("Error", response.message());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

    }
}