package com.example.motivationalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.motivationalapp.data.QuoteData;
import com.example.motivationalapp.data.QuoteListAsyncResonse;
import com.example.motivationalapp.data.QuoteViewPagerAdapter;
import com.example.motivationalapp.model.Quote;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private QuoteViewPagerAdapter quoteViewPagerAdapter;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteViewPagerAdapter = new QuoteViewPagerAdapter(getSupportFragmentManager(), getFragments());
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(quoteViewPagerAdapter);

    }

    private List<Fragment> getFragments() {
        final List<Fragment> fragmentList = new ArrayList<>();


        new QuoteData().getQuotes(new QuoteListAsyncResonse() {
            @Override
            public void processFinished(ArrayList<Quote> quotes) {
                for (int i = 0; i < quotes.size(); i++){

                    QuoteFragment quoteFragment = QuoteFragment.newInstance(quotes.get(i).getQuote(), quotes.get(i).getAuthor());

                    fragmentList.add(quoteFragment);
                }

                quoteViewPagerAdapter.notifyDataSetChanged();
            }

        });

        return fragmentList;
    }

}
