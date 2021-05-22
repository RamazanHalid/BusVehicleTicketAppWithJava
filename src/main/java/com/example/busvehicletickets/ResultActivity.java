package com.example.busvehicletickets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.busvehicletickets.dto.TravelDto;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        TravelDto travelDto = (TravelDto) intent.getSerializableExtra("travelDetails2");

        TextView fromAndToCity = (TextView) findViewById(R.id.result_FromAndToCities);
        fromAndToCity.setText(travelDto.getFromCity().toUpperCase() + " ---> " + travelDto.getToCity().toUpperCase());

        TextView dateOfTravel = (TextView) findViewById(R.id.result_dateOfTravel);
        dateOfTravel.setText(travelDto.getDate());

        TextView leavingTime = (TextView) findViewById(R.id.result_leavingTime);
        leavingTime.setText("Leaving Time\n" + travelDto.getLeavingTime());

        TextView travelTime = (TextView) findViewById(R.id.result_travelTime);
        travelTime.setText("Travel Time\n" + travelDto.getTravelTime()+"h");

        TextView travelDistance = (TextView) findViewById(R.id.result_distance);
        travelDistance.setText("Distance\n" + travelDto.getDistance() + "km");



        //travelDto.setChairNumber("34");

        TextView chairNumber = (TextView) findViewById(R.id.result_chairNumber);
        Intent intent2 = getIntent();
        String seatNumber = intent2.getStringExtra("seatNumber");


        travelDto.setChairNumber(seatNumber);
        chairNumber.setText("Chair Number\n" + travelDto.getChairNumber());

        TextView price = (TextView) findViewById(R.id.result_price);
        price.setText("Price\n" + travelDto.getPrice() + "TL");


    }
}