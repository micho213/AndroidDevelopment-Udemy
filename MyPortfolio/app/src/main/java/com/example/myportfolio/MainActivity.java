package com.example.myportfolio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myportfolio.controller.devPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = findViewById(R.id.viewPager);

        viewPager.setAdapter(new devPagerAdapter(getSupportFragmentManager()));

        TabLayout tabs = findViewById(R.id.tabLayout);
        tabs.setupWithViewPager(viewPager);

        tabs.setTabTextColors(Color.BLACK,Color.BLUE);

        TextView contact = findViewById(R.id.contact);

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "0838272874";
                Uri call = Uri.parse("tel:" + number);
                Intent phone = new Intent(Intent.ACTION_DIAL, call);
                startActivity(phone);
            }
        });



    }
    public void gitPage(View view){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/micho213"));
        startActivity(i);
    }

    public void call(View view){
        String number = "0838272874";
        Uri call = Uri.parse("tel:" + number);
        Intent phone = new Intent(Intent.ACTION_DIAL, call);
        startActivity(phone);
    }

    public void email(View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"michalwolas@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "");
        startActivity(intent);

    }

}
