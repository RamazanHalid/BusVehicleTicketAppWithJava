package com.example.busvehicletickets.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.busvehicletickets.R;

public class SendEMailActivity extends AppCompatActivity {
    TextView eMailAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_e_mail);

        eMailAddress = findViewById(R.id.email_address);
        eMailAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("mailto:" + "ramazan.halid.35@gmail.com"));

                startActivity(myIntent);

            }
        });
    }
}