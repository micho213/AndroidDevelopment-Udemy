package com.example.myportfolio.controller;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myportfolio.views.AboutFragment;
import com.example.myportfolio.views.ContactFragment;
import com.example.myportfolio.views.SkillsFragment;
import com.example.myportfolio.views.WorkFragment;

public class devPagerAdapter extends FragmentPagerAdapter {

    public devPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position ) {
            case  0: return new AboutFragment();
            case 1: return new WorkFragment();
            case 2: return new SkillsFragment();
            case 3: return new ContactFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "About";
            case 1: return "Work";
            case 2: return "Skills";
            case 3: return "Contact";

        }
        return null;
    }
}
