package hr.mars.muzicow.fragments.User;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import hr.mars.muzicow.R;

/**
 * Created by mars on 27/10/15.
 */
public class EventFragment extends Fragment  {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dj, container, false);

        //Toast.makeText(getContext(),"Opening: Find Dj Fragment",Toast.LENGTH_SHORT).show();

        // Inflate the layout for this fragment
        return view;

    }
}
