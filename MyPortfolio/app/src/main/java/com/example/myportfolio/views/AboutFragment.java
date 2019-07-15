package com.example.myportfolio.views;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myportfolio.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {


    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View aboutView = inflater.inflate(R.layout.fragment_about, container, false);


//        TextView aboutText = aboutView.findViewById(R.id.aboutText);
//        aboutText.setText("Helloooo");


        // Inflate the layout for this fragment
        

       // return inflater.inflate(R.layout.fragment_about, container, false);

        return aboutView;
    }

}
