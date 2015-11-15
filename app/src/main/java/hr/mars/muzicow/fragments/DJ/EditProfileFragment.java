package hr.mars.muzicow.fragments.DJ;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hr.mars.muzicow.R;

/**
 * Created by mars on 14/11/15.
 */
public class EditProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        //Toast.makeText(getContext(),"Opening: Find Dj Fragment",Toast.LENGTH_SHORT).show();
        return view;

    }
}