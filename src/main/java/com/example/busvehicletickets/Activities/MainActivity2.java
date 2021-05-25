package com.example.busvehicletickets.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.busvehicletickets.R;
import com.example.busvehicletickets.Adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity2
        extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);

        viewPagerAdapter = new ViewPagerAdapter(
                getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        // It is used to join TabLayout with ViewPager.
        tabLayout.setupWithViewPager(viewPager);


    }
}