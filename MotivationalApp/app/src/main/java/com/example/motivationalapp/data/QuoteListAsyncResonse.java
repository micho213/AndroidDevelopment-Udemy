package com.example.motivationalapp.data;

import com.example.motivationalapp.model.Quote;

import java.util.ArrayList;

public interface QuoteListAsyncResonse {
    void processFinished(ArrayList<Quote> quotes);
}
