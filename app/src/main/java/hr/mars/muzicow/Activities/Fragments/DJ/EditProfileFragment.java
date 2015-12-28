package hr.mars.muzicow.Activities.Fragments.DJ;


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

import hr.mars.muzicow.APIs.DJAPI;
import hr.mars.muzicow.Activities.adapters.FragmentAdapter;
import hr.mars.muzicow.Models.DJ;
import hr.mars.muzicow.R;
import hr.mars.muzicow.Services.ServiceGenerator;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by mars on 14/11/15.
 */
public class EditProfileFragment extends Fragment implements View.OnClickListener {

    Button updateProfileButton;
    TextView id;
    TextView location;
    TextView name;
    TextView nickname;
    TextView website;
    TextView profileUrl;
    TextView twitterUrl;
    TextView description;
    FragmentAdapter faObject = new FragmentAdapter();


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        updateProfileButton = (Button) view.findViewById(R.id.updateProfileButton);
        updateProfileButton.setOnClickListener(this);
        id = (TextView)view.findViewById(R.id.IDFd);
        location = (TextView)view.findViewById(R.id.Location);
        name = (TextView)view.findViewById(R.id.NameFd);
        nickname = (TextView)view.findViewById(R.id.NicknameFd);
        website = (TextView)view.findViewById(R.id.WebsiteFd);
        profileUrl =(TextView)view.findViewById(R.id.profileurl);
        twitterUrl =(TextView)view.findViewById(R.id.twitterurl);
        description = (TextView)view.findViewById(R.id.description);

        loadData();
        return view;
    }

    String twitterID = "djs?filter=%7B%22where%22%3A%7B%22_ID%22%3A%22" + faObject.getDjObject().get_ID() + "%22%7D%7D";

    public void loadData(){
        DJAPI eventRetrofit = ServiceGenerator.createService(DJAPI.class);
        eventRetrofit.getDJ(twitterID,new Callback<List<DJ>>() {
            @Override
            public void success(List<DJ> djs, Response response) {
                if(djs.isEmpty())
                {
                    id.setText(faObject.getDjObject().get_ID());
                    name.setText(faObject.getDjObject().getName());
                    website.setText(faObject.getDjObject().getWebsite());
                    location.setText(faObject.getDjObject().getLocation());
                    nickname.setText(faObject.getDjObject().getNickname());
                    profileUrl.setText(faObject.getDjObject().getProfile_url());
                    twitterUrl.setText("https://twitter.com/" + faObject.getDjObject().getName());
                    description.setText(faObject.getDjObject().getDescription());
                    createDJ();

                }
                else {
                    id.setText(djs.get(0).get_ID());
                    name.setText(djs.get(0).getName());
                    website.setText(djs.get(0).getWebsite());
                    location.setText(djs.get(0).getLocation());
                    nickname.setText(djs.get(0).getNickname());
                    profileUrl.setText(djs.get(0).getProfile_url());
                    twitterUrl.setText(djs.get(0).getTwitter_url());
                    description.setText(djs.get(0).getDescription());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Load data failed",error.getMessage());
            }
        });
    }



    public void createDJ(){
        DJAPI eventRetrofit = ServiceGenerator.createService(DJAPI.class);
        eventRetrofit.createDJ(id.getText().toString(),
                name.getText().toString(), website.getText().toString(),
                location.getText().toString(), nickname.getText().toString(), profileUrl.getText().toString(),
                twitterUrl.getText().toString(), description.getText().toString(),
                new Callback<List<DJ>>() {
                    @Override
                    public void success(List<DJ> djs, Response response) {
                        Log.d("DJ Update ok", "Success Update");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("DJ update error", error.getMessage());

                    }
                });

    }

    @Override
    public void onClick(View view) {
        String url = "update?where=%7B%22_ID%22%3A%22"+faObject.getDjObject().get_ID()+"%22%7D";
        DJAPI eventRetrofit = ServiceGenerator.createService(DJAPI.class);
        eventRetrofit.updateDJ(url, id.getText().toString(),
                name.getText().toString(), website.getText().toString(),
                location.getText().toString(),nickname.getText().toString(),profileUrl.getText().toString(),
                twitterUrl.getText().toString(),description.getText().toString(),
                new Callback<List<DJ>>() {
                    @Override
                    public void success(List<DJ> djs, Response response) {
                        Log.d("DJ Update ok","Success Update");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("DJ update error",error.getMessage());

                    }
                });
    }
}