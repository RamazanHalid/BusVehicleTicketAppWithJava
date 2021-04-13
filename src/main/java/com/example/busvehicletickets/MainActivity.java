/**
 * @author Ramazan Halid 20160807009
 * @version 05.04.2021
 *
 * ToDo
 * This is a Bus Vehicle Tickets Android Application.
 *
 *
 * */


package com.example.busvehicletickets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] selection = {"News", "Announcements", "Find Ticket", "History ", "Setting ", "Logout "};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter my_adapter = new ArrayAdapter<String>(this,
                R.layout.my_list,R.id.customizedTextView, selection);
        ListView List = findViewById(R.id.DoList);
        List.setAdapter(my_adapter);
    }


}

