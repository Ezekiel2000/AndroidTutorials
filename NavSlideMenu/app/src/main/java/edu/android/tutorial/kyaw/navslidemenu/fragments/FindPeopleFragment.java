package edu.android.tutorial.kyaw.navslidemenu.fragments;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.android.tutorial.kyaw.navslidemenu.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class FindPeopleFragment extends Fragment {


    public FindPeopleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_people, container, false);
    }


}
