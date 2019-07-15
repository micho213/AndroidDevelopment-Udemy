package com.example.motivationalapp;


import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.motivationalapp.model.Quote;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {


    public QuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View quoteView = inflater.inflate(R.layout.fragment_quote, container, false);


        TextView quote = quoteView.findViewById(R.id.quote);

        TextView author = quoteView.findViewById(R.id.author);

        CardView cardView = quoteView.findViewById(R.id.card);


        String quoteText = getArguments().getString("quote");

        String authorText = getArguments().getString("author");


        quote.setText(quoteText);
        author.setText("by " + authorText);


        return quoteView;
    }

    public static final QuoteFragment newInstance(String quote, String author){

        QuoteFragment fragment = new QuoteFragment();

        Bundle bundle = new Bundle();
        bundle.putString("quote", quote);
        bundle.putString("author", author);
        fragment.setArguments(bundle);

        return fragment;
    }

}
