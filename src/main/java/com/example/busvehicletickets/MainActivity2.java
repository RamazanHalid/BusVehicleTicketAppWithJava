package com.example.busvehicletickets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.busvehicletickets.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2
        extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    private ListView listOfHomePageItems;

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
        final ArrayList<String> homePageItems = new ArrayList<>();
        homePageItems.add("News");
        homePageItems.add("Announcements");
        homePageItems.add("Last ticket");
        listOfHomePageItems = findViewById(R.id.listOfHomePageItems);

        ArrayAdapter<String> listOfHomePageItemsAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, homePageItems
        );
        listOfHomePageItems.setAdapter(listOfHomePageItemsAdapter);
        listOfHomePageItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity2.this, homePageItems.get(position) + "Selected", Toast.LENGTH_SHORT).show();
            }
        });

    }
}