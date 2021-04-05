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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView textViewser = (TextView) findViewById(R.id.main_textViewUser);
        textViewser.setText("Welcome, " + mAuth.getCurrentUser().getEmail() );
    }

    public void logout(View view){
        mAuth.signOut();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}

