package com.example.busvehicletickets;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter
        extends FragmentPagerAdapter {

    public ViewPagerAdapter(
            @NonNull FragmentManager fm)
    {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment = null;
        if (position == 0)
            fragment = new HomeFragment();
        else if (position == 1)
            fragment = new MenuFragment();
        else if (position == 2)
            fragment = new FavoritesFragment();
        else if (position == 3)
            fragment = new OthersFragment();

        return fragment;
    }

    @Override
    public int getCount()
    {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if (position == 0)
            title = "Home";

        else if (position == 1)
            title = "Menu";
        else if (position == 2)
            title = "Favorites";
        else if (position == 3)
            title = "Others";
        return title;
    }
}