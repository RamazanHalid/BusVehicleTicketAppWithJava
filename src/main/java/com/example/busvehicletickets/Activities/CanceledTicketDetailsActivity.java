package com.example.busvehicletickets.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.busvehicletickets.R;
import com.example.busvehicletickets.dto.TicketDto;
import com.example.busvehicletickets.dto.TravelDto;

public class CanceledTicketDetailsActivity extends AppCompatActivity {
    TicketDto ticketDto;
    TravelDto travelDto;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_ticket_details);
        intent = getIntent();
        ticketDto = (TicketDto) intent.getSerializableExtra("canceledTicket");
        travelDto = ticketDto.getTravelDto();
        TextView fromAndToCity = (TextView) findViewById(R.id.previous_result_FromAndToCities);
        fromAndToCity.setText(travelDto.getFromCity().toUpperCase() + " ---> " + travelDto.getToCity().toUpperCase());

        TextView dateOfTravel = (TextView) findViewById(R.id.previous_result_dateOfTravel);
        dateOfTravel.setText(travelDto.getDate());

        TextView leavingTime = (TextView) findViewById(R.id.previous_result_leavingTime);
        leavingTime.setText("Leaving Time\n" + travelDto.getLeavingTime());

        TextView travelTime = (TextView) findViewById(R.id.previous_result_travelTime);
        travelTime.setText("Travel Time\n" + travelDto.getTravelTime()+"h");

        TextView travelDistance = (TextView) findViewById(R.id.previous_result_distance);
        travelDistance.setText("Distance\n" + travelDto.getDistance() + "km");


        TextView chairNumber = (TextView) findViewById(R.id.previous_result_chairNumber);
        chairNumber.setText("Chair Number\n" + travelDto.getChairNumber());

        TextView price = (TextView) findViewById(R.id.previous_result_price);
        price.setText("Price\n" + travelDto.getPrice() + "TL");
    }
}