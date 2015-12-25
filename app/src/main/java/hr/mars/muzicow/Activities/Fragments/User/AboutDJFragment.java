package hr.mars.muzicow.Activities.Fragments.User;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hr.mars.muzicow.R;

/**
 * Created by mars on 27/10/15.
 */
public class AboutDJFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_artist, container, false);
        //Toast.makeText(getContext(), "Opening: Artist Fragment", Toast.LENGTH_LONG).show();

        // Inflate the layout for this fragment
        return view;

    }
}
