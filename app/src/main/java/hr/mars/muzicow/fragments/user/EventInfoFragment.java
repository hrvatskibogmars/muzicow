package hr.mars.muzicow.fragments.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import hr.mars.muzicow.R;
import hr.mars.muzicow.activities.AboutDJActivity;
import hr.mars.muzicow.activities.RequestSongActivity;
import hr.mars.muzicow.models.Event;
import hr.mars.muzicow.utils.Registry;

/**
 * Created by Emil on 20.1.2016..
 */
public class EventInfoFragment extends Fragment implements View.OnClickListener {

    TextView EventGenre;
    TextView EventName;
    Button djButton;
    Button reqSong;
    Event eventObj;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.event_show_data, container, false);

        eventObj = (Event) Registry.getInstance().get("Event");
        EventGenre = (TextView) view.findViewById(R.id.GenreInfo);
        EventName = (TextView) view.findViewById(R.id.NameInfo);

        djButton = (Button) view.findViewById(R.id.djShowbtn);
        djButton.setOnClickListener(this);

        reqSong = (Button) view.findViewById(R.id.requestBtn);
        reqSong.setOnClickListener(this);
        showData();

        return view;
    }

    /**
     * Method listening onClick event for buttons
     * It's starting new activity based on which
     * button is clicked
     * @param view    View object
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.djShowbtn:
                Intent intent = new Intent(getActivity(), AboutDJActivity.class);
                startActivity(intent);
                break;
            case R.id.requestBtn:

                intent = new Intent(getActivity(), RequestSongActivity.class);

                startActivity(intent);
                break;

        }
    }
    /**
     * Method listening show Event data in TextView
     */
    public void showData() {
        EventName.setText(eventObj.getName());
        EventGenre.setText(eventObj.getGenre());
        Log.d("event", eventObj.getName());
    }
}
