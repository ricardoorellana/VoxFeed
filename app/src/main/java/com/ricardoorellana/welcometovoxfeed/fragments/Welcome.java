package com.ricardoorellana.welcometovoxfeed.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ricardoorellana.welcometovoxfeed.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Welcome} factory method to
 * create an instance of this fragment.
 */
public class Welcome extends Fragment {


    public Welcome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

}
