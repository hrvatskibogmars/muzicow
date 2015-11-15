package hr.mars.muzicow.fragments.DJ;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hr.mars.muzicow.R;

/**
 * Created by mars on 15/11/15.
 */
public class CreateEventFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_event, container, false);
        return view;
    }
}
